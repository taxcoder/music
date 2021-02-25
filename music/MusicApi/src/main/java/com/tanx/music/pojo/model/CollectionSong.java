package com.tanx.music.pojo.model;

/**
 * 用户收藏歌曲
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午6:44
 */

public class CollectionSong implements Cloneable{

    /** 我的喜欢歌曲ID */
    private String collectionSongId;
    /** 歌曲ID--UUID */
    private String songId;
    /** 用户ID--UUID */
    private String userId;

    public String getCollectionSongId() {
        return collectionSongId;
    }

    public void setCollectionSongId(String collectionSongId) {
        this.collectionSongId = collectionSongId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CollectionSong{" +
                "collectionSongId='" + collectionSongId + '\'' +
                ", songId='" + songId + '\'' +
                ", userId='" + userId + '\'' +
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
