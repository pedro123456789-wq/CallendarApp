package com.calendar;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;


// modification of the JTextField built-in class to display hint
// focus listener interface is used to define the behaviour of the input box when it is selected de-selected
public class HintTextInput extends JTextField implements FocusListener{
    private String hint;

    public HintTextInput(String hint){
        this.hint = hint;
        this.setText(hint);
        this.setForeground(Color.gray);
        this.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().equals(this.hint)){
            this.setText("");
        }
        
        this.setForeground(this.getForeground());
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().equals("")){
            this.setText(this.hint);
            this.setForeground(Color.gray);
        }else{
            this.setForeground(this.getForeground());
        }
    }
}
