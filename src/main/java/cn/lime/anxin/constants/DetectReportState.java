package cn.lime.anxin.constants;

/**
 * @ClassName: DetectReportState
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/22 12:09
 */
public enum DetectReportState {

    TO_BE_PAID(0),
    TO_BE_DETECTED(1),
    DETECTING(2),
    FINISH(3)

    ;
    private final int val;

    DetectReportState(int dbVal) {
        this.val = dbVal;
    }

    public int getVal() {
        return val;
    }

}
