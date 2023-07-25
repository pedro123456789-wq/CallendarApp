package com.calendar;

import java.util.Calendar;

public class Event {
    // use of encapsulation
    private String title;
    private Calendar date;
    private String description;
    private String dateString;

    public Event(String title, String date, String description){
        this.title = title;
        this.date = this.dateStringToCalendar(date);
        this.dateString = date;
        this.description = description;
    }

    private Calendar dateStringToCalendar(String dateString){
        // convert date string in MySQL format to Calendar object
        String[] YMD = dateString.split(" ")[0].split("-"); //[year, month, day]
        String[] hm = dateString.split(" ")[1].split(":"); //[hours, minutes]

        // create new calendar object and set the correct values to represent the current day
        Calendar dateObj = Calendar.getInstance();
        dateObj.set(Calendar.YEAR, Integer.parseInt(YMD[0]));
        dateObj.set(Calendar.MONTH, Integer.parseInt(YMD[1]) - 1);
        dateObj.set(Calendar.DAY_OF_MONTH, Integer.parseInt(YMD[2]));
        dateObj.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hm[0]));
        dateObj.set(Calendar.MINUTE, Integer.parseInt(hm[1]));

        return dateObj;
    }

    public String getTitle(){
        return this.title;
    }

    public Calendar getDate(){
        return this.date;
    }
    
    public String getDescription(){
        return this.description;
    }

    public String getDateString(){
        return this.dateString;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setDate(String date){
        this.date = this.dateStringToCalendar(date);
        this.dateString = date;
    }
}
