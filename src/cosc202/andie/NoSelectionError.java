package cosc202.andie;

import javax.swing.*;


public class NoSelectionError {
    
    public NoSelectionError() {
        JOptionPane.showMessageDialog(null, "Please select the image region you wish to apply this operation to", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
