package com.travelagency.app.web.command.tour;


import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.web.service.impl.TourServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllToursListCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger(AllToursListCommand.class);
    private final TourServiceImpl tourService;

    public AllToursListCommand(TourServiceImpl tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Tour> tours = null;
        try {
            tours = tourService.findAllTours();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("tours", tours);
        return "index.jsp";
    }
}
