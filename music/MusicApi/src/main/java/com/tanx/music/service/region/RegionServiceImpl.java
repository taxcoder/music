package com.tanx.music.service.region;

import com.tanx.music.dao.RegionMapper;
import com.tanx.music.pojo.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/24 上午8:58
 */
@Service
public class RegionServiceImpl implements IRegionService{

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> findAll() {
        return regionMapper.findAll();
    }
}
