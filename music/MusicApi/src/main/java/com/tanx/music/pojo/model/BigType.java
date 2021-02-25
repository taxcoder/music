package com.tanx.music.pojo.model;

/**
 * 大类别
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/17 下午9:45
 */
public class BigType implements Cloneable{

    /** 大类别ID */
    private long bigTypeId;
    /** 大类别名称 */
    private String bigTypeName;

    public long getBigTypeId() {
        return bigTypeId;
    }

    public void setBigTypeId(long bigTypeId) {
        this.bigTypeId = bigTypeId;
    }

    public String getBigTypeName() {
        return bigTypeName;
    }

    public void setBigTypeName(String bigTypeName) {
        this.bigTypeName = bigTypeName;
    }

    @Override
    public String toString() {
        return "BigType{" +
                "bigTypeId=" + bigTypeId +
                ", bigTypeName='" + bigTypeName + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Object cloneTarget() throws CloneNotSupportedException {
        return clone();
    }
}
