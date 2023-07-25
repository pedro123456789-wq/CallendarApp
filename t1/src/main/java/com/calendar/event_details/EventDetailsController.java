package com.calendar.event_details;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import com.calendar.Event;
import com.calendar.GUI;

public class EventDetailsController {
    private EventDetailsModel model;
    private EventDetailsView view;
    private JPanel pages;

    public EventDetailsController(EventDetailsModel model, EventDetailsView view, JPanel pages) {
        this.model = model;
        this.view = view;
        this.pages = pages;
    }

    public void initController() {
        this.view.addBackBtnListener(new BackBtnListener());
    }

    public void setEvent(Event event) {
        // called when user navigates to event page to display the details of the event
        // selected
        this.model.setEvent(event);
        this.view.setUpView(this.model.getEventTitle(), this.model.getTime(), this.model.getDescription());
        this.view.addEditActionListener(new EditButtonListener());
        this.view.addDeleteBtnListener(new DeleteButtonListener());
    }

    class BackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GUI.dayController.refreshEvents(); // fetch the events from the database again when the day view is loaded,
                                               // in case any events have been changed
            CardLayout cl = (CardLayout) pages.getLayout();
            cl.show(pages, "day-view");
        }
    }

    class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] result = model.updateEvent(view.getNewTitle(), view.getNewDescription(), view.getNewTime());

            if (result[0].equals("false")) {
                // if update is not successful show error message
                view.showMessage(result[1]);
            } else {
                // if update is successful update the view with the new event data
                view.setUpView(model.getEventTitle(), model.getTime(), model.getDescription());
            }
        }
    }

    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] result = model.deleteEvent();

            if (result[0].equals("false")) {
                // if there was an error deleting the event show error message popup
                view.showMessage(result[1]);
            } else {
                // move back to the day view, since event no longer exists
                GUI.dayController.refreshEvents();
                CardLayout cl = (CardLayout) pages.getLayout();
                cl.show(pages, "day-view");
            }
        }
    }
}
