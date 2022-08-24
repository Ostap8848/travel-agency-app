package com.travelagency.app.web.command.user;

import com.travelagency.app.encryption.CryptPassword;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.service.UserService;
import com.travelagency.app.web.service.exception.ServiceException;
import com.travelagency.app.web.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        try {
            if(userService.getUserByLogin(login).getLogin().equals(login) &&
                    CryptPassword.check(password,userService.getUserByLogin(login).getPassword())) {
                page = "home.jsp";
            } else {
                page = "errorPage.jsp";
            }
        } catch (ServiceException e) {
            throw new CommandException();
        }
        return page;
    }
}
