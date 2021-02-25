package com.tanx.music.pojo.vo;

/**
 * 专辑Vo
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/25 下午5:09
 */
public class AlbumVo {
    /** 专辑ID */
    private String albumId;
    /** 专辑名称 */
    private String albumName;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return "AlbumVo{" +
                "albumId='" + albumId + '\'' +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
