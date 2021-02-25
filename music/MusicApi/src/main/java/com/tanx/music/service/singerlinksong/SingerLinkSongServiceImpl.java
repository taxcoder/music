package com.tanx.music.service.singerlinksong;

import com.tanx.music.dao.SingerLinkSongMapper;
import com.tanx.music.pojo.vo.SongVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/25 下午5:18
 */
@Service
public class SingerLinkSongServiceImpl implements ISingerLinkSongService {

    @Autowired
    private SingerLinkSongMapper singerLinkSongMapper;

    @Override
    public List<SongVo> findSingerSong(int singerId, int current, int length){
        Map<String, Object> map = new HashMap<>(6);
        map.put("singerId",singerId);
        map.put("current",(current - 1) * length);
        map.put("length",length);
        return singerLinkSongMapper.findSingerSong(map);
    }

    @Override
    public int findSingerSongCount(int singerId){
        Integer singerSongCount = singerLinkSongMapper.findSingerSongCount(singerId);
        return  singerSongCount == null ? 0 : singerSongCount;
    }
}
