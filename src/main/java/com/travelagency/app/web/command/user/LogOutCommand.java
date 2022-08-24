package com.travelagency.app.web.command.user;

import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "login.jsp";
    }
}
