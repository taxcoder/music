package com.tanx.music.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取configure.properties
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午10:06
 */
public class PropertiesUtil {
    private final Properties propertiesData;
    private PropertiesUtil() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new ClassPathResource("configure.properties").getInputStream();
        if(inputStream == null){
            throw new RuntimeException("configure.properties不存在！");
        }
        properties.load(inputStream);
        propertiesData = properties;
    }

    static class Pro{
        private static PropertiesUtil propertiesUtil = null;

        static {
            try {
                propertiesUtil = new PropertiesUtil();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String get(String key){
        if(key == null || "".equals(key)){
            return null;
        }
        return propertiesData.getProperty(key);
    }

    public void push(String key,String value){
        if(key == null || "".equals(key)){
            throw new RuntimeException("key不能为空！");
        }
        propertiesData.setProperty(key,value);
    }

    public static PropertiesUtil getInstance(){
        return Pro.propertiesUtil;
    }
}
