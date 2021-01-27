package com.epam.jwd.filter;

import com.epam.jwd.command.CommandName;
import com.epam.jwd.command.PageName;
import com.epam.jwd.domain.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebFilter(urlPatterns = "/pharmacy")
public class PharmacyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<Role> roles = CommandName.resolveCommandByName(servletRequest.getParameter("command")).getRoles();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String userRole = (String) request.getSession().getAttribute("user_role");
        if (!roles.isEmpty()) {
            Optional<Role> optionalRole = roles.stream().filter(role -> role.getBaseName().equals(userRole)).findAny();
            if (optionalRole.isPresent()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                servletRequest.getRequestDispatcher(PageName.ACCESS_DENIED_PAGE.getJspFileName()).forward(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
   }
}
