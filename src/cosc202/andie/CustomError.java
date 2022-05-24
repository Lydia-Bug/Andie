package cosc202.andie;

import javax.swing.*;


public class CustomError {
    String message;
    String title;
    
    public CustomError(String message, String title) {
        this.message = message;
        this.title = title;

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
