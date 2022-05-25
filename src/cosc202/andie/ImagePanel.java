package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well
 * as zooming
 * in and out.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel {

    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is
     * zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally
     * as a percentage.
     * </p>
     */


    private double scale;

    Rectangle2D selectedArea = null;
    protected Point startDrag;
    protected Point endDrag;

    public void deselectMouse() {
        selectedArea = null;
        startDrag = null;
        endDrag = null;
    }

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println("DRAG LISTENER CALLED");
                double selectorScale = 1/(scale);
                int x = (int)(e.getX()*selectorScale);
                int y = (int)(e.getY()*selectorScale);
                ImagePanel.this.startDrag = new Point(x, y);
                ImagePanel.this.endDrag = startDrag;
                System.out.println("EVENT: " + ImagePanel.this.startDrag);
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                if (endDrag != null && startDrag != null) {
                    try {
                        double selectorScale = 1/(scale);
                        int x = (int)(e.getX()*selectorScale);
                        int y = (int)(e.getY()*selectorScale);
                        selectedArea = makeRectangle(startDrag.x, startDrag.y, x, y);
                        startDrag = null;
                        endDrag = null;

                        repaint();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                double selectorScale = 1/(scale);
                int x = (int)(e.getX()*selectorScale);
                int y = (int)(e.getY()*selectorScale);
                endDrag = new Point(x, y);
                repaint();
            }
        });
    }

    public Point getStartDrag() {
        return this.startDrag;
    }

    public Rectangle2D GetMouseRectangle() {
        return this.selectedArea;

    }

    public Point getEndDrag() {
        return this.endDrag;
    }


    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * </p>
     * 
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * 
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a
     * default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                    (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            g2.setStroke(new BasicStroke(2));

            if (selectedArea != null && selectedArea.getHeight()*selectedArea.getWidth() > 1) {
                // if(isValidSelectedArea()) {
                g2.setPaint(Color.RED);
                g2.draw(selectedArea);
                // }
            }

            if (startDrag != null && endDrag != null) {
                g2.setPaint(Color.LIGHT_GRAY);
                Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x,
                        endDrag.y);
                g2.draw(r);
            }

            g2.dispose();
        }

    }

    public Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        if(x1 - x2 == 0 || y1 - y2 == 0){
            return null;
        }
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2),
                Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    /**
     * incomplete - needs fixing and to check that area selected is within image
     * boundaries
     * private boolean isValidSelectedArea() {
     * return ((Math.abs(endDrag.x - startDrag.x) <
     * image.getCurrentImage().getWidth()) &&
     * (Math.abs(endDrag.y - startDrag.y) < image.getCurrentImage().getHeight()));
     * }
     */

}
