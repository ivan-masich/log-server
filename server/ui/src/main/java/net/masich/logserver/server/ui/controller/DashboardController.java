package net.masich.logserver.server.ui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @RequestMapping("/dashboard")
    public Object dashboard() {
        return new Object() {
            public boolean status;
        };
    }

}
