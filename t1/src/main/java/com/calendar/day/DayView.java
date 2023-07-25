package com.calendar.day;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.calendar.Event;
import com.calendar.Globals;
import com.calendar.HintTextInput;



public class DayView extends JPanel{
    private JLabel dayTitle;
    private JButton backBtn;
    private JButton addBtn;
    private JPanel eventsPanel;
    private JScrollPane eventsScroller;

    private JTextField titleInput;
    private JTextArea descriptionInput;
    private HintTextInput timeInput;
    private LinkedList<JButton> eventButtons;


    public DayView(){
        this.setLayout(new BorderLayout());
        
        // title panel at the top
        JPanel titlePanel = new JPanel();
        this.dayTitle = new JLabel("");
        this.dayTitle.setFont(Globals.mainFont);
        titlePanel.add(dayTitle);

        this.backBtn = new JButton("Back");
        titlePanel.add(backBtn);

        this.add(titlePanel, BorderLayout.NORTH);
        

        // central panel
        JPanel centralPanel = new JPanel(new GridLayout(2, 1));
        
        // list of events for the day 
        this.eventsPanel = new JPanel();
        this.eventsScroller = new JScrollPane(eventsPanel);
        this.eventButtons = new LinkedList<>();
        centralPanel.add(this.eventsScroller);
        
        // form to create new event
        JPanel newEvent = new JPanel(new GridLayout(4, 2));

        // event title input
        JLabel titleLbl = new JLabel("Event title: ");
        titleLbl.setFont(Globals.subFont);
        newEvent.add(titleLbl);

        this.titleInput = new JTextField();
        this.titleInput.setFont(Globals.subFont);
        newEvent.add(titleInput);

        // event description input
        JLabel descriptionLbl = new JLabel("Event description: ");
        descriptionLbl.setFont(Globals.subFont);
        newEvent.add(descriptionLbl);

        this.descriptionInput = new JTextArea(5, 1);
        this.descriptionInput.setFont(Globals.subFont);
        newEvent.add(this.descriptionInput);
        

        // event time input
        JLabel timeLbl = new JLabel("Event time");
        timeLbl.setFont(Globals.subFont);
        newEvent.add(timeLbl);

        this.timeInput = new HintTextInput("HH:MM");
        this.timeInput.setFont(Globals.subFont);
        this.setForeground(Color.black);
        newEvent.add(this.timeInput);

        this.addBtn = new JButton("Create new event");
        this.addBtn.setFont(Globals.subFont);
        newEvent.add(this.addBtn);

        centralPanel.add(newEvent);
        this.add(centralPanel, BorderLayout.CENTER);
    }

    public void showEvents(LinkedList<Event> events){
        this.eventsPanel.removeAll(); //clear events from previous day shown
        this.eventsPanel.setLayout(new GridLayout(Math.max(10, events.size()), 1));

        for (Event event: events){
            JButton eventBtn = new JButton(event.getTitle());
            eventBtn.putClientProperty("event", event);
            this.eventButtons.add(eventBtn); //add to linked list storing the buttons
            this.eventsPanel.add(eventBtn); //add to panel to display buttons
        }
        
        //refresh display
        this.revalidate();
        this.repaint();
    }

    public void setBackActionListener(ActionListener listener){
        this.backBtn.addActionListener(listener);
    }

    public void setDayTitle(int day, int month, int year){
        this.dayTitle.setText(day + "/" + month + "/" + year);
    }

    public void setNewEventListener(ActionListener listener){
        this.addBtn.addActionListener(listener);
    }

    public void setEventButtonListeners(ActionListener listener){
        for (JButton btn : this.eventButtons){
            btn.addActionListener(listener);
        }
    }

    public String getNewTitle(){
        return this.titleInput.getText();
    }

    public String getNewDescription(){
        return this.descriptionInput.getText();
    }

    public String getNewTime(){
        return this.timeInput.getText();
    }

    public void displayError(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
