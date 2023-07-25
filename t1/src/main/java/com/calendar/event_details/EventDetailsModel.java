package com.calendar.event_details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import com.calendar.Event;
import com.calendar.GUI;
import com.calendar.Globals;

public class EventDetailsModel {
    private Event event;

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getEventTitle() {
        return this.event.getTitle();
    }

    public String getDescription() {
        return this.event.getDescription();
    }

    public String getTime() {
        Calendar date = this.event.getDate();
        return date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE);
    }

    public String[] getEventID(String eventTitle, String dateString) {
        String query = "SELECT callendar_db.event.eventID FROM callendar_db.event INNER JOIN callendar_db.user" +
                " WHERE callendar_db.user.userID = " + GUI.userID +
                " AND callendar_db.event.title = '" + eventTitle + "' " +
                "AND callendar_db.event.date = '" + dateString + "';";

        try (Connection connection = DriverManager.getConnection(Globals.dbUrl, Globals.dbUsername,
                Globals.dbPassword)) {
            try (Statement statement = connection.createStatement()) {
                ResultSet result = statement.executeQuery(query);
                result.next(); // get first result from the set
                return new String[] { "true", result.getString("eventID") };
            } catch (SQLException ex) {
                return new String[] { "false", "null" };
            }
        } catch (SQLException ex) {
            return new String[] { "false", "null" };
        }
    }

    public String[] updateEvent(String newTitle, String newDescription, String newTime) {
        // method to update the title, description and time of a given event
        if (newTitle.equals("") || newDescription.equals("") || newTime.equals("")) {
            return new String[] { "false", "You must enter a value for all input fields" };
        }

        String[] idResp = this.getEventID(this.event.getTitle(), this.event.getDateString());
        String eventID = "0";

        if (idResp[0].equals("false")) {
            return new String[] { "false", idResp[1] };
        } else {
            eventID = idResp[1];
        }

        // once id of the event is obtained update the database (query 2)
        String newDate = this.event.getDateString().split(" ")[0] + " " + newTime; // create new date string by
                                                                                   // attaching new time to the end of
                                                                                   // the current date
        String updateQuery = "UPDATE callendar_db.event" +
                " SET title = '" + newTitle + "'," +
                " description = '" + newDescription + "'," +
                " date = '" + newDate + "'" +
                " WHERE callendar_db.event.eventID = " + eventID + ";";

        // update the local event object as well
        this.event.setTitle(newTitle);
        this.event.setDescription(newDescription);
        this.event.setDate(newDate);

        try (Connection connection = DriverManager.getConnection(Globals.dbUrl, Globals.dbUsername,
                Globals.dbPassword)) {
            try (Statement statement2 = connection.createStatement()) {
                statement2.executeUpdate(updateQuery);
                return new String[] { "true", "Successful update" };
            } catch (SQLException ex) {
                return new String[] { "false", ex.getMessage() };
            }
        } catch (SQLException ex) {
            return new String[] { "false", ex.getMessage() };
        }
    }

    public String[] deleteEvent() {
        String[] idResp = this.getEventID(this.event.getTitle(), this.event.getDateString());
        String eventID = "0";

        if (idResp[0].equals("false")) {
            return new String[] { "false", idResp[1] };
        } else {
            eventID = idResp[1];
        }

        String deleteQuery = "DELETE FROM callendar_db.event "
                + "WHERE callendar_db.event.eventID = " + eventID;

        try (Connection connection = DriverManager.getConnection(Globals.dbUrl, Globals.dbUsername,
                Globals.dbPassword)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(deleteQuery);
                return new String[] { "true", "Event successfully deleted" };
            } catch (SQLException ex) {
                return new String[] { "false", ex.getMessage() };
            }
        } catch (SQLException ex) {
            return new String[] { "false", ex.getMessage() };
        }

    }
}
