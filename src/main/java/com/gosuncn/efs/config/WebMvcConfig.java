package com.gosuncn.efs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    EFSConfig efsConfig;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //registry.addResourceHandler("/advertIMG/**").addResourceLocations("file:D:/rentHouse/");
        registry.addResourceHandler("/"+efsConfig.getStore().getAccessRootDir()+"/**")
                .addResourceLocations("file:"+efsConfig.getStore().getDiskRootDir());
    }
 
}