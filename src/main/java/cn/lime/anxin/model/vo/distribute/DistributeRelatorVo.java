package cn.lime.anxin.model.vo.distribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class DistributeRelatorVo implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    private String nickname;
    private Integer level;
}
