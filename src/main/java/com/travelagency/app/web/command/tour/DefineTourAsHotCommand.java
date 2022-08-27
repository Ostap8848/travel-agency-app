package com.travelagency.app.web.command.tour;

import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefineTourAsHotCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(DefineTourAsHotCommand.class);
    private final TourService tourService;

    public DefineTourAsHotCommand(TourService tourService) {
        this.tourService = tourService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            Tour tour = tourService.getTourById(Integer.parseInt(request.getParameter("tourId")));
            tour.setTourHot(true);
            tourService.update(tour);
            return "adminListOfTours.jsp";
        } catch (ServiceException e) {
            LOG.error("Error: {}", e);
            return "errorPage.jsp";
            //throw new CommandException(e);
        }
    }
}
