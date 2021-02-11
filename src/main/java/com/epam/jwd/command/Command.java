package com.epam.jwd.command;

import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;

public interface Command {

    ResponseContext execute(RequestContext requestContext);
}
