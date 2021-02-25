package com.tanx.music.controller;

import com.tanx.music.pojo.Result;
import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SongSheetVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.service.songSheet.SongSheetInfoServiceImpl;
import com.tanx.music.utils.ConstantUtil;
import com.tanx.music.utils.PageUtil;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/26 下午1:04
 */
@RestController
@RequestMapping("/songSheet")
public class SongSheetController {

    @Autowired
    private SongSheetInfoServiceImpl songSheetInfoService;

    @GetMapping("/info")
    public Result<List<SongSheetVo>> info(String songSheetId){
        return new Result<>(1,songSheetInfoService.findSongSheet(songSheetId));
    }

    @GetMapping("/songs")
    public Result<PageUtil<SongVo>> info(String songSheetId, String current){
        if(!current.matches(ConstantUtil.MATCH)){
            return new Result<>(0, null, new Exception("参数异常!"));
        }
        PageUtil<SongVo> pageUtil = new PageUtil<>();
        pageUtil.setCurrent(Integer.parseInt(current));
        pageUtil.setLength(ConstantUtil.LIMIT_LENGTH);
        pageUtil.setList(songSheetInfoService.findSongSheetLinkSong(songSheetId,pageUtil.getCurrent(),pageUtil.getLength()));
        pageUtil.setTotal(songSheetInfoService.findSongSheetLinkSongCount(songSheetId));
        return new Result<>(1, pageUtil, null);
    }
}
