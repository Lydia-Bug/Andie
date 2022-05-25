/**
 * <p>
 * Actions provided by the Add Text menu.
 * </p>
 * 
 * <p>
 * Adds text in a textbox to the image. The position of the text is determined by the location of the selected region. Text color, font and size
 * can then be chosen in a gui.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Hamzah Alansi
 * @version 1.0
 */

package cosc202.andie.Draw;

import java.util.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import cosc202.andie.ImageAction;

public class AddTextAction {

    protected ArrayList<Action> actions;

    /**
     * <p>
     * Constructor. Creates an ArrayList of Text menu actions 
     * </p>
     */
    public AddTextAction() {
        actions = new ArrayList<Action>();
        actions.add(new DrawTextAction("Add Text", null, "Add text to selected area",
                null));

    }

    /**
     * <p>
     * Creates a menu containing the list of text actions. Assigns
     * keyboard shortcuts to each action.
     * </p>
     * 
     * @return The Transformation menu UI element.
     */

    public JMenu createMenu() {
        JMenu DrawMenu = new JMenu("Add Text to Selected Area...");

        for (Action action : actions) {
            DrawMenu.add(new JMenuItem(action));
        }

        return DrawMenu;
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
            System.out.println(jf.dialogResultValue);
            if (jf.dialogResultValue == jf.CANCEL_OPTION) {
                return;
            }

            String s = jf.sampleText.getText();
            String fontFamily = jf.getSelectedFontFamily();
            int fontSize = jf.getSelectedFontSize();
            int fontStyle = jf.getSelectedFontStyle();
            ColorPicker c = new ColorPicker("Pick text Color: ");
            Color colour = c.getPenColor();
            target.getImage().apply(new DrawText((int) m.getX(), (int) m.getY(),
                    (int) m.getWidth(), (int) m.getHeight(), fontFamily, s, fontSize, fontStyle, colour));
            target.validate();
            target.deselectMouse();
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
