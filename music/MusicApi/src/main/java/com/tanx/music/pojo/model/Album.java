package com.tanx.music.pojo.model;


import java.sql.Timestamp;

/**
 * 专辑
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/17 下午9:11
 */
public class Album implements Cloneable{

    /** 专辑ID */
    private String albumId;
    /** 专辑名称 */
    private String albumName;
    /** 专辑图片地址 */
    private String albumImagesAddress;
    /** 专辑简介 */
    private String albumDesc;
    /** 发售公司 */
    private String sellingCompany;
    /** 语言 */
    private String language;
    /** 专辑创建时间 默认创建 */
    private Timestamp createTime;

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

    public String getAlbumImagesAddress() {
        return albumImagesAddress;
    }

    public void setAlbumImagesAddress(String albumImagesAddress) {
        this.albumImagesAddress = albumImagesAddress;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public String getSellingCompany() {
        return sellingCompany;
    }

    public void setSellingCompany(String sellingCompany) {
        this.sellingCompany = sellingCompany;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId='" + albumId + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumImagesAddress='" + albumImagesAddress + '\'' +
                ", albumDesc='" + albumDesc + '\'' +
                ", sellingCompany='" + sellingCompany + '\'' +
                ", language='" + language + '\'' +
                ", createTime=" + createTime +
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
