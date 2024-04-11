package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thflo
 * @Date 2024/4/7
 */
@RestController
@RequestMapping("/thf")
public class Controller {
    @RequestMapping(value="/test")
    @ResponseBody
    public String test() {
        return "test";
    }
}
