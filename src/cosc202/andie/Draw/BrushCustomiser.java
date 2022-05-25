/**package cosc202.andie.Draw;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class BrushCustomiser extends JPanel {
    private Color outline = Color.BLACK;
    private Color fill = Color.BLACK;
    private int thickness;
    private boolean hasFill;
    
    public BrushCustomiser(boolean hasFill) {
        super(new GridLayout(1, 1));
        this.hasFill = hasFill;

        JPanel outlinePanel = new JPanel();
        outlinePanel.setLayout(new GridLayout(1,1));

        JPanel fillPanel = new JPanel();
        fillPanel.setLayout(new GridLayout(1,1));

        JPanel thicknessPanel = new JPanel();
        thicknessPanel.setLayout(new GridLayout(1,1));

        JOptionPane outlineOption = new JOptionPane(new JColorChooser(outline), JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    
        fillPanel.add(new JColorChooser(outline));
        outlinePanel.add(outlineOption);
        
        JSlider thicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 2);
        
        thicknessSlider.setMajorTickSpacing(5);
        thicknessSlider.setMinorTickSpacing(1);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);

        thicknessPanel.add(thicknessSlider);

        JTabbedPane options = new JTabbedPane();
        options.addTab("Border Colour", outlinePanel);
        if (hasFill) options.addTab("Fill Colour", fillPanel);
        options.addTab("Brush Thickness", thicknessPanel);

        add(options);

        JOptionPane outlineOption = new JOptionPane(ccOutline, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

        int outlineOption = JOptionPane.showOptionDialog(null, ccOutline, "Select border colour",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        int fillOption = JOptionPane.showOptionDialog(null, ccFill, "Select fill colour",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        int brushOption = JOptionPane.showOptionDialog(null, thicknessSlider, "Select brush thickness",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); 
    }

    public void createAndShowGUI(boolean hasFill) {
        //Create and set up the window.
        JFrame frame = new JFrame("Brush Options");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Add content to the window.
        frame.add(new BrushCustomiser(hasFill), BorderLayout.CENTER);
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
} */
