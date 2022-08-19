package com.travelagency.app.web.command.user;

import com.travelagency.app.model.entity.User;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.service.UserService;
import com.travelagency.app.web.service.exception.ServiceException;
import com.travelagency.app.web.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class AllUsersListCommand implements ActionCommand {
    UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<User> users = null;
        try {
            users = userService.findAllUsers();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("allUsers", users);
        return "users.jsp";
    }
}
