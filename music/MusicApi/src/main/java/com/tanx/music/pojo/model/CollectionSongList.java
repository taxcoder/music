package com.tanx.music.pojo.model;

/**
 * 用户收藏歌单
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午9:42
 */
public class CollectionSongList implements Cloneable{

    /** 我的喜欢歌曲ID */
    private String collectionSongListId;
    /** 歌单ID */
    private String songListId;

    public String getCollectionSongListId() {
        return collectionSongListId;
    }

    public void setCollectionSongListId(String collectionSongListId) {
        this.collectionSongListId = collectionSongListId;
    }

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    @Override
    public String toString() {
        return "CollectionSongList{" +
                "collectionSongListId=" + collectionSongListId +
                ", songListId=" + songListId +
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
