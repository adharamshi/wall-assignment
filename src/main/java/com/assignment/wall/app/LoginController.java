package com.assignment.wall.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Login Controller
 */

@Controller
public class LoginController {
    @RequestMapping("/loginForm")
    String loginForm(){
        return "login";
    }
}
