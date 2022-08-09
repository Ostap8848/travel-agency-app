package com.travelagency.app.command;

import com.travelagency.app.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ActionCommand {

    /**
     * @param request  the request
     * @param response the response
     * @return the string
     * @throws CommandException the command exception
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
