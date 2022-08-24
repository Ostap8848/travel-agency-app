package com.travelagency.app.web.command.user;

import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterFormCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return "registration.jsp";
    }
}
