package com.tanx.music.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Random;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午10:31
 */
@SuppressWarnings("all")
public class ObtainHeadUtil {

    private static final PropertiesUtil PROPERTIES_UTIL = PropertiesUtil.getInstance();

    /**
     * 获取指定位置下的图片
     * @return 返回图片地址
     * @throws IOException IO异常
     */
    public String headImage(String readerAddress) throws IOException {
        String[] list = headAddress(readerAddress).list();
        String index = null;
        if(list != null && list.length > 0){
            index = list[new Random().nextInt(list.length) + 1];
        }
        return PROPERTIES_UTIL.get("head.prefix") + index;
    }

    /**
     * 将base64转成图片进行存储，并返回地址
     * @throws IOException IO异常
     */
    public String imageAddress(String base,String prefix,String readerAddress,int size) throws IOException {
        File file = headAddress(readerAddress);
        List<Object> base64 = change(base);
        String uuid = UuidUtil.uuidComplete(size);
        File index = new File(file,uuid+"."+base64.get(0));
        if(index.exists()){
            return imageAddress(base,prefix,readerAddress,size);
        }
        index.createNewFile();
        OutputStream stream = new FileOutputStream(index);
        stream.write((byte[]) base64.get(1));
        stream.flush();
        stream.close();
        return PROPERTIES_UTIL.get(prefix) + index.getName();
    }

    /**
     * 读取项目下指定的文件目录
     * @return 返回文件流
     * @throws IOException IO异常
     */
    private File headAddress(String readerAddress) throws IOException {
        String value = PROPERTIES_UTIL.get(readerAddress);
        ClassPathResource classPathResource = new ClassPathResource(value);
        return classPathResource.getFile();
    }

    /**
     * 去除base64前缀
     * @param base base64 图片
     * @return 返回图片的后缀和base64主体
     */
    private List<Object> change(String base){
        String data = base.split("data:image/")[1];
        String substring = data.substring(0,data.indexOf(",") + 1);
        String add = data.split(substring)[1];
        return Arrays.asList(substring.split(";")[0],encoder(add));
    }

    private byte[] encoder(String base){
        Decoder decoder = Base64.getDecoder();
        return decoder.decode(base);
    }
}
