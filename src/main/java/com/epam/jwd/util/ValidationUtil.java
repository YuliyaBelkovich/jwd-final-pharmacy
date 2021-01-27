package com.epam.jwd.util;

import com.epam.jwd.annotation.RoleValidation;
import com.epam.jwd.annotation.StringValidation;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.Role;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.UserService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static <T extends Entity> boolean validateEntity(T entity) throws ValidationException, EntityNotFoundException, DAOException {
        boolean isValid = false;
        for (Field field : entity.getClass().getDeclaredFields()) {
            Class c = field.getType();
            if (c.equals(Integer.class)) {
                isValid = validateInt(field, entity);
            } else if (c.equals(Double.class)) {
                isValid = validateDouble(field, entity);
            } else if (c.equals(String.class)) {
                isValid = validateString(field, entity);
            } else if (c.equals(LocalDateTime.class)) {
                isValid = validateDate(field, entity);
            } else {
                isValid = true;
            }
        }
        return isValid;
    }

    public static <T extends Entity> boolean validateInt(Field field, T t) throws ValidationException, DAOException, EntityNotFoundException {
        field.setAccessible(true);
        int value;
        try {
            value = field.getInt(t);
        } catch (IllegalAccessException e) {
            throw new ValidationException("Unable to validate field " + field.getName() + ", access denied ");
        }

        if (value > 0) {
            if (Arrays.stream(field.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType().equals(RoleValidation.class))) {
                RoleValidation annotation = field.getAnnotation(RoleValidation.class);
                return UserService.getInstance().findById(value).getRole().getBaseName().equals(annotation.role());
            }
            return true;
        } else {
            throw new ValidationException("Field " + field.getName() + " can not be less than zero");
        }
    }

    public static <T extends Entity> boolean validateDouble(Field field, T t) throws ValidationException {
        field.setAccessible(true);
        double value;
        try {
            value = field.getDouble(t);
        } catch (IllegalAccessException e) {
            throw new ValidationException("Unable to validate field " + field.getName() + ", access denied ");
        }
        if (value > 0) {
            return true;
        } else {
            throw new ValidationException("Field " + field.getName() + " can not be less than zero");
        }
    }

    public static <T extends Entity> boolean validateDate(Field field, T t) throws ValidationException {
        field.setAccessible(true);
        LocalDateTime value;
        try {
            value = (LocalDateTime) field.get(t);
        } catch (IllegalAccessException e) {
            throw new ValidationException("Unable to validate field " + field.getName() + ", access denied ");
        }
        if (value.isBefore(LocalDateTime.now().minusMinutes(30))) {
            throw new ValidationException("Field " + field.getName() + " can not be before today");
        } else {
            return true;
        }

    }

    public static <T extends Entity> boolean validateString(Field field, T t) throws ValidationException {
        if (Arrays.stream(field.getDeclaredAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(StringValidation.class))) {
            field.setAccessible(true);
            StringValidation annotation = field.getAnnotation(StringValidation.class);
            String pattern = annotation.pattern();
            int maxLength = annotation.maxLength();
            int minLength = annotation.minLength();
            String value;
            try {
                value = (String) field.get(t);
            } catch (IllegalAccessException e) {
                throw new ValidationException("Unable to validate field " + field.getName() + ", access denied ");
            }
            if (value.length() > minLength && value.length() < maxLength) {
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(value);
                if (m.matches()) {
                    return true;
                } else {
                    throw new ValidationException("Field " + field.getName() + "doesn't matches pattern " + pattern);
                }
            } else {
                throw new ValidationException("Field " + field.getName() + " must be longer than " + minLength + " symbols and no longer than " + maxLength + " symbols");
            }
        }
        return true;
    }
}
