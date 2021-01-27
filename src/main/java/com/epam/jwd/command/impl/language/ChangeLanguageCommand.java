package com.epam.jwd.command.impl.language;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.util.LanguageManager;

public class ChangeLanguageCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        String language;
        String url;
        url = requestContext.getParameter("url");
        language = requestContext.getParameter("lang");
        LanguageManager current = LanguageManager.getInstance();
        switch (language) {
            case "ru": {
                current.setRussian();
                requestContext.getSession().setAttribute("language", current);
                break;
            }
            default: {
                current.setEnglish();
                requestContext.getSession().setAttribute("language", current);
                break;
            }
        }
        return () -> url;
    }
}
