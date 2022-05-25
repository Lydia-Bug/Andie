/**
 * Allows the user to select a colour from the JColorChooser GUI.
 * @author Ella Taylor
 */

package cosc202.andie.Draw;

import java.awt.Color;
import javax.swing.*;

public class ColorPicker {
    protected Color penColor;
    protected String titleText;

    public ColorPicker(String titleText) {
        if (!CancelDrawOperation.drawCancelled) {
            JColorChooser cc = new JColorChooser();

            int option = JOptionPane.showOptionDialog(null, cc, titleText,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                CancelDrawOperation.drawCancelled = true;
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                penColor = cc.getColor();
            }
        }
    }

    /**
     * Accessor for pen colour.
     * @return penColor, colour of graphics2D object
     */
    public Color getPenColor() {
        return penColor;
    }
}
