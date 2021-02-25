package com.tanx.music.pojo.model;

import java.sql.Timestamp;

/**
 * 歌手
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午10:07
 */
public class Singer implements Cloneable{

    /** 歌手ID */
    private long singerId;
    /** 歌手名称 */
    private String singerName;
    /** 歌手描述 */
    private String singerDesc;
    /** 歌手头像地址 */
    private String singerHeadImage;
    /** 歌手属性(0为男歌手 1为女歌手 2为组合) */
    private int singerStatus;
    /** 地域ID */
    private int regionId;
    /** 首字母 */
    private String abbreviation;
    /** 歌手出生日期 */
    private Timestamp singerBorn;
    /** 歌手增加时间 */
    private Timestamp addTime;

    public long getSingerId() {
        return singerId;
    }

    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerDesc() {
        return singerDesc;
    }

    public void setSingerDesc(String singerDesc) {
        this.singerDesc = singerDesc;
    }

    public String getSingerHeadImage() {
        return singerHeadImage;
    }

    public void setSingerHeadImage(String singerHeadImage) {
        this.singerHeadImage = singerHeadImage;
    }

    public int getSingerStatus() {
        return singerStatus;
    }

    public void setSingerStatus(int singerStatus) {
        this.singerStatus = singerStatus;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Timestamp getSingerBorn() {
        return singerBorn;
    }

    public void setSingerBorn(Timestamp singerBorn) {
        this.singerBorn = singerBorn;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                ", singerDesc='" + singerDesc + '\'' +
                ", singerHeadImage='" + singerHeadImage + '\'' +
                ", singerStatus=" + singerStatus +
                ", regionId=" + regionId +
                ", abbreviation='" + abbreviation + '\'' +
                ", singerBorn=" + singerBorn +
                ", addTime=" + addTime +
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
