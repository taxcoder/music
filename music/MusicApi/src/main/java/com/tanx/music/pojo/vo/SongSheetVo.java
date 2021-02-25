package com.tanx.music.pojo.vo;

import com.tanx.music.pojo.model.SongSheetType;

import java.sql.Timestamp;
import java.util.List;

/**
 * 歌单Vo
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/26 下午12:29
 */
public class SongSheetVo {

    /** 歌单Id */
    private String songListId;
    /** 歌单名称 */
    private String songListName;
    /** 歌单图片地址 */
    private String songListImagesAddress;
    /** 热度 */
    private long temperature;
    /** 作者 */
    private String author;
    /** 歌单简介 */
    private String songListDesc;
    /** 歌单创建时间 */
    private Timestamp createTime;
    /** 歌单类别 */
    private List<SongSheetType> songSheetType;

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public String getSongListImagesAddress() {
        return songListImagesAddress;
    }

    public void setSongListImagesAddress(String songListImagesAddress) {
        this.songListImagesAddress = songListImagesAddress;
    }

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSongListDesc() {
        return songListDesc;
    }

    public void setSongListDesc(String songListDesc) {
        this.songListDesc = songListDesc;
    }

    public List<SongSheetType> getSongSheetType() {
        return songSheetType;
    }

    public void setSongSheetType(List<SongSheetType> songSheetType) {
        this.songSheetType = songSheetType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SongSheetVo{" +
                "songListId='" + songListId + '\'' +
                ", songListName='" + songListName + '\'' +
                ", songListImagesAddress='" + songListImagesAddress + '\'' +
                ", temperature=" + temperature +
                ", author='" + author + '\'' +
                ", songListDesc='" + songListDesc + '\'' +
                ", createTime=" + createTime +
                ", songSheetType=" + songSheetType +
                '}';
    }
}
