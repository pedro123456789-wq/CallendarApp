package com.calendar.event_details;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.calendar.Globals;
import com.calendar.HintTextInput;

public class EventDetailsView extends JPanel {
    private JLabel eventTitle;
    private JLabel eventDescription;
    private JButton backBtn;

    private JPanel eventEditForm;
    private JLabel titleLbl;
    private JLabel timeLbl;
    private JLabel descLbl;
    private HintTextInput titleInput;
    private HintTextInput timeInput;
    private HintTextInput descInput;
    private JButton editEventBtn;
    private JButton deleteEventBtn;

    public EventDetailsView() {
        this.setLayout(new BorderLayout());

        // title pannel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.eventTitle = new JLabel("Title");
        this.eventTitle.setFont(Globals.mainFont);

        this.backBtn = new JButton("Back");
        this.backBtn.setFont(Globals.subFont);

        titlePanel.add(backBtn);
        titlePanel.add(eventTitle);
        this.add(titlePanel, BorderLayout.NORTH);

        // main content pannel
        JPanel content = new JPanel(new GridLayout(2, 1));

        // event description
        this.eventDescription = new JLabel("desc");
        this.eventDescription.setFont(Globals.subFont);
        content.add(eventDescription);

        // form to edit event
        this.eventEditForm = new JPanel(new GridLayout(4, 2));
        content.add(this.eventEditForm);

        this.add(content, BorderLayout.CENTER);
    }

    public void setUpView(String title, String time, String description) {
        // set up the view using the data from the model

        // display event title
        this.eventEditForm.removeAll();
        this.eventTitle.setText(title + " - " + time);
        this.eventDescription.setText("Description: \n" + description);

        // create inputs where the hint texts are the current values
        this.titleInput = new HintTextInput(title);
        this.timeInput = new HintTextInput(time);
        this.descInput = new HintTextInput(description);

        // create form labels
        this.titleLbl = new JLabel("Title: ");
        this.titleLbl.setFont(Globals.subFont);

        this.timeLbl = new JLabel("Time: ");
        this.timeLbl.setFont(Globals.subFont);

        this.descLbl = new JLabel("Description: ");
        this.descLbl.setFont(Globals.subFont);

        this.editEventBtn = new JButton("Make changes");
        this.editEventBtn.setFont(Globals.subFont);

        this.deleteEventBtn = new JButton("Delete event");
        this.deleteEventBtn.setForeground(Color.red);
        this.deleteEventBtn.setFont(Globals.subFont);

        // add labels and inputs to create form
        this.eventEditForm.add(this.titleLbl);
        this.eventEditForm.add(this.titleInput);
        this.eventEditForm.add(this.timeLbl);
        this.eventEditForm.add(this.timeInput);
        this.eventEditForm.add(this.descLbl);
        this.eventEditForm.add(this.descInput);
        this.eventEditForm.add(this.editEventBtn);
        this.eventEditForm.add(this.deleteEventBtn);

        // refresh window
        this.revalidate();
        this.repaint();
    }

    public void addEditActionListener(ActionListener listener) {
        // must be called after 'setUpView'
        this.editEventBtn.addActionListener(listener);
    }

    public void addBackBtnListener(ActionListener listener) {
        // add action listener to back button to allow user to return to day view
        this.backBtn.addActionListener(listener);
    }

    public void addDeleteBtnListener(ActionListener listener) {
        this.deleteEventBtn.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getNewTitle() {
        return this.titleInput.getText();
    }

    public String getNewTime() {
        return this.timeInput.getText();
    }

    public String getNewDescription() {
        return this.descInput.getText();
    }
}