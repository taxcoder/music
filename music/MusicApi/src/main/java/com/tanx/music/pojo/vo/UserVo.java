package com.tanx.music.pojo.vo;

import java.sql.Timestamp;

/**
 *
 * 用户Vo
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 上午8:32
 */
public class UserVo {

    /** 用户ID */
    private String userId;
    /** 用户名称 */
    private String userName;
    /** 用户手机 */
    private String userPhone;
    /** 用户出生日期 */
    private Timestamp userBornTime;
    /** 用户简介 */
    private String userDesc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() { return userPhone;}

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Timestamp getUserBornTime() {
        return userBornTime;
    }

    public void setUserBornTime(Timestamp userBornTime) {
        this.userBornTime = userBornTime;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userBornTime=" + userBornTime +
                ", userDesc='" + userDesc + '\'' +
                '}';
    }
}
