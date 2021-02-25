package com.tanx.music.controller.manager;

import com.tanx.music.pojo.model.SongList;
import com.tanx.music.service.affair.AffairServiceImpl;
import com.tanx.music.service.songList.SongSheetServiceImpl;
import com.tanx.music.utils.ConstantUtil;
import com.tanx.music.utils.ObtainHeadUtil;
import com.tanx.music.utils.ReturnUtil;
import com.tanx.music.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/30 下午7:32
 */
@RestController
@RequestMapping("/admin/administrator")
public class SongSheetManagerController {

    @Autowired
    private SongSheetServiceImpl songSheetService;
    @Autowired
    private AffairServiceImpl affairService;

    private static final ReturnUtil RETURN_UTIL = ReturnUtil.getInstance();

    @GetMapping("/allSongSheet")
    public String allSongSheet(){
        return RETURN_UTIL.success(songSheetService.findAllSongSheet());
    }

    @PutMapping("/updateSongSheet")
    public String updateSongSheet(String songSheetId,String songListName,String songListDesc,@RequestParam(value = "typeIds") List<Integer> typeIds){
        if(songSheetId == null || songListName == null || typeIds == null){
            return RETURN_UTIL.error("参数格式错误！");
        }
        if(songListName.length() == 0){
            return RETURN_UTIL.error("歌单名为空！");
        }
        if(typeIds.size() == 0){
            return RETURN_UTIL.error("类别为空！");
        }

        try {
            return RETURN_UTIL.success(affairService.updateSongSheet(songSheetId,songListName,songListDesc,typeIds));
        } catch (Exception e) {
            return RETURN_UTIL.error(e.getMessage());
        }

    }

    @DeleteMapping("/deleteSongSheet")
    public String deleteSongSheet(String songSheetId){
        try {
            return RETURN_UTIL.success(affairService.deleteSongSheet(songSheetId));
        } catch (Exception e) {
            return RETURN_UTIL.error(e.getMessage());
        }
    }

    @PostMapping("/addSongSheet")
    public String addSongSheet(String songListName,@RequestParam(value = "value", required = true) List<Integer> value, String author, String desc, String image){
        if(songListName == null || author == null || image == null){
            return RETURN_UTIL.error("参数格式错误！");
        }
        if(songListName.length() == 0){
            return RETURN_UTIL.error("参数格式错误！");
        }
        if(value.size() == 0){
            return RETURN_UTIL.error("类别错误！");
        }
        if(author.length() == 0){
            return RETURN_UTIL.error("作者为空！");
        }
        if(image.length() == 0){
            return RETURN_UTIL.error("封面错误！");
        }
        SongList songList = new SongList();
        songList.setAuthor(author);
        songList.setSongListName(songListName);
        songList.setSongListId(UuidUtil.uuidComplete());
        songList.setSongListDesc(desc == null || desc.length() == 0 ? null : desc);
        try {
            songList.setSongListImagesAddress(new ObtainHeadUtil().imageAddress(image,"songSheet.prefix","songSheet.image",32));
        } catch (IOException e) {
            return RETURN_UTIL.error("封面转换错误！");
        }
        try {
           return RETURN_UTIL.success(affairService.addSongList(songList,value));
        } catch (Exception e) {
            return RETURN_UTIL.error(e.getMessage());
        }
    }
}
