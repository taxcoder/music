package com.tanx.music.controller.manager;

import com.tanx.music.service.affair.AffairServiceImpl;
import com.tanx.music.service.singer.SingerServiceImpl;
import com.tanx.music.service.song.SongServiceImpl;
import com.tanx.music.service.songList.SongSheetServiceImpl;
import com.tanx.music.service.songSheet.SongSheetInfoServiceImpl;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/12/1 下午12:35
 */
@RestController
@RequestMapping("/admin/administrator")
public class SongListManagerController {
    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private AffairServiceImpl affairService;
    @Autowired
    private SingerServiceImpl singerService;
    @Autowired
    private SongSheetServiceImpl songSheetService;
    @Autowired
    private SongSheetInfoServiceImpl songSheetInfoService;

    private static final ReturnUtil RETURN_UTIL = ReturnUtil.getInstance();

    @GetMapping("/allSheetLinkSong")
    public String allSongSheet(String songListId){
        if(songListId == null){
            return RETURN_UTIL.error("参数异常！");
        }

        return RETURN_UTIL.success(songService.findSongLinkSheet(songListId));
    }


    @DeleteMapping("/deleteSheetLinkSong")
    public String deleteSheetLinkSong(String songId){
        try {
            return RETURN_UTIL.success(affairService.deleteSong(songId));
        } catch (Exception e) {
            return RETURN_UTIL.error(e.getMessage());
        }
    }

    @PostMapping("/addSheetLinkSong")
    public String addSheetLinkSong(String songListId,String songId){
        if(songListId == null || songId == null){
            return RETURN_UTIL.error("参数格式错误！");
        }

        if(songService.findSongById(songId) == null){
            return RETURN_UTIL.error("歌曲不存在！");
        }

        if(songSheetService.findSongListById(songListId) == null){
            return RETURN_UTIL.error("歌曲不存在！");
        }

        if(songSheetInfoService.addSongSheetLinkSong(songListId,songId) == 0){
            return RETURN_UTIL.error("歌曲加入歌单失败！");
        }
        return RETURN_UTIL.success("歌曲加入歌单成功！");
    }

    @GetMapping("/querySinger")
    public String querySinger(String query){
        if(query == null){
            return RETURN_UTIL.success("");
        }
        return RETURN_UTIL.success( singerService.findSingerName(query));
    }

    @GetMapping("/querySong")
    public String querySinger(String query,Integer singerId){
        if(query == null){
            return RETURN_UTIL.success("");
        }
        return RETURN_UTIL.success( songService.findSongName(query,singerId));
    }
}
