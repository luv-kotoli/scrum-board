package se.auth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebAppContext extends WebMvcConfigurationSupport{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置登录处理操作
        registry.addViewController("/login").setViewName("login");
    }
}

