package com.tanx.music.config.service;

import com.tanx.music.dao.RoleMapper;
import com.tanx.music.pojo.model.Admin;
import com.tanx.music.pojo.model.Roles;
import com.tanx.music.pojo.model.User;
import com.tanx.music.service.admin.AdminServiceImpl;
import com.tanx.music.service.user.UserServiceImpl;
import com.tanx.music.utils.PropertiesUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午12:48
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询判断是用户还是管理员
        String value = PropertiesUtil.getInstance().get("security.type");
        String admin = "admin";
        if(admin.equals(value)){
            // 查询处管理员的信息
            Admin adminByUsername = adminService.findAdminByUsername(username);
            if(adminByUsername == null){
                throw new BadCredentialsException("管理员不存在！");
            }
            // 获取用户的完整信息
            return new org.springframework.security.core.userdetails.User(adminByUsername.getLoginName(), adminByUsername.getAdminPassword(),
                    grantedAuthorities(adminByUsername.getRoleId()));
        }
        // 查询处用户的信息
        User byUserEmail = userService.findByUserEmail(username);
        if(byUserEmail == null){
                throw new RuntimeException("用户不存在！");
        }
        // 获取用户的完整信息
        User user = userService.findUser(byUserEmail.getUserId());
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), grantedAuthorities(user.getRoleId()));
    }

    private List<GrantedAuthority> grantedAuthorities(int roleId){
        // 默认为[]
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        // 获取用户的权限
        Roles byRoleId = roleMapper.findByRoleId(roleId);
        // 判断查询的权限是否为null
        if(byRoleId != null && byRoleId.getRoleSystemName() != null){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(byRoleId.getRoleSystemName());
            // 将权限加入grantedAuthorities之中
            grantedAuthorities.add(grantedAuthority);
        }
        return grantedAuthorities;
    }
}
