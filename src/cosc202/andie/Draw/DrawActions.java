package cosc202.andie.Draw;

import java.util.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import cosc202.andie.ImageAction;

public class DrawActions {

    protected ArrayList<Action> actions;
    
    /**
     * <p>
     * Constructor. Creates an ArrayList of Colour menu actions.
     * </p>
     */
    public DrawActions() {
        actions = new ArrayList<Action>();
        actions.add(new DrawTextAction("Create Text", null, "Create Text",
                null));
        actions.add(new DrawRectangleAction("Draw Rectangle", null, "Draw Rectangle (no fill)",
                null));
    }

    /**
     * <p>
     * Creates a menu containing the list of Transformation actions. Assigns
     * keyboard shortcuts to each action.
     * </p>
     * 
     * @return The Transformation menu UI element.
     */

    public JMenu createMenu() {
        JMenu DrawMenu = new JMenu("Draw...");

        for (Action action : actions) {
            DrawMenu.add(new JMenuItem(action));
        }

        return DrawMenu;
    }

    /**
     * <p>
     * 
     * </p>
     * 
     * @see Rotate
     */
    public class DrawRectangleAction extends ImageAction {

        /**
         * <p>
         * 
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            ColorPicker colorPicker = new ColorPicker("Select pen colour");
            target.getImage().apply(new DrawRectangle(colorPicker.getPenColor()));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class DrawFilledRectangle extends ImageAction {

        /**
         * <p>
         * 
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawFilledRectangle(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // target.getImage().apply(new Rotate(true));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class DrawLine extends ImageAction {

        /**
         * <p>
         * 
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawLine(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // target.getImage().apply(new Rotate(true));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class DrawOval extends ImageAction {

        /**
         * <p>
         * 
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawOval(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // target.getImage().apply(new Rotate(true));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class DrawFilledOval extends ImageAction {

        /**
         * <p>
         * 
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawFilledOval(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // target.getImage().apply(new Rotate(true));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class DrawTextAction extends ImageAction {

        /**
         * <p>
         * Create a new rotate clockwise action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawTextAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the rotate clockwise action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate90ClockwiseAction is triggered.
         * It rotates the image 90 degrees clockwise.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            Rectangle2D m = target.GetMouseRectangle();
            target.getImage().apply(new DrawText(
                    (int) m.getX(),
                    (int) m.getY(),
                    (int) m.getWidth(),
                    (int) m.getHeight()));
            target.deselectMouse();
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
