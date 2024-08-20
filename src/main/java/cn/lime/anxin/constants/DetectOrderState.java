package cn.lime.anxin.constants;

/**
 * @ClassName: DetectOrderState
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 17:20
 */
public enum DetectOrderState {
    TO_BE_SAMPLING(0),
    RETURNING(1),
    DETECTING(2),
    FINISH(3)

    ;
    private final int val;

    DetectOrderState(int dbVal) {
        this.val = dbVal;
    }

    public int getVal() {
        return val;
    }
}
