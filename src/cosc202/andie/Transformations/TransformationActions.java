//Documentation goes here!

package cosc202.andie.Transformations;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.andie.ImageAction;

public class TransformationActions {

    /** A list of actions for the Resize, Rotate and Flip menu. */
    protected ArrayList<Action> actions;

    public TransformationActions() {
        actions = new ArrayList<Action>();
        actions.add(new Rotate90ClockwiseAction("Rotate 90° clockwise", null, "Rotate 90° clockwise",
                Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new Rotate90AnticlockwiseAction("Rotate 90° anticlockwise", null, "Rotate 90° anticlockwise",
                Integer.valueOf(KeyEvent.VK_G)));
        actions.add(
                new VerticalFlipAction("Flip vertically", null, "Flip vertically", Integer.valueOf(KeyEvent.VK_RIGHT)));
        actions.add(new HorizontalFlipAction("Flip horizontally", null, "Flip horizontally",
                Integer.valueOf(KeyEvent.VK_DOWN)));
        actions.add(
                new ResizeAction("Resize", null, "Resize Image", Integer.valueOf(KeyEvent.VK_R)));
    }

    /**
     * <p>
     * Create a menu containing the list of Transformation actions
     * and assign keyboard shortcuts to Transformation action.
     * </p>
     * 
     * @return The Transformation menu UI element.
     */

    public JMenu createMenu() {
        JMenu TransformationMenu = new JMenu("Transform");
        actions.get(0).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, KeyEvent.CTRL_DOWN_MASK));
        actions.get(1).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, KeyEvent.CTRL_DOWN_MASK));
        actions.get(2).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.CTRL_DOWN_MASK));
        actions.get(3).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK));
        actions.get(4).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));

        for (Action action : actions) {
            TransformationMenu.add(new JMenuItem(action));
        }

        return TransformationMenu;
    }

    public class Rotate90ClockwiseAction extends ImageAction {

        Rotate90ClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rotate(90));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Rotate90AnticlockwiseAction extends ImageAction {

        Rotate90AnticlockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rotate(270));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class VerticalFlipAction extends ImageAction {

        VerticalFlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Flip(true));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class HorizontalFlipAction extends ImageAction {

        HorizontalFlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Flip(false));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to Resize an image.
     * </p>
     * 
     * <p>
     * Note that this action affects the way the image is displayed and its
     * actual contents (Losing pixels/clarity).
     * </p>
     */

    public class ResizeAction extends ImageAction {

        /**
         * <p>
         * Create a new Resize action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */

        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * Default constructor which does not require parameters.
         * 
         */

        ResizeAction() {
        }

        /**
         * <p>
         * Callback for when the Resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeAction is triggered.
         * It pops up a new tab with a slider to resize the image, with the default
         * percentage of the slider being 100%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            int ScaleFactor = 0;

            JSlider ResizeNumModel = new JSlider(JSlider.HORIZONTAL, 0, 500, 100);

            ResizeNumModel.setMajorTickSpacing(100);
            ResizeNumModel.setMinorTickSpacing(10);
            ResizeNumModel.setPaintTicks(true);
            ResizeNumModel.setPaintLabels(true);

            int option = JOptionPane.showOptionDialog(null, ResizeNumModel, "Enter image scale factor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                ScaleFactor = (int) ResizeNumModel.getValue();
            }

            target.getImage().apply(new ResizeImage(ScaleFactor));
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
