package com.tanx.music.service.songList;

import com.tanx.music.pojo.model.SongList;
import com.tanx.music.pojo.vo.SongListTagVo;
import com.tanx.music.pojo.vo.SongListVo;
import com.tanx.music.pojo.vo.SongSheetVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.utils.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/22 下午9:28
 */
@Service
public interface ISongSheetService {

    /**
     * 查询歌单数据
     * @param current 当前页
     * @param length 长度（指定长度或0）
     * @return 返回歌单数据
     */
    public List<SongListVo> findSongList(int current,int length);

    /**
     * 查询最新歌单数据
     * @param current 当前页
     * @param length 长度（指定长度或0）
     * @return 返回歌单数据
     */
    public List<SongListVo> findNewSongList(int current, int length);

    /**
     * 查询推荐歌单数据
     * @param current 当前页
     * @param length 长度（指定长度或0）
     * @return 返回歌单数据
     */
    public List<SongListVo> findRecommendSongList(int current, int length);

    /**
     * 查询最新歌单数据的总数
     * @return 返回歌单数据
     */
    public int findSongListCount();

    /**
     * 根据类别ID查询歌单
     * @param pageUtil 分页工具类
     * @param songSheetTypeId 类别ID
     * @return 返回歌单
     */
    public List<SongListTagVo> findSongListByTagId(PageUtil<SongListTagVo> pageUtil, int songSheetTypeId);
    /**
     * 根据类别ID查询歌单
     * @param songSheetTypeId 类别ID
     * @return 返回歌单
     */
    public int findSongListByTagIdCount(int songSheetTypeId);

    /**
     * 返回所有歌单
     * @return 歌单
     */
    List<SongSheetVo> findAllSongSheet();

    /**
     * 查询歌单是否存在
     * @param songListId 歌单ID
     * @return 返回歌单数据
     */
    public List<SongList> findSongListById(String songListId);

    /**
     * 歌单个数
     * @return 返回歌单个数
     */
    int finSongSheetLength();

    /**
     * 根据条件查询歌单
     * @param util 分页信息
     * @param keyword 关键字
     * @return 返回歌单列表
     */
    List<SongListVo> findSongSheetByKeyword(PageUtil<SongListVo> util, String keyword);

    /**
     * 根据条件查询歌单
     * @param keyword 关键字
     * @return 返回歌单个数
     */
    int findSongSheetByKeywordCount(String keyword);
}
