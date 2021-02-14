package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.PageName;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;

/**
 * Command responsible for navigating to different pages
 */
public class GoToCommand implements Command {

    private ResponseContext PAGE;

    public GoToCommand(PageName name) {
        PAGE = name::getJspFileName;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());
        return PAGE;
    }
}
