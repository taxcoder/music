package com.tanx.music.controller.manager;

import com.tanx.music.service.singer.SingerServiceImpl;
import com.tanx.music.service.song.SongServiceImpl;
import com.tanx.music.service.songList.SongSheetServiceImpl;
import com.tanx.music.service.user.UserServiceImpl;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/12/1 下午10:19
 */
@RestController
@RequestMapping("/admin/administrator")
public class IndexManagerController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private SongSheetServiceImpl songSheetService;
    @Autowired
    private SingerServiceImpl singerService;
    private static final ReturnUtil RETURN_UTIL = ReturnUtil.getInstance();

    @GetMapping("/userLength")
    public String addUserLength(){
        return RETURN_UTIL.success(userService.findUserLength());
    }

    @GetMapping("/songLength")
    public String addSongLength(){
        return RETURN_UTIL.success(songService.findSongLength());
    }

    @GetMapping("/singerLength")
    public String addSingerLength(){
        return RETURN_UTIL.success(singerService.findSingerLength());
    }

    @GetMapping("/songSheetLength")
    public String addSongSheetLength(){
        return RETURN_UTIL.success(songSheetService.finSongSheetLength());
    }
}
