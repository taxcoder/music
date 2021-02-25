package com.tanx.music.service.types;

import com.tanx.music.dao.TypesMapper;
import com.tanx.music.pojo.model.BigType;
import com.tanx.music.pojo.model.SongSheetType;
import com.tanx.music.utils.DataConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/24 下午6:28
 */
@Service
public class TypeServiceImpl implements ITypesService{

    @Autowired
    private TypesMapper typesMapper;

    @Override
    public List<BigType> findAll() {
        return typesMapper.findAll();
    }

    @Override
    public  Map<String, List<SongSheetType>> findTypeAll() throws CloneNotSupportedException {
        List<Map<String, Object>> typeAll = typesMapper.findTypeAll();
        DataConversionUtil conversionUtil = new DataConversionUtil();
        return conversionUtil.listConversionMap(typeAll, "bigTypeName",findAll(),"songSheetTypeId","songSheetTypeName");
    }

    @Override
    public SongSheetType findTypeName(int songSheetTypeId){
        return typesMapper.findTypeName(songSheetTypeId);
    }
}
