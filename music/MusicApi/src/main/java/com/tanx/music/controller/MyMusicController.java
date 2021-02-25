package com.tanx.music.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午5:48
 */
@RestController
@RequestMapping("/myMusic")
public class MyMusicController {

    @GetMapping("/data")
    public String data(){
        return "data";
    }

}
