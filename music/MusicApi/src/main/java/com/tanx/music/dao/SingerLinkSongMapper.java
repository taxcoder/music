package com.tanx.music.dao;

import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SongVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/25 下午5:07
 */
@Mapper
@Repository
public interface SingerLinkSongMapper {

    /**
     * 查询歌手歌曲
     *
     * @param map map集合
     * @return 返回歌曲信息
     */
    List<SongVo> findSingerSong(Map<String, Object> map);

    /**
     * 查询歌手的歌曲总数
     * @param singerId 歌手ID
     * @return 歌曲总数
     */
    Integer findSingerSongCount(@Param("singerId") int singerId);

    /**
     * 查询歌手的歌曲总数
     * @param singerId 歌手ID
     * @param songId 歌曲ID
     * @param linkId 链接ID
     * @return 歌曲总数
     */
    int addSingerLinkSong(@Param("singerId") int singerId,@Param("songId") String songId,@Param("linkId") String linkId);

    /**
     * 查询歌手的所有歌曲ID
     * @param singerId 歌手ID
     * @return 歌曲ID
     */
    List<String> findSingerSongAll(@Param("singerId") Long singerId);

    /**
     * 删除连接的数据
     * @param singerId 歌手ID
     * @return 返回删除的函数
     */
    int deleteSingerLink(Long singerId);

    /**
     * 根据歌曲ID删除数据
     * @param songId 歌曲ID
     * @return 返回删除的行数
     */
    int deleteSongLink(String songId);
}
