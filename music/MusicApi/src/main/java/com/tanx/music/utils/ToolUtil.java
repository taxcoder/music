package com.tanx.music.utils;

import javax.servlet.http.HttpSession;

/**
 * 工具包
 */
public class ToolUtil {

    /**
     * 判断用户是否登录
     */
    public Boolean isUserLoginSession(HttpSession session, String condition, String username) {
        String attribute = (String) session.getAttribute(condition);
        return attribute != null && attribute.equals(username);
    }
}
