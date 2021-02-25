package com.tanx.music.pojo.vo;

import java.util.List;

/**
 * 前台返回的歌曲
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/25 下午5:08
 */
public class ReturnSongVo {

    /** 歌曲ID */
    private String songId;
    /** 歌曲名称 */
    private String songName;
    /** 歌曲的作者 */
    private List<SingerVo> author;
    /** 歌曲时长(秒) */
    private int songLength;
    /** 专辑名称 */
    private List<AlbumVo> album;
    /** 歌曲的封面 */
    private String songImages;

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

    public List<SingerVo> getAuthor() {
        return author;
    }

    public void setAuthor(List<SingerVo> author) {
        this.author = author;
    }

    public int getSongLength() {
        return songLength;
    }

    public void setSongLength(int songLength) {
        this.songLength = songLength;
    }

    public List<AlbumVo>  getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumVo> album) {
        this.album = album;
    }

    public String getSongImages() {
        return songImages;
    }

    public void setSongImages(String songImages) {
        this.songImages = songImages;
    }

    @Override
    public String toString() {
        return "ShowSongVo{" +
                "songId='" + songId + '\'' +
                ", songName='" + songName + '\'' +
                ", author=" + author +
                ", songLength=" + songLength +
                ", album='" + album + '\'' +
                ", songImages='" + songImages + '\'' +
                '}';
    }
}
