package cn.lime.anxin.constants;

/**
 * @ClassName: DetectOrderState
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 17:20
 */
public enum DetectOrderState {
    TO_BE_SAMPLING(0),
    READY_TO_RETURN(1),
    RETURNING(2),
    DETECTING(3),
    FINISH(4)

    ;
    private final int val;

    DetectOrderState(int dbVal) {
        this.val = dbVal;
    }

    public int getVal() {
        return val;
    }
}
