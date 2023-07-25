package com.calendar.sign_up;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.calendar.Globals;



public class SignUpView extends JPanel{
    private JButton backBtn;
    private JButton submitBtn;
    
    private JTextField usrInput = new JTextField(20);
    private JPasswordField passInput = new JPasswordField(20);
    
    public SignUpView(){
        this.setLayout(new BorderLayout());
        
        //top pannel
        JPanel titlePanel = new JPanel();
        FlowLayout leftLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
        titlePanel.setLayout(leftLayout);
        
        JLabel title = new JLabel("Sign Up");
        title.setFont(Globals.mainFont);
        titlePanel.add(title);

        this.add(titlePanel, BorderLayout.NORTH);

        //sign-up form
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
        this.submitBtn = new JButton("Create account");
        this.submitBtn.setFont(Globals.mainFont);
        formPanel.add(this.submitBtn, c);

        this.add(formPanel, BorderLayout.CENTER);

        //bottom pannel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));

        this.backBtn = new JButton("Back");
        this.backBtn.setFont(Globals.subFont);
        bottomPanel.add(backBtn);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public String getUsername(){
        return this.usrInput.getText();
    }

    public String getPassword(){
        return new String(this.passInput.getPassword());
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    public void setBackListener(ActionListener listener){
        this.backBtn.addActionListener(listener);
    }

    public void setSubmitListener(ActionListener listener){
        this.submitBtn.addActionListener(listener);
    }
}
