package com.calendar.login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.calendar.GUI;
import com.calendar.Globals;



public class LoginModel {
    public String[] tryLogin(String username, String password){
        try (Connection connection = DriverManager.getConnection(
                    Globals.dbUrl,
                    Globals.dbUsername, 
                    Globals.dbPassword)){

            String query = "SELECT userID, username, password FROM callendar_db.user WHERE username = '" + username + "'";
            
            try (Statement statement = connection.createStatement()){
                ResultSet result = statement.executeQuery(query);

                while (result.next()){
                    String extractedPsw = result.getString("password");
                    String userID = result.getString("userID");

                    if (extractedPsw.equals(password)){
                        GUI.username = username;
                        GUI.userID = userID;
                        
                        return new String[]{"true", "Logged in"};
                    }
                }

                return new String[]{"false", "Invalid Credentials"};
            } catch (SQLException e){
                return new String[]{"false", e.getMessage()};
            }
            
        }catch (SQLException e){
            return new String[]{"false", e.getMessage()};
        }   
    }
}
