package com.tanx.music.dao;

import com.tanx.music.pojo.model.SongList;
import com.tanx.music.pojo.vo.SongListTagVo;
import com.tanx.music.pojo.vo.SongListVo;
import com.tanx.music.pojo.vo.SongSheetVo;
import com.tanx.music.pojo.vo.SongVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 歌单
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/22 下午9:09
 */
@Repository
@Mapper
public interface SongSheetMapper {

    /**
     * 查询歌单数据
     * @param map map集合
     * @return 返回歌单数据
     */
    public List<SongListVo> findSongList(Map<String, Object> map);

    /**
     * 查询歌单是否存在
     * @param songListId 歌单ID
     * @return 返回歌单数据
     */
    public List<SongList> findSongListById(@Param("songListId") String songListId);

    /**
     * 查询最新歌单数据
     * @param map map集合
     * @return 返回歌单数据
     */
    public List<SongListVo> findNewSongList(Map<String, Object> map);

    /**
     * 查询推荐歌单数据
     * @param map map集合
     * @return 返回歌单数据
     */
    public List<SongListVo> findRecommendSongList(Map<String, Object> map);

    /**
     * 查询最新歌单数据总数
     * @return 返回歌单总数
     */
    public Integer findSongListCount();

    /**
     * 根据类别ID查询歌单
     * @param map map集合
     * @return 返回歌单
     */
    public List<SongListTagVo> findSongListByTagId(Map<String, Object> map);
    /**
     * 根据类别ID查询歌单
     * @param songSheetTypeId 类别ID
     * @return 返回歌单
     */
    public Integer findSongListByTagIdCount(int songSheetTypeId);

    /**
     * 获取所有的歌单
     * @return 返回所有的歌单
     */
    List<SongSheetVo> findAllSongSheet();

    /**
     * 增加歌单
     * @param songList 歌单信息
     * @return 增加歌单
     */
    int addSongList(SongList songList);


    /**
     * 获取歌单下面的歌曲
     * @param songSheetId 歌单ID
     * @return 返回歌曲ID
     */
    List<String> findSongSheetLinkSong(String songSheetId);

    /**
     * 删除歌单
     * @param songSheetId 歌单ID
     * @return 返回删结果
     */
    int deleteSongSheet(String songSheetId);

    /**
     * 删除歌单
     * @param lists 歌曲ID集合
     * @return 返回删结果
     */
    int deleteSongSheetLinkSong(@Param("lists") List<String> lists);


    /**
     * 修改歌单信息
     * @param songListId 歌单ID
     * @param songListName 歌单名称
     * @param songListDesc 歌单描述
     * @return 返回修改的结果
     */
    int updateSongSheet(@Param("songListId") String songListId,@Param("songListName") String songListName,@Param("songListDesc") String songListDesc);

    Integer finSongSheetLength();

    /**
     * 根据条件查询歌单
     * @param map 包含分页信息和关键字的map集合
     * @return 返回歌单列表
     */
    List<SongListVo> findSongSheetByKeyword(Map<String,Object> map);

    /**
     * 根据条件查询歌单
     * @param keyword 关键字
     * @return 返回歌单个数
     */
    Integer findSongSheetByKeywordCount(String keyword);
}
