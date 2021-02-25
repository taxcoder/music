package com.tanx.music.service.songSheet;

import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SongListVo;
import com.tanx.music.pojo.vo.SongSheetVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.utils.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/26 下午3:12
 */
@Service
public interface ISongSheetInfoService {

    /**
     * 获取歌单的具体信息
     * @param songSheetId 歌单ID
     * @return 返回歌单类型
     */
    public List<SongSheetVo> findSongSheet(String songSheetId);

    /**查询歌单的歌曲
     *
     * @param songSheetId 歌单ID
     * @param current 当前页
     * @param length 长度
     * @return 返回歌曲
     */
    public List<SongVo> findSongSheetLinkSong(String songSheetId, int current, int length);

    /**查询歌单的歌曲
     *
     * @param songSheetId 歌单ID
     * @return 返回歌曲
     */
    public int findSongSheetLinkSongCount(String songSheetId);

    /**
     * 歌曲和歌单连接
     * @param songSheetId 歌单ID
     * @param songId 歌曲ID
     * @return 返回增加的结果
     */
    public int addSongSheetLinkSong(String songSheetId,String songId);



}
