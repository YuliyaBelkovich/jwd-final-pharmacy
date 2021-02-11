package com.epam.jwd.command.impl.language;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.util.LanguageManager;

public class ChangeLanguageCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        String language;
        String url;
        url = (String) requestContext.getSession().getAttribute("previousPage");
        language = requestContext.getParameter("lang");
        LanguageManager current = LanguageManager.getInstance();
        switch (language) {
            case "ru": {
                current.setRussian();
                requestContext.getSession().setAttribute("locale","ru_RU");
                requestContext.getSession().setAttribute("rb", current.getBundle().getBaseBundleName());
                break;
            }
            default: {
                current.setEnglish();
                requestContext.getSession().setAttribute("locale","en_US");
                requestContext.getSession().setAttribute("rb", current.getBundle().getBaseBundleName());
                break;
            }
        }
        return () -> url;
    }
}
