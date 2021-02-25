package com.tanx.music.utils;

import java.util.Random;

/**
 * 验证码随机数
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/19 上午8:40
 */
public class CreateRandomUtil {

    public static String random(){
        return random(6);
    }

    public static String random(int length){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<length;++i){
            builder.append(ConstantUtil.LETTER.charAt(random.nextInt(ConstantUtil.LETTER.length())+ 1));
        }
        return builder.toString();
    }

    public static String numberRandom(){
        return numberRandom(6);
    }

    public static String numberRandom(int length){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<length;++i){
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
