package cn.lime.anxin.constants;

/**
 * @ClassName: DistributeOrderOpType
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 11:40
 */
public enum DistributeOrderOpType {

    ORDER_FINISH(1),
    WITHDRAW(2)

    ;
    private final int val;

    DistributeOrderOpType(int dbVal) {
        this.val = dbVal;
    }

    public int getVal() {
        return val;
    }
}
