package com.github.owl4soul.shopping_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC-конфигурация.
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * Маппинг контроллеров и страниц-вьюшек.
     * @param registry ViewControllerRegistry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
    }
}
