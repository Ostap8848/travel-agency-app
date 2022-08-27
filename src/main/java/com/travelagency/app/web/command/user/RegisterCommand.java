package com.travelagency.app.web.command.user;

import com.travelagency.app.encryption.CryptPassword;
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

public class RegisterCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger(RegisterCommand.class);
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        User user = User.newUserBuilder()
                .setLogin(request.getParameter("login"))
                .setPassword(CryptPassword.getSaltedHash(request.getParameter("password")))
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setInstagram(request.getParameter("instagram"))
                .setPhoneNumber(request.getParameter("phoneNumber"))
                .setRole(Role.CLIENT)
                .setIsBlocked(false)
                .build();
        try {
            userService.insert(user);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        page = "login.jsp";
        return page;
    }
}
