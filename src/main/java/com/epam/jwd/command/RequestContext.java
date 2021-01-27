package com.epam.jwd.command;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RequestContext {

    List<String> getParamsList();

    String getParameter(String name);

    boolean hasParameter(String name);

    HttpSession getSession();

    void setAttribute(String name, Object attr);
    String getUrl();
}
