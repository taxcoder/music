package com.tanx.music.filter;

import com.tanx.music.utils.ConstantUtil;
import com.tanx.music.utils.PropertiesUtil;
import com.tanx.music.utils.ReturnUtil;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Properties;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/20 下午5:09
 */
@WebFilter(urlPatterns = "/login", filterName = "urlIntercept")
public class UrlInterceptFilter implements Filter {

    private static Properties pro;

    @Override
    public void init(FilterConfig filterConfig) {
        Properties properties = new Properties();
        InputStream resource;
        try {
            resource = new ClassPathResource("configure.properties").getInputStream();
            if(resource == null){
                throw new RuntimeException("configure.properties不存在！");
            }
            properties.load(resource);
            pro = properties;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取前台传递的参数
        String k = request.getParameter("k");
        // 获取流对象
        // 数据相等，表示是用户登录页面传递数据
        if(ConstantUtil.USER_TYPE.equals(k)){
            String loginCode = request.getParameter("code");
            // 获取登录验证码
            String code = (String) request.getSession().getAttribute("code");
            Writer writer = response.getWriter();
            if(code == null){
                writer.write(ReturnUtil.getInstance().error("验证码异常，请刷新！"));
                return;
            }

            if(!code.equals(loginCode)){
                writer.write(ReturnUtil.getInstance().error("验证码错误！"));
                return;
            }
            PropertiesUtil.getInstance().push("security.type","user");
        }else if(ConstantUtil.ADMIN_TYPE.equals(k)){
            PropertiesUtil.getInstance().push("security.type","admin");
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {}
}
