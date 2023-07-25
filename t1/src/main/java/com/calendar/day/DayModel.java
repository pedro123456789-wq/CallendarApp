package com.calendar.day;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;

import com.calendar.Event;
import com.calendar.GUI;
import com.calendar.Globals;

public class DayModel {
    private Calendar date;
    private LinkedList<Event> events;

    public DayModel() {
        this.events = new LinkedList<>();
    }

    public Calendar getDate() {
        return this.date;
    }

    public String getDateString() {
        return this.dateToString(this.date);
    }

    public void setDate(Calendar date) {
        // reset model to display events for new day
        this.date = date;
        this.events = new LinkedList<>();
    }

    public String dateToString(Calendar date) {
        // convert Calendar object to datetime string format used in MySQL
        String day = String.format("%02d", date.get(Calendar.DAY_OF_MONTH));
        String month = String.format("%02d", date.get(Calendar.MONTH) + 1);
        String year = String.format("%04d", date.get(Calendar.YEAR));
        return year + "-" + month + "-" + day;
    }

    public LinkedList<Event> fetchEvents() {
        this.events = new LinkedList<>(); // reset events linked list

        // get start and end dates which give events from a given day
        String startDate = this.dateToString(this.date);
        this.date.add(Calendar.DAY_OF_MONTH, 1);
        String endDate = this.dateToString(this.date);
        this.date.add(Calendar.DAY_OF_MONTH, -1);

        // create SQL query
        String query = "SELECT title, description, date " +
                "FROM callendar_db.event " +
                "INNER JOIN callendar_db.user " +
                "ON callendar_db.event.userID = callendar_db.user.userID " +
                "WHERE callendar_db.user.username = '" + GUI.username + "' " +
                "and callendar_db.event.date >= '" + startDate + "' " +
                "and callendar_db.event.date <= '" + endDate + "' " +
                "ORDER BY date;";

        // execute sql statement and save events in linked list
        try (Connection connection = DriverManager.getConnection(Globals.dbUrl, Globals.dbUsername,
                Globals.dbPassword)) {
            try (Statement statement = connection.createStatement()) {
                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    String title = results.getString("title");
                    String description = results.getString("description");
                    String eventDate = results.getString("date");

                    Event event = new Event(title, eventDate, description);
                    this.events.add(event);
                }

                return this.events;
            } catch (SQLException ex) {
                return new LinkedList<>();
            }
        } catch (SQLException ex) {
            return new LinkedList<>();
        }
    }

    public String[] addEvent(String title, String description, String time) {
        String queryDate = this.dateToString(this.date) + " " + time;
        String query = "INSERT INTO callendar_db.event(userID, title, description, date) "
                + "VALUES (" + GUI.userID + ", '" + title + "' , '" + description + "', '" + queryDate + "');";

        try (Connection connection = DriverManager.getConnection(Globals.dbUrl, Globals.dbUsername,
                Globals.dbPassword)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
                Event newEvent = new Event(title, queryDate, description); // adding the event like this means that one
                                                                           // does not need to fecth all the events from
                                                                           // the db again
                this.events.add(newEvent);

                return new String[] { "true", "Added event successfully" };
            } catch (SQLException ex) {
                return new String[] { "false", ex.getMessage() };
            }
        } catch (SQLException ex) {
            return new String[] { "false", ex.getMessage() };
        }
    }

    public LinkedList<Event> getEvents() {
        return this.events;
    }
}
