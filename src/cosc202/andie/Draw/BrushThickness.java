package cosc202.andie.Draw;
import javax.swing.*;

public class BrushThickness {
    private int thickness;

    public BrushThickness() {
        if(!DrawActions.operationCancelled) {
            JSlider thicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 2);

            thicknessSlider.setMajorTickSpacing(5);
            thicknessSlider.setMinorTickSpacing(1);
            thicknessSlider.setPaintTicks(true);
            thicknessSlider.setPaintLabels(true);
            int option = JOptionPane.showOptionDialog(null, thicknessSlider, "Select brush thickness",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                DrawActions.operationCancelled = true;
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

