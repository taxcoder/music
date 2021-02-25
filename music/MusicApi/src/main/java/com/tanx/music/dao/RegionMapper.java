package com.tanx.music.dao;

import com.tanx.music.pojo.model.Region;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/24 上午8:53
 */
@Mapper
@Repository
public interface RegionMapper {


    /**
     * 查询所有的数据
     * @return 返回所有的地域信息
     */
    public List<Region> findAll();
}
