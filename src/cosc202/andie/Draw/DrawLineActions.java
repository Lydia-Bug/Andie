package cosc202.andie.Draw;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import cosc202.andie.ImageAction;


public class DrawLineActions {

    protected ArrayList<Action> actions;

    /**
     * <p>
     * Constructor. Creates an ArrayList of Colour menu actions.
     * </p>
     */
    public DrawLineActions() {
        actions = new ArrayList<Action>();
        actions.add(new DrawVerticalLineAction("Draw Vertical Line", null, "Draw solid vertical line in selected area",
                null));
        actions.add(new DrawHorizontalLineAction("Draw Horizontal Line", null, "Draw solid horizontal line in selected area",
                null));
        actions.add(new DrawDiagonalDownLineAction("Draw Downwards Diagonal Line", null, "Draw solid line between top-left and bottom-right corner of selected area",
                null));
        actions.add(new DrawDiagonalUpLineAction("Draw Upwards Diagonal Line", null, "Draw solid line between bottom-left and top-right corner of selected area",
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
        JMenu DrawMenu = new JMenu("Draw Lines in Selected Area...");

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
    
    public class DrawVerticalLineAction extends ImageAction {

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
        DrawVerticalLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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

                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawLine(colorPicker.getPenColor(), bt.getThickness(), 0, target));
                    target.deselectMouse();
                }
                CancelDrawOperation.drawCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    public class DrawDiagonalDownLineAction extends ImageAction {

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
        DrawDiagonalDownLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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

                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawLine(colorPicker.getPenColor(), bt.getThickness(), 2, target));
                    target.deselectMouse();
                }
                CancelDrawOperation.drawCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    public class DrawDiagonalUpLineAction extends ImageAction {

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
        DrawDiagonalUpLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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

                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawLine(colorPicker.getPenColor(), bt.getThickness(), 3, target));
                    target.deselectMouse();
                }
                CancelDrawOperation.drawCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    public class DrawHorizontalLineAction extends ImageAction {

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
        DrawHorizontalLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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

                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawLine(colorPicker.getPenColor(), bt.getThickness(), 1, target));
                    target.deselectMouse();
                }
                CancelDrawOperation.drawCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }
}
