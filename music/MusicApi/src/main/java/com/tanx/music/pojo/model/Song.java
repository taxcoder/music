package com.tanx.music.pojo.model;

import java.sql.Timestamp;

/**
 * 歌曲
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午10:00
 */
public class Song implements Cloneable{

    /** 歌曲ID */
    private String songId;
    /** 歌曲名称 */
    private String songName;
    /** 歌曲的封面 */
    private String songImages;
    /** 歌曲时长(秒) */
    private int songLength;
    /** 歌曲地址 */
    private String songAddress;
    /** 歌曲歌词 */
    private String lyric;
    /** 专辑ID */
    private String albumId;
    /** 增加时间 */
    private Timestamp addTime;

    private int regionId;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongImages() {
        return songImages;
    }

    public void setSongImages(String songImages) {
        this.songImages = songImages;
    }

    public int getSongLength() {
        return songLength;
    }

    public void setSongLength(int songLength) {
        this.songLength = songLength;
    }

    public String getSongAddress() {
        return songAddress;
    }

    public void setSongAddress(String songAddress) {
        this.songAddress = songAddress;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId='" + songId + '\'' +
                ", songName='" + songName + '\'' +
                ", songImages='" + songImages + '\'' +
                ", songLength=" + songLength +
                ", songAddress='" + songAddress + '\'' +
                ", lyric='" + lyric + '\'' +
                ", albumId='" + albumId + '\'' +
                ", addTime=" + addTime +
                ", regionId=" + regionId +
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
