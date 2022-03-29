//Documentation goes here!

package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class TransformationActions {

    /** A list of actions for the Resize, Rotate and Flip menu. */
    protected ArrayList<Action> actions;


    public TransformationActions() {
        actions = new ArrayList<Action>();
        actions.add(new Rotate90ClockwiseAction("Rotate 90° clockwise", null, null, null));
        actions.add(new Rotate90AnticlockwiseAction("Rotate 90° anticlockwise", null, null, null));
    }

    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Transform");
    
        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }
    
        return fileMenu;
    }

    public class Rotate90ClockwiseAction extends ImageAction {

        Rotate90ClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rotate90(90));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class Rotate90AnticlockwiseAction extends ImageAction {

        Rotate90AnticlockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rotate90(270));
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
