# Features

## **Filters**
#### *Implmented by Callum Walker*
### Sharpen filter
- Accessed via: 
- Tested on...
- Known issues...
### Gaussian blur filter
- Accessed via: 
- Tested on...
- Known issues...
### Median filter
- Accessed via: 
- Tested on...
- Known issues...
<br/><br/>

## **Colour Adjustments**
#### *Implmented by Lydia Acton*
### Brightness adjustment
- Accessed via: Colour menu (Brightness option)
- Tested on various images including different file types, and images with transparant backgrounds. Also tested that -100% would make it completly black, and 100% would make it completly white.
- No known issues
### Contrast adjustment
- Accessed via: Colour menu (Contrast option)
- Tested on various images including different file types, and images with transparant backgrounds. 
- No known issues
<br/><br/>

## **Image Transform**
#### *Implmented by Ella Taylor*
### Image rotations: 90◦ left; 90◦ right; 180◦
- Accessed via: Transformations menu (Options for rotating 90 degrees left or right). Keyboard shortcut - Ctrl-Comma for clockwise rotation and Ctrl-period for anticlockwise rotation. 
- No testing framework was used, but this feature was tested on both square and rectangular images to ensure that image mapping was mathematically correct for both shapes. Both transparent PNG and solid JPEG images were tested, and all were rotated several times to assess image quality after many rotations. I initially used Graphics2D to implement this feature, but found that image quality declined significantly after only a few rotations. Additionally, many (> 5) rotations of images with white backgrounds would produce a blue grid overlaying the image. These issues were resolved when I switched to AffineTransform.
- Known issues: After many rotations, a very narrow black border sometimes forms around the image. I assume this is due to the accumulation of slight precision errors in the values used in AffineTransform.x. This issue is very subtle and is unlikely to be noticed by a user not specifically looking for it. Switching from a Graphics2D to an AffineTransform implementation significantly decreased the width of the border. 
### Image flip: Horizontal; Vertical
- Accessed via: Transformations menu (Options for flipping horizontally or vertically). Keyboard shortcut - Ctrl-Down for vertical flip, Ctr-Right for horizontal flip. 
- No testing framework was used. These features were tested in a similar manner to the image rotation features - a range of square and rectangular images (including PNGs with transparent backgrounds and JPEGs with solid backgrounds) were flipped many times to check for image quality deprecations. Image quality did not appear to decrease over time when AffineTransform was used.  
- No known issues.
<br/><br/>

### **Image Resize**
#### *Implmented by Hamzah Alansi*
- Accessed via: Transform menu (Resize), and Key shortcuts (Ctrl + R);
- Tested on: different type of images with different file types and transparent background.
- No Known issues. 
<br/><br/>

### **Image Export**
#### *Implmented by Noah Greig*
- Accessed via: 
- Tested on...
- Known issues...
<br/><br/>

### **Exception handling**
#### *Implmented by Lydia Acton*
- Opening an image: When you try and open an image it will give you an apropiate error message if you open a file that isn't an image, or try and open a file that doesn't exist, and if neither if those are the issue (such as a corrupt file) then it will still give an error message saying it was unable to open the image.
- Tested by seeing what would happen if I tried to open files that weren't images, files that were images (.jpg, .png, .gif), files that weren't there, and corrupt files(a txt file name image.png). They all worked as expected.
- The code can't tell spesifically if a file is corrupt, just if its unable to open it, so I can't give a spesific error message in that instant, but as far as I can tell thats not possible
- Input for filters, adjustements, and transforms: Some of the filters, etc can't or it doesn't make sense for it to take a value above of below a certain range, for example negitive values wouldn't make sense for the filters. So instead of giving an error message, I have implemented silders instead of input boxes for the user is unable to go above or below a certain range. I think it also makes the input more intutive, rather then blindly inputing numbers. 
- Tested the new sliders to make sure they did what was expected to the images, I didn't feel I had to be too comprehensive about this as it was an asthetic change mostly.
<br/><br/>

### **Other error avoidance/prevention**
#### *Implmented by Lydia Acton*
- Exiting without saving: If you try to exit or open a new image, without having saved the current one, it'll ask you if you want to continue since your work isn't saved. If your work is saved this dialog box won't come up.
- Tested by making changes then exiting or opening a new image, and by either making no changes, or saving before exiting or opening a new image, both worked as expected.
<br/><br/>

### **Toolbar for common operations**
#### *Implmented by Callum Walker*
- Accessed via: 
- Tested on...
- Known issues...
- For the Toolbar and Keyboard Shortcuts, a brief discussion about how you decided what features to make accessible
<br/><br/>

### **Keyboard shortcuts**
#### *Implmented by Hamzah Alansi, Lydia Acton*
- Accessed via: Keyboard keys
- Tested on Different type of images with different background transparencies.
- No Known issues. 
- Keyboard shortcuts were chosen based on previous experience using Image Editing softwares and their shortcuts. However, for actions that were not marked with shortcuts in such softwares and therefore not experienced. I decided to add shortcuts to them that were a bit logical to the action for example, flip horizontal would be ctrl + UP_ARROW (OR DOWN_ARROW in which case I just happened to choose the UP_ARROW).
<br/><br/>

