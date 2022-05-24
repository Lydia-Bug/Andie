# Features

### **Extended filters**
#### *Implemented by Lydia Acton, and Callum Walker*
When Calum created the kernal filters in the first part of the project he implemented 'extended filter' functionality on the filters that needed it. He didn't participate in the second part of the project so Lydia simplying copyed over the code he had already writen, to the mean filter, which still had the issue, as it wasn't one of the filters he created. 
- Accessed via: There's 'extended filter' code used for the mean, and gaussian filter, the other kernal filters don't have the issue of a black border, because the radius of the kernals used to small to see any difference
- I've tested a couple different types of images of different size, and types. And I've tested different radiuses, the extended filter seems to work fine
- The filter does cause a kinda of white blury border around the edge, its not nearly as noticable and the hard black border, especially at smaller radius. And for a function like this there is no perfect soultion, because you don't know the values of the pixcels outside of the images bounds, so this implimentation works fine. 
<br/><br/>

### **Emboss and edge detection filters (filters w/ negative results)**
#### *Implemented by Lydia Acton*
- Accessed via: Filter menu (Emboss Filter) and (Sobel Filter) and Key shortcuts (Ctrl + E) and (Ctrl + H)
- I've tested it on various images. I've implement the eight emboss filters as each being in 'directions' so I made sure they were all in the correct direction by making sure I got the same output from a sperate image editor
- For my implimentation of emboss I would have liked to have a slider where you can only place it on 0, 45, 90, 135, ect, I think that would make more sense to the user, and be better for functionality purposes.
<br/><br/>

### **Posterise effect**
#### *Implemented by Lydia Acton*
- Accessed via: Filter menu (Posterise) and Key shortcuts (Ctrl + P)
- Various images, transperant images. I tested it on large images, initially it took way to long for large images. So I edited my code so it doesn't autally sort all the pixels in the images and it now goes much faster and works for images of any sizes
- Sometimes the output where gradients used to be, can be quite blocky. I've seen some iplimentations on other images editors that allow you to 'smooth' the output, I think if I implemented that this filter would be improved. 
<br/><br/>

### **Mouse selection of rectangular regions**
#### *Implemented by *
- Accessed via: 
- Tested on zoomed in and out images, previously didn't work but now factors in the scale when drawing a rectangal
- Known issues...
<br/><br/>

### **Crop to selction**
#### *Implemented by Hamzah Alansi *
- Accessed via: Transformation tab, Keyboard Shortcut (Shift + X)
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
#### *Implemented by *
- Accessed via: 
- Tested on...
- Known issues...
<br/><br/>

### **Show us something: **
#### *Implemented by *
- Accessed via: 
- Tested on...
- Known issues...
<br/><br/>