package com.tanx.music.dao;

import com.tanx.music.pojo.model.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色
 *
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午1:48
 */
@Repository
@Mapper
public interface RoleMapper {

    /**
     * 根据角色ID查询数据
     * @param roleId 角色ID
     * @return 返回角色信息
     */
    Roles findByRoleId(@Param("roleId") int roleId);

    /**
     * 查询数据
     * @return 返回角色信息
     */
    List<Roles> findAll();
}
