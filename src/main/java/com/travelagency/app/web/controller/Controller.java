package com.travelagency.app.web.controller;

import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.CommandContainer;
import com.travelagency.app.web.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//http://localhost:8080/travel_agency_app/home?command
@WebServlet(name = "controller", value = "/home")
public class Controller extends HttpServlet {

    static final Logger LOG = LogManager.getLogger(Controller.class);
    private CommandContainer commands;

    @Override
    public void init(ServletConfig config) {
        commands = (CommandContainer) config.getServletContext().getAttribute("commandContainer");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = null;
        try {
            url = getUrl(req,resp);
        } catch (CommandException e) {
            LOG.debug("Error: {}", e);
            resp.sendError(500, "Can`t process the command");
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = null;
        try {
            url = getUrl(req, resp);
        } catch (CommandException e) {
            LOG.debug("Error: {}", e);
            resp.sendError(500, "Can`t process the command");
        }
        resp.sendRedirect(url);
    }

    public String getUrl(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String commandName = req.getParameter("command");
        ActionCommand actionCommand = CommandContainer.getCommand(commandName);
        return actionCommand.execute(req, resp);
    }

}
