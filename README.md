# Features

### **Extended filters**
#### *Implemented by Lydia Acton, and Callum Walker*
Although Callum didn't work on the second part of the project, he already did the extended filters when he created the filters in the first part of the project. Callum write the code for the extended filters and Lydia copy and pasted them to the mean filter where it hadn't been implimented. 
- Accessed via: There's 'extended filter' code used for the mean, and gaussian filter, the other kernal filters don't have the issue of a black border, because the radius of the kernals used is too small to see any difference
- I've tested it on various images, including transperent.
- The filter does cause a kinda of blury border around the edge, its not nearly as noticable and the hard black border. This can't really be fixed, different implmentations will lead to differnt non-perfect results, so this is appceptable.
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
#### *Implemented by *
- Accessed via: 
- Tested on images that are zoomed in or out, previously it didn't work at would draw the rectangular behind or infront of the mouse. Now the rectangular selection considers the scale of the image, and it works fine.
- Known issues...
<br/><br/>

### **Crop to selection**
#### *Implemented by Hamzah Alansi *
- Accessed via: Transformation tab, Keyboard Shortcut (Shift + X), Crop to selection works by selecting a mouse region first before using the action. The best way to do this would be to mouse select then use the shortcut.
- Tested on: 2 images with different background transparencies.
- Known issues: When trying to crop an Image using mouse selection and going outside of the container bounds it breaks.
<br/><br/>

### **Drawing functions**
#### *Implemented by *
- Accessed via: 
- Tested on...
- Known issues...
<br/><br/>

### **Macros**
#### *Implemented by Lydia Acton*
- Accessed via: Macros tab, Keyboard Shortcuts Start: (Ctrl + Q), Stop:(Ctrl + W), Load: (Ctrl + A)
- I've tested all the different functions to make sure they save. Initially transformations didn't save because they didn't impliment java.io.Serializable, and crop cound't save because it had a bufferent issue in is constructor. I tested saving an out of bounds crop, initially would cause an error, but now the code checks if the coordionates are out of bounds. I tested the maving on mac and windows, I coded it on a mac then realised I had to alter it to work on windows because they use different slashes. I've tested trying to load incorrect files, or stoping when you haven't started, and it brings up apropiate error messages. 
- No known issues
<br/><br/>

## **Show us something**

### **Filters can be applied to rectangular selection**
#### *Implemented by *
- Accessed via: 
- Tested on...
- Known issues...
<br/><br/>

### **Write text**
#### *Implemented by *
- Accessed via: 
- Tested on...
- Known issues...
<br/><br/>

### **Show us something: Draw Text **
#### *Implemented by Hamzah Alansi *
- Accessed via: "Draw text" action in the "Draw in Selected Area" tab
- Tested on: 2 Different Images with different Background transparencies
- Known issues: When user clicks cancel after clicking the Draw Text Action it still draws the text.
<br/><br/>