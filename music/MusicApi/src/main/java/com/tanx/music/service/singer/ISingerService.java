package com.tanx.music.service.singer;

import com.tanx.music.pojo.model.Singer;
import com.tanx.music.pojo.vo.SingerVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.utils.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/23 下午12:02
 */
@Service
public interface ISingerService {

    /**
     * 推荐歌手
     * @return 返回歌手信息
     */
    public List<SingerVo> recommendSinger();

    /**
     * 获取歌手信息
     * @param regionId 地域ID
     * @param singerStatus 歌手状态
     * @param abbreviation 首字母
     * @param page 分页信息
     * @return 返回歌手信息
     */
    public List<SingerVo> pageSinger(int regionId,int singerStatus,String abbreviation, PageUtil<SingerVo> page);

    /**
     * 获取歌手信息（流行）
     * @param regionId 域名ID
     * @param singerStatus 歌手状态
     * @param page 分页信息
     * @return 返回歌手信息
     */
    public List<SingerVo> pageSingerPopular(int regionId, int singerStatus, PageUtil<SingerVo> page);

    /**
     * 获取歌手信息
     * @param regionId 地域ID
     * @param singerStatus 歌手状态
     * @param abbreviation 首字母
     * @param page 分页信息
     * @return 返回歌手信息
     */
    public int pageSingerCount(int regionId,int singerStatus,String abbreviation, PageUtil<SingerVo> page);

    /**
     * 获取歌手信息（流行）
     * @param regionId 域名ID
     * @param singerStatus 歌手状态
     * @param page 分页信息
     * @return 返回歌手信息
     */
    public int pageSingerPopularCount(int regionId, int singerStatus, PageUtil<SingerVo> page);

    /**
     * 获取歌手信息
     * @param singerId 歌手ID
     * @return 返回歌手信息
     */
    public SingerVo findSingerInfo(int singerId);


    /**
     * 获取所有的歌手
     * @return 歌手
     */
    public List<Singer> findAll();

    /**
     * 增加一个歌手
     * @param singer 歌手信息
     * @return 返回增加的结果
     */
    int addSinger(Singer singer);

    /**
     * 修改歌手信息
     * @param singer 歌手信息
     * @return 返回修改的信息
     */
    int updateSinger(Singer singer);

    /**
     * 根据数据查询歌手信息
     * @param query 查询条件
     * @return 返回歌手信息
     */
    List<SingerVo> findSingerName(String query);

    /**
     * 返回歌手个数
     * @return 返回歌手个数
     */
    int findSingerLength();

    /**
     * 根据条件查询歌手
     * @param util 分页信息
     * @param keyword 关键字
     * @return 返回歌手列表
     */
    List<SongVo> findSingerByKeyword(PageUtil<SongVo> util, String keyword);

    /**
     * 根据条件查询歌手
     * @param keyword 关键字
     * @return 返回歌手个数
     */
    int findSingerByKeywordCount(String keyword);
}
