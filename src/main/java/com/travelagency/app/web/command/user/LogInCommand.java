package com.travelagency.app.web.command.user;

import com.travelagency.app.util.encryption.CryptPassword;
import com.travelagency.app.model.entity.User;
import com.travelagency.app.model.entity.constant.Role;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.service.UserService;
import com.travelagency.app.web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(LogInCommand.class);
    private final UserService userService;

    public LogInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = userService.getUserByLogin(login);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if(user != null && CryptPassword.check(password, user.getPassword()) && !user.getIsBlocked()) {
            session.setAttribute("user", user);
        } else {
            LOG.info("Cannot log in");
            return "errorPage.jsp";
            //throw new CommandException();
        }
        if (user.getRole() == Role.ADMINISTRATOR) {
            page = "adminPanel.jsp";
        }
        if (user.getRole() == Role.MANAGER) {
            page = "managerCabinet.jsp";
        }
        if (user.getRole() == Role.CLIENT) {
            page = "personalAccount.jsp";
        }
        return page;
    }
}
