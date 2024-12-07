package cn.lime.anxin.model.vo.distribute;

import cn.lime.anxin.model.entity.DistributeOrderLog;
import cn.lime.mall.model.vo.OrderDetailVo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserRelateDistributeOrderVo
 * @Description: TODO 描述类的功能
 * @Author: riang
 * @Date: 2024/11/2 10:26
 */
@Data
public class UserRelateDistributeOrderVo implements Serializable {
    private Long id;
    private Long orderId;
    private Integer opType;
    private Long userId;
    private Integer amount;
    private OrderDetailVo orderDetailVo;

    public static UserRelateDistributeOrderVo fromLogBean(DistributeOrderLog bean){
        UserRelateDistributeOrderVo vo = new UserRelateDistributeOrderVo();
        vo.setOrderId(bean.getOrderId());
        vo.setOpType(bean.getOpType());
        vo.setUserId(bean.getUserId());
        vo.setAmount(bean.getAmount());
        return vo;
    }
}