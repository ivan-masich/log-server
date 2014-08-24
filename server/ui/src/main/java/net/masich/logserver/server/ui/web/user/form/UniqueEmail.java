package net.masich.logserver.server.ui.web.user.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {

    String message() default "{net.masich.logserver.server.ui.web.user.form.UniqueEmail.message}";
    String idFieldName() default "id";
    String emailFieldName() default "email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
