package com.tanx.music.utils;

import java.util.UUID;

/**
 * uuid生成器
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/6 下午1:47
 */
public class UuidUtil {

    private UuidUtil(){}

    static class Uuid{
        private static final UuidUtil UUID = new UuidUtil();
    }

    private static UuidUtil getInstance(){
        return Uuid.UUID;
    }

    public static String uuidComplete(int length){
        UuidUtil uuidUtil = getInstance();
        return uuidUtil.change(length, uuidUtil.uuid());
    }

    public static String uuidComplete(){
        UuidUtil uuidUtil = getInstance();
        return uuidUtil.change(uuidUtil.uuid());
    }

    private String uuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }

    private String uuid(int size){
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while(index <= size){
            builder.append(UUID.randomUUID());
            if(index != 1 && index == size){
                index ++;
            }
            index ++;
        }

        return builder.toString().replaceAll("-","");
    }

    private String change(int length,String uuid){
        if(length <= 0) {
            length = 16;
        }

        if(length > 32){
            return uuid(length%32==0?length/32-1:length/32).substring(0,length);
        }

        return uuid.substring(0,length);
    }

    private String change(String uuid){
       return change(16,uuid);
    }
}
