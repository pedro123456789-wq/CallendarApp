package com.calendar.callendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.calendar.Globals;

public class CallendarView extends JPanel {
    private JLabel monthHeader;
    private JButton nextBtn;
    private JButton prevBtn;
    private LinkedList<JButton> dayButtons;

    public CallendarView() {
        this.setLayout(new BorderLayout());

        // header panel
        JPanel headerPanel = new JPanel();
        this.monthHeader = new JLabel("");
        this.monthHeader.setFont(Globals.mainFont);
        headerPanel.add(monthHeader);

        this.nextBtn = new JButton("Next");
        this.nextBtn.setFont(Globals.subFont);
        headerPanel.add(nextBtn);

        this.prevBtn = new JButton("Previous");
        this.prevBtn.setFont(Globals.subFont);
        headerPanel.add(prevBtn);

        this.add(headerPanel, BorderLayout.NORTH);

        // callendar grid pannel
        JPanel callendarGrid = new JPanel(new GridLayout(6, 6));
        this.dayButtons = new LinkedList<>();

        for (int i = 0; i < 36; i++) {
            JButton btn = new JButton("");
            callendarGrid.add(btn);
            this.dayButtons.add(i, btn);
        }

        this.add(callendarGrid, BorderLayout.CENTER);
    }

    public void setMonthHeader(String month, String year) {
        this.monthHeader.setText(month + " " + year);
    }

    public void buildGrid(int monthDays, Calendar selectedDate, Calendar dateToday) {
        // function to build a grid with the correct number of days for the month

        // check if the month displayed is the same as the month of the day today
        int selMonth = selectedDate.get(Calendar.MONTH);
        int selYear = selectedDate.get(Calendar.YEAR);
        int currMonth = dateToday.get(Calendar.MONTH);
        int currYear = dateToday.get(Calendar.YEAR);
        int currDay = dateToday.get(Calendar.DAY_OF_MONTH);
        boolean isSameMonth = false;

        if (selMonth == currMonth && selYear == currYear) {
            isSameMonth = true;
        }

        // add correct number to the number of days in the month
        for (int i = 0; i < monthDays; i++) {
            JButton btn = this.dayButtons.get(i);
            btn.setForeground(Color.black); // set all buttons to black first

            btn.setText("" + (i + 1));

            if (isSameMonth && currDay == i + 1) {
                btn.setForeground(Color.red);
            }

            btn.setEnabled(true);
        }

        // remove unecessay days that may have been left from previous month
        for (int i = monthDays; i < 36; i++) {
            JButton btn = this.dayButtons.get(i);
            btn.setForeground(Color.black);
            btn.setText("");
            btn.setEnabled(false);
        }
    }

    public void addNextActionListener(ActionListener listener) {
        this.nextBtn.addActionListener(listener);
    }

    public void addPreviousActionListener(ActionListener listener) {
        this.prevBtn.addActionListener(listener);
    }

    public void addDayButtonListeners(ActionListener listener) {
        for (int i = 0; i < 36; i++) {
            JButton btn = this.dayButtons.get(i);
            btn.putClientProperty("day", i + 1);
            btn.addActionListener(listener);
        }
    }
}