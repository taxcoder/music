package com.tanx.music.controller.manager;

import com.tanx.music.pojo.model.Singer;
import com.tanx.music.service.affair.AffairServiceImpl;
import com.tanx.music.service.singer.SingerServiceImpl;
import com.tanx.music.utils.ConstantUtil;
import com.tanx.music.utils.ObtainHeadUtil;
import com.tanx.music.utils.PropertiesUtil;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/29 下午3:19
 */
@RestController
@RequestMapping("/admin/administrator")
public class SingerManagerController {

    @Autowired
    private SingerServiceImpl singerService;
    @Autowired
    private AffairServiceImpl affairService;

    private static final ReturnUtil RETURN_UTIL = ReturnUtil.getInstance();

    @GetMapping("/allSinger")
    public String allSinger(){
        return RETURN_UTIL.success(singerService.findAll());
    }

    @PostMapping("/addSingerInfo")
    public String addSinger(String name,String sex,String pic,String birth,String location,String introduction,String abbreviation){
        if(name == null || name.length() == 0){
            return RETURN_UTIL.error("歌手名称不能为空！");
        }
        if(sex == null || !sex.matches(ConstantUtil.MATCH) || Integer.parseInt(sex) <0 || Integer.parseInt(sex) >2){
            return RETURN_UTIL.error("性别输入错误！");
        }
        if(pic == null || pic.length() == 0 || (!pic.contains("data:image/jpg;base64,") && !pic.contains("data:image/jpeg;base64,") && !pic.contains("data:image/png;base64,"))){
            return RETURN_UTIL.error("歌手头像格式错误！");
        }
        if(birth == null || birth.length() == 0 || !birth.matches(ConstantUtil.MATCH)){
            return RETURN_UTIL.error("出生日期格式异常！");
        }
        if(location == null || !location.matches(ConstantUtil.MATCH) || Integer.parseInt(location) <1 || Integer.parseInt(location) >4){
            return RETURN_UTIL.error("地域输入错误！");
        }
        if(abbreviation == null || (!ConstantUtil.LETTER_WORD.contains(abbreviation) && !"#".equals(abbreviation))){
            return RETURN_UTIL.error("首字母异常！");
        }

        Singer singer = new Singer();
        singer.setSingerName(name);
        singer.setSingerDesc(introduction == null || introduction.length() == 0 ? null : introduction);
        singer.setAbbreviation(abbreviation);
        singer.setRegionId(Integer.parseInt(location));
        singer.setSingerBorn(new Timestamp(Long.parseLong(birth)));
        singer.setSingerStatus(Integer.parseInt(sex));
        try {
            singer.setSingerHeadImage(new ObtainHeadUtil().imageAddress(pic,"singer.prefix","singer.image",32));
        } catch (IOException e) {
            return RETURN_UTIL.error("图片上传异常！");
        }
        return singerService.addSinger(singer) == 0?RETURN_UTIL.error("增加失败！"):RETURN_UTIL.success("增加成功！");
    }

    @DeleteMapping("/deleteSinger")
    public String addSinger(String singerId){
        if(singerId == null || !singerId.matches(ConstantUtil.MATCH)){
            return RETURN_UTIL.error("Id信息错误！");
        }

        try {
            return RETURN_UTIL.success(affairService.deleteSinger(Long.parseLong(singerId)));
        } catch (Exception e) {
            return RETURN_UTIL.error(e.getMessage());
        }
    }

    @PutMapping("/updateSinger")
    public String updateSinger(String id,String name,String sex,String birth,String location,String introduction,String abbreviation){
        if(id == null || !id.matches(ConstantUtil.MATCH)){
            return RETURN_UTIL.error("Id信息错误！");
        }
        if(name == null || name.length() == 0){
            return RETURN_UTIL.error("歌手名称错误！");
        }
        if(sex == null || !sex.matches(ConstantUtil.MATCH) || Integer.parseInt(sex) <0 ||  Integer.parseInt(sex) > 2){
            return RETURN_UTIL.error("歌手性别错误！");
        }
        if(birth == null || !birth.matches(ConstantUtil.MATCH)){
            return RETURN_UTIL.error("歌手出生日期错误！");
        }
        if(location == null || !location.matches(ConstantUtil.MATCH) || Integer.parseInt(location) <1 ||  Integer.parseInt(location) >4){
            return RETURN_UTIL.error("地域信息错误！");
        }
        if(abbreviation == null || (!ConstantUtil.LETTER_WORD.contains(abbreviation) && !"#".equals(abbreviation))){
            return RETURN_UTIL.error("首字母异常！");
        }
        Singer singer = new Singer();
        singer.setSingerId(Long.parseLong(id));
        singer.setSingerName(name);
        singer.setSingerStatus(Integer.parseInt(sex));
        singer.setSingerBorn(new Timestamp(Long.parseLong(birth)));
        singer.setRegionId(Integer.parseInt(location));
        singer.setSingerDesc(introduction == null || introduction.length()==0 ? null : introduction);
        singer.setAbbreviation(abbreviation);
        return singerService.updateSinger(singer) == 0? RETURN_UTIL.error("修改失败"):RETURN_UTIL.success("修改成功!");
    }
}
