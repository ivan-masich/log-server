package net.masich.logserver.server.ui.web.user.controller;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;
import net.masich.logserver.server.ui.service.user.UserService;
import net.masich.logserver.server.ui.web.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    public UserDto info() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findByEmail(auth.getName());
        return UserDto.valueOf(user);
    }

}
