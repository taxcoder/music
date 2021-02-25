package com.tanx.music.service.song;

import com.tanx.music.dao.SongMapper;
import com.tanx.music.pojo.model.Song;
import com.tanx.music.pojo.vo.ReturnSongVo;
import com.tanx.music.pojo.vo.SheetSongVo;
import com.tanx.music.pojo.vo.SingerSongVo;
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
 * @date 2020/11/23 下午12:00
 */
@Service
public class SongServiceImpl implements ISongService {

    @Autowired
    private SongMapper songMapper;

    private static Map<String, Object> map;

    @Override
    public List<SongVo> findSong(int regionId) {
        return songMapper.findSong(regionId);
    }

    @Override
    public List<SongVo> findNewSong() {
        return songMapper.findNewSong();
    }

    @Override
    public String findSongAddress(String songId) {
        return songMapper.findSongAddress(songId);
    }

    @Override
    public List<SingerSongVo> findSongSinger(int singerId) {
        return songMapper.findSongSinger(singerId);
    }

    @Override
    public int updateSong(String songId, String songName, int regionId, String lyric) {
        Map<String, Object> map = new HashMap<>();
        map.put("songId", songId);
        map.put("songName", songName);
        map.put("regionId", regionId);
        map.put("lyric", lyric == null || lyric.length() == 0 ? null : lyric);
        return songMapper.updateSong(map);
    }

    @Override
    public List<SheetSongVo> findSongLinkSheet(String songSheetId) {
        return songMapper.findSongLinkSheet(songSheetId);
    }

    @Override
    public List<SongVo> findSongName(String query, Integer singerId) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("query", "%" + query + "%");
        map.put("singerId", singerId);
        return songMapper.findSongName(map);
    }

    @Override
    public Song findSongById(String songId) {
        return songMapper.findSongById(songId);
    }

    @Override
    public int findSongLength() {
        Integer songLength = songMapper.findSongLength();
        return songLength == null ? 0 : songLength;
    }

    @Override
    public ReturnSongVo querySongById(String songId) {
        return songMapper.querySongById(songId);
    }

    @Override
    public List<SongVo> findSongByKeyword(PageUtil<SongVo> util, String keyword) {
        Map<String, Object> map = new HashMap<>(6);
        map.put("current", (util.getCurrent() - 1) * util.getLength());
        map.put("length", util.getLength());
        map.put("keyword", "%" + keyword + "%");
        return songMapper.findSongByKeyword(map);
    }

    @Override
    public int findSongByKeywordCount(String keyword) {
        Integer songByKeywordCount = songMapper.findSongByKeywordCount( "%" + keyword + "%");
        return songByKeywordCount == null ? 0 : songByKeywordCount;
    }
}
