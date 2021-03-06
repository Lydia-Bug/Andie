
/**
 * Menu for shape operations.
 * @author Ella Taylor
 */
package cosc202.andie.Draw;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import cosc202.andie.ImageAction;


public class DrawShapeActions {

    protected ArrayList<Action> actions;
    

    /**
     * <p>
     * Constructor. Creates an ArrayList of Shape menu actions.
     * </p>
     */
    public DrawShapeActions() {
        actions = new ArrayList<Action>();
        actions.add(new DrawRectangleAction("Draw Rectangle", null, "Draw rectangle (no fill) in selected area",
                null));
        actions.add(new DrawFilledRectangleAction("Draw Filled Rectangle", null,
                "Draw rectangle (with fill) in selected area",
                null));
        actions.add(new DrawOvalAction("Draw Oval", null, "Draw oval (no fill) in selected area",
                null));
        actions.add(new DrawFilledOvalAction("Draw Filled Oval", null, "Draw Oval (with fill) in selected area",
                null));

    }

    /**
     * <p>
     * Creates a menu containing the list of Shape actions.
     * </p>
     * 
     * @return The Shape menu UI element.
     */

    public JMenu createMenu() {
        JMenu DrawShapeMenu = new JMenu("Draw Shape in Selected Area...");

        for (Action action : actions) {
            DrawShapeMenu.add(new JMenuItem(action));
        }

        return DrawShapeMenu;
    }

    /**
     * <p>
     * Draws unfilled rectangle
     * </p>
     * 
     * 
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

            if (CheckErrors.checkErrors(target)) {
                ColorPicker colorPicker = new ColorPicker("Select pen colour");
                BrushThickness bt = new BrushThickness();
                if (colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawRectangle(colorPicker.getPenColor(), null, bt.getThickness(), false, target));
                    target.deselectMouse();
                }
                CancelDrawOperation.drawCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Draws filled rectangle
     * </p>
     * 
     * 
     */
    public class DrawFilledRectangleAction extends ImageAction {

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
        DrawFilledRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            if (CheckErrors.checkErrors(target)) {
                ColorPicker outline = new ColorPicker("Select outline colour");
                ColorPicker fill = new ColorPicker("Select fill colour");
                BrushThickness bt = new BrushThickness();

                if (outline.getPenColor() != null && fill.getPenColor() != null) {
                    target.getImage().apply(new DrawRectangle(outline.getPenColor(), fill.getPenColor(), bt.getThickness(), true, target));
                    target.deselectMouse();
                } 
                CancelDrawOperation.drawCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Draws unfilled oval
     * </p>
     * 
     * 
     */
    public class DrawOvalAction extends ImageAction {

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
        DrawOvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (CheckErrors.checkErrors(target)) {

                ColorPicker colorPicker = new ColorPicker("Select pen colour");
                BrushThickness bt = new BrushThickness();

                if (colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawOval(colorPicker.getPenColor(), null, bt.getThickness(), false, target));
                    target.deselectMouse();
                }
                CancelDrawOperation.drawCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Draws filled oval
     * </p>
     * 
     * 
     */
    public class DrawFilledOvalAction extends ImageAction {

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
        DrawFilledOvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (CheckErrors.checkErrors(target)) {
                ColorPicker outline = new ColorPicker("Select outline colour");
                ColorPicker fill = new ColorPicker("Select fill colour");
                BrushThickness bt = new BrushThickness();

                if (outline.getPenColor() != null && fill.getPenColor() != null) {
                    target.getImage().apply(
                            new DrawOval(outline.getPenColor(), fill.getPenColor(), bt.getThickness(), true, target));
                    target.deselectMouse();
                } 
                CancelDrawOperation.drawCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

}

