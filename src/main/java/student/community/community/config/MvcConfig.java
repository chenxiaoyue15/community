package student.community.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {//告知系统当成静态资源访问
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }//addResourceHandler是指你想在url请求的路径
             //addResourceLocations是图片存放的真实路径
        };
    }
}
