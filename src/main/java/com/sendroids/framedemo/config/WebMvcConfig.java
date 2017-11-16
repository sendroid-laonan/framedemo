package com.sendroids.framedemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.AjaxThymeleafViewResolver;
import org.thymeleaf.spring4.view.FlowAjaxThymeleafView;

public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public AjaxThymeleafViewResolver ajaxThymeleafViewResolver() {
        AjaxThymeleafViewResolver viewResolver = new AjaxThymeleafViewResolver();
        viewResolver.setViewClass(FlowAjaxThymeleafView.class);
        viewResolver.setTemplateEngine(springTemplateEngine);
        return viewResolver;
    }
}
