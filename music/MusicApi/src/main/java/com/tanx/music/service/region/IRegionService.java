package com.tanx.music.service.region;

import com.tanx.music.pojo.model.Region;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/24 上午8:57
 */
@Service
public interface IRegionService {


    /**
     * 查询所有的数据
     * @return 返回所有的地域信息
     */
    public List<Region> findAll();

}
