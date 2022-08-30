package com.travelagency.app.web.command.order;

import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.service.OrderService;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FulfillOrderCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(FulfillOrderCommand.class);
    private final TourService tourService;
    private final OrderService orderService;

    public FulfillOrderCommand(TourService tourService, OrderService orderService) {
        this.tourService = tourService;
        this.orderService = orderService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Tour tour = null;
        BigDecimal finalPrice = null;
        int tourId = Integer.parseInt(request.getParameter("tourId"));
        int personsToGo = Integer.parseInt(request.getParameter("numberOfPersons"));
        BigDecimal stepDiscount = BigDecimal.valueOf(Double.parseDouble(request.getParameter("stepDiscount")));
        try {
          tour = tourService.getTourById(tourId);
            finalPrice = (tour.getPrice().subtract((tour.getPrice().multiply(stepDiscount)).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN))).multiply(BigDecimal.valueOf(personsToGo));
            int orderId = orderService.getOrderId(tourId);
        } catch (ServiceException e) {
            LOG.error("Error: {}", e);
            throw new CommandException(e);
        }
        return "personalAccount.jsp";
    }

    private BigDecimal calculateFinalPrice() {
        return BigDecimal.valueOf(0);
    }
}
