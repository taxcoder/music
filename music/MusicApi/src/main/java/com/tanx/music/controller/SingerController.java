package com.tanx.music.controller;

import com.tanx.music.pojo.Result;
import com.tanx.music.pojo.vo.SingerVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.service.singer.SingerServiceImpl;
import com.tanx.music.service.singerlinksong.SingerLinkSongServiceImpl;
import com.tanx.music.utils.ConstantUtil;
import com.tanx.music.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/25 下午3:39
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerServiceImpl singerService;

    @Autowired
    private SingerLinkSongServiceImpl singerLinkSongService;

    @GetMapping("/info")
    public Result<SingerVo> singerInfo(String singerId){
        if(!singerId.matches(ConstantUtil.MATCH)){
            return new Result<>(0, null, new Exception("歌手ID错误！"));
        }

        SingerVo singerInfo = singerService.findSingerInfo(Integer.parseInt(singerId));
        return new Result<>(1, singerInfo, null);
    }

    @GetMapping("/songs")
    public Result<PageUtil<SongVo>> singerSongs(String singerId,String current){
        if(!singerId.matches(ConstantUtil.MATCH) || !current.matches(ConstantUtil.MATCH)){
            return new Result<>(0, null, new Exception("数据异常！"));
        }

        PageUtil<SongVo> pageUtil = new PageUtil<>();
        pageUtil.setCurrent(Integer.parseInt(current));
        pageUtil.setLength(ConstantUtil.LIMIT_LENGTH);
        pageUtil.setTotal(singerLinkSongService.findSingerSongCount(Integer.parseInt(singerId)));
        pageUtil.setList(singerLinkSongService.findSingerSong(Integer.parseInt(singerId),pageUtil.getCurrent(),pageUtil.getLength()));
        return new Result<>(1, pageUtil, null);
    }
}
