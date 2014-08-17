package net.masich.logserver.server.ui.controller;

import net.masich.logserver.server.ui.dao.entity.User;
import net.masich.logserver.server.ui.model.UserInfo;
import net.masich.logserver.server.ui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    public UserInfo info() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        return new UserInfo(user.getId(), user.getName(), user.getEmail());
    }

}
