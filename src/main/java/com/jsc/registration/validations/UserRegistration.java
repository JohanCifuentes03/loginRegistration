package com.jsc.registration.validations;

import com.jsc.data.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistration {

    public static int registerUsers(String name, String email, String pass, String contact){
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String query      = "INSERT INTO users (username, email, phone, password)" + "VALUES (?, ?, ?, ?)";
            connection        = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,contact);
            preparedStatement.setString(4,pass);

            rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        }catch (SQLException e){
            e.printStackTrace(System.out);
        }finally {
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }

                if(connection != null){
                    connection.close();
                }

            }catch (SQLException e){
                e.printStackTrace(System.out);
            }
        }
        return  rowsAffected;
    }
}
