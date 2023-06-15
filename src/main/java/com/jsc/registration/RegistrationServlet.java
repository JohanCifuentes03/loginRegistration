package com.jsc.registration;

import com.jsc.data.UserDAO;
import com.jsc.entity.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String userName     = request.getParameter("name");
        String userEmail    = request.getParameter("email");
        String userPassword = request.getParameter("pass");
        String userMobile   = request.getParameter("contact");

        User user = new User(userName, userEmail, userMobile, userPassword);

        try {
            UserDAO userDAO  = new UserDAO();
            int rowsAffected = userDAO.insert(user);
            dispatcher = request.getRequestDispatcher("registration.jsp");
            if (rowsAffected > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
            userDAO.closeConnection();
        } catch (SQLException e) {
            request.setAttribute("status", "failed");
            request.setAttribute("errorMessage", "Error in database operation: " + e.getMessage());
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        registerUser(request, response);
    }

}
