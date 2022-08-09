package com.travelagency.app.command;

import com.travelagency.app.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return null;
    }
}
