package com.epam.jwd.command.impl.auth;

import com.epam.jwd.command.*;
import com.epam.jwd.context.PageName;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.util.LanguageManager;

/**
 * Command responsible for starting the session
 * Sets default user attributes for GUEST
 * Sets default locale and resource bundle
 */
public class StartCommand implements Command {
    private static final ResponseContext HOMEPAGE = PageName.MAIN_PAGE::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("user_role","GUEST");
        requestContext.getSession().setAttribute("user_email","");
        requestContext.getSession().setAttribute("basket", null);
        requestContext.getSession().setAttribute("locale","en_US");
        requestContext.getSession().setAttribute("rb",LanguageManager.getInstance().getBundle().getBaseBundleName());
        requestContext.getSession().setAttribute("previousPage","/pharmacy?command=go_to_main_page");
        return HOMEPAGE;
    }
}
