package com.calendar;

// Improvements
// Hash passwords
// Add reminders
// Allow toggle through day screen
// Add logout option
// Add persistent login storage
// add validation for event time
// use text box for description inputs
// clean up the code
// create class for sql responses
// create global state class instead of using static variables

public class App {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
}