package com.tanx.music.pojo.model;

/**
 *
 * 歌手和歌单
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/17 下午10:21
 */
public class SingerAndSong implements Cloneable{

    /** 歌手和歌曲ID */
    private String singerAndSongId;
    /** 歌手ID */
    private long singerId;
    /** 歌曲ID */
    private String songId;

    public String getSingerAndSongId() {
        return singerAndSongId;
    }

    public void setSingerAndSongId(String singerAndSongId) {
        this.singerAndSongId = singerAndSongId;
    }

    public long getSingerId() {
        return singerId;
    }

    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return "SingerAndSong{" +
                "singerAndSongId='" + singerAndSongId + '\'' +
                ", singerId=" + singerId +
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
