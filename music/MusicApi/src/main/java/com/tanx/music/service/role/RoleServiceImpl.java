package com.tanx.music.service.role;

import com.tanx.music.dao.RoleMapper;
import com.tanx.music.pojo.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午1:55
 */
@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Roles findByRoleId(int roleId) {
        return roleMapper.findByRoleId(roleId);
    }

    @Override
    public List<Roles> findAll() {
        return roleMapper.findAll();
    }
}
