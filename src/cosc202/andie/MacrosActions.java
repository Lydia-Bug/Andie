package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Macro menu.
 * </p>
 * 
 * <p>
 * This is for creating ops files that contain operations
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Lydia Acton
 * @version 1.0
 */
public class MacrosActions {

    /** A list of actions for the Macros menu. */
    protected ArrayList<Action> actions;

    boolean recording = false;
    /**
     * <p>
     * Create a set of Macro menu actions.
     * </p>
     */
    public MacrosActions() {
        actions = new ArrayList<Action>();
        actions.add(new StartAction("Start", null, "Start macros recording", Integer.valueOf(KeyEvent.VK_Q)));
        actions.add(new StopAction("Stop and Save", null, "Stop and save macros recording", Integer.valueOf(KeyEvent.VK_W)));
        actions.add(new LoadAction("Load macros", null, "Load macros recording", Integer.valueOf(KeyEvent.VK_A)));
    }

    /**
     * <p>
     * Create a menu containing the list of Macro actions
     * and assign keyboard shortcuts to each Macro action.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Macros");

        actions.get(0).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        actions.get(1).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        actions.get(2).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to start recording
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class StartAction extends ImageAction {

        /**
         * <p>
         * Create a new start action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        StartAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the start action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the startAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            recording = true;
            target.getImage().start();
        }

    }

    /**
     * <p>
     * Action to stop recording and save the resulting ops file
     * Prompts the user to enter a filename
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class StopAction extends ImageAction {

        /**
         * <p>
         * Create a new stop action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        StopAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the stop action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the stopAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(recording){
                JFrame f = new JFrame();  
                f=new JFrame();   
                String filename = JOptionPane.showInputDialog(f,"Enter filename"); 
            
                try {
                    target.getImage().stop(filename);
                    recording = false;
                } catch (Exception ex) {
                    //System.exit(1);
                }
            }else{
                JFrame exceptionFrame = new JFrame();
                JOptionPane.showMessageDialog(exceptionFrame, "You haven't started recording");
            }
        }

    }

    /**
     * <p>
     * Creat new load action
     * </p>
     */
    public class LoadAction extends ImageAction {

        /**
         * <p>
         * Create a new load action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        LoadAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the load action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the LoadAction is triggered.
         * It loads in the called ops file and adds the operations to the current ops stack
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    // checks if file is an ops file
                    if (!(imageFilepath.substring(imageFilepath.length() - 4).equals(".ops"))) {
                        JFrame exceptionFrame = new JFrame();
                        JOptionPane.showMessageDialog(exceptionFrame, "Incorrect file type");
                    } else {
                        // checks if the file exists
                        try {
                            target.getImage();
                            target.getImage().load(imageFilepath);
                        } catch (Exception ex) {
                            JFrame exceptionFrame = new JFrame();
                            JOptionPane.showMessageDialog(exceptionFrame, "Can't find that file");
                        }
                    }
                    // if for any other reason the file can't open (if the file is corrupt)
                    } catch (Exception ex) {
                        JFrame exceptionFrame = new JFrame();
                        JOptionPane.showMessageDialog(exceptionFrame, "File didn't work");
                        // System.exit(1);
                    }
                }
                target.repaint();
                target.getParent().revalidate();
            }
        

    }

}
