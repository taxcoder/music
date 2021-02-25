package com.tanx.music.controller;

import com.tanx.music.pojo.Result;
import com.tanx.music.pojo.model.Region;
import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SingerVo;
import com.tanx.music.pojo.vo.SongListVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.service.region.RegionServiceImpl;
import com.tanx.music.service.singer.SingerServiceImpl;
import com.tanx.music.service.song.SongServiceImpl;
import com.tanx.music.service.songList.SongSheetServiceImpl;
import com.tanx.music.utils.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午5:47
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    private static final String NEW = "new";

    @Autowired
    private SongServiceImpl songService;

    @Autowired
    private SingerServiceImpl singerService;

    @Autowired
    private SongSheetServiceImpl songSheetService;

    @Autowired
    private RegionServiceImpl regionService;

    /**
     * 推荐歌单信息
     */
    @GetMapping("/songSheet")
    public Result<List<SongListVo>> getSongSheet(){
        return new Result<>(1, songSheetService.findSongList(1, 10));
    }

    /**
     * 推荐歌手信息
     */
    @GetMapping("/singer")
    public Result<List<SingerVo>> getSinger() {
        return new Result<>(1, singerService.recommendSinger());
    }

    /**
     * 最新歌曲信息
     */
    @GetMapping("/song")
    public Result<List<SongVo>> getSong(String info){
        if(info == null){   
            return new Result<>(0, null,new Exception("传入条件为空！"));
        }

        if(NEW.equals(info)){
            return new Result<>(1, songService.findNewSong());
        }

        if(info.matches(ConstantUtil.MATCH)){
            return new Result<>(1, songService.findSong(Integer.parseInt(info)));
        }

        return new Result<>(0, null,new Exception("地域信息错误！"));
    }

    /**
     * 地域信息
     */
    @GetMapping("/region")
    public Result<List<Region>> getRegion(){
        return new Result<>(1, regionService.findAll());
    }

    /**
     * 歌曲的详细信息
     * @param songId 歌曲ID
     * @return 返回歌曲信息
     */
    @GetMapping("/songInfo")
        public Result<ReturnSongVo> songInfo(String songId){
        return new Result<>(1, songService.querySongById(songId));
    }
}
