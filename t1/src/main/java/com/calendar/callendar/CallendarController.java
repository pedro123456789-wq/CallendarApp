package com.calendar.callendar;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.calendar.GUI;

public class CallendarController {
    private CallendarView view;
    private CallendarModel model;
    private JPanel pages;

    public CallendarController(CallendarView view, CallendarModel model, JPanel pages) {
        this.view = view;
        this.model = model;
        this.pages = pages;
    }

    public void initController() {
        // add appropriate action listeners
        this.view.addPreviousActionListener(new PreviousActionListener());
        this.view.addNextActionListener(new NextActionListener());
        this.view.addDayButtonListeners(new DayButtonListener());

        // set view to current month and year
        this.view.setMonthHeader(this.model.getMonthSelected(), this.model.getYearSelected());
        this.view.buildGrid(this.model.getSelectedMonthDays(), this.model.getDateSelected(), this.model.getDateToday());
    }

    class NextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // make change to model
            model.incrementMonth();

            // update view
            view.setMonthHeader(model.getMonthSelected(), model.getYearSelected());
            view.buildGrid(model.getSelectedMonthDays(),
                    model.getDateSelected(),
                    model.getDateToday());
        }
    }

    class PreviousActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // make change to model
            model.decrementMonth();

            // update view
            view.setMonthHeader(model.getMonthSelected(), model.getYearSelected());
            view.buildGrid(model.getSelectedMonthDays(),
                    model.getDateSelected(),
                    model.getDateToday());
        }
    }

    class DayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            // for each button in the calendar grid convert its index into a date
            int day = (int) btn.getClientProperty("day");
            int currMonth = model.getDateSelected().get(Calendar.MONTH);
            int currYear = model.getDateSelected().get(Calendar.YEAR);

            Calendar date = Calendar.getInstance();
            date.set(Calendar.MONTH, currMonth);
            date.set(Calendar.YEAR, currYear);
            date.set(Calendar.DAY_OF_MONTH, day);

            CardLayout cl = (CardLayout) pages.getLayout();
            cl.show(pages, "day-view");

            // set the day section to the date selected by the user through the button press
            GUI.dayController.setDate(date);
        }
    }
}
