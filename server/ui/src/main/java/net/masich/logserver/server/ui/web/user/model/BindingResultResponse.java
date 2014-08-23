package net.masich.logserver.server.ui.web.user.model;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class BindingResultResponse {

    private Object data;
    private boolean hasErrors;
    private List<FieldError> errors = new ArrayList<>();

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    private static class FieldError {

        private String fieldName;
        private String message;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public static BindingResultResponse valueOf(BindingResult bindingResult) {
        BindingResultResponse response = new BindingResultResponse();
        response.data = bindingResult.getTarget();

        if (bindingResult.hasErrors()) {
            response.hasErrors = true;

            for (org.springframework.validation.FieldError error : bindingResult.getFieldErrors()) {
                FieldError fieldError = new FieldError();
                fieldError.setFieldName(error.getField());
                fieldError.setMessage(error.getDefaultMessage());

                response.errors.add(fieldError);
            }
        }

        return response;
    }

}
