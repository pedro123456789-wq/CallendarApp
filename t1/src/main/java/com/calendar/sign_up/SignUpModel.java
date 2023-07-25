package com.calendar.sign_up;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.calendar.Globals;


public class SignUpModel {
    public String[] trySignUp(String username, String password){
        try (Connection connection = DriverManager.getConnection(
                    Globals.dbUrl,
                    Globals.dbUsername, 
                    Globals.dbPassword)){

            String query = "INSERT INTO callendar_db.user(username, password) VALUES ('" + username + "', '" + password + "')";
            
            try (Statement statement = connection.createStatement()){
                statement.executeUpdate(query); //executeUpdate is used instead of executeQuery since the statement does not return any results from the database
                return new String[]{"true", "Account created successfully. You can now log in."};
            }catch (SQLException e){
                return new String[]{"false", e.getMessage()};
            }
        }catch (SQLException e){
            return new String[]{"false", e.getMessage()};
        }
    }
}
