package cn.lime.anxin.constants;

/**
 * @ClassName: AdStructureType
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 17:08
 */
public enum AdStructureType {
    TEXT(1),
    PICTURE(2),
    VIDEO(3),
    RADIO(4),
    LINK(5)


    ;
    private final int val;

    AdStructureType(int dbVal) {
        this.val = dbVal;
    }

    public int getVal() {
        return val;
    }
}
