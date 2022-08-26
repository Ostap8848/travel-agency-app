package com.travelagency.app.web.command;

import com.travelagency.app.web.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLocale implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute("locale", request.getParameter("locale"));
        String path = "home?command=" + request.getParameter("pageToTranslate");
        return path;
    }
}
