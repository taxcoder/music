package com.tanx.music.pojo.model;

/**
 * 歌单类型(二级类别)
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/17 下午10:57
 */
public class SongSheetType implements Cloneable{
    /** 歌单(二级)类别ID */
    private long songSheetTypeId;
    /** 歌单(二级)类别名称 */
    private String songSheetTypeName;
    /** 一级类别ID */
    private long bigTypeId;

    public long getSongSheetTypeId() {
        return songSheetTypeId;
    }

    public void setSongSheetTypeId(long typeId) {
        this.songSheetTypeId = typeId;
    }

    public String getSongSheetTypeName() {
        return songSheetTypeName;
    }

    public void setSongSheetTypeName(String typeName) {
        this.songSheetTypeName = typeName;
    }

    public long getBigTypeId() {
        return bigTypeId;
    }

    public void setBigTypeId(long bigTypeId) {
        this.bigTypeId = bigTypeId;
    }

    @Override
    public String toString() {
        return "SongSheetLinkType{" +
                "typeId=" + songSheetTypeId +
                ", typeName='" + songSheetTypeName + '\'' +
                ", bigTypeId=" + bigTypeId +
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
