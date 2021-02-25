package com.tanx.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author tan
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.tanx.music.dao")
public class MusicServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicServerApplication.class, args);
    }

}
