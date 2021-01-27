package com.epam.jwd.command.impl.entity.user;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.command.PageName;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.UnknownEntityException;
import com.epam.jwd.service.impl.UserService;

import java.util.List;

public class SearchUserCommand implements Command {

    private static final ResponseContext SEARCH_PATIENT_RESULT = PageName.SEARCH_PATIENT_PAGE::getJspFileName;
    private static final ResponseContext SEARCH_DOCTOR_RESULT = PageName.SEARCH_DOCTOR_PAGE::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        String name = null;
        String email = null;
        String role = null;
        String status = null;
        if (requestContext.hasParameter("user_role")) {
            role = requestContext.getParameter("user_role");
        }
        if (requestContext.hasParameter("user_id")) {
            id = Integer.parseInt(requestContext.getParameter("user_id"));
        }
        if (requestContext.hasParameter("user_name")) {
            name = requestContext.getParameter("user_name");
        }
        if (requestContext.hasParameter("user_email")) {
            email = requestContext.getParameter("user_email");
        }
        if (requestContext.hasParameter("user_status")) {
            status = requestContext.getParameter("user_status");
        }
        Criteria<User> criteria = null;
        try {
            criteria = UserCriteria.builder()
                    .id(id)
                    .setName(name)
                    .setEmail(email)
                    .setRole(Role.resolveRoleByDBName(role))
                    .setStatus(UserStatus.resolveStatusByDBName(status))
                    .build();
        } catch (UnknownEntityException e) {
            e.printStackTrace();
        }
        List<User> users;
        try {
            users = UserService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            if (role.equals("PATIENT")) {
                requestContext.setAttribute("Error", "Patient not found");
                return SEARCH_PATIENT_RESULT;
            } else {
                requestContext.setAttribute("Error", "Doctor not found");
                return SEARCH_DOCTOR_RESULT;
            }
        }

        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());

        if (role.equals("PATIENT")) {
            requestContext.setAttribute("Patient", users);
            return SEARCH_PATIENT_RESULT;
        } else {
            requestContext.setAttribute("Doctor", users);
            return SEARCH_DOCTOR_RESULT;
        }
    }
}
