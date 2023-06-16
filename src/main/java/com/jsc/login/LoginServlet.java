package com.jsc.login;

import com.jsc.data.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String userPsw = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        try{
            UserDAO userDAO     = new UserDAO();
            ResultSet resultSet = userDAO.searchByEmailPws(userName, userPsw);
            if (resultSet !=null && resultSet.next()) {
                session.setAttribute("name", resultSet.getString("username"));
                dispatcher = request.getRequestDispatcher("index.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }

            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("An error occurred while accessing the database.", e);
        }
    }
}
