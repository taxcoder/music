package com.tanx.music.service.mail;

import org.springframework.stereotype.Service;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午12:45
 */
@Service
public interface IMailService {

    /**
     * 发送邮件
     * @return 返回信息
     */
    String send() throws Exception;
}
