/**
 * <p>
 * Provides a JSlider allowing the user to adjust brush thickness. 
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Ella Taylor
 * @version 1.0
 */
package cosc202.andie.Draw;
import javax.swing.*;

public class BrushThickness {
    /**
     * Width of brush.
     */
    private int thickness;


    public BrushThickness() {
        if(!CancelDrawOperation.drawCancelled) {
            JSlider thicknessSlider = new JSlider(JSlider.HORIZONTAL, 1, 21, 2);

            thicknessSlider.setMajorTickSpacing(5);
            thicknessSlider.setMinorTickSpacing(1);
            thicknessSlider.setPaintTicks(true);
            thicknessSlider.setPaintLabels(true);
            int option = JOptionPane.showOptionDialog(null, thicknessSlider, "Select brush thickness",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                CancelDrawOperation.drawCancelled = true;
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                thickness = (int) thicknessSlider.getValue();
            }
        }
    }

    public int getThickness() {
        return thickness;
    }
}

