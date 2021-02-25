package com.tanx.music.controller;

import com.tanx.music.pojo.vo.SingerVo;
import com.tanx.music.service.region.RegionServiceImpl;
import com.tanx.music.service.singer.SingerServiceImpl;
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
 * @date 2020/11/20 下午5:47
 */
@RestController
@RequestMapping("/singers")
public class SingersController {

    private static final String POPULAR = "流行";
    private static final ReturnUtil RETURN_UTIL = ReturnUtil.getInstance();

    @Autowired
    private SingerServiceImpl singerService;

    @Autowired
    private RegionServiceImpl regionService;

    @GetMapping("/allSinger")
    public String getSingers(String regionId,String singerStatus,String abbreviation,String current){
        if(!regionId.matches(ConstantUtil.MATCH) ||
                !singerStatus.matches(ConstantUtil.MATCH) ||
                !current.matches(ConstantUtil.MATCH)){
            throw new RuntimeException("传入的格式不正确！");
        }
        List<SingerVo> singerVos;
        int total;
        PageUtil<SingerVo> page = new PageUtil<>();
        page.setCurrent(Integer.parseInt(current));
        page.setLength(ConstantUtil.LIMIT_LENGTH);
        if(POPULAR.equals(abbreviation)){
            singerVos = singerService.pageSingerPopular(Integer.parseInt(regionId), Integer.parseInt(singerStatus), page);
            total = singerService.pageSingerPopularCount(Integer.parseInt(regionId), Integer.parseInt(singerStatus), page);
        }else{
            singerVos = singerService.pageSinger(Integer.parseInt(regionId), Integer.parseInt(singerStatus), abbreviation.toLowerCase(), page);
            total = singerService.pageSingerCount(Integer.parseInt(regionId), Integer.parseInt(singerStatus), abbreviation.toLowerCase(), page);
        }
        page.setList(singerVos);
        page.setTotal(total);
        return RETURN_UTIL.success(page);
    }

    @GetMapping("/region")
    public String getRegion(){
        return RETURN_UTIL.success(regionService.findAll());
    }
}
