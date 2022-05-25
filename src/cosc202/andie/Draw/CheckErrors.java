package cosc202.andie.Draw;

import cosc202.andie.*;

public class CheckErrors {
    public static boolean checkErrors(ImagePanel target) {
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
