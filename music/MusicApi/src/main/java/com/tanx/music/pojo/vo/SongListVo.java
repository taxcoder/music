package com.tanx.music.pojo.vo;

import com.tanx.music.pojo.model.SongSheetType;

import java.util.List;

/**
 * 歌单数据
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/22 下午8:23
 */
public class SongListVo {

    /** 歌单ID */
    private String songListId;
    /** 歌单名称 */
    private String songListName;
    /** 歌单图片地址 */
    private String songListImagesAddress;
    /** 热度 */
    private String temperature;
    /** 类型名称 */
    private List<SongSheetType> songSheetTypeList;


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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public List<SongSheetType> getSongSheetTypeList() {
        return songSheetTypeList;
    }

    public void setSongSheetTypeList(List<SongSheetType> songSheetTypeList) {
        this.songSheetTypeList = songSheetTypeList;
    }

    @Override
    public String toString() {
        return "SongListVo{" +
                "songListId='" + songListId + '\'' +
                ", songListName='" + songListName + '\'' +
                ", songListImagesAddress='" + songListImagesAddress + '\'' +
                ", temperature='" + temperature + '\'' +
                ", songSheetTypeList=" + songSheetTypeList +
                '}';
    }
}
