package com.travelagency.app.web.command.order;

import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class FulfillOrderCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(FulfillOrderCommand.class);
    private final TourService tourService;

    public FulfillOrderCommand(TourService tourService) {
        this.tourService = tourService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int personsToGo = Integer.parseInt(request.getParameter("numberOfPersons"));
        BigDecimal stepDiscount = BigDecimal.valueOf(Double.parseDouble(request.getParameter("stepDiscount")));
        try {
            tourService.getTourById(Integer.parseInt(request.getParameter("tourId")));
        } catch (ServiceException e) {
            LOG.error("Error: {}", e);
            throw new CommandException(e);
        }
        return "personalAccount.jsp";
    }
}
