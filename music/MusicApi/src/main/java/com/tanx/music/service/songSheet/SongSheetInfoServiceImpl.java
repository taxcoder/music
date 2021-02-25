package com.tanx.music.service.songSheet;

import com.tanx.music.dao.SongSheetInfoMapper;
import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SongSheetVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.utils.PageUtil;
import com.tanx.music.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/26 下午3:12
 */
@Service
public class SongSheetInfoServiceImpl implements ISongSheetInfoService{

    @Autowired
    private SongSheetInfoMapper songSheetInfoMapper;

    @Override
    public List<SongSheetVo> findSongSheet(String songSheetId) {
        return songSheetInfoMapper.findSongSheet(songSheetId);
    }

    @Override
    public List<SongVo> findSongSheetLinkSong(String songSheetId, int current, int length){
        Map<String,Object> map = new HashMap<>(6);
        map.put("songSheetId",songSheetId);
        map.put("current",(current - 1) * length);
        map.put("length",length);
        return songSheetInfoMapper.findSongSheetLinkSong(map);
    }

    @Override
    public int findSongSheetLinkSongCount(String songSheetId) {
        Integer songSheetLinkSongCount = songSheetInfoMapper.findSongSheetLinkSongCount(songSheetId);
        return songSheetLinkSongCount == null ? 0 : songSheetLinkSongCount;
    }

    @Override
    public int addSongSheetLinkSong(String songSheetId, String songId) {
        return songSheetInfoMapper.addSongSheetLinkSong(UuidUtil.uuidComplete(),songSheetId,songId);
    }
}
