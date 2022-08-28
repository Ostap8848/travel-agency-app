package com.travelagency.app.web.command.tour;


import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Hotel;
import com.travelagency.app.model.entity.constant.TourType;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class CreateTourCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(CreateTourCommand.class);
    private final TourService tourService;

    public CreateTourCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Tour tour = Tour.newTourBuilder()
                .setNameUkr(request.getParameter("ukrName"))
                .setNameEng(request.getParameter("engName"))
                .setTourType(TourType.valueOf(request.getParameter("tourType").toUpperCase()))
                .setPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("tourPrice"))))
                .setNumberOfPersons(Integer.parseInt(request.getParameter("numberOfPersons")))
                .setHotelTypeByStars(Hotel.valueOf(request.getParameter("hotelType").toUpperCase()))
                .setIsTourHot(Boolean.parseBoolean(request.getParameter("hot")))
                .setDiscount(BigDecimal.valueOf(Double.parseDouble(request.getParameter("discount"))))
                .setDescription(request.getParameter("description"))
                .build();
        try {
            tourService.insert(tour);
            return "home?command=adminListOfTours";
        } catch (ServiceException e) {
            return "error.jsp";
            //throw new CommandException(e);
        }
    }
}
