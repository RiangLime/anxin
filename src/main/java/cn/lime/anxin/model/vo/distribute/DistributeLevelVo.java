package cn.lime.anxin.model.vo.distribute;

import cn.lime.anxin.model.entity.DistributeLevel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @ClassName: DistributeLevelVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/23 14:38
 */
@Data
public class DistributeLevelVo implements Serializable {
    private Integer levelId;

    /**
     * 等级名称
     */
    private String levelName;

    /**
     * 一级佣金比例 百分比
     */
    private Integer rate1;

    /**
     * 二级佣金比例 百分比
     */
    private Integer rate2;

    /**
     * 自购佣金比例 百分比
     */
    private Integer rate3;

    public static DistributeLevelVo fromBean(DistributeLevel bean){
        DistributeLevelVo vo = new DistributeLevelVo();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}
