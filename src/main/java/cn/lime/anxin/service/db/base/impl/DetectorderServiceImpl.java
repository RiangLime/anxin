package cn.lime.anxin.service.db.base.impl;

import cn.lime.anxin.config.AnXinParams;
import cn.lime.anxin.constants.DetectOrderState;
import cn.lime.anxin.model.vo.DetectOrderDetailVo;
import cn.lime.anxin.model.vo.DetectOrderPageVo;
import cn.lime.anxin.model.vo.QrCodeVo;
import cn.lime.anxin.utils.DetectOrderCodeGenerator;
import cn.lime.core.common.*;
import cn.lime.core.constant.AuthLevel;
import cn.lime.core.constant.YesNoEnum;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import cn.lime.core.threadlocal.ReqThreadLocal;
import cn.lime.mall.model.vo.OrderDetailVo;
import cn.lime.mall.service.db.OrderService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.Detectorder;
import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.anxin.mapper.DetectorderMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author riang
 * @description 针对表【DetectOrder】的数据库操作Service实现
 * @createDate 2024-08-20 15:37:08
 */
@Service
public class DetectorderServiceImpl extends ServiceImpl<DetectorderMapper, Detectorder>
        implements DetectorderService {
    @Resource
    private SnowFlakeGenerator ids;
    @Resource
    private AnXinParams anXinParams;
    @Resource
    private OrderService orderService;

    public Detectorder getByCode(String code) {
        Optional<Detectorder> detectorder = lambdaQuery().eq(Detectorder::getCode, code).oneOpt();
        ThrowUtils.throwIf(detectorder.isEmpty(), ErrorCode.NOT_FOUND_ERROR, "无法根据编号找到已生成二维码");
        return detectorder.get();
    }

    @Override
    public QrCodeVo createDetectOrder(Long productId, Long skuId, Long orderId) {
        Detectorder detectorder = new Detectorder();
        detectorder.setId(ids.nextId());
        detectorder.setCode(DetectOrderCodeGenerator.generateUniqueCode());
        detectorder.setProductId(productId);
        detectorder.setSkuId(skuId);
        detectorder.setOrderId(orderId);
        detectorder.setQrcode(anXinParams.getQrCodePrefix() + detectorder.getCode());
        ThrowUtils.throwIf(!save(detectorder), ErrorCode.INSERT_ERROR, "生成二维码失败");
        return new QrCodeVo(detectorder.getQrcode(), detectorder.getCode());
    }

    @Override
    public QrCodeVo copyFromDetectOrder(String oldCode, Long productId, Long skuId, Long orderId) {
        Detectorder old = getByCode(oldCode);
        if (old.getHasUpdated().equals(YesNoEnum.YES.getVal())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该检测已经升级过报告,请勿重复升级");
        }
        ThrowUtils.throwIf(!lambdaUpdate().eq(Detectorder::getCode,oldCode).set(Detectorder::getHasUpdated,YesNoEnum.YES.getVal()).update(),
                ErrorCode.UPDATE_ERROR,"更新旧检测订单异常");
        Detectorder detectorder = new Detectorder();
        detectorder.setId(ids.nextId());
        detectorder.setCode(DetectOrderCodeGenerator.generateUniqueCode());
        detectorder.setProductId(productId);
        detectorder.setSkuId(skuId);
        detectorder.setOrderId(orderId);
        detectorder.setQrcode(anXinParams.getQrCodePrefix() + detectorder.getCode());
        detectorder.setIsUpdated(YesNoEnum.YES.getVal());
        detectorder.setBindUserId(old.getBindUserId());
        detectorder.setBindTime(old.getBindTime());
        detectorder.setReturnDeliverCompany(old.getReturnDeliverCompany());
        detectorder.setReturnDeliverId(old.getReturnDeliverId());
        detectorder.setDetectState(DetectOrderState.UPDATE_WAIT_PAID.getVal());
        ThrowUtils.throwIf(!save(detectorder), ErrorCode.INSERT_ERROR, "生成二维码失败");
        return new QrCodeVo(detectorder.getQrcode(), detectorder.getCode());
    }

    @Override
    @Transactional
    public void bind(String code) {
        Detectorder detectorder = getByCode(code);
        // 获取当前UTC时间
        Instant now = Instant.now();
        ThrowUtils.throwIf(ObjectUtils.isNotEmpty(detectorder.getBindUserId()), ErrorCode.PARAMS_ERROR, "该二维码已被绑定");
        ThrowUtils.throwIf(!lambdaUpdate().eq(Detectorder::getId, detectorder.getId())
                .set(Detectorder::getBindUserId, ReqThreadLocal.getInfo().getUserId())
                .set(Detectorder::getBindTime, Date.from(now))
                .update(), ErrorCode.UPDATE_ERROR, "二维码绑定用户失败");
    }

    @Override
    public void setReturnDeliverInfo(String code, String deliverCompany, String deliverCode) {
        Detectorder detectorder = getByCode(code);
        ThrowUtils.throwIf(!detectorder.getDetectState().equals(DetectOrderState.READY_TO_RETURN.getVal())
                        && !detectorder.getDetectState().equals(DetectOrderState.RETURNING.getVal()),
                ErrorCode.AUTH_FAIL, "用户还未完成采样");
        ThrowUtils.throwIf(!lambdaUpdate().eq(Detectorder::getId, detectorder.getId())
                .set(Detectorder::getReturnDeliverId, deliverCode)
                .set(Detectorder::getReturnDeliverCompany, deliverCompany)
                .set(Detectorder::getDetectState, DetectOrderState.RETURNING.getVal())
                .update(), ErrorCode.UPDATE_ERROR, "管理员设置回寄信息失败");

    }

    @Override
    public void confirmReceiveReturn(String code) {
        Detectorder detectorder = getByCode(code);
        ThrowUtils.throwIf(!lambdaUpdate().eq(Detectorder::getId, detectorder.getId())
                .set(Detectorder::getDetectState, DetectOrderState.DETECTING.getVal())
                .update(), ErrorCode.UPDATE_ERROR, "管理员确认收到寄回商品失败");
    }

    @Override
    public void confirmReadyToReturn(String code) {
        Detectorder detectorder = getByCode(code);
        ThrowUtils.throwIf(!detectorder.getBindUserId().equals(ReqThreadLocal.getInfo().getUserId())
                        && ReqThreadLocal.getInfo().getAuthLevel() < AuthLevel.ADMIN.getVal(),
                ErrorCode.NO_AUTH_ERROR, "该二维码绑定用户不是您,您无权操作");
        ThrowUtils.throwIf(!lambdaUpdate().eq(Detectorder::getId, detectorder.getId())
                .set(Detectorder::getDetectState, DetectOrderState.READY_TO_RETURN.getVal())
                .update(), ErrorCode.UPDATE_ERROR, "用户确认准备寄回商品失败");
    }

    @Override
    public void uploadReport(String code, String title, String name, Integer isNormal, Integer canUpdate, Long proId,
                             Long skuId, List<String> reportUrls, List<String> contactorUrls) {
        Detectorder detectorder = getByCode(code);
        LambdaUpdateWrapper<Detectorder> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Detectorder::getId,detectorder.getId());
        wrapper.set(Detectorder::getDetectState, DetectOrderState.FINISH.getVal());
        if (StringUtils.isNotEmpty(title)) wrapper.set(Detectorder::getReportTitle, title);
        if (StringUtils.isNotEmpty(name)) wrapper.set(Detectorder::getReportName, name);
        if (ObjectUtils.isNotEmpty(isNormal)) wrapper.set(Detectorder::getReportTitle, isNormal);
        if (!CollectionUtils.isEmpty(reportUrls)) wrapper.set(Detectorder::getReportTitle, JSON.toJSONString(reportUrls));
        if (!CollectionUtils.isEmpty(contactorUrls)) wrapper.set(Detectorder::getReportTitle, JSON.toJSONString(contactorUrls));
        if (ObjectUtils.isNotEmpty(canUpdate)) wrapper.set(Detectorder::getReportTitle, canUpdate);
        if (ObjectUtils.isNotEmpty(proId)) wrapper.set(Detectorder::getReportTitle, proId);
        if (ObjectUtils.isNotEmpty(skuId)) wrapper.set(Detectorder::getReportTitle, skuId);
        ThrowUtils.throwIf(!update(wrapper), ErrorCode.UPDATE_ERROR, "管理员上传报告失败");
    }

    @Override
    public PageResult<DetectOrderPageVo> pageDetectOrders(Long bindUserId, String userName, String productName, String code,
                                                          Integer state, Integer canUpdate, Integer isUpdated, Integer isBind,
                                                          Integer current, Integer pageSize) {
        Page<?> page = PageUtils.build(current, pageSize, null, null);
        Page<DetectOrderPageVo> res = baseMapper.page(bindUserId, userName, productName, code, state, canUpdate, isUpdated, isBind, page);
        return new PageResult<>(res);
    }

    @Override
    public DetectOrderDetailVo getDetectOrderDetail(Long id) {
        DetectOrderDetailVo vo = baseMapper.detail(id);
        ThrowUtils.throwIf(ObjectUtils.isEmpty(vo), ErrorCode.NOT_FOUND_ERROR);
        if (ReqThreadLocal.getInfo().getAuthLevel() == AuthLevel.ADMIN.getVal()
                || ReqThreadLocal.getInfo().getUserId().equals(vo.getBindUserId())) {
            if (ObjectUtils.isNotEmpty(vo.getRelateOrderId())) {
                OrderDetailVo orderDetailVo = orderService.getOrderDetail(vo.getRelateOrderId());
                vo.setOrderDetailVo(orderDetailVo);
            }
            vo.form();
            return vo;
        } else {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
    }

    @Override
    public void userSetReturnDeliverInfo(String code, String returnDeliverUserName, String returnDeliverUserPosition,
                                         String returnDeliverUserAddress, String returnDeliverUserPhone,
                                         Integer returnDeliverUserAge, String returnDeliverVisitTime) {
        Detectorder detectorder = getByCode(code);
        ThrowUtils.throwIf(!Objects.equals(detectorder.getBindUserId(), ReqThreadLocal.getInfo().getUserId())
                && ReqThreadLocal.getInfo().getAuthLevel() < AuthLevel.ADMIN.getVal(), ErrorCode.NO_AUTH_ERROR);
        if (StringUtils.isNotEmpty(detectorder.getReturnDeliverUserPhone())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"您已填写过回寄信息,如有修改请联系管理员");
        }
        lambdaUpdate().eq(Detectorder::getCode, code)
                .set(Detectorder::getReturnDeliverUserName, returnDeliverUserName)
                .set(Detectorder::getReturnDeliverUserAge, returnDeliverUserAge)
                .set(Detectorder::getReturnDeliverUserPhone, returnDeliverUserPhone)
                .set(Detectorder::getReturnDeliverUserPosition, returnDeliverUserPosition)
                .set(Detectorder::getReturnDeliverUserAddress, returnDeliverUserAddress)
                .set(Detectorder::getReturnDeliverUserTime, returnDeliverVisitTime)
                .update();
    }

    @Override
    public List<Long> getUpdateWaitingSendOrderIds() {
        return baseMapper.getUpdateWaitingSendOrderIds();
    }
}




