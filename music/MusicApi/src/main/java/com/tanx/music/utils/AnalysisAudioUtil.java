package com.tanx.music.utils;

import org.springframework.core.io.ClassPathResource;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 解析mp3
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/30 下午3:35
 */
public class AnalysisAudioUtil {

    private static final PropertiesUtil PROPERTIES_UTIL = PropertiesUtil.getInstance();

    /**
     * 将base64转成map3
     * @param base base64字符串
     * @param prefix 前缀
     * @param readerAddress 存储地址
     * @return 返回存储信息(第一个为音频地址，第二个为音频长度)
     */
    public List<Object> depositAddress(String base,String prefix,String readerAddress) throws IOException, EncoderException {
        File file = headAddress(readerAddress);
        String uuid = UuidUtil.uuidComplete();
        File index = new File(file,uuid+".mp3");
        if(index.exists()){
            return depositAddress(base,prefix,readerAddress);
        }
        OutputStream writer = new FileOutputStream(index);
        writer.write(change(base));
        writer.flush();
        writer.close();
        List<Object> list = new ArrayList<Object>(4);
        list.add(PropertiesUtil.getInstance().get(prefix) + index.getName());
        list.add(musicLength(index));
        return list;
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
    private byte[] change(String base){
        String data = base.split("data:audio/")[1];
        String substring = data.substring(0,data.indexOf(",") + 1);
        String add = data.split(substring)[1];
        return encoder(add);
    }

    /**
     * 将字符串转成字节
     * @param base 需要转换的base64
     * @return 返回字节数组
     */
    private byte[] encoder(String base){
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base);
    }

    private int musicLength(File file) throws EncoderException {
        MultimediaObject multimediaObject = new MultimediaObject(file);
        MultimediaInfo info = multimediaObject.getInfo();
        long playTime = info.getDuration();
        return (int) Math.floor(Double.parseDouble(String.valueOf(playTime)) / 1000);
    }
}
