package com.calendar.login;
import javax.swing.*;

import com.calendar.Globals;

import java.awt.*;
import java.awt.event.ActionListener;



public class LoginView extends JPanel{
    private JTextField usrInput = new JTextField(20);
    private JPasswordField passInput = new JPasswordField(20);
    private JButton submitButton = new JButton("Login");
    private JButton signUpButton = new JButton("Create new account");


    public LoginView(){
        this.setLayout(new BorderLayout());
        
        // title section
        JPanel titlePanel = new JPanel();
        FlowLayout leftLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);

        titlePanel.setLayout(leftLayout);
        JLabel title = new JLabel("Calendar App - Login");
        title.setFont(Globals.mainFont);
        titlePanel.add(title);
        this.add(titlePanel, BorderLayout.NORTH);

        // form section
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
    
        JLabel usernameLbl = new JLabel("Username: ");
        usernameLbl.setFont(Globals.subFont);
        
        JLabel passwordLbl = new JLabel("Password: ");
        passwordLbl.setFont(Globals.subFont);
        
        this.passInput.setFont(Globals.subFont);
        this.usrInput.setFont(Globals.subFont);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(usernameLbl, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(usrInput, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(passwordLbl, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(passInput, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.submitButton.setFont(Globals.mainFont);
        formPanel.add(this.submitButton, c);

        this.add(formPanel, BorderLayout.CENTER);


        // create new account section
        FlowLayout rightLayout = new FlowLayout(FlowLayout.RIGHT, 15, 15);
        JPanel sumbitSection = new JPanel(rightLayout);
       
        this.signUpButton.setFont(Globals.subFont);
        this.signUpButton.setForeground(Color.BLUE);
        sumbitSection.add(this.signUpButton);

        this.add(sumbitSection, BorderLayout.SOUTH);
    }

    public String getUsername(){
        return this.usrInput.getText();
    }

    public String getPassword(){
        return new String(this.passInput.getPassword());
    }

    public void displayError(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void setLoginActionLister(ActionListener listener){
        this.submitButton.addActionListener(listener);
    }

    public void setSignUpActionListener(ActionListener listener){
        this.signUpButton.addActionListener(listener);
    }
}
