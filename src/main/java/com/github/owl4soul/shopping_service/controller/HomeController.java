package com.github.owl4soul.shopping_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для домашней страницы.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }
}
