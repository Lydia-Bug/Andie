package cosc202.andie.Colours;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.andie.ImageAction;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction("Greyscale", null, "Convert to greyscale", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(
                new AdjustBrightnessAction("Brightness", null, "Adjust brightness", Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new AdjustContrastAction("Contrast", null, "Adjust contrast", Integer.valueOf(KeyEvent.VK_C)));
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions
     * and add keyboard shortcuts to each Colour action.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu ColourMenu = new JMenu("Colour");
        actions.get(0).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        actions.get(1).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
        actions.get(2).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));

        for (Action action : actions) {
            ColourMenu.add(new JMenuItem(action));
        }

        return ColourMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action change brightness of image
     * </p>
     * 
     * @see AdjustBrightness
     */
    public class AdjustBrightnessAction extends ImageAction {

        /**
         * <p>
         * Create a new change brightness action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        AdjustBrightnessAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the adjust brightness action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the AdjustBrightnessAction is triggered.
         * It adjusts the images brightness
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the brightness- ask the user.
            double brightness = 0;

            // Pop-up dialog box to ask for the brightness value.
            JSlider brightnessModel = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);

            brightnessModel.setMajorTickSpacing(50);
            brightnessModel.setMinorTickSpacing(5);
            brightnessModel.setPaintTicks(true);
            brightnessModel.setPaintLabels(true);

            int option = JOptionPane.showOptionDialog(null, brightnessModel, "Enter brightness percentage",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                brightness = (int) brightnessModel.getValue();
            }

            target.getImage().apply(new AdjustBrightness(brightness));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action change contrast of image
     * </p>
     * 
     * @see AdjustBrightness
     */
    public class AdjustContrastAction extends ImageAction {

        /**
         * <p>
         * Create a new change brightness action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        AdjustContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the adjust contrastaction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the AdjustContrastAction is triggered.
         * It adjusts the images brightness
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the contrast- ask the user.
            double contrast = 0;

            // Pop-up dialog box to ask for the contrast value.
            JSlider contrastModel = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);

            contrastModel.setMajorTickSpacing(50);
            contrastModel.setMinorTickSpacing(5);
            contrastModel.setPaintTicks(true);
            contrastModel.setPaintLabels(true);

            int option = JOptionPane.showOptionDialog(null, contrastModel, "Enter contrast percentage",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                contrast = (int) contrastModel.getValue();
            }

            target.getImage().apply(new AdjustContrast(contrast));
            target.repaint();
            target.getParent().revalidate();
        }

    }

}
