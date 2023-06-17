package com.jsc.login;

import com.jsc.data.UserDAO;
import com.jsc.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String userName     = request.getParameter("username");
        String userPsw      = request.getParameter("password");
        HttpSession session = request.getSession();


        try{
            UserDAO userDAO = new UserDAO();
            User user       = userDAO.searchByEmailPws(userName, userPsw);
            if (user != null) {
                session.setAttribute("name", user.getName());
                dispatcher = request.getRequestDispatcher("index.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }

            userDAO.closeConnection();

            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("An error occurred while accessing the database.", e);
        }
    }
}
