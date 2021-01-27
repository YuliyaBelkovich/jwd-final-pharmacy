package com.epam.jwd.controller;

import com.epam.jwd.command.CommandName;
import com.epam.jwd.command.CommandType;
import com.epam.jwd.command.impl.CustomRequestContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "pharmacy", urlPatterns = "/pharmacy")
public class PharmacyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        CommandType type = CommandName.resolveCommandByName(command).getType();
        String page = CommandName.resolveCommandByName(command).execute(new CustomRequestContext(req)).getPage();
        if (type.equals(CommandType.FORWARD)) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        CommandType type = CommandName.resolveCommandByName(command).getType();
        String page = CommandName.resolveCommandByName(command).execute(new CustomRequestContext(req)).getPage();
        if (type.equals(CommandType.FORWARD)) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }
}
