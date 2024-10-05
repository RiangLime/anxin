package cn.lime.anxin.model.vo.distribute;

import lombok.Data;

import java.io.Serializable;

@Data
public class DistributeRelatorVo implements Serializable {
    private Long userId;
    private String nickname;
    private Integer level;
}
