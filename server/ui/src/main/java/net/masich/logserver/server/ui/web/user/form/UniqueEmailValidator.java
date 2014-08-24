package net.masich.logserver.server.ui.web.user.form;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;
import net.masich.logserver.server.ui.service.user.UserService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {

    private String idFieldName;
    private String emailFieldName;

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail annotation) {
        idFieldName = annotation.idFieldName();
        emailFieldName = annotation.emailFieldName();
    }

    @Override
    public boolean isValid(Object bean, ConstraintValidatorContext ctx) {
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
           .addNode(emailFieldName).addConstraintViolation();

        try {
            Long id = (Long) PropertyUtils.getProperty(bean, idFieldName);
            String email = (String) PropertyUtils.getProperty(bean, emailFieldName);

            if (email == null) {
                return true;
            }

            UserEntity user = userService.findByEmail(email);
            if (user == null || (id != null && user.getId().equals(id))) {
                return true;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }

}