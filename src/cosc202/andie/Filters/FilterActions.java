package cosc202.andie.Filters;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;

import cosc202.andie.ImageAction;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
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
        actions.add(new MeanFilterAction("Mean", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SharpenFilterAction("Sharpen", null, "Apply a sharpening filter",
                Integer.valueOf(KeyEvent.VK_J)));
        actions.add(
                new MedianFilterAction("Median", null, "Apply a median filter", Integer.valueOf(KeyEvent.VK_K)));
        actions.add(new GuassianFilterAction("Gaussian", null, "Apply a gaussian filter",
                Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new PosteriseAction("Posterise", null, "Posterise image", Integer.valueOf(KeyEvent.VK_P)));
        actions.add(new EmbossFilterAction("Embossed", null, "Apply a emboss filter",
                Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new SobelAction("Sobel", null, "Apply a sobel filter",
                Integer.valueOf(KeyEvent.VK_H)));
    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions
     * and add keyboard shortcuts to each Filter action.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu filterMenu = new JMenu("Filters...");

        actions.get(0).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
        actions.get(1).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));
        actions.get(2).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK));
        actions.get(3).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        actions.get(4).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        actions.get(5).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        actions.get(6).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));

        for (Action action : actions) {
            filterMenu.add(new JMenuItem(action));
        }

        return filterMenu;
    }

    public class GuassianFilterAction extends ImageAction {

        GuassianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * Prompt the user for what the radius of the filter should be
         * 
         * @param e The action that triggered the event
         * 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int radius = 1;
            JSlider radNumModel = new JSlider(JSlider.HORIZONTAL, 5, 20, 5);

            radNumModel.setMajorTickSpacing(5);
            radNumModel.setMinorTickSpacing(1);
            radNumModel.setPaintTicks(true);
            radNumModel.setPaintLabels(true);

            int option = JOptionPane.showOptionDialog(null, radNumModel, "Enter radius of blur",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = (int) radNumModel.getValue();
            }

            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
            boolean selection = true;
            try{
                Rectangle2D m = target.GetMouseRectangle();
                x = (int) m.getX();
                y = (int) m.getY();
                width = (int) m.getWidth();
                height = (int) m.getHeight();
            }catch(Exception ex){
                selection = false;
            }
            target.getImage().apply(new GaussianFilter(radius, x, y, width, height, selection));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class MedianFilterAction extends ImageAction {

        /**
         * Constructor for median filter action
         * 
         * @param name     Name of the function
         * @param icon     Icon for the function
         * @param desc     Description of the function
         * @param mnemonic Shortcut key for the function
         * 
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * Applies the median filter
         * 
         * @param e The event which triggered the action
         * 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //target.getImage().apply(new MedianFilter());
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
         * @param name     The name of the action
         * @param icon     Image icon associated with the action
         * @param desc     A description of what the action does
         * @param mnemonic The shortcut for using the action
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
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
            JSlider sharpNumModel = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);

            sharpNumModel.setMajorTickSpacing(5);
            sharpNumModel.setMinorTickSpacing(1);
            sharpNumModel.setPaintTicks(true);
            sharpNumModel.setPaintLabels(true);

            int option = JOptionPane.showOptionDialog(null, sharpNumModel, "Enter sharpness amount",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                sharpness = (int) sharpNumModel.getValue();
            }

            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
            boolean selection = true;
            try{
                Rectangle2D m = target.GetMouseRectangle();
                x = (int) m.getX();
                y = (int) m.getY();
                width = (int) m.getWidth();
                height = (int) m.getHeight();
            }catch(Exception ex){
                selection = false;
            }
            target.getImage().apply(new SharpenFilter(sharpness, x, y, width, height, selection));
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
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
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
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            JSlider radiusModel = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);

            radiusModel.setMajorTickSpacing(5);
            radiusModel.setMinorTickSpacing(1);
            radiusModel.setPaintTicks(true);
            radiusModel.setPaintLabels(true);

            int option = JOptionPane.showOptionDialog(null, radiusModel, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = (int) radiusModel.getValue();
            }

            // Create and apply the filter
            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
            boolean selection = true;
            try{
                Rectangle2D m = target.GetMouseRectangle();
                x = (int) m.getX();
                y = (int) m.getY();
                width = (int) m.getWidth();
                height = (int) m.getHeight();
            }catch(Exception ex){
                selection = false;
            }
            target.getImage().apply(new MeanFilter(radius, x, y, width, height, selection));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action posterise an image
     * </p>
     * 
     * @see Posterise
     */
    public class PosteriseAction extends ImageAction {

        /**
         * <p>
         * Create a new posterise action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        PosteriseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the posterise is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the PosteriseAction is triggered.
         * It adjusts the posterises an image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the contrast- ask the user.
            int layers = 0;

            // Pop-up dialog box to ask for the contrast value.
            JSlider posteriseModel = new JSlider(JSlider.HORIZONTAL, 0, 30, 0);

            posteriseModel.setMajorTickSpacing(10);
            posteriseModel.setMinorTickSpacing(1);
            posteriseModel.setPaintTicks(true);
            posteriseModel.setPaintLabels(true);

            int option = JOptionPane.showOptionDialog(null, posteriseModel, "Enter amount of colours",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                layers = (int) posteriseModel.getValue();
            }

            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
            boolean selection = true;
            try{
                Rectangle2D m = target.GetMouseRectangle();
                x = (int) m.getX();
                y = (int) m.getY();
                width = (int) m.getWidth();
                height = (int) m.getHeight();
            }catch(Exception ex){
                selection = false;
            }
            target.getImage().apply(new Posterise(layers, x, y, width, height, selection));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to emboss image with emboss filter
     * </p>
     * 
     * @see EmbossFilter
     */
    public class EmbossFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new emboss-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EmbossFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the emboss action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilterAction is triggered.
         * It prompts the user for an emboss angle
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the angle - ask the user.
            int angle = 0;

            // Pop-up dialog box to ask for the angle value.
            JSlider angleModel = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);

            angleModel.setMajorTickSpacing(90);
            angleModel.setMinorTickSpacing(45);
            angleModel.setPaintTicks(true);
            angleModel.setPaintLabels(true);

            int option = JOptionPane.showOptionDialog(null, angleModel, "Enter emboss angle",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                angle = (int) angleModel.getValue();
            }

            // Create and apply the filter
            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
            boolean selection = true;
            try{
                Rectangle2D m = target.GetMouseRectangle();
                x = (int) m.getX();
                y = (int) m.getY();
                width = (int) m.getWidth();
                height = (int) m.getHeight();
            }catch(Exception ex){
                selection = false;
            }
            target.getImage().apply(new EmbossFilter(angle,x, y, width, height, selection));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to apply sobel filer
     * </p>
     * 
     * @see SobelFilter
     */
    public class SobelAction extends ImageAction {

        /**
         * <p>
         * Create a new sobel filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the sobelaction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the sobel is triggered.
         * It prompts the user for either horizontal or vertical sobel filter
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Pop-up dialog box to ask for the angle value.
            Object[] options = { "Horizontal", "Vertical" };
            int direction = JOptionPane.showOptionDialog(null, "Pick direction", "Warning", JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                        
            boolean horizontal = false;
            if(direction == 0){
                horizontal = true;
            }
            if(direction == 1){
                horizontal = false;
            }
            // Create and apply the filter
            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
            boolean selection = true;
            try{
                Rectangle2D m = target.GetMouseRectangle();
                x = (int) m.getX();
                y = (int) m.getY();
                width = (int) m.getWidth();
                height = (int) m.getHeight();
            }catch(Exception ex){
                selection = false;
            }
            target.getImage().apply(new SobelFilter(horizontal, x, y, width, height, selection));
            target.repaint();
            target.getParent().revalidate();
        }

    }

}
