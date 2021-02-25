package com.tanx.music.pojo.model;

import java.sql.Timestamp;

/**
 * 管理员
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午2:30
 */

public class Admin implements Cloneable{
    /** 管理员ID--UUID */
    private String adminId;
    /** 管理员名称 */
    private String adminName;
    /** 管理头像图片地址 */
    private String adminHeadImage;
    /** 登录名 */
    private String loginName;
    /** 角色ID */
    private Integer roleId;
    /** 管理员密码 */
    private String adminPassword;
    /** 管理员账号创建时间 */
    private Timestamp createTime;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getAdminHeadImage() {
        return adminHeadImage;
    }

    public void setAdminHeadImage(String adminHeadImage) {
        this.adminHeadImage = adminHeadImage;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminHeadImage='" + adminHeadImage + '\'' +
                ", loginName='" + loginName + '\'' +
                ", roleId=" + roleId +
                ", adminPassword='" + adminPassword + '\'' +
                ", createTime=" + createTime +
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
