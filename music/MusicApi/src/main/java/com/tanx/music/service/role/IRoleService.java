package com.tanx.music.service.role;

import com.tanx.music.pojo.model.Roles;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午1:54
 */
@Service
public interface IRoleService {

    /**
     * 根据角色ID查询数据
     * @param roleId 角色ID
     * @return 返回角色信息
     */
    Roles findByRoleId(int roleId);

    /**
     * 查询数据
     * @return 返回角色信息
     */
    List<Roles> findAll();
}
