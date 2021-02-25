package com.tanx.music.controller;

import com.alibaba.fastjson.JSON;
import com.tanx.music.pojo.Result;
import com.tanx.music.pojo.vo.SongListVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.service.singer.SingerServiceImpl;
import com.tanx.music.service.song.SongServiceImpl;
import com.tanx.music.service.songList.SongSheetServiceImpl;
import com.tanx.music.utils.ConstantUtil;
import com.tanx.music.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private SingerServiceImpl singerService;
    @Autowired
    private SongSheetServiceImpl songSheetInfoService;

    @GetMapping("/data")
    public Result<?> data(Integer page, String type, String keyword){

        if(page == null || page == 0){
            return new Result<String>(0,null,new Exception("查询页数错误！"));
        }

        if("song".equals(type)){
            return song(page,keyword);
        }else if("singer".equals(type)){
            return singer(page,keyword);
        }else if("album".equals(type)){
            return album(page,keyword);
        }else if("songSheet".equals(type)){
            return songSheet(page,keyword);
        }else{
            return new Result<String>(0,null,new Exception("查询类别错误！"));
        }
    }

    private Result<String> song(int page,String keyword){
        PageUtil<SongVo> util = new PageUtil<>();
        util.setCurrent(page);
        util.setLength(ConstantUtil.LIMIT_LENGTH);
        util.setList(songService.findSongByKeyword(util,keyword));
        util.setTotal(songService.findSongByKeywordCount(keyword));
        Map<String,Object> map = new HashMap<>(8);
        map.put("lists",util.getList());
        map.put("pagination",util.getPagination());
        map.put("total",util.getTotal());
        map.put("length",util.getLength());
        return new Result<>(1, JSON.toJSONString(map),null);
    }
    private Result<String> singer(int page, String keyword){
        PageUtil<SongVo> util = new PageUtil<>();
        util.setCurrent(page);
        util.setLength(ConstantUtil.LIMIT_LENGTH);
        util.setList(singerService.findSingerByKeyword(util,keyword));
        util.setTotal(singerService.findSingerByKeywordCount(keyword));
        Map<String,Object> map = new HashMap<>(8);
        map.put("lists",util.getList());
        map.put("pagination",util.getPagination());
        map.put("total",util.getTotal());
        map.put("length",util.getLength());
        return new Result<>(1, JSON.toJSONString(map), null);
    }
    private Result<String> album(int page,String keyword){
        Map<String,Object> map = new HashMap<>(8);
        map.put("lists",new ArrayList<>());
        map.put("pagination",0);
        map.put("total",0);
        map.put("length",0);
        return new Result<>(1, JSON.toJSONString(map), null);
    }
    private Result<String> songSheet(int page,String keyword){
        PageUtil<SongListVo> util = new PageUtil<>();
        util.setCurrent(page);
        util.setLength(ConstantUtil.LIMIT_LENGTH);
        util.setList(songSheetInfoService.findSongSheetByKeyword(util,keyword));
        util.setTotal(songSheetInfoService.findSongSheetByKeywordCount(keyword));
        Map<String,Object> map = new HashMap<>(8);
        map.put("lists",util.getList());
        map.put("pagination",util.getPagination());
        map.put("total",util.getTotal());
        map.put("length",util.getLength());
        return new Result<>(1, JSON.toJSONString(map), null);
    }
}
