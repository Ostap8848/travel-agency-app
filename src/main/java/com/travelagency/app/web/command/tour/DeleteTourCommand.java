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

public class DeleteTourCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(DeleteTourCommand.class);
    private final TourService tourService;

    public DeleteTourCommand(TourService tourService) {
        this.tourService = tourService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        try {
            Tour tour = tourService.getTourById(Integer.parseInt(request.getParameter("tourId")));
            tourService.delete(tour);
            return "home?command=adminListOfTours";
        } catch (ServiceException e) {
            return "error.jsp";
        }
    }
}
