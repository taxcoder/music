package com.tanx.music.handle;

import com.tanx.music.pojo.model.Admin;
import com.tanx.music.service.admin.AdminServiceImpl;
import com.tanx.music.service.user.UserServiceImpl;
import com.tanx.music.utils.PropertiesUtil;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/27 上午11:53
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private AdminServiceImpl adminService;

    public static LoginSuccessHandler successAuthenticationHandler;

    @PostConstruct
    public void init(){
        successAuthenticationHandler = this;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession();
        String value = PropertiesUtil.getInstance().get("security.type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        Writer writer = response.getWriter();
        String admin = "admin";
        if(admin.equals(value)){
            Admin adminByUsername = successAuthenticationHandler.adminService.findAdminByUsername(authentication.getName());
            adminByUsername.setAdminPassword(null);
            adminByUsername.setRoleId(null);
            adminByUsername.setCreateTime(null);
            adminByUsername.setAdminHeadImage(null);
            session.setAttribute("admin",authentication.getName());
            writer.write(ReturnUtil.getInstance().success(adminByUsername,"登录成功！"));
        }else{
            session.setAttribute("user",authentication.getName());
            writer.write(ReturnUtil.getInstance().success(successAuthenticationHandler.service.findByUserEmail(authentication.getName()),"登录成功！"));
        }

    }
}
