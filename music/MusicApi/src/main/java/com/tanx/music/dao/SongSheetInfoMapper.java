package com.tanx.music.dao;

import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SongSheetVo;
import com.tanx.music.pojo.vo.SongVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/26 下午3:03
 */
@Mapper
@Repository
public interface SongSheetInfoMapper {

    /**
     * 获取歌单的具体信息
     * @param songSheetId 歌单ID
     * @return 返回歌单类型
     */
    public List<SongSheetVo> findSongSheet(@Param("songSheetId") String songSheetId);

    /**
     * 获取歌单的歌曲
     * @param map map集合
     * @return 返回歌曲数据
     */
    List<SongVo> findSongSheetLinkSong(Map<String,Object> map);

    /**查询歌单的歌曲
     *
     * @param songSheetId 歌单ID
     * @return 返回歌曲
     */
    public Integer findSongSheetLinkSongCount(String songSheetId);

    /**
     * 歌曲和歌单连接
     * @param sheetSongId 歌单与歌曲连接ID
     * @param songSheetId 歌单ID
     * @param songId 歌曲ID
     * @return 返回增加的结果
     */
    int addSongSheetLinkSong(@Param("sheetSongId") String sheetSongId,@Param("songSheetId") String songSheetId,@Param("songId") String songId);
}
