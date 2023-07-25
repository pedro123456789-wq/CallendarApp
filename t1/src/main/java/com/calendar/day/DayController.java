package com.calendar.day;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.calendar.Event;
import com.calendar.GUI;

public class DayController {
    private DayView view;
    private DayModel model;
    private JPanel pages;

    public DayController(DayView view, DayModel model, JPanel pages) {
        this.view = view;
        this.model = model;
        this.pages = pages;
    }

    public void initController() {
        this.view.setBackActionListener(new BackActionListener());
        this.view.setNewEventListener(new AddEventListener());
    }

    public void setDate(Calendar date) {
        this.model.setDate(date);
        LinkedList<Event> events = this.model.fetchEvents();

        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1;
        int year = date.get(Calendar.YEAR);

        this.view.setDayTitle(day, month, year);
        this.view.showEvents(events);
        this.view.setEventButtonListeners(new EventButtonListener());
    }

    public void refreshEvents() {
        LinkedList<Event> events = this.model.fetchEvents();
        this.view.showEvents(events);
        this.view.setEventButtonListeners(new EventButtonListener());
    }

    class BackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) pages.getLayout();
            cl.show(pages, "callendar");
        }
    }

    class AddEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String description = view.getNewDescription();
            String title = view.getNewTitle();
            String time = view.getNewTime();

            System.out.println(time);
            String[] result = model.addEvent(title, description, time);

            if (result[0].equals("true")) {
                LinkedList<Event> events = model.getEvents();
                view.showEvents(events);
            } else {
                view.displayError(result[1]);
            }
        }
    }

    class EventButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // get the event associated with the button clicked
            JButton sourceBtn = (JButton) e.getSource();
            Event targetEvent = (Event) sourceBtn.getClientProperty("event");

            // store the event in the event model by updating it through the event
            // controller
            GUI.eventDetailsController.setEvent(targetEvent);

            // navigate to the event details view
            CardLayout cl = (CardLayout) pages.getLayout();
            cl.show(pages, "event-details");
        }

    }
}
