package net.masich.logserver.server.ui.web.user.controller;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;
import net.masich.logserver.server.ui.service.user.UserService;
import net.masich.logserver.server.ui.web.user.dto.UserDto;
import net.masich.logserver.server.ui.web.user.form.UserForm;
import net.masich.logserver.server.ui.web.user.model.BindingResultResponse;
import net.masich.logserver.server.ui.web.user.model.UsersListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping("/signed-in/info")
    public UserDto info() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findByEmail(auth.getName());
        return UserDto.valueOf(user);
    }

    @RequestMapping("/all")
    public UsersListResponse all() {
        Collection<UserEntity> users = userService.findAll();

        return new UsersListResponse(
                users.stream()
                     .map(UserDto::valueOf)
                     .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @RequestMapping(value = "/new", method = POST)
    public BindingResultResponse create(@Validated({UserForm.Edit.class}) UserForm userForm, BindingResult result) {
        BindingResultResponse response = BindingResultResponse.valueOf(result);

        if (result.hasErrors()) {
            return response;
        }

        UserEntity updated = userService.create(userForm.toEntity());
        response.setData(UserForm.valueOf(updated));
        return response;
    }

    @RequestMapping(value = "/{id}/edit", method = POST)
    public BindingResultResponse edit(@Validated({UserForm.Edit.class}) UserForm userForm, BindingResult result,
                                      @PathVariable("id") Long id) {

        BindingResultResponse response = BindingResultResponse.valueOf(result);

        if (result.hasErrors()) {
            return response;
        }

        UserEntity updated = userService.update(userForm.toEntity());
        response.setData(UserForm.valueOf(updated));
        return response;
    }

}
