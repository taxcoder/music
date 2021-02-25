package com.tanx.music.dao;

import com.tanx.music.pojo.model.Singer;
import com.tanx.music.pojo.vo.SingerVo;
import com.tanx.music.pojo.vo.SongVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/23 下午12:03
 */
@Mapper
@Repository
public interface SingerMapper {

    /**
     * 推荐歌手
     * @return 返回歌手信息
     */
    public List<SingerVo> recommendSinger();


    /**
     * 获取歌手信息
     * @param map 集合
     * @return 返回歌手信息
     */
    public List<SingerVo> pageSinger(Map<String,Object> map);

    /**
     * 获取歌手信息
     * @param map 集合
     * @return 返回歌手信息
     */
    public List<SingerVo> pageSingerPopular(Map<String,Object> map);

    /**
     * 获取歌手总数（条件判断，非流行）
     * @param map 集合
     * @return 返回歌手信息
     */
    public Integer pageSingerCount(Map<String, Object> map);

    /**
     * 获取歌手信息（条件判断，流行）
     * @param map 集合
     * @return 返回歌手信息
     */
    public Integer pageSingerPopularCount(Map<String,Object> map);

    /**
     * 获取歌手信息
     * @param singerId 歌手ID
     * @return 返回歌手信息
     */
    public SingerVo findSingerInfo(int singerId);

    /**
     * 获取所有的歌手
     * @return 歌手信息
     */
    public List<Singer> findAll();

    /**
     * 增加一个歌手
     * @param singer 歌手信息
     * @return 返回增加的结果
     */
    int addSinger(Singer singer);

    /**
     * 删除歌手
     * @param singerId 歌手ID
     * @return 返回删除的结果
     */
    int deleteSinger(Long singerId);

    /**
     * 修改歌手信息
     * @param singer 歌手信息
     * @return 返回修改的信息
     */
    int updateSinger(Singer singer);

    /**
     * 根据条件模糊查询歌手信息
     * @param map 查询条件
     * @return 返回歌手信息
     */
    List<SingerVo> findSingerName(Map<String, Object> map);

    /**
     * 返回歌手个数
     * @return 返回歌手个数
     */
    Integer findSingerLength();

    /**
     * 根据条件查询歌手
     * @param map 包含分页信息和关键字的map集合
     * @return 返回歌手列表
     */
    List<SongVo> findSingerByKeyword(Map<String,Object> map);

    /**
     * 根据条件查询歌手
     * @param keyword 关键字
     * @return 返回歌手个数
     */
    Integer findSingerByKeywordCount(String keyword);
}
