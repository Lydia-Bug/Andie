package cosc202.andie;

import javax.swing.*;


public class NoLoadedImageError {
    
    public NoLoadedImageError() {
        JOptionPane.showMessageDialog(null, "No image loaded into workspace!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
