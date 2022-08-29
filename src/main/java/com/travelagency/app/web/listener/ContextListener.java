package com.travelagency.app.web.listener;

import com.travelagency.app.dao.OrderDAO;
import com.travelagency.app.dao.UserDAO;
import com.travelagency.app.dao.impl.OrderDAOImpl;
import com.travelagency.app.dao.impl.UserDAOImpl;
import com.travelagency.app.web.command.*;
import com.travelagency.app.dao.TourDAO;
import com.travelagency.app.dao.impl.TourDAOImpl;
import com.travelagency.app.web.command.order.AddTourIntoOrderCommand;
import com.travelagency.app.web.command.order.FulfillOrderCommand;
import com.travelagency.app.web.command.order.OrderFormCommand;
import com.travelagency.app.web.command.order.SetTourStatusCommand;
import com.travelagency.app.web.command.tour.*;
import com.travelagency.app.web.command.user.*;
import com.travelagency.app.web.service.OrderService;
import com.travelagency.app.web.service.TourService;
import com.travelagency.app.web.service.UserService;
import com.travelagency.app.web.service.impl.OrderServiceImpl;
import com.travelagency.app.web.service.impl.TourServiceImpl;
import com.travelagency.app.web.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.jsp.jstl.fmt.LocaleSupport;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {

    private static final Logger LOG = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LocaleSupport l;
        LOG.debug("Start context initialization");
        ServletContext context = sce.getServletContext();
        initServices(context);
        LOG.debug("Services initialized");
    }

    private void initServices(ServletContext context) {
        // create Dao if it required
        TourDAO tourDao = TourDAOImpl.getInstance();
        LOG.trace("created 'tourDao': " + tourDao);
        UserDAO userDao = UserDAOImpl.getInstance();
        LOG.trace("created 'userDao': " + userDao);
        OrderDAO orderDao = OrderDAOImpl.getInstance();
        LOG.trace("created 'orderDao': " + orderDao);

        // create services
        TourService tourService = new TourServiceImpl(tourDao);
        context.setAttribute("tourService", tourService);
        LOG.trace("context.setAttribute 'addService': " + tourService);

        UserService userService = new UserServiceImpl(userDao);
        context.setAttribute("userService", userService);
        LOG.trace("context.setAttribute 'addService': " + userService);

        OrderService orderService = new OrderServiceImpl(orderDao);
        context.setAttribute("orderService", orderService);
        LOG.trace("context.setAttribute 'addService': " + orderService);


        CommandContainer commands = new CommandContainer();
        ActionCommand command = new HomePageCommand();
        commands.addCommand("homePage", command);
        commands.addCommand(null, command);
        commands.addCommand("", command);
        command = new SetLocale();
        commands.addCommand("setLocale", command);

        command = new ErrorCommand();
        commands.addCommand("error", command);

        //User commands
        command = new RegisterFormCommand();
        commands.addCommand("registerForm", command);

        command = new RegisterCommand(userService);
        commands.addCommand("register", command);

        command = new LogInCommand(userService);
        commands.addCommand("login", command);

        command = new LoginFormCommand();
        commands.addCommand("loginForm", command);

        command = new LogOutCommand();
        commands.addCommand("logout", command);

        command = new PersonalAccountCommand();
        commands.addCommand("personalAccount", command);

        command = new BlockUserCommand(userService);
        commands.addCommand("blockUser", command);

        command = new UnblockUserCommand(userService);
        commands.addCommand("unblockUser", command);

        command = new EditUserCommand();
        commands.addCommand("editUser", command);

        command = new AllUsersListCommand(userService);
        commands.addCommand("allUsers", command);

        //Tour commands
        command = new AllToursListCommand(tourService);
        commands.addCommand("allTours", command);

        command = new AdminListOfToursCommand(tourService);
        commands.addCommand("adminListOfTours", command);

        command = new AllHotToursListCommand(tourService);
        commands.addCommand("hotTours", command);

        command = new CreateTourFormCommand();
        commands.addCommand("createTourForm", command);

        command = new CreateTourCommand(tourService);
        commands.addCommand("createTour", command);

        command = new DefineTourAsHotCommand(tourService);
        commands.addCommand("defineTourAsHot", command);

        command = new DeleteTourCommand(tourService);
        commands.addCommand("deleteTour", command);

        /*command = new CatchTourForEditionCommand(tourService);
        commands.addCommand("catchTourForEdition", command);*/

        command = new EditTourInfoFormCommand(tourService);
        commands.addCommand("editTourInfoForm", command);

        command = new EditTourInfoCommand(tourService);
        commands.addCommand("editTourInfo", command);

        command = new ToursByHotelTypeListCommand(tourService);
        commands.addCommand("toursByHotelType", command);

        command = new ToursByNumberOfPersonsListCommand(tourService);
        commands.addCommand("toursByNumberOfPersons", command);

        command = new ToursByPriceListCommand(tourService);
        commands.addCommand("toursByPrice", command);

        command = new ToursByTypeListCommand(tourService);
        commands.addCommand("toursByType", command);

        //Order commands
        command = new AddTourIntoOrderCommand(tourService, orderService);
        commands.addCommand("addTourIntoOrder", command);

        command = new SetTourStatusCommand();
        commands.addCommand("setTourStatus", command);

        command = new OrderFormCommand(tourService);
        commands.addCommand("orderForm", command);

        command = new FulfillOrderCommand(tourService);
        commands.addCommand("fulfillOrder", command);

        context.setAttribute("commandContainer", commands);
    }

}
