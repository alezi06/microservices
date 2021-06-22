package com.example.byeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ByeController {

    private final ByeService byeService;

    @Autowired
    public ByeController(ByeService byeService) {
        this.byeService = byeService;
    }

    @GetMapping("/bye")
    public String showBye() {
        byeService.send("1");
        return String.format("Всего доброго! Вы здоровались %s количество раз",
                            byeService.getCounter());
    }
}
