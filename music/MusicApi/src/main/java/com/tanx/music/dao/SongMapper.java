package com.tanx.music.dao;

import com.tanx.music.pojo.model.Song;
import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SheetSongVo;
import com.tanx.music.pojo.vo.SingerSongVo;
import com.tanx.music.pojo.vo.SongVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/23 上午11:31
 */
@Mapper
@Repository
public interface SongMapper {

    /**
     * 查询所在地域的最新歌曲
     * @param regionId 地域ID
     * @return 返回歌曲信息
     */
    List<SongVo> findSong(@Param("regionId") int regionId);

    /**
     * 查询最新歌曲
     * @return 返回歌曲信息
     */
    List<SongVo> findNewSong();

    /**
     * 通过歌曲ID获取歌曲地址
     *
     * @param songId 歌曲ID
     * @return 返回歌曲地址
     */
    String findSongAddress(@Param("songId") String songId);


    /**
     * 获取歌手的所有歌曲
     * @param singerId 歌手ID
     * @return 返回歌曲
     */
    List<SingerSongVo> findSongSinger(@Param("singerId")int singerId);

    /**
     * 增加一首歌
     * @param song 歌曲信息
     * @return 返回增加的结果
     */
    int addSong(Song song);

    /**
     * 删除歌曲
     * @param songId 歌曲ID
     * @return 返回删除的结果
     */
    int deleteSong(@Param("songId")String songId);

    /**
     * 删除歌曲
     * @param songId 歌曲ID
     * @return 返回删除的结果
     */
    int deleteSongList(@Param("songId")List<String> songId);

    /**
     * 删除多首歌曲
     * @param lists 歌曲ID
     * @return 返回删除的结果
     */
    int deleteSongs(@Param("lists")List<String> lists);

    /**
     * 根据歌曲ID查询歌曲
     * @param songId 歌曲ID
     * @return 返回歌曲信息
     */
    Song findSongById(@Param("songId") String songId);

    /**
     * 修改歌曲信息
     * @param map map集合
     * @return 返回修改的行数
     */
    int updateSong(Map<String, Object> map);

    /**
     * 获取歌单歌曲
     * @param songSheetId 歌单ID
     * @return 返回歌曲
     */
    List<SheetSongVo> findSongLinkSheet(@Param("songSheetId") String songSheetId);

    /**
     * 查询歌曲
     * @param map map集合
     * @return 返回歌曲
     */
    List<SongVo> findSongName(Map<String, Object> map);

    /**
     * 歌曲个数
     * @return 返回歌曲个数
     */
    Integer findSongLength();

    /**
     * 获取一首歌曲具体信息
     * @param songId 歌曲ID
     * @return 返回歌曲信息
     */
    ReturnSongVo querySongById(String songId);

    /**
     * 根据条件查询歌曲
     * @param map 包含分页信息和关键字的map集合
     * @return 返回歌曲列表
     */
    List<SongVo> findSongByKeyword(Map<String,Object> map);

    /**
     * 根据条件查询歌曲
     * @param keyword 关键字
     * @return 返回歌曲个数
     */
    Integer findSongByKeywordCount(String keyword);
}
