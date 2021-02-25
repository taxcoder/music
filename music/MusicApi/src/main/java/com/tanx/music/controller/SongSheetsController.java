package com.tanx.music.controller;

import com.tanx.music.pojo.model.SongSheetType;
import com.tanx.music.pojo.vo.SongListTagVo;
import com.tanx.music.pojo.vo.SongListVo;
import com.tanx.music.service.songList.SongSheetServiceImpl;
import com.tanx.music.service.types.TypeServiceImpl;
import com.tanx.music.utils.ConstantUtil;
import com.tanx.music.utils.PageUtil;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午5:48
 */
@RestController
@RequestMapping("/songSheets")
@SuppressWarnings("unchecked")
public class SongSheetsController {

    private static final ReturnUtil RETURN_UTIL = ReturnUtil.getInstance();

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private SongSheetServiceImpl songSheetService;

    @GetMapping("/types")
    public String getBigType() throws CloneNotSupportedException {
        return RETURN_UTIL.success(typeService.findTypeAll());
    }

    @GetMapping("/recommends")
    public String getRecommend(String current) {
        if(!current.matches(ConstantUtil.MATCH)){
            throw new RuntimeException("页码错误！");
        }
        PageUtil<SongListVo> pageUtil = new PageUtil<>();
        pageUtil.setCurrent(Integer.parseInt(current));
        pageUtil.setLength(ConstantUtil.LIMIT_LENGTH);
        pageUtil.setList(songSheetService.findRecommendSongList(pageUtil.getCurrent(), pageUtil.getLength()));
        pageUtil.setTotal(songSheetService.findSongListCount());
        return RETURN_UTIL.success(pageUtil);
    }
    @GetMapping("/news")
    public String getNews(String current) {
        if(!current.matches(ConstantUtil.MATCH)){
            throw new RuntimeException("页码错误！");
        }
        PageUtil<SongListVo> pageUtil = new PageUtil<>();
        pageUtil.setCurrent(Integer.parseInt(current));
        pageUtil.setLength(ConstantUtil.LIMIT_LENGTH);
        pageUtil.setList(songSheetService.findNewSongList(pageUtil.getCurrent(), pageUtil.getLength()));
        pageUtil.setTotal(songSheetService.findSongListCount());
        return RETURN_UTIL.success(pageUtil);
    }

    @GetMapping("/tagDatas")
    public String tagSongList(String current,String tagId){
        if(!tagId.matches(ConstantUtil.MATCH) || !current.matches(ConstantUtil.MATCH)){
            throw new RuntimeException("数据传入错误！");
        }
        PageUtil<SongListTagVo> pageUtil = new PageUtil<>();
        pageUtil.setCurrent(Integer.parseInt(current));
        pageUtil.setLength(ConstantUtil.LIMIT_LENGTH);
        pageUtil.setTotal(songSheetService.findSongListByTagIdCount(Integer.parseInt(tagId)));
        if(pageUtil.getTotal() == 0){
            // 未查询到数据创建传输对象，存储类别信息
            SongListTagVo songListTagVo = new SongListTagVo();
            // 获取查询到的类别信息
            LinkedList<SongListTagVo> objects = new LinkedList<>();
            SongSheetType typeName = typeService.findTypeName(Integer.parseInt(tagId));
            // 转存到songListTagVo对象之中
            songListTagVo.setSongSheetTypeId(typeName==null?0:typeName.getSongSheetTypeId());
            songListTagVo.setSongSheetTypeName(typeName==null?"暂无":typeName.getSongSheetTypeName());
            songListTagVo.setSongList((List<SongListVo>) objects.clone());
            // 将对象存入创建的list集合之中
            objects.add(songListTagVo);
            pageUtil.setList(objects);
        }else{
            pageUtil.setList(songSheetService.findSongListByTagId(pageUtil,Integer.parseInt(tagId)));
        }
        return RETURN_UTIL.success(pageUtil);
    }

}
