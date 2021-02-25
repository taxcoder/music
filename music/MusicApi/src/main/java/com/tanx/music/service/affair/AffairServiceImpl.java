package com.tanx.music.service.affair;

import com.tanx.music.dao.*;
import com.tanx.music.pojo.model.Song;
import com.tanx.music.pojo.model.SongList;
import com.tanx.music.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/30 下午4:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AffairServiceImpl implements IAffairService{

    @Autowired
    private SongMapper songMapper;
    @Autowired
    private TypesMapper typesMapper;
    @Autowired
    private SingerMapper singerMapper;
    @Autowired
    private SongSheetMapper songSheetMapper;
    @Autowired
    private SingerLinkSongMapper singerLinkSongMapper;

    @Override
    public String addSongLinkSinger(Song song, int singerId) throws Exception {
        if(songMapper.addSong(song) == 0){
            throw new Exception("歌曲增加失败！");
        }
        if(singerMapper.findSingerInfo(singerId) == null){
            throw new Exception("歌手查询错误！");
        }
        if(singerLinkSongMapper.addSingerLinkSong(singerId,song.getSongId(), UuidUtil.uuidComplete()) == 0){
            throw new Exception("歌曲和歌手链接失败！");
        }
        return "增加成功！";
    }

    @Override
    public String deleteSinger(Long singerId) throws Exception {
        List<String> singerSongAll = singerLinkSongMapper.findSingerSongAll(singerId);
        if(singerSongAll.size() != 0 && songMapper.deleteSongs(singerSongAll) == 0){
            throw new Exception("歌曲删除失败！");
        }
        if(singerSongAll.size() != 0 && singerLinkSongMapper.deleteSingerLink(singerId) == 0){
            throw new Exception("连接删除失败！");
        }
        if(singerMapper.deleteSinger(singerId) == 0){
            throw new Exception("歌手删除失败！");
        }
        return "删除成功！";
    }

    @Override
    public String deleteSong(String songId) throws Exception {
        if(songMapper.findSongById(songId) == null){
            throw new Exception("未查询到此歌曲！");
        }

        if(singerLinkSongMapper.deleteSongLink(songId) == 0){
            throw new Exception("链接删除失败！");
        }

        if(songMapper.deleteSong(songId) == 0){
            throw new Exception( "删除失败！");
        }

        return "删除成功！";
    }

    @Override
    public String deleteSongAndSheet(String songId) throws Exception {
        if(songMapper.findSongById(songId) == null){
            throw new Exception("未查询到此歌曲！");
        }

        if(songSheetMapper.deleteSongSheet(songId) == 0){
            throw new Exception("链接删除失败！");
        }

        if(songMapper.deleteSong(songId) == 0){
            throw new Exception( "删除失败！");
        }

        return "删除成功！";
    }


    @Override
    public String addSongList(SongList songList, List<Integer> typeId) throws Exception {
        if(songSheetMapper.addSongList(songList) == 0){
            throw new Exception("歌单增加失败！");
        }
        if(typesMapper.findTypeBySongSheetTypeId(typeId).size() != typeId.size()){
            throw new Exception("类别不一致！");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Integer integer : typeId) {
            Map<String, Object> map = new HashMap<>(6);
            map.put("linkId", UuidUtil.uuidComplete());
            map.put("songListId", songList.getSongListId());
            map.put("songSheetTypeId", integer);
            list.add(map);
        }
        if(typesMapper.addLinkSongList(list) == 0){
            throw new Exception("歌单与类别连接失败！");
        }
        return "增加成功！";
    }

    @Override
    public String deleteSongSheet(String songSheetId) throws Exception {
        List<String> songSheetLinkSong = songSheetMapper.findSongSheetLinkSong(songSheetId);
        if(songSheetLinkSong.size() != 0 && songMapper.deleteSongList(songSheetLinkSong) == 0){
            throw new Exception("歌单内歌曲删除失败！");
        }

        if(songSheetMapper.deleteSongSheet(songSheetId) == 0){
            throw new Exception("歌单删除失败！");
        }
        return "删除成功！";
    }

    @Override
    public String updateSongSheet(String songListId, String songListName, String songListDesc, List<Integer> types) throws Exception {
        if(songSheetMapper.updateSongSheet(songListId,songListName,songListDesc)==0){
            throw new Exception("歌单修改失败！");
        }

        if(typesMapper.deleteTypes(songListId) == 0){
            throw new Exception("歌单链接修改失败！");
        }

        List<Map<String, Object>> list = new ArrayList<>();
        for (Integer type : types) {
            Map<String, Object> map = new HashMap<>(6);
            map.put("linkId", UuidUtil.uuidComplete());
            map.put("songListId", songListId);
            map.put("songSheetTypeId", type);
            list.add(map);
        }
        if(typesMapper.addLinkSongList(list) == 0){
            throw new Exception("歌单与类别链接失败！");
        }
        return "修改成功！";
    }
}
