package com.tanx.music.pojo.model;

import java.sql.Timestamp;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午9:52
 */
public class User implements Cloneable{

    /** 用户ID */
    private String userId;
    /** 角色ID */
    private int roleId;
    /** 用户名称 */
    private String userName;
    /** 用户密码 */
    private String userPassword;
    /** 用户头像地址 */
    private String userHeadAddress;
    /** 用户手机 */
    private String userPhone;
    /** 用户出生日期 */
    private Timestamp userBornTime;
    /** 用户性别 */
    private int userSex;
    /** 用户邮箱 */
    private String userEmail;
    /** 用户简介 */
    private String userDesc;
    /** 创建日期 */
    private Timestamp createTime;
    /** 修改日期 */
    private Timestamp updateTime;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Timestamp getUserBornTime() {
        return userBornTime;
    }

    public void setUserBornTime(Timestamp userBornTime) {
        this.userBornTime = userBornTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserHeadAddress() {
        return userHeadAddress;
    }

    public void setUserHeadAddress(String userHeadAddress) {
        this.userHeadAddress = userHeadAddress;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userHeadAddress='" + userHeadAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userBornTime=" + userBornTime +
                ", userSex=" + userSex +
                ", userEmail='" + userEmail + '\'' +
                ", userDesc='" + userDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Object cloneTarget() throws CloneNotSupportedException {
        return clone();
    }
}
