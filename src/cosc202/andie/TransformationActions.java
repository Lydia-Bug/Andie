//Documentation goes here!

package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class TransformationActions {

        /** A list of actions for the Resize, Rotate and Flip menu. */
        protected ArrayList<Action> actions;

        /**
         * <p>
         * Creates list of menu actions.
         * </p>
         */
        public TransformationActions() {
            actions = new ArrayList<Action>();
        }

        public JMenu createMenu() {
            JMenu fileMenu = new JMenu("Colour");
    
            for(Action action: actions) {
                fileMenu.add(new JMenuItem(action));
            }
    
            return fileMenu;
        }
}
