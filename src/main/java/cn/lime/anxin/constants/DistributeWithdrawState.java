package cn.lime.anxin.constants;

/**
 * @ClassName: DistributeWithdrawState
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 12:25
 */
public enum DistributeWithdrawState {
    APPLY(0),
    APPROVE(1),
    REFUSE(2),

    ;
    private final int val;

    DistributeWithdrawState(int dbVal) {
        this.val = dbVal;
    }

    public int getVal() {
        return val;
    }
}
