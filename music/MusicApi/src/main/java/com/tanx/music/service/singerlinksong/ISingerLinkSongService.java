package com.tanx.music.service.singerlinksong;

import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SongVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/25 下午5:18
 */
@Service
public interface ISingerLinkSongService {


    /**
     * 查询歌手的歌曲
     * @param singerId 歌手ID
     * @param current 当前页
     * @param length 长度
     * @return 返回歌曲信息
     */
    public List<SongVo> findSingerSong(int singerId, int current, int length);

    /**
     * 查询歌手的歌曲总数
     * @param singerId 歌手Id
     * @return 歌曲总数
     */
    public int findSingerSongCount(int singerId);
}
