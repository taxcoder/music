package com.tanx.music.config;


import com.tanx.music.config.service.UserDetailServiceImpl;
import com.tanx.music.filter.UrlInterceptFilter;
import com.tanx.music.handle.LoginFailureHandler;
import com.tanx.music.handle.LoginSuccessHandler;
import com.tanx.music.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;



/**
 * @author tanxiang
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Value("${spring.profiles.active}")
    private String active;

    private static final String ADMIN = "admin";

    private static final String[] EXCLUDED_AUTH_PAGES = {
            "/user/**",
            "/index/**",
            "/singer/**",
            "/singers/**",
            "/songSheet/**",
            "/songSheets/**"
    };

    /** 配置密码加密 */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /** 用户自定义认证 */
    @Bean
    public UserDetailsService userDetailService(){
        return new UserDetailServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService());
        auth.authenticationProvider(userAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors().configurationSource(new CorsConfigure().urlBasedCorsConfigurationSource());
        // 开启授权认证
        http.authorizeRequests().antMatchers(EXCLUDED_AUTH_PAGES).permitAll()
        .antMatchers("/myMusic/**").authenticated();

        http.addFilterBefore(new UrlInterceptFilter(), UsernamePasswordAuthenticationFilter.class).authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
        .antMatchers(HttpMethod.OPTIONS).permitAll();
        // 开启登录
        http.formLogin().usernameParameter("username").passwordParameter("password")
        // 自定义登录页面
        .loginPage(PropertiesUtil.getInstance().get("web." + active + ".login")).loginProcessingUrl("/login");

        http.formLogin().successHandler(new LoginSuccessHandler()).failureHandler(new LoginFailureHandler());

        http.logout().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(EXCLUDED_AUTH_PAGES);
    }

    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}