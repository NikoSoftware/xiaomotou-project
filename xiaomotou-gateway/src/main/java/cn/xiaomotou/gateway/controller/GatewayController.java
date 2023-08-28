package cn.xiaomotou.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/gateway/api")
@RestController
public class GatewayController {

    @GetMapping("/ok")
    public String getName(String name){
        return name;
    }
}
