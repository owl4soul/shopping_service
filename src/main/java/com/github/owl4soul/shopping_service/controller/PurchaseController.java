package com.github.owl4soul.shopping_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер обеспечивающий взаимодействие с api для покупок.
 */
@RestController
@RequestMapping(path = "/api/purchases")
public class PurchaseController {

    @RequestMapping(value = "/test")
    public void test() {
        System.out.println("Success!");
    }
}
