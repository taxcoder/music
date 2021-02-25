package com.tanx.music.pojo.model;

/**
 * 歌单和二级类别多对多
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/17 下午10:52
 */
public class SongSheetLinkType implements Cloneable{

    /** 歌单和类别多对多ID */
    private String songSheetLinkTypeId;
    /** 歌单ID */
    private String songListId;
    /** 类别ID */
    private long songSheetTypeId;

    public String getSongSheetLinkTypeId() {
        return songSheetLinkTypeId;
    }

    public void setSongSheetLinkTypeId(String songSheetLinkTypeId) {
        this.songSheetLinkTypeId = songSheetLinkTypeId;
    }

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    public long getSongSheetTypeId() {
        return songSheetTypeId;
    }

    public void setSongSheetTypeId(long songSheetTypeId) {
        this.songSheetTypeId = songSheetTypeId;
    }

    @Override
    public String toString() {
        return "SongSheetLinkType{" +
                "songSheetLinkTypeId='" + songSheetLinkTypeId + '\'' +
                ", songListId='" + songListId + '\'' +
                ", songSheetTypeId=" + songSheetTypeId +
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
