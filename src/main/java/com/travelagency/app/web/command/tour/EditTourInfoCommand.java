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

public class EditTourInfoCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(EditTourInfoCommand.class);
    private final TourService tourService;

    public EditTourInfoCommand(TourService tourService) {
        this.tourService = tourService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String ukrName = request.getParameter("ukrName");
        String engName = request.getParameter("engName");
        String tourType = request.getParameter("tourType");
        String price = request.getParameter("tourPrice");
        String numberOfPersons = request.getParameter("numberOfPersons");
        String hotelType = request.getParameter("hotelType");
        String hot = request.getParameter("hot");
        String discount = request.getParameter("discount");
        String description = request.getParameter("description");
        try {
            Tour tourToUpdate = tourService.getTourById(Integer.parseInt(request.getParameter("tourId")));
            Tour tour = tourService.getTourById(Integer.parseInt(request.getParameter("tourId")));

            if(ukrName == null || ukrName.isEmpty()) {
                tourToUpdate.setNameUkr(tour.getNameUkr());
            } else {
                tourToUpdate.setNameUkr(ukrName);
            }

            if(engName == null || engName.isEmpty()) {
                tourToUpdate.setNameEng(tour.getNameEng());
            } else {
                tourToUpdate.setNameEng(engName);
            }

            if(tourType == null || tourType.isEmpty()) {
                tourToUpdate.setTourType(tour.getTourType());
            } else {
                tourToUpdate.setTourType(TourType.valueOf(tourType.toUpperCase()));
            }

            if(price == null || price.isEmpty()) {
                tourToUpdate.setPrice(tour.getPrice());
            } else {
                tourToUpdate.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
            }

            if(numberOfPersons == null || numberOfPersons.isEmpty()) {
                tourToUpdate.setNumberOfPersons(tour.getNumberOfPersons());
            } else {
                tourToUpdate.setNumberOfPersons(Integer.parseInt(numberOfPersons));
            }

            if(hotelType == null || hotelType.isEmpty()) {
                tourToUpdate.setHotelTypeByStars(tour.getHotelTypeByStars());
            } else {
                tourToUpdate.setHotelTypeByStars(Hotel.valueOf(hotelType.toUpperCase()));
            }

            if(hot == null || hot.isEmpty()) {
                tourToUpdate.setTourHot(tour.getIsTourHot());
            } else {
                tourToUpdate.setTourHot(Boolean.parseBoolean(hot));
            }

            if(discount == null || discount.isEmpty()) {
                tourToUpdate.setDiscount(tour.getDiscount());
            } else {
                tourToUpdate.setDiscount(BigDecimal.valueOf(Double.parseDouble(discount)));
            }

            if(description == null || description.isEmpty()) {
                tourToUpdate.setDescription(tour.getDescription());
            } else {
                tourToUpdate.setDescription(description);
            }

            tourService.update(tourToUpdate);
            return "home?command=adminListOfTours";
        } catch (ServiceException e) {
            return "error.jsp";
        }
    }
}
