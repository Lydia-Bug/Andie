package cosc202.andie.Draw;

import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import cosc202.andie.ImageAction;
import cosc202.andie.*;

public class DrawActions {

    protected ArrayList<Action> actions;
    protected static boolean operationCancelled;

    /**
     * <p>
     * Constructor. Creates an ArrayList of Colour menu actions.
     * </p>
     */
    public DrawActions() {
        actions = new ArrayList<Action>();
        actions.add(new DrawTextAction("Create Text", null, "Create text",
                null));
        actions.add(new DrawRectangleAction("Draw Rectangle", null, "Draw rectangle (no fill) in selected area",
                null));
        actions.add(new DrawFilledRectangleAction("Draw Filled Rectangle", null, "Draw rectangle (with fill) in selected area",
                null));
        actions.add(new DrawOvalAction("Draw Oval", null, "Draw oval (no fill) in selected area",
                null));
        actions.add(new DrawFilledOvalAction("Draw Filled Oval", null, "Draw Oval (with fill) in selected area",
                null));
        actions.add(new DrawVerticalLineAction("Draw Vertical Line", null, "Draw solid vertical line in selected area",
                null));
        actions.add(new DrawHorizontalLineAction("Draw Horizontal Line", null, "Draw solid horizontal line in selected area",
                null));
        actions.add(new DrawDiagonalLineAction("Draw Diagonal Line", null, "Draw solid line between top-left and bottom-right corner of selected area",
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
        JMenu DrawMenu = new JMenu("Draw in Selected Area");

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

            if (checkErrors(target)) {
                ColorPicker colorPicker = new ColorPicker("Select pen colour");
                BrushThickness bt = new BrushThickness();
                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawRectangle(colorPicker.getPenColor(), bt.getThickness(), target));
                    target.deselectMouse();
                }
                operationCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

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

            if (checkErrors(target)) {
                ColorPicker outline = new ColorPicker("Select outline colour");
                ColorPicker fill = new ColorPicker("Select fill colour");
                BrushThickness bt = new BrushThickness();

                if(outline.getPenColor() != null && fill.getPenColor() != null) {
                    target.getImage().apply(new DrawFilledRectangle(outline.getPenColor(), fill.getPenColor(), bt.getThickness(), target));
                    target.deselectMouse();
                } 
                operationCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

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
            if (checkErrors(target)) {

                ColorPicker colorPicker = new ColorPicker("Select pen colour");
                BrushThickness bt = new BrushThickness();

                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawLine(colorPicker.getPenColor(), bt.getThickness(), 0, target));
                    target.deselectMouse();
                }
                operationCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    public class DrawDiagonalLineAction extends ImageAction {

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
        DrawDiagonalLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (checkErrors(target)) {

                ColorPicker colorPicker = new ColorPicker("Select pen colour");
                BrushThickness bt = new BrushThickness();

                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawLine(colorPicker.getPenColor(), bt.getThickness(), 2, target));
                    target.deselectMouse();
                }
                operationCancelled = false;
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
            if (checkErrors(target)) {

                ColorPicker colorPicker = new ColorPicker("Select pen colour");
                BrushThickness bt = new BrushThickness();

                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawLine(colorPicker.getPenColor(), bt.getThickness(), 1, target));
                    target.deselectMouse();
                }
                operationCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

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
            if (checkErrors(target)) {

                ColorPicker colorPicker = new ColorPicker("Select pen colour");
                BrushThickness bt = new BrushThickness();

                if(colorPicker.getPenColor() != null) {
                    target.getImage().apply(new DrawOval(colorPicker.getPenColor(), bt.getThickness(), target));
                    target.deselectMouse();
                }
                operationCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

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
            if (checkErrors(target)) {
                ColorPicker outline = new ColorPicker("Select outline colour");
                ColorPicker fill = new ColorPicker("Select fill colour");
                BrushThickness bt = new BrushThickness();

                if(outline.getPenColor() != null && fill.getPenColor() != null) {
                    target.getImage().apply(new DrawFilledOval(outline.getPenColor(), fill.getPenColor(), bt.getThickness(), target));
                    target.deselectMouse();
                } 
                operationCancelled = false;
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to draw text on the Image.
     * </p>
     * 
     * @see Draw
     */
    public class DrawTextAction extends ImageAction {

        /**
         * <p>
         * Create a Draw Text action.
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
         * Callback for when the Draw Text action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawTextAction is triggered.
         * It draws user Text Over regions of mouse selection on top of the Image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            Rectangle2D m = target.GetMouseRectangle();
            JTextField jtxt = new JTextField("Enter Text: ", 0);
            JFontChooser jf = new JFontChooser();

            jf.showDialog(jtxt);
            String s = jf.sampleText.getText();
            String fontFamily = jf.getSelectedFontFamily();
            int fontSize = jf.getSelectedFontSize();
            int fontStyle = jf.getSelectedFontStyle();

            target.getImage().apply(new DrawText((int) m.getX(), (int) m.getY(),
                    (int) m.getWidth(), (int) m.getHeight(), fontFamily, s, fontSize, fontStyle));
            target.validate();
            target.deselectMouse();
            target.repaint();
            target.getParent().revalidate();
        }
    }

    private boolean checkErrors(ImagePanel target) {
        if (!target.getImage().hasImage()) {
            new NoLoadedImageError();
            return false;
        }
        if (target.GetMouseRectangle() == null) {
            new NoSelectionError();
            return false;
        }
        return true;
    }

}
