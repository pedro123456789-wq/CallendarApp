package com.calendar.sign_up;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SignUpController {
    private SignUpView view;
    private SignUpModel model;
    private JPanel pages;

    public SignUpController(SignUpView view, SignUpModel model, JPanel pages) {
        this.view = view;
        this.model = model;
        this.pages = pages;
    }

    public void initController() {
        this.view.setBackListener(new BackListener());
        this.view.setSubmitListener(new SubmitListener());
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) pages.getLayout();
            cl.show(pages, "log-in");
        }
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String username = view.getUsername();
                String password = view.getPassword();

                // show sign up message
                String[] status = model.trySignUp(username, password);
                view.showMessage(status[1]);

                // if sign up is successful re-direct user to the log in page
                if (status[0].equals("true")) {
                    CardLayout cl = (CardLayout) pages.getLayout();
                    cl.show(pages, "log-in");
                }
            } catch (Exception ex) {
                view.showMessage(ex.getMessage());
            }
        }
    }
}
