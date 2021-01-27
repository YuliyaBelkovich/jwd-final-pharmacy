package com.epam.jwd.command;

public interface Command {

    ResponseContext execute(RequestContext requestContext);
}
