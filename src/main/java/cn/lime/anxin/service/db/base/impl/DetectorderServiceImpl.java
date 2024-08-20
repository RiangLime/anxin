package cn.lime.anxin.service.db.base.impl;

import cn.lime.anxin.config.AnXinParams;
import cn.lime.anxin.constants.DetectOrderState;
import cn.lime.anxin.model.vo.QrCodeVo;
import cn.lime.anxin.utils.DetectOrderCodeGenerator;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import cn.lime.core.threadlocal.ReqThreadLocal;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.Detectorder;
import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.anxin.mapper.DetectorderMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
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

    public Detectorder getByCode(String code) {
        Optional<Detectorder> detectorder = lambdaQuery().eq(Detectorder::getCode, code).oneOpt();
        ThrowUtils.throwIf(detectorder.isEmpty(), ErrorCode.NOT_FOUND_ERROR, "无法根据编号找到已生成二维码");
        return detectorder.get();
    }

    @Override
    public QrCodeVo createDetectOrder(Long productId, Long skuId) {
        Detectorder detectorder = new Detectorder();
        detectorder.setId(ids.nextId());
        detectorder.setCode(DetectOrderCodeGenerator.generateUniqueCode());
        detectorder.setProductId(productId);
        detectorder.setSkuId(skuId);
        detectorder.setQrcode(anXinParams.getQrCodePrefix() + detectorder.getCode());
        ThrowUtils.throwIf(!save(detectorder), ErrorCode.INSERT_ERROR, "生成二维码失败");
        return new QrCodeVo(detectorder.getQrcode(), detectorder.getCode());
    }

    @Override
    @Transactional
    public void bind(String code) {
        Detectorder detectorder = getByCode(code);
        ThrowUtils.throwIf(ObjectUtils.isNotEmpty(detectorder.getBindUserId()), ErrorCode.PARAMS_ERROR, "该二维码已被绑定");
        ThrowUtils.throwIf(!lambdaUpdate().eq(Detectorder::getId, detectorder.getId())
                .set(Detectorder::getBindUserId, ReqThreadLocal.getInfo().getUserId())
                .set(Detectorder::getDetectState, DetectOrderState.TO_BE_SAMPLING.getVal())
                .set(Detectorder::getBindTime, new Date())
                .update(), ErrorCode.UPDATE_ERROR, "二维码绑定用户失败");
    }
}




