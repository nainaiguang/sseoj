package com.ustc.sse.sseoj.configure;

import com.ustc.sse.sseoj.configure.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/2 12:46
 */

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

//        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/users/login",
                "/users/register",
                "/user/login",
                "/user/register",
                "/user/index",
                "classpath:/static/**");
    }

}
