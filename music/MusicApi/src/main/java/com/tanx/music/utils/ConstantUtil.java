package com.tanx.music.utils;

/**
 * 常量
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午9:32
 */
public class ConstantUtil {
    public static final String EMAIL_REGEX = "^\\w+((-\\w)|(\\.\\w))*@[A-Za-z0-9]+((\\.\\|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,16})$";
    public static final String LETTER = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTER_WORD = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBER = "1234567890";
    public static final String[] URL = new String[]{"/user/,/admin/administrator/"};
    public static final String MATCH = "\\d+";
    public static final Integer LIMIT_LENGTH = 20;
    public static final String USER_TYPE = "db22214f6b74452793804bb3ebb1a4b2f0f74b95a04048ff94beb8";
    public static final String ADMIN_TYPE = "105ce0adc5ff4ef095497be797d6f85d1f5730f8e9aa4f61afef08";
}
