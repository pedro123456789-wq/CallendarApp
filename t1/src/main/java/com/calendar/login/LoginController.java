package com.calendar.login;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

// controller object for login system (using MVC architecture)
public class LoginController {
    private LoginView view;
    private LoginModel model;
    private JPanel pages; // need access to the root JPanel to handle navigation

    public LoginController(LoginView view,
            LoginModel model,
            JPanel pages) {
        this.view = view;
        this.model = model;
        this.pages = pages;
    }

    public void initController() {
        this.view.setLoginActionLister(new LoginListener()); // add action listener for the login button
        this.view.setSignUpActionListener(new SignUpListener()); // add action listener for the sign up button
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String username = view.getUsername();
                String password = view.getPassword();

                // perform login
                model.tryLogin(username, password);
                String[] state = model.tryLogin(username, password);

                if (state[0].equals("true")) {
                    // if the user provides valid crednetials navigate to the main callendar section
                    CardLayout cl = (CardLayout) pages.getLayout();
                    cl.show(pages, "callendar");
                } else {
                    view.displayError(state[1]);
                }
            } catch (Exception ex) {
                view.displayError(ex.getMessage());
            }
        }
    }

    class SignUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) pages.getLayout();
            cl.show(pages, "sign-up");
        }
    }
}
