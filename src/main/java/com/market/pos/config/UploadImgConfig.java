package com.market.pos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class UploadImgConfig {

    @Configuration
    public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry){
            //指向外部目录
            registry.addResourceHandler("img//**").addResourceLocations("file:C:/img/");
            super.addResourceHandlers(registry);
        }
    }
}
