package com.tanx.music.dao;

import com.tanx.music.pojo.model.BigType;
import com.tanx.music.pojo.model.SongSheetLinkType;
import com.tanx.music.pojo.model.SongSheetType;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/24 下午6:15
 */
@Mapper
@Repository
public interface TypesMapper {

    /**
     * 查询所有的大类别
     * @return 返回所有的大类别
     */
    public List<BigType> findAll();

    /**
     * 查询所有的二级类别
     * @return 返回所有的二级类别
     */
    @MapKey("bigTypeName")
    List<Map<String,Object>> findTypeAll();

    /**
     * 获取类别的信息
     * @param songSheetTypeId 类别ID
     * @return 返回类别信息
     */
    SongSheetType findTypeName(@Param("songSheetTypeId") int songSheetTypeId);

    /**
     * 增加类别与歌单链接
     * @param lists 存储多个map集合
     * @return 返回增加的结果
     */
    int addLinkSongList(@Param("lists")List<Map<String, Object>> lists);

    /**
     * 增加类别与歌单链接
     * @param songSheetTypeId 类别ID
     * @return 返回增加的结果
     */
    List<SongSheetType> findTypeBySongSheetTypeId(@Param("songSheetTypeId") List<Integer> songSheetTypeId);

    /**
     * 删除歌单的类别
     * @param songListId 歌单ID
     * @return 返回删除的结果
     */
    int deleteTypes(String songListId);

    /**
     * 增加歌单和类别连接
     * @param list list集合
     * @return 返回增加的行数
     */
    int addTypeLinkSongSheet(@Param("lists") List<Map<String, Object>> lists);
}