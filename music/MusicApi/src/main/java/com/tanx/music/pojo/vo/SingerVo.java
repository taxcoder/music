package com.tanx.music.pojo.vo;


/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/23 上午11:58
 */
public class SingerVo {
    /** 歌手ID */
    private long singerId;
    /** 歌手名称 */
    private String singerName;
    /** 歌手头像地址 */
    private String singerHeadImage;
    /** 歌手描述信息 */
    private String singerDesc;

    public long getSingerId() {
        return singerId;
    }

    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerHeadImage() {
        return singerHeadImage;
    }

    public void setSingerHeadImage(String singerHeadImage) {
        this.singerHeadImage = singerHeadImage;
    }

    public String getSingerDesc() {
        return singerDesc;
    }

    public void setSingerDesc(String singerDesc) {
        this.singerDesc = singerDesc;
    }

    @Override
    public String toString() {
        return "SingerVo{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                ", singerHeadImage='" + singerHeadImage + '\'' +
                ", singerDesc='" + singerDesc + '\'' +
                '}';
    }
}
