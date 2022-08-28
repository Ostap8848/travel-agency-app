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


public class EditTourInfoFormCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(EditTourInfoFormCommand.class);
    private final TourService tourService;

    public EditTourInfoFormCommand(TourService tourService) {
        this.tourService = tourService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Tour tour = null;
        int id = 0;
        try {
            id = Integer.parseInt((request.getParameter("tourId")));
            tour = tourService.getTourById(id);
        } catch (ServiceException e) {
            LOG.error("Failed to get tour");
            throw new CommandException(e);
        }
        request.setAttribute("tour", tour);
        return "editTour.jsp";
    }
}
