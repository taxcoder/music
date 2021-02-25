package com.tanx.music.dao;

import com.tanx.music.pojo.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/6 下午3:13
 */

@Mapper
@Repository
public interface AdminMapper {

    /**
     * 通过登录名名和密码查询管理员是否存在
     *
     * @param username 登录名
     * @param password 密码
     * @return 返回查询的对象
     */
    Admin findAdmin(String loginName,String password);

    /**
     * 通过登录名查询管理员信息
     *
     * @param loginName 登录名
     * @return 返回查询的对象
     */
    Admin findAdminByUsername(@Param("loginName") String loginName);
}
