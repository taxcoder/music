package com.tanx.music.utils;

import com.tanx.music.utils.entry.MapTimer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Map数据定时器
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午10:20
 */
public class MapTimerUtil {
    /** 超时时间 */
    private static int timeout;

    private static Map<String,MapTimer> map = null;
    private static MapTimer timer = null;

    private MapTimerUtil(){
        map = new HashMap<>();
        timer = new MapTimer(1800,new Date(),"");
    }

    static class Timer{
        private static final MapTimerUtil MAP_TIMER_UTIL = new MapTimerUtil();
    }

    public void put(String key,Object value) throws CloneNotSupportedException {
        if(key == null || "".equals(key)){
            throw new RuntimeException("key值不能为空！");
        }
        MapTimer mapTimer = (MapTimer) timer.cloneTarget();
        mapTimer.setTimeout(timeout);
        mapTimer.setStore(new Date());
        mapTimer.setData(value);
        map.put(key, mapTimer);
    }

    public Object get(String key) {
        if(key == null || "".equals(key)){
            throw new RuntimeException("key值不能为空！");
        }
        MapTimer mapTimer = map.get(key);
        if(mapTimer == null){
            return null;
        }else{
            int timeout = mapTimer.getTimeout();
            long store = mapTimer.getStore().getTime();
            long time = System.currentTimeMillis();
            if(time - store > timeout){
                return null;
            }
            return mapTimer.getData();
        }
    }

    public void remove(String key){
        if(key == null || "".equals(key)){
            throw new RuntimeException("key值不能为空！");
        }

        map.remove(key);
    }

    public static MapTimerUtil getInstance(int time) {
        timeout = time;
        return Timer.MAP_TIMER_UTIL;
    }

    public static MapTimerUtil getInstance() {
        String timer = PropertiesUtil.getInstance().get("map.timer");
        if(timer.matches(ConstantUtil.MATCH)){
            return getInstance(Integer.parseInt(timer));
        }
        return getInstance(1800000);
    }
}
