package net.masich.logserver.server.ui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    @RequestMapping("/sign-in")
    public Object signIn(@RequestParam String email, @RequestParam String password) {
        return new Object() {
            public boolean status = false;
            public String errorMessage = "some error";
        };
    }

}
