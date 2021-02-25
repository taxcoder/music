package com.tanx.music.service.affair;

import com.tanx.music.pojo.model.Song;
import com.tanx.music.pojo.model.SongList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/30 下午4:55
 */
@Service
public interface IAffairService {

    /**
     * 增加一首歌和与歌手连通
     * @param song 歌曲信息
     * @param singerId 歌手ID
     * @return 返回结果
     * @throws Exception 异常
     */
    public String addSongLinkSinger(Song song,int singerId) throws Exception;

    /**
     * 删除歌手和他的歌曲
     * @param singerId 歌手ID
     * @return 返回删除的结果
     * @throws Exception 异常
     */
    public String deleteSinger(Long singerId) throws Exception;

    /**
     * 删除歌曲
     * @param songId 歌曲ID
     * @return 返回删除的结果
     * @throws Exception 异常
     */
    String deleteSong(String songId) throws Exception;

    /**
     * 删除歌曲
     * @param songId 歌曲ID
     * @return 返回删除的结果
     * @throws Exception 异常
     */
    String deleteSongAndSheet(String songId) throws Exception;

    /**
     *
     * @param songList 歌单信息
     * @param typeId 类别ID
     * @return 返回增加的结果
     * @throws Exception 异常
     */
    String addSongList(SongList songList, List<Integer> typeId) throws Exception;

    /**
     * 删除歌单
     * @param songSheetId 歌单ID
     * @return 返回删除的结果
     * @throws Exception 异常
     */
    String deleteSongSheet(String songSheetId) throws Exception;

    /**
     * 删除歌单
     * @param songListId 歌单ID
     * @param songListName 歌单名称
     * @param songListDesc 歌单描述
     * @param types 歌单的类别
     * @return 返回删除的结果
     * @throws Exception 异常
     */
    String updateSongSheet(String songListId,String songListName,String songListDesc,List<Integer> types) throws Exception;
}
