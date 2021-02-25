package com.tanx.music.service.song;

import com.tanx.music.pojo.model.Song;
import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SheetSongVo;
import com.tanx.music.pojo.vo.SingerSongVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.utils.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/23 下午12:00
 */
@Service
public interface ISongService {

    /**
     * 查询所在地域的最新歌曲
     * @param regionId 地域ID
     * @return 返回歌曲信息
     */
    public List<SongVo> findSong(int regionId);

    /**
     * 查询最新歌曲
     * @return 返回歌曲信息
     */
    public List<SongVo> findNewSong();

    /**
     * 通过歌曲ID获取歌曲地址
     *
     * @param songId 歌曲ID
     * @return 返回歌曲地址
     */
    public String findSongAddress(String songId);

    /**
     * 获取歌手的所有歌曲
     * @param singerId 歌手ID
     * @return 返回歌曲
     */
    List<SingerSongVo> findSongSinger(int singerId);

    /**
     * 修改歌曲信息
     * @param songId 歌曲ID
     * @param songName 歌曲名称
     * @param regionId 地域ID
     * @param lyric 歌词
     * @return 返回修改的结果
     */
    int updateSong(String songId,String songName,int regionId,String lyric);

    /**
     * 获取歌单的歌曲
     * @param songSheetId 歌单ID
     * @return 返回歌曲信息
     */
    List<SheetSongVo> findSongLinkSheet(String songSheetId);


    /**
     * 查询歌曲
     * @param query 条件
     * @param singerId 歌手Id
     * @return 返回歌曲
     */
    List<SongVo> findSongName(String query, Integer singerId);

    /**
     * 根据ID查询数据
     * @param songId 歌曲ID
     * @return 返回歌曲信息
     */
    Song findSongById(String songId);

    /**
     * 歌曲个数
     * @return 返回歌曲个数
     */
    int findSongLength();

    /**
     * 通过歌曲ID查询歌曲详细信息
     * @param songId 歌曲ID
     * @return 返回
     */
    ReturnSongVo querySongById(String songId);

    /**
     * 根据条件查询歌曲
     * @param util 分页信息
     * @param keyword 关键字
     * @return 返回歌曲列表
     */
    List<SongVo> findSongByKeyword(PageUtil<SongVo> util,String keyword);

    /**
     * 根据条件查询歌曲
     * @param keyword 关键字
     * @return 返回歌曲个数
     */
    int findSongByKeywordCount(String keyword);
}
