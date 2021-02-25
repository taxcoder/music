package com.tanx.music.service.admin;

import com.tanx.music.pojo.model.Admin;
import org.springframework.stereotype.Service;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午7:27
 */
@Service
public interface IAdminService {

    /**
     * 通过登录名和密码查询管理员是否存在
     *
     * @param loginName 登录名
     * @param password 密码
     * @return 返回查询的对象
     */
    Admin findAdmin(String loginName, String password);

    /**
     * 通过登录名和密码查询管理员是否存在
     *
     * @param loginName 登录名
     * @return 返回查询的对象
     */
    Admin findAdminByUsername(String loginName);
}
