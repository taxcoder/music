package com.tanx.music.handle;

import com.tanx.music.utils.PropertiesUtil;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/27 下午3:34
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        Writer writer = response.getWriter();
        String admin = "admin";
        String value = PropertiesUtil.getInstance().get("security.type");
        if(admin.equals(value)){
            writer.write(ReturnUtil.getInstance().error("登录失败，请重试！"));
        }else{
            if("Bad credentials".equals(e.getMessage())){
                writer.write(ReturnUtil.getInstance().error("用户名或密码错误，请重试！"));
            }else{
                writer.write(ReturnUtil.getInstance().error(e.getMessage()));
            }
        }
    }
}
