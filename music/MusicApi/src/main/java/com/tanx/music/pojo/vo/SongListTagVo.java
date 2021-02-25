package com.tanx.music.pojo.vo;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/25 下午1:40
 */
public class SongListTagVo {

    private long songSheetTypeId;
    private String songSheetTypeName;
    private List<SongListVo> songList;

    public long getSongSheetTypeId() {
        return songSheetTypeId;
    }

    public void setSongSheetTypeId(long songSheetTypeId) {
        this.songSheetTypeId = songSheetTypeId;
    }

    public String getSongSheetTypeName() {
        return songSheetTypeName;
    }

    public void setSongSheetTypeName(String songSheetTypeName) {
        this.songSheetTypeName = songSheetTypeName;
    }

    public List<SongListVo> getSongList() {
        return songList;
    }

    public void setSongList(List<SongListVo> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return "SongListTagVo{" +
                "songSheetTypeId='" + songSheetTypeId + '\'' +
                ", songSheetTypeName='" + songSheetTypeName + '\'' +
                ", songList=" + songList +
                '}';
    }
}
