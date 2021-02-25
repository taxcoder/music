package com.tanx.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午2:18
 */

@Configuration
public class CorsConfigure implements WebMvcConfigurer {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 设置所有的域名
        List<String> list = new ArrayList<>();
        list.add("http://localhost:8090");
        list.add("http://localhost:8070");
        list.add("https://www.tanxiangblog.com:8081");
        list.add("https://www.tanxiangblog.com:8082");
        list.add("https://www.tanxiangblog.com:8083");
        corsConfiguration.setAllowedOrigins(list);
        // 设置所有的请求头
        corsConfiguration.addAllowedHeader("*");
        // 设置所有的方法
        corsConfiguration.addAllowedMethod("*");
        // 设置cookie可以传递
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    @Bean
    public UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return source;
    }

}
