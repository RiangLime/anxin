package cn.lime.anxin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.signature.qual.CanonicalNameAndBinaryName;

import java.io.Serializable;

/**
 * @ClassName: DailyNewUserCount
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/7/31 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyNewUserCount implements Serializable {
    private String date;
    private Integer count;
}
