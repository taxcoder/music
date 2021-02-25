package com.tanx.music.controller.manager;

import com.tanx.music.pojo.model.Song;
import com.tanx.music.service.affair.AffairServiceImpl;
import com.tanx.music.service.song.SongServiceImpl;
import com.tanx.music.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ws.schild.jave.EncoderException;

import java.io.IOException;
import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/29 下午10:55
 */
@RestController
@RequestMapping("/admin/administrator")
public class SongSingerMangerController {

    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private AffairServiceImpl affairService;
    
    private static final ReturnUtil RETURN_UTIL = ReturnUtil.getInstance();


    @GetMapping("/allSong")
    public String allSong(String singerId){
        if(singerId == null || !singerId.matches(ConstantUtil.MATCH)){
            return RETURN_UTIL.error("歌手信息错误！");
        }
        return RETURN_UTIL.success(songService.findSongSinger(Integer.parseInt(singerId)));
    }

    @PostMapping("/addSong")
    public String addSong(String singerId,String songName,String regionId,String lyric,String image,String audio){
        if(singerId == null || songName == null || regionId == null || image == null || audio == null){
            return RETURN_UTIL.error("参数格式错误!"); }
        if(!singerId.matches(ConstantUtil.MATCH) || !regionId.matches(ConstantUtil.MATCH)){
            return RETURN_UTIL.error("参数格式错误!"); }
        if(songName.length() == 0){
            return RETURN_UTIL.error("歌名不能为空!"); }
        if(image.length() == 0){
            return RETURN_UTIL.error("封面不能为空!"); }
        if(audio.length() == 0){
            return RETURN_UTIL.error("音频不能为空!"); }
        if(Integer.parseInt(regionId) <1 || Integer.parseInt(regionId) > 4){
            return RETURN_UTIL.error("地域错误!"); }
        Song song = new Song();
        song.setSongId(UuidUtil.uuidComplete());
        song.setSongName(songName);
        song.setRegionId(Integer.parseInt(regionId));
        try {
            song.setSongImages(new ObtainHeadUtil().imageAddress(image,"song.prefix","song.image",16));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<Object> address = new AnalysisAudioUtil().depositAddress(audio,"audio.prefix","audio.address");
            song.setSongAddress((String) address.get(0));
            song.setSongLength((Integer) address.get(1));
        } catch (IOException e) {
            return RETURN_UTIL.error("音频转换错误!");
        } catch (EncoderException e) {
            return RETURN_UTIL.error("音频时长读取失败!");
        }
        try {
           return RETURN_UTIL.success(affairService.addSongLinkSinger(song,Integer.parseInt(singerId)));
        } catch (Exception e) {
            return RETURN_UTIL.error(e.getMessage());
        }
    }

    @DeleteMapping("/deleteSong")
    public String deleteSong(String songId){
        try {
            return RETURN_UTIL.success(affairService.deleteSong(songId));
        } catch (Exception e) {
            return RETURN_UTIL.error(e.getMessage());
        }
    }

    @PutMapping("/updateSong")
    public String updateSong(String songId,String songName,String regionId,String lyric){
        if(songId == null || songName == null || regionId == null || lyric == null){
            return RETURN_UTIL.error("参数错误！");
        }

        if(songName.length() == 0){
            return RETURN_UTIL.error("歌曲名称不能为空!");
        }

        if(!regionId.matches(ConstantUtil.MATCH) || Integer.parseInt(regionId) < 1 || Integer.parseInt(regionId) > 4){
            return RETURN_UTIL.error("地域输入错误！!");
        }

        return songService.updateSong(songId,songName,Integer.parseInt(regionId),lyric) == 0 ? RETURN_UTIL.error("修改失败！"):RETURN_UTIL.success("修改成功！");
    }
}
