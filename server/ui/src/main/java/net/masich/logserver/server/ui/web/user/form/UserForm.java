package net.masich.logserver.server.ui.web.user.form;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;

import javax.validation.constraints.NotNull;

public class UserForm {

    @NotNull(groups = {Edit.class})
    private Long id;

    @NotNull(groups = {Create.class, Edit.class})
    private String name;

    @NotNull(groups = {Create.class, Edit.class})
    private String email;

    @NotNull(groups = {Create.class})
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static interface Create {}
    public static interface Edit {}

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setName(name);
        userEntity.setEmail(email);
        userEntity.setPassword(password);

        return userEntity;
    }

    public static UserForm valueOf(UserEntity entity) {
        UserForm userForm = new UserForm();
        userForm.setId(entity.getId());
        userForm.setName(entity.getName());
        userForm.setEmail(entity.getEmail());

        return userForm;
    }

}
