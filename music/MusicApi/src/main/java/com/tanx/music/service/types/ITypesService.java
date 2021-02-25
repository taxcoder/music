package com.tanx.music.service.types;

import com.tanx.music.pojo.model.BigType;
import com.tanx.music.pojo.model.SongSheetType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/24 下午6:19
 */
@Service
public interface ITypesService {

    /**
     * 查询所有的大类别
     * @return 返回所有的大类别
     */
    public List<BigType> findAll();

    /**
     * 查询所有的二级类别
     * @return 返回所有的数据
     */
    public Map<String, List<SongSheetType>> findTypeAll() throws CloneNotSupportedException;

    /**
     * 获取类别的信息
     * @param songSheetTypeId 类别ID
     * @return 返回类别信息
     */
    public SongSheetType findTypeName(int songSheetTypeId);
}
