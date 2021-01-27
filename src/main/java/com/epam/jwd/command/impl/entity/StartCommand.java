package com.epam.jwd.command.impl.entity;

import com.epam.jwd.command.*;
import com.epam.jwd.util.LanguageManager;

public class StartCommand implements Command {
    private static final ResponseContext HOMEPAGE = PageName.MAIN_PAGE::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("user_role","GUEST");
        requestContext.getSession().setAttribute("basket", null);
        requestContext.getSession().setAttribute("language", LanguageManager.getInstance());
        return HOMEPAGE;
    }
}
