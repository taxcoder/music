package com.tanx.music.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据缓存
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/23 下午12:20
 */
public class MapCacheUtil {

    private static final Map<String,Object> MAP = new HashMap<>();
    private MapCacheUtil(){}

    public void push(String key,String value) throws Exception {
        if(key == null || value == null){
            throw new Exception("参数错误！");
        }
        MAP.put(key,value);
    }

    public Object get(String key) throws Exception {
        if(key == null){
            throw new Exception("参数错误！");
        }
        return MAP.get(key);
    }

    static class Cache{
        private static final MapCacheUtil MAP_CACHE_UTIL = new MapCacheUtil();
    }

    public static MapCacheUtil getInstance(){
        return Cache.MAP_CACHE_UTIL;
    }
}
