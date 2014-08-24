package net.masich.logserver.server.ui.web.user.controller;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;
import net.masich.logserver.server.ui.service.user.UserService;
import net.masich.logserver.server.ui.web.user.dto.UserDto;
import net.masich.logserver.server.ui.web.user.form.UserForm;
import net.masich.logserver.server.ui.web.user.model.BindingResultResponse;
import net.masich.logserver.server.ui.web.user.model.ChangingStatusResponse;
import net.masich.logserver.server.ui.web.user.model.DeleteResponse;
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

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
    public BindingResultResponse create(@Validated({UserForm.Create.class}) UserForm userForm, BindingResult result) {
        BindingResultResponse response = BindingResultResponse.valueOf(result);

        if (result.hasErrors()) {
            return response;
        }

        response.setData(UserDto.valueOf(userService.create(userForm.toEntity())));
        return response;
    }

    @RequestMapping(value = "/{id}/edit", method = PUT)
    public BindingResultResponse edit(@Validated({UserForm.Edit.class}) UserForm userForm, BindingResult result,
                                      @PathVariable("id") Long id) {

        BindingResultResponse response = BindingResultResponse.valueOf(result);

        if (result.hasErrors()) {
            return response;
        }

        response.setData(UserDto.valueOf(userService.update(userForm.toEntity())));
        return response;
    }

    @RequestMapping(value = "/{id}/activate", method = PATCH)
    public ChangingStatusResponse activate(@PathVariable("id") Long id) {
        UserEntity entity = userService.activate(id);

        return new ChangingStatusResponse(true, UserDto.valueOf(entity));
    }

    @RequestMapping(value = "/{id}/deactivate", method = PATCH)
    public ChangingStatusResponse deactivate(@PathVariable("id") Long id) {
        UserEntity entity = userService.deactivate(id);

        return new ChangingStatusResponse(true, UserDto.valueOf(entity));
    }

    @RequestMapping(value = "/{id}/delete", method = DELETE)
    public DeleteResponse delete(@PathVariable("id") Long id) {
        userService.delete(id);

        return new DeleteResponse(id, true);
    }

}
