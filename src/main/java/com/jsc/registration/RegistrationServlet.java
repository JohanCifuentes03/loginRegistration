package com.jsc.registration;


import com.jsc.registration.validations.UserRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher;
        String userName     = request.getParameter("name");
        String userEmail    = request.getParameter("email");
        String userPassword = request.getParameter("pass");
        String userMobile   = request.getParameter("contact");

        int rows   = UserRegistration.registerUsers(userName,userEmail,userPassword,userMobile);
        dispatcher = request.getRequestDispatcher("registration.jsp");
        if (rows > 0){
            request.setAttribute("status", "success");
        }else {
            request.setAttribute("status", "failed");
        }

        dispatcher.forward(request, response);

        }


}
