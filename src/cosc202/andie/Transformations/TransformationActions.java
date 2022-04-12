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

    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Transform");
        actions.get(0).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        actions.get(1).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, KeyEvent.CTRL_DOWN_MASK));
        actions.get(2).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.CTRL_DOWN_MASK));
        actions.get(3).putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK));
        actions.get(4).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));

        fileMenu.add(new JMenuItem(actions.get(0)));
        fileMenu.add(new JMenuItem(actions.get(1)));
        fileMenu.add(new JMenuItem(actions.get(2)));
        fileMenu.add(new JMenuItem(actions.get(3)));
        fileMenu.add(new JMenuItem(actions.get(4)));

        /*
         * for (Action action : actions) {
         * fileMenu.add(new JMenuItem(action));
         * }
         */

        return fileMenu;
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

    public class ResizeAction extends ImageAction {
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

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
