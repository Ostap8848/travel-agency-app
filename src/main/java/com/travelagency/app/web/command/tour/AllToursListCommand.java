package com.travelagency.app.web.command.tour;


import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AllToursListCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger(AllToursListCommand.class);
    private final TourService tourService;


    public AllToursListCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        /*List<Tour> tours = null;
        String spage = request.getParameter("page");
        int pageId = 0;
        if (spage != null) {
            pageId = Integer.parseInt(spage);
        } else {
            pageId = 1;
        }
        try {
            tours = tourService.findAllTours(pageId * 10 - 10);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("tours", tours);

        return "tours.jsp";*/
        int page;
        if (request.getParameter("page") == null) {
            page = 1;
        } else{
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Tour> tours = null;
        try {
            tours = tourService.findAllTours(page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("tours", tours);
        int countPages = tours.size()/10+1;
        List<Integer> pages = new ArrayList<>();
        for (int i=1; i<=countPages; i++){
            pages.add(i);
        }
        request.setAttribute("pages", pages);
        return "tours.jsp";
    }
}
