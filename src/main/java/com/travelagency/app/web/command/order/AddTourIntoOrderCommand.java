package com.travelagency.app.web.command.order;

import com.travelagency.app.model.entity.Order;
import com.travelagency.app.model.entity.Tour;
import com.travelagency.app.model.entity.constant.Status;
import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.exception.CommandException;
import com.travelagency.app.web.command.user.BlockUserCommand;
import com.travelagency.app.web.service.OrderService;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddTourIntoOrderCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(AddTourIntoOrderCommand.class);
    private final TourService tourService;
    private final OrderService orderService;

    public AddTourIntoOrderCommand(TourService tourService, OrderService orderService) {
        this.tourService = tourService;
        this.orderService = orderService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession httpSession = request.getSession();
        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            Tour tour = tourService.getTourById(Integer.parseInt(request.getParameter("tourId")));

            Order order = Order.newOrderBuilder()
                    .setPrice(tour.getPrice())
                    .setStatus(Status.REGISTERED)
                    .setNotes(null)
                    .build();
            orderService.insert(userId, tour.getId(), order);
            request.setAttribute("tour", tour);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return "makeOrder.jsp";
    }
}
