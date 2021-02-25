package com.tanx.music.utils;

import com.tanx.music.pojo.model.BigType;
import com.tanx.music.pojo.model.SongSheetType;

import java.util.*;

/**
 * 
 * 数据转换
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/24 下午7:21
 */
@SuppressWarnings("unchecked")
public class DataConversionUtil {

    /**
     *    List转Map
     */
    public Map<String, List<SongSheetType>> listConversionMap(List<Map<String, Object>> lists, String key, List<BigType> all,String ...values) throws CloneNotSupportedException {
        SongSheetType type = new SongSheetType();
        ArrayList<SongSheetType> types = new ArrayList<>();
        Map<String, List<SongSheetType>> mapData = new HashMap<>(all.size() * 2);
        for(BigType map:all){
            ArrayList<SongSheetType> clone = (ArrayList<SongSheetType>) types.clone();
            String bigTypeName = map.getBigTypeName();
            for(Map<String, Object> list:lists){
                if(bigTypeName.equals(list.get(key))){
                    SongSheetType songSheetType = (SongSheetType) type.cloneTarget();
                    songSheetType.setSongSheetTypeId((Long) list.get(values[0]));
                    songSheetType.setSongSheetTypeName((String) list.get(values[1]));
                    clone.add(songSheetType);
                }
            }
            mapData.put(bigTypeName,clone);
        }
        return mapData;
    }
}
