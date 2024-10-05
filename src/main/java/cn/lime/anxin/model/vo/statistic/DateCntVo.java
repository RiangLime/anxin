package cn.lime.anxin.model.vo.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateCntVo implements Serializable {
    private String date;
    private Integer count;
}
