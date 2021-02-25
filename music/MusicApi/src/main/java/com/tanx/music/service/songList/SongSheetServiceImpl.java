package com.tanx.music.service.songList;

import com.tanx.music.dao.SongSheetMapper;
import com.tanx.music.pojo.model.SongList;
import com.tanx.music.pojo.vo.SongListTagVo;
import com.tanx.music.pojo.vo.SongListVo;
import com.tanx.music.pojo.vo.SongSheetVo;
import com.tanx.music.pojo.vo.SongVo;
import com.tanx.music.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/22 下午9:28
 */
@Service
public class SongSheetServiceImpl implements ISongSheetService {

    @Autowired
    private SongSheetMapper songSheetMapper;
    private static Map<String, Object> map;

    @Override
    public List<SongListVo> findSongList(int current, int length) {
        map = new HashMap<>(4);
        map.put("current",current);
        map.put("length",length);
        return songSheetMapper.findSongList(map);
    }

    @Override
    public int findSongListCount() {
        Integer songListCount = songSheetMapper.findSongListCount();
        return songListCount==null?0:songListCount;
    }

    @Override
    public List<SongListTagVo> findSongListByTagId(PageUtil<SongListTagVo> pageUtil, int songSheetTypeId) {
        map = new HashMap<>(6);
        map.put("current",pageUtil.getCurrent());
        map.put("length",pageUtil.getLength());
        map.put("songSheetTypeId",songSheetTypeId);
        return songSheetMapper.findSongListByTagId(map);
    }

    @Override
    public int findSongListByTagIdCount(int songSheetTypeId) {
        Integer songListByTagIdCount = songSheetMapper.findSongListByTagIdCount(songSheetTypeId);
        return songListByTagIdCount == null ? 0: songListByTagIdCount;
    }

    @Override
    public List<SongSheetVo> findAllSongSheet() {
        return songSheetMapper.findAllSongSheet();
    }

    @Override
    public List<SongList> findSongListById(String songListId) {
        return songSheetMapper.findSongListById(songListId);
    }

    @Override
    public List<SongListVo> findNewSongList(int current, int length) {
        map = new HashMap<>(4);
        map.put("current",(current - 1) * length);
        map.put("length",length);
        return songSheetMapper.findNewSongList(map);
    }

    @Override
    public List<SongListVo> findRecommendSongList(int current, int length) {
        map = new HashMap<>(4);
        map.put("current",(current - 1) * length);
        map.put("length",length);
        return songSheetMapper.findRecommendSongList(map);
    }

    @Override
    public int finSongSheetLength() {
        Integer integer = songSheetMapper.finSongSheetLength();
        return integer == null ? 0 : integer;
    }

    @Override
    public List<SongListVo> findSongSheetByKeyword(PageUtil<SongListVo> util, String keyword) {
        Map<String, Object> map = new HashMap<>(6);
        map.put("current", (util.getCurrent() - 1) * util.getLength());
        map.put("length", util.getLength());
        map.put("keyword", "%" + keyword + "%");
        return songSheetMapper.findSongSheetByKeyword(map);
    }

    @Override
    public int findSongSheetByKeywordCount(String keyword) {
        Integer singerByKeywordCount = songSheetMapper.findSongSheetByKeywordCount( "%" + keyword + "%");
        return singerByKeywordCount == null ? 0 : singerByKeywordCount;
    }
}
