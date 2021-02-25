package com.tanx.music.service.admin;

import com.tanx.music.dao.AdminMapper;
import com.tanx.music.pojo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午7:28
 */
@Service
public class AdminServiceImpl implements IAdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdmin(String loginName, String password) {
        return adminMapper.findAdmin(loginName,password);
    }

    @Override
    public Admin findAdminByUsername(String loginName) {
        return adminMapper.findAdminByUsername(loginName);
    }
}
