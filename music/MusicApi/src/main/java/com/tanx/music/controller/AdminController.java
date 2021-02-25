package com.tanx.music.controller;

import com.tanx.music.pojo.model.Admin;
import com.tanx.music.service.admin.AdminServiceImpl;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午5:49
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/administrator/info")
    public String adminInfo(String loginName, HttpServletRequest request){
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("admin");
        if(user == null){
            return ReturnUtil.getInstance().error("未登录！");
        }
        if(!user.equals(loginName)){
            return ReturnUtil.getInstance().error("未登录！");
        }
        Admin admin = adminService.findAdminByUsername(loginName);
        admin.setRoleId(null);
        admin.setAdminPassword(null);
        admin.setCreateTime(null);
        return ReturnUtil.getInstance().success(admin,"登录成功！");
    }

    @PostMapping("/administrator/logout")
    public String adminLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return ReturnUtil.getInstance().success("注销成功！");
    }
}
