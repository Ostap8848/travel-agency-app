package com.travelagency.app.web.command.user;

import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.command.tour.AllToursListCommand;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.UserService;
import com.travelagency.app.web.service.exception.ServiceException;
import com.travelagency.app.web.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class AllUsersListCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(AllUsersListCommand.class);
    private final UserService userService;

    public AllUsersListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int page;
        if (request.getParameter("page") == null || request.getParameter("page").equals("")) {
            page = 1;
        } else {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<User> users = null;
        try {
            users = userService.findAllUsers(page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("users", users);
        int countPages = userService.getNumberOfRecords() / 10 + 1;
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= countPages; i++) {
            pages.add(i);
        }
        request.setAttribute("pages", pages);
        return "users.jsp";
    }
}
