package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${names}")
    private String[] names;

    @RequestMapping("/name")
    public String getName() {
        int i = (int) Math.round(Math.random() * (names.length - 1));
        return names[i];
    }

    @RequestMapping("/names")
    public String[] getNames() {
        return names;
    }
}
