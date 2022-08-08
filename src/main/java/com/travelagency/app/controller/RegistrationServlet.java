package com.travelagency.app.controller;


import com.travelagency.app.model.entity.User;
import com.travelagency.app.model.entity.constant.Role;
import com.travelagency.app.model.service.UserService;
import com.travelagency.app.util.DataSourceConnection;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    /*@Override
    public void init() throws ServletException {
        super.init();
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = DataSourceConnection.getInstance().getConnection();
        Statement stm = null;
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * FROM tours");
            while (rs.next()) {
                String title = rs.getString("tour_name_eng");
                response.getWriter().println(title);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*@Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user = User.newUserBuilder()
                .setFirstName(request.getParameter("first_name"))
                .setLastName(request.getParameter("last_name"))
                .setLogin(request.getParameter("email_login"))
                .setPassword(request.getParameter("password"))
                .setInstagram(request.getParameter("instagram"))
                .setPhoneNumber(request.getParameter("phone_number"))
                .setRole(Role.valueOf(request.getParameter("role")))
                .setIsBlocked(Boolean.parseBoolean(request.getParameter("is_blocked")))
                .build();
        UserService userService = new UserService();
        userService.insertUser(user);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/home.jsp");
        requestDispatcher.forward(request, response);

}*/


}


