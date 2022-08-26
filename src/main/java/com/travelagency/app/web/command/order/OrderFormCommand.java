package com.travelagency.app.web.command.order;

import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderFormCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return "makeOrder.jsp";
    }
}
