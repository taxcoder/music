package com.tanx.music.pojo.vo;

import com.tanx.music.pojo.model.Album;
import com.tanx.music.pojo.model.Region;
import com.tanx.music.pojo.model.Singer;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/23 上午11:42
 */
public class SongVo {

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
    /** 增加时间 */
    private Timestamp addTime;
    /** 地域信息 */
    private Region region;
    /** 歌手信息 */
    private List<Singer> author;
    /** 专辑信息 */
    private Album album;

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

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Singer> getAuthor() {
        return author;
    }

    public void setAuthor(List<Singer> author) {
        this.author = author;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "SongVo{" +
                "songId='" + songId + '\'' +
                ", songName='" + songName + '\'' +
                ", songImages='" + songImages + '\'' +
                ", songLength=" + songLength +
                ", songAddress='" + songAddress + '\'' +
                ", lyric='" + lyric + '\'' +
                ", addTime=" + addTime +
                ", region=" + region +
                ", author=" + author +
                ", album=" + album +
                '}';
    }
}
