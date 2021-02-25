package com.tanx.music;
import com.tanx.music.dao.SingerLinkSongMapper;
import com.tanx.music.dao.SongMapper;
import com.tanx.music.dao.TypesMapper;
import com.tanx.music.pojo.model.CollectionSong;
import com.tanx.music.pojo.model.Singer;
import com.tanx.music.pojo.model.User;
import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.service.admin.AdminServiceImpl;
import com.tanx.music.service.affair.AffairServiceImpl;
import com.tanx.music.service.affair.IAffairService;
import com.tanx.music.service.collectionSong.ICollectionSong;
import com.tanx.music.service.region.RegionServiceImpl;
import com.tanx.music.service.role.RoleServiceImpl;
import com.tanx.music.service.singer.SingerServiceImpl;
import com.tanx.music.service.singerlinksong.SingerLinkSongServiceImpl;
import com.tanx.music.service.song.SongServiceImpl;
import com.tanx.music.service.songList.SongSheetServiceImpl;
import com.tanx.music.service.songSheet.SongSheetInfoServiceImpl;
import com.tanx.music.service.types.TypeServiceImpl;
import com.tanx.music.service.user.UserServiceImpl;
import com.tanx.music.utils.*;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
@MapperScan("com.tanx.music.dao")
class MusicServerApplicationTests {

    @Autowired
    private SingerServiceImpl singerService;
    @Autowired
    private SongServiceImpl songService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private RegionServiceImpl regionService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private TypeServiceImpl typeService;
    @Autowired
    private SongSheetInfoServiceImpl songSheetInfoService;
    @Autowired
    private SongSheetServiceImpl songSheetService;
    @Autowired
    private SingerLinkSongMapper singerLinkSongMapper;
    @Autowired
    private SongMapper songMapper;
    @Autowired
    private TypesMapper typesMapper;
    @Autowired
    private AffairServiceImpl service;
    @Autowired
    private ICollectionSong collectionSong;

    @Test
    void contextLoads() throws IOException, CloneNotSupportedException {

    }
}
