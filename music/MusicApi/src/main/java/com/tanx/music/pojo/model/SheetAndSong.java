package com.tanx.music.pojo.model;

/**
 *
 * 歌单和歌曲
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/17 下午10:10
 */
public class SheetAndSong implements Cloneable{

    /** 歌单和歌曲ID */
    private String sheetSongId;
    /** 歌单ID */
    private String songListId;
    /** 歌曲ID */
    private String songId;

    public String getSheetSongId() {
        return sheetSongId;
    }

    public void setSheetSongId(String sheetSongId) {
        this.sheetSongId = sheetSongId;
    }

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return "SheetAndSong{" +
                "sheetSongId='" + sheetSongId + '\'' +
                ", songListId='" + songListId + '\'' +
                ", songId='" + songId + '\'' +
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
