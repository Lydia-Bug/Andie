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
        actions.add(new Rotate90ClockwiseAction("Rotate 90° clockwise", null, null, null));
        actions.add(new Rotate90AnticlockwiseAction("Rotate 90° anticlockwise", null, null, null));
        actions.add(new VerticalFlipAction("Flip vertically", null, null, null));
        actions.add(new HorizontalFlipAction("Flip horizontally", null, null, null));
        actions.add(new ResizeAction("Resize", null, null, null));
    }

    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Transform");

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

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

            SpinnerNumberModel ResizeNumModel = new SpinnerNumberModel(ScaleFactor, 0, 500, 10);
            JSpinner ResizeSpinner = new JSpinner(ResizeNumModel);
            float option = JOptionPane.showOptionDialog(null, ResizeSpinner, "Enter Image Scale factor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                ScaleFactor = (ResizeNumModel.getNumber().intValue());
            }

            target.getImage().apply(new ResizeImage(ScaleFactor));
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
