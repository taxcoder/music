package com.tanx.music.pojo.model;


import java.sql.Timestamp;

/**
 * 评论信息
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午9:44
 */
public class Comment implements Cloneable{

    /** 评论ID--UUID */
    private String commentId;
    /** 用户ID--UUID */
    private String userId;
    /** 评论信息 */
    private String commentInfo;
    /** 评论日期 默认添加 */
    private Timestamp commentDate;
    /** 歌单ID （非歌单评论为000000000000000） */
    private String songSheetId;
    /** 专辑ID （非专辑评论为000000000000000） */
    private String albumId;
    /** 歌曲ID （非歌曲评论为000000000000000） */
    private String songId;
    /** 歌手ID （非歌手评论为0） */
    private long singerId;
    /** 评论点赞数 */
    private int commentGoods;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    public String getSongSheetId() {
        return songSheetId;
    }

    public void setSongSheetId(String songSheetId) {
        this.songSheetId = songSheetId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public long getSingerId() {
        return singerId;
    }

    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    public int getCommentGoods() {
        return commentGoods;
    }

    public void setCommentGoods(int commentGoods) {
        this.commentGoods = commentGoods;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", userId='" + userId + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                ", commentDate=" + commentDate +
                ", songSheetId='" + songSheetId + '\'' +
                ", albumId='" + albumId + '\'' +
                ", songId='" + songId + '\'' +
                ", singerId=" + singerId +
                ", commentGoods=" + commentGoods +
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
