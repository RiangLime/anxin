package cn.lime.anxin.constants;

/**
 * @ClassName: DetectSampleState
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/22 12:06
 */
public enum DetectSampleState {
    TO_BE_SAMPLED(0),
    RETURNING(1),
    RECEIVED(2)


    ;
    private final int val;

    DetectSampleState(int dbVal) {
        this.val = dbVal;
    }

    public int getVal() {
        return val;
    }
}
