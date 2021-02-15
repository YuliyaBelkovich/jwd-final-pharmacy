package com.epam.jwd.controller;

import com.epam.jwd.command.CommandManager;
import com.epam.jwd.command.CommandType;
import com.epam.jwd.context.CustomRequestContext;
import com.epam.jwd.context.PageName;
import com.epam.jwd.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet class responsible for managing the coming requests and executing commands
 */
@WebServlet(name = "pharmacy", urlPatterns = "/pharmacy")
public class PharmacyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        CommandType type = null;
        String page = null;
        try {
            type = CommandManager.resolveCommandByName(command).getType();
            page = CommandManager.resolveCommandByName(command).execute(new CustomRequestContext(req)).getPage();
        } catch (CommandException e) {
            req.getRequestDispatcher(PageName.COMMAND_NOT_FOUND.getJspFileName()).forward(req, resp);
        }

        if (type.equals(CommandType.FORWARD)) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        CommandType type = null;
        String page = null;
        try {
            type = CommandManager.resolveCommandByName(command).getType();
            page = CommandManager.resolveCommandByName(command).execute(new CustomRequestContext(req)).getPage();
        } catch (CommandException e) {
            req.getRequestDispatcher(PageName.COMMAND_NOT_FOUND.getJspFileName()).forward(req, resp);
        }
        if (type.equals(CommandType.FORWARD)) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }
}
