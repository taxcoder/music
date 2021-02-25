package com.tanx.music.pojo.model;

/**
 * 地域
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/17 下午10:03
 */
public class Region implements Cloneable{
    /** 地域ID */
    private int regionId;
    /** 地域名称 */
    private String regionName;

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
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
