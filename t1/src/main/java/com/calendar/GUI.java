package com.calendar;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.calendar.callendar.CallendarController;
import com.calendar.callendar.CallendarModel;
import com.calendar.callendar.CallendarView;
import com.calendar.day.DayController;
import com.calendar.day.DayModel;
import com.calendar.day.DayView;
import com.calendar.event_details.EventDetailsController;
import com.calendar.event_details.EventDetailsModel;
import com.calendar.event_details.EventDetailsView;
import com.calendar.login.LoginController;
import com.calendar.login.LoginModel;
import com.calendar.login.LoginView;
import com.calendar.sign_up.SignUpController;
import com.calendar.sign_up.SignUpModel;
import com.calendar.sign_up.SignUpView;

public class GUI extends JFrame {
    public static DayController dayController;
    public static EventDetailsController eventDetailsController;

    public static String username;
    public static String userID;

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calendar App");
        setSize(800, 800);
        setResizable(false);

        JPanel pages = new JPanel(new CardLayout());

        // login page
        LoginView loginView = new LoginView();
        LoginModel loginModel = new LoginModel();
        LoginController loginController = new LoginController(loginView, loginModel, pages);
        loginController.initController();
        pages.add(loginView, "log-in");

        // sign up page
        SignUpView signUpView = new SignUpView();
        SignUpModel signUpModel = new SignUpModel();
        SignUpController signUpController = new SignUpController(signUpView, signUpModel, pages);
        signUpController.initController();
        pages.add(signUpView, "sign-up");

        // callendar page
        CallendarView callendarView = new CallendarView();
        CallendarModel callendarModel = new CallendarModel();
        CallendarController callendarController = new CallendarController(callendarView, callendarModel, pages);
        callendarController.initController();
        pages.add(callendarView, "callendar");

        // day section
        DayView dayView = new DayView();
        DayModel dayModel = new DayModel();
        dayController = new DayController(dayView, dayModel, pages);
        dayController.initController();
        pages.add(dayView, "day-view");

        // event details
        EventDetailsView eventDetailsView = new EventDetailsView();
        EventDetailsModel eventDetailsModel = new EventDetailsModel();
        eventDetailsController = new EventDetailsController(eventDetailsModel, eventDetailsView, pages);
        pages.add(eventDetailsView, "event-details");

        this.add(pages);
        CardLayout cl = (CardLayout) pages.getLayout();
        cl.show(pages, "log-in");
    }
}
