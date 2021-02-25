package com.tanx.music.pojo.model;

import java.sql.Timestamp;

/**
 * 角色
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午1:46
 */
public class Roles {

    /** 角色ID */
    private int roleId;
    /** 角色名称(人看) */
    private String roleName;
    /** 角色系统名称 */
    private String roleSystemName;
    /** 角色系统名称 */
    private String url;
    /** 角色系统名称 */
    private Timestamp createTime;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSystemName() {
        return roleSystemName;
    }

    public void setRoleSystemName(String roleSystemName) {
        this.roleSystemName = roleSystemName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleSystemName='" + roleSystemName + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
