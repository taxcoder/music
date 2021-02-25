package com.tanx.music.controller;

import com.tanx.music.service.song.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/26 下午8:44
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private SongServiceImpl songService;

    @PostMapping("/song")
    public String address(String songId){
        return songService.findSongAddress(songId);
    }
}
