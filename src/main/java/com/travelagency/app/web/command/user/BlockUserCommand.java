package com.travelagency.app.web.command.user;

import com.travelagency.app.model.entity.User;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.service.UserService;
import com.travelagency.app.web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(BlockUserCommand.class);
    private final UserService userService;

    public BlockUserCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            User user = userService.getUserById(Integer.parseInt(request.getParameter("userId")));
            userService.blockUser(user);
        } catch (ServiceException e) {
            throw new  CommandException(e);
        }
        return "home?command=allUsers";
    }
}
