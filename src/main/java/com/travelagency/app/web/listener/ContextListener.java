package com.travelagency.app.web.listener;

import com.travelagency.app.web.command.ActionCommand;
import com.travelagency.app.web.command.CommandContainer;
import com.travelagency.app.web.command.IndexCommand;
import com.travelagency.app.web.command.tour.AllToursListCommand;
import com.travelagency.app.dao.TourDAO;
import com.travelagency.app.dao.impl.TourDAOImpl;
import com.travelagency.app.web.service.impl.TourServiceImpl;
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

        // create services
        TourServiceImpl tourService = new TourServiceImpl(tourDao);
        context.setAttribute("tourService", tourService);
        LOG.trace("context.setAttribute 'addService': " + tourService);

        CommandContainer commands = new CommandContainer();
        ActionCommand command = new IndexCommand();
        commands.addCommand(null, command);
        commands.addCommand("", command);

        // add product flow
//        command = new AddProductFormCommand();
//        commands.addCommand("addProductForm", command);
//        command = new AddProductCommand(productService);
//        commands.addCommand("addProduct", command);
        command = new AllToursListCommand(tourService);
        commands.addCommand("allTours", command);

        context.setAttribute("commandContainer", commands);
    }

}
