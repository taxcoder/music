package com.tanx.music.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回信息工具类
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午2:26
 */
public class ReturnUtil {

    private static Map<String,Object> map = null;

    private ReturnUtil(){
        map = new HashMap<>(16);
    }

    static class Return{
        private static final ReturnUtil RETURN_UTIL = new ReturnUtil();
    }

    public String success(Object data,String message){
        map.put("code",1);
        map.put("data", JSONArray.toJSONString(data));
        map.put("message",message);
        return JSON.toJSONString(map);
    }

    public String success(String message){
        return success(null,message);
    }

    public String success(Object data){
        return success(data,null);
    }

    public String error(String message){
        map.put("code",0);
        map.put("data", null);
        map.put("message",message);
        return JSON.toJSONString(map);
    }

    public static ReturnUtil getInstance(){
        return Return.RETURN_UTIL;
    }

}
