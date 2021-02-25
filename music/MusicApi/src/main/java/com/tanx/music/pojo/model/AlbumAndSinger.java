package com.tanx.music.pojo.model;

/**
 * 专辑与歌手多对多
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/17 下午9:21
 */
public class AlbumAndSinger implements Cloneable{
    /** 专辑与歌手多对多ID */
    private String albumSingerId;
    /** 专辑ID */
    private String albumId;
    /** 歌手ID */
    private long singerId;

    public String getAlbumSingerId() {
        return albumSingerId;
    }

    public void setAlbumSingerId(String albumSingerId) {
        this.albumSingerId = albumSingerId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public long getSingerId() {
        return singerId;
    }

    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    @Override
    public String toString() {
        return "AlbumAndSinger{" +
                "albumSingerId='" + albumSingerId + '\'' +
                ", albumId='" + albumId + '\'' +
                ", singerId='" + singerId + '\'' +
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
