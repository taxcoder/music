package com.tanx.music.service.singer;

import com.tanx.music.dao.SingerMapper;
import com.tanx.music.pojo.model.Singer;
import com.tanx.music.pojo.vo.SingerVo;
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
 * @date 2020/11/23 下午12:19
 */
@Service
public class SingerServiceImpl implements ISingerService{
    @Autowired
    private SingerMapper singerMapper;
    private static final Map<String,Object> MAP = new HashMap<>(10);

    @Override
    public List<SingerVo> recommendSinger() {
        return singerMapper.recommendSinger();
    }

    @Override
    public List<SingerVo> pageSinger(int regionId, int singerStatus, String abbreviation, PageUtil<SingerVo> page) {
        MAP.put("regionId",regionId);
        MAP.put("singerStatus",singerStatus);
        MAP.put("abbreviation",abbreviation);
        MAP.put("current",(page.getCurrent() - 1) * page.getLength());
        MAP.put("length", page.getLength());
        return singerMapper.pageSinger(MAP);
    }
    @Override
    public int pageSingerCount(int regionId, int singerStatus, String abbreviation, PageUtil<SingerVo> page) {
        MAP.put("regionId",regionId);
        MAP.put("singerStatus",singerStatus);
        MAP.put("abbreviation",abbreviation);
        MAP.put("current",(page.getCurrent() - 1) * page.getLength());
        MAP.put("length", page.getLength());
        Integer integer = singerMapper.pageSingerCount(MAP);
        return integer == null?0:integer;
    }


    @Override
    public List<SingerVo> pageSingerPopular(int regionId, int singerStatus, PageUtil<SingerVo> page) {
        MAP.put("regionId",regionId);
        MAP.put("singerStatus",singerStatus);
        MAP.put("current",(page.getCurrent() - 1) * page.getLength());
        MAP.put("length", page.getLength());
        return singerMapper.pageSingerPopular(MAP);
    }

    @Override
    public int pageSingerPopularCount(int regionId, int singerStatus, PageUtil<SingerVo> page) {
        MAP.put("regionId",regionId);
        MAP.put("singerStatus",singerStatus);
        MAP.put("current",(page.getCurrent() - 1) * page.getLength());
        MAP.put("length", page.getLength());
        Integer integer = singerMapper.pageSingerPopularCount(MAP);
        return  integer == null?0:integer;
    }

    @Override
    public SingerVo findSingerInfo(int singerId) {
        return singerMapper.findSingerInfo(singerId);
    }

    @Override
    public List<Singer> findAll() {
        return singerMapper.findAll();
    }

    @Override
    public int addSinger(Singer singer) {
        return singerMapper.addSinger(singer);
    }

    @Override
    public int updateSinger(Singer singer) {
        return singerMapper.updateSinger(singer);
    }

    @Override
    public List<SingerVo> findSingerName(String query) {
        Map<String, Object> map = new HashMap<>();
        map.put("query","%"+query+"%");
        return singerMapper.findSingerName(map);
    }

    @Override
    public int findSingerLength() {
        Integer singerLength = singerMapper.findSingerLength();
        return singerLength == null ? 0 : singerLength;
    }

    @Override
    public List<SongVo> findSingerByKeyword(PageUtil<SongVo> util, String keyword) {
        Map<String, Object> map = new HashMap<>(6);
        map.put("current", (util.getCurrent() - 1) * util.getLength());
        map.put("length", util.getLength());
        map.put("keyword", "%" + keyword + "%");
        return singerMapper.findSingerByKeyword(map);
    }

    @Override
    public int findSingerByKeywordCount(String keyword) {
        Integer singerByKeywordCount = singerMapper.findSingerByKeywordCount( "%" + keyword + "%");
        return singerByKeywordCount == null ? 0 : singerByKeywordCount;
    }
}
