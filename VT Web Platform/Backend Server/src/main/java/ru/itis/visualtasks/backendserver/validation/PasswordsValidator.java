package ru.itis.visualtasks.backendserver.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordsValidator implements ConstraintValidator<ValidPasswords, Object> {

    protected String passwordProperty;
    protected String repeatedPasswordProperty;

    @Override
    public void initialize(ValidPasswords constraintAnnotation) {
        this.passwordProperty = constraintAnnotation.password();
        this.repeatedPasswordProperty = constraintAnnotation.repeatedPassword();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Object password = new BeanWrapperImpl(object).getPropertyValue(passwordProperty);
        Object repeatedPassword = new BeanWrapperImpl(object).getPropertyValue(repeatedPasswordProperty);
        return password!=null && password.equals(repeatedPassword);
    }
}
