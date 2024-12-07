package cn.lime.anxin.model.vo.distribute;

import cn.lime.mall.model.vo.OrderDetailVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDistributeOrderLogVo extends OrderDetailVo implements Serializable {

    private Integer amount;
    private Integer opType;

}
