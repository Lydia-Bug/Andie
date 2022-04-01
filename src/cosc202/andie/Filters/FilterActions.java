package cosc202.andie.Filters;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.andie.ImageAction;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood. 
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {
    
    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction("Mean filter", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SharpenFilterAction("Sharpen filter", null, "Apply a sharpening filter", null));
        actions.add(new MedianFilterAction("Median filter", null, "Apply a median filter", null));
        actions.add(new GuassianFilterAction("Gaussian filter", null, "Apply a gaussian filter", null));
    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Filter");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    public class GuassianFilterAction extends ImageAction {

        GuassianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name,icon,desc,mnemonic);
        }

        /**
         * Prompt the user for what the radius of the filter should be
         * 
         * @param e The action that triggered the event
         * 
         */
        @Override
        public void actionPerformed(ActionEvent e) {            
            int radius = 3;

            SpinnerNumberModel radNumModel = new SpinnerNumberModel(radius, 3,11,2);
            JSpinner radSpinner=  new JSpinner(radNumModel);
            float option = JOptionPane.showOptionDialog(null, radSpinner, "Enter radius of blur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radNumModel.getNumber().intValue();
            }

            target.getImage().apply(new GaussianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class MedianFilterAction extends ImageAction {

        /**
         * Constructor for median filter action
         * 
         * @param name Name of the function
         * @param icon Icon for the function
         * @param desc Description of the function
         * @param mnemonic Shortcut key for the function
         * 
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name,icon,desc,mnemonic);
        }

        /**
         * Applies the median filter
         * 
         * @param e The event which triggered the action
         * 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new MedianFilter());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to apply a sharpening filter to the image
     * </p>
     */
    public class SharpenFilterAction extends ImageAction {

        /**
         * Creates a new Sharpen-Filter Action
         * 
         * @param name The name of the action
         * @param icon Image icon associated with the action
         * @param desc A description of what the action does
         * @param mnemonic The shortcut for using the action
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name,icon,desc,mnemonic);
        }

        /**
         * Prompts the user for the degree to which they want
         * to sharpen the image. Then parses this data to the
         * SharpenFilter class.
         * 
         * @param e The action which called the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int sharpness = 1;
            
            SpinnerNumberModel sharpNumModel = new SpinnerNumberModel(sharpness, 1, 5, 1);
            JSpinner sharpnessSpinner = new JSpinner(sharpNumModel);
            float option = JOptionPane.showOptionDialog(null, sharpnessSpinner, "Enter sharpness amount", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                sharpness = sharpNumModel.getNumber().intValue();
            }

            target.getImage().apply(new SharpenFilter(sharpness));
            target.repaint();
            target.getParent().revalidate();
        }       
    }


    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }
}