package com.tanx.music.pojo.vo;


/**
 * 管理员
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 上午8:31
 */
public class AdminVo {
    /** 管理员ID--UUID */
    private String adminId;
    /** 管理员名称 */
    private String adminName;

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

    @Override
    public String toString() {
        return "AdminVo{" +
                "adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                '}';
    }
}
