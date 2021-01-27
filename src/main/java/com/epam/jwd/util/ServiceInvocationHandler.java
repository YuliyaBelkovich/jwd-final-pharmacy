package com.epam.jwd.util;

import com.epam.jwd.annotation.Access;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.EntityService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.rmi.AccessException;
import java.util.Arrays;

public class ServiceInvocationHandler  implements InvocationHandler {

   private EntityService<Entity> service;
   private User user;

    public ServiceInvocationHandler(EntityService<Entity> service, User user) {
        this.service = service;
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean isRightRole = false;
        if(method.isAnnotationPresent(Access.class)){
            isRightRole = Arrays.stream(method.getAnnotation(Access.class).allowedRoles())
                    .filter(role -> role.equals(user.getRole().getBaseName()))
                    .findAny()
                    .isPresent();
        }
        if(isRightRole) {
            return method.invoke(service, args);
        } else throw new AccessException("Access denied");
    }
}
