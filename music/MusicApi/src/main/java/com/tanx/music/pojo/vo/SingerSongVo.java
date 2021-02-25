package com.tanx.music.pojo.vo;

import java.sql.Timestamp;

/**
 * 后台歌手的歌曲
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/30 上午10:27
 */
public class SingerSongVo {
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
    /** 地域ID */
    private int regionId;
    /** 专辑信息 */
    private AlbumVo album;
    /** 歌曲歌词 */
    private String lyric;
    /** 增加时间 */
    private Timestamp addTime;

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

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public AlbumVo getAlbum() {
        return album;
    }

    public void setAlbum(AlbumVo album) {
        this.album = album;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public String getSongAddress() {
        return songAddress;
    }

    public void setSongAddress(String songAddress) {
        this.songAddress = songAddress;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    @Override
    public String toString() {
        return "SingerSongVo{" +
                "songId='" + songId + '\'' +
                ", songName='" + songName + '\'' +
                ", songImages='" + songImages + '\'' +
                ", songLength=" + songLength +
                ", songAddress='" + songAddress + '\'' +
                ", regionId=" + regionId +
                ", album=" + album +
                ", lyric='" + lyric + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
