# Features

### **Extended filters**
#### *Implemented by Lydia Acton, and Callum Walker*
Although Callum didn't work on the second part of the project, he already did the extended filters when he created the filters in the first part of the project. Callum write the code for the extended filters and Lydia copy and pasted them to the mean filter where it hadn't been implimented. 
- Accessed via: There's 'extended filter' code used for the mean, and gaussian filter, the other kernal filters don't have the issue of a black border, because the radius of the kernals used is too small to see any difference
- I've tested it on various images, including transperent.
- The filter does cause a kinda of blury border around the edge, its not nearly as noticable and the hard black border. This can't really be fixed, different implmentations will lead to differnt non-perfect results, so this is acceptable.
<br/><br/>

### **Emboss and edge detection filters (filters w/ negative results)**
#### *Implemented by Lydia Acton*
- Accessed via: Filter menu (Emboss Filter) and (Sobel Filter) and Key shortcuts (Ctrl + E) and (Ctrl + H)
- I've tested it on various images, including transperent. I've implement the eight emboss filters as each being applied at different angle. I made sure the angle was for the correct filter, but comparing my results to a seperate image editor which also allows you apply an emboss filter at an angle. 
- My emboss filter can only be applied at angles that are a multiple of 45 degrees. While on windows the slider works as intended an only allows those values, that isn't the case for mac. While my code still works and will pick the closest apropiate value, its a usability issue if the user is able to input a value which autually can't be used.
<br/><br/>

### **Posterise effect**
#### *Implemented by Lydia Acton*
- Accessed via: Filter menu (Posterise) and Key shortcuts (Ctrl + P)
- I've tested it on various images, including transperent. I tested it on large images, initially because the k-means clustering had to sort all the pixels in the image many times, it took took long. So I changed my code so that for larger images it won't read in every single pixel, but will skip some, making the code run much faster.
- No known issues
<br/><br/>

### **Mouse selection of rectangular regions**
#### *Implemented by Ella Taylor and Hamzah Alansi *
- Accessed via: Clicking and dragging across the screen.
- Tested on images that are zoomed in or out, and transparent/opaque images. Previously it didn't work and would draw the rectangular behind or infront of the mouse. Now the rectangular selection considers the scale of the image, and it works fine. Also won't select 1x1 rectangle if you just click. Automatically deselects when save/open options are used and after a shape has been drawn/crop/filter has been applied. Can select area outside of the image, as this is consistent with some other photo editors I have used. However, shapes etc. will be cut off by the edge of the image (aren't drawn outside the image).
- Known issues...
<br/><br/>

### **Crop to selection**
#### *Implemented by Hamzah Alansi *
- Accessed via: Transformation tab, Keyboard Shortcut (Shift + X), Crop to selection works by selecting a mouse region first before using the action. The best way to do this would be to mouse select then use the shortcut.
- Tested on: 2 images with different background transparencies.
- No known issues
<br/><br/>

### **Drawing functions**
#### *Implemented by Ella Taylor *
- Accessed via: Corresponding menu tabs. Area of image must be selected first, then shape/line is drawn within.
- Tested on transparent and opaque images. Tested attempting to draw a shape with selection fully out of bounds (no shape is drawn) and partially in bounds (shape is cut off at boundary). Warns the user if they attempt to draw shape when no image is loaded or if no area is selected. Successfully drawing a shape removes the area selection box. I chose to implement vertical and horizontal lines specifically (even though they are technically superfluous because they can be drawn with a mouse region of zero width etc.) because it is difficult to draw a perfectly straight line with the diagonal line method. 
- Known issues: Doesn't work with macros or redo (undo works fine)
<br/><br/>

### **Macros**
#### *Implemented by Lydia Acton*
- Accessed via: Macros tab, Keyboard Shortcuts Start: (Ctrl + Q), Stop:(Ctrl + W), Load: (Ctrl + A)
- I've tested all the different functions to make sure they save. Initially transformations didn't save because they didn't impliment java.io.Serializable, and crop cound't save because it had a bufferent issue in is constructor. I tested saving an out of bounds crop, initially would cause an error, but now the code checks if the coordionates are out of bounds. I tested the maving on mac and windows, I coded it on a mac then realised I had to alter it to work on windows because they use different slashes. I've tested trying to load incorrect files, or stoping when you haven't started, and it brings up apropiate error messages. 
- No known issues
<br/><br/>

## **Show us something**

### **Filters can be applied to rectangular selection**
#### *Implemented by Lydia Acton*
- Accessed via: By selecting an area then applying a filter or colour filter
- I've tested this on all the filter and colour filters, I've test that it works if you select a region outside an image.
- No known issues
<br/><br/>

### **Show us something: Draw Text **
#### *Implemented by Hamzah Alansi *
- Accessed via: "Draw text" action in the "Draw in Selected Area" tab
- Tested on: 2 Different Images with different Background transparencies
- No known issues
<br/><br/>
