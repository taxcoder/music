package com.tanx.music.pojo.vo;

import java.sql.Timestamp;
import java.util.List;

/**
 * 后台歌单的歌曲
 * @author tanxiang
 * @version 1.0
 * @date 2020/12/1 下午1:04
 */
public class SheetSongVo {

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
    /** 地域ID */
    private int regionId;
    /** 专辑ID */
    private String albumId; 
    /** 增加时间 */
    private Timestamp addTime;
    /** 专辑信息 */
    private AlbumVo albumVo;
    /** 歌手信息 */
    private List<SingerVo> singers;

    public List<SingerVo> getSingers() {
        return singers;
    }

    public void setSingers(List<SingerVo> singers) {
        this.singers = singers;
    }

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

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public AlbumVo getAlbumVo() {
        return albumVo;
    }

    public void setAlbumVo(AlbumVo albumVo) {
        this.albumVo = albumVo;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "SheetSongVo{" +
                "songId='" + songId + '\'' +
                ", songName='" + songName + '\'' +
                ", songImages='" + songImages + '\'' +
                ", songLength=" + songLength +
                ", songAddress='" + songAddress + '\'' +
                ", lyric='" + lyric + '\'' +
                ", regionId=" + regionId +
                ", albumId='" + albumId + '\'' +
                ", addTime=" + addTime +
                ", albumVo=" + albumVo +
                ", singers=" + singers +
                '}';
    }
}
