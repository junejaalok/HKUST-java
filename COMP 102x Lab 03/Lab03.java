import comp102x.ColorImage;
import comp102x.Canvas;

public class Lab03
{  
    
    public void loadAnImage() 
    {
        // Please write your code after this line
        
        ColorImage image = new ColorImage();
        int width=image.getWidth();
        int height=image.getHeight();
        Canvas canvas = new Canvas(width,height);
        canvas.add(image,0,0);
       
        
        
    }
    
    public void loadTwoImagesOnTheSameCanvas()
    {
        // Please write your code after this line
        ColorImage image1 = new ColorImage();
        int width1=image1.getWidth();
        int height1=image1.getHeight();
        
        ColorImage image2 = new ColorImage();
        int width2=image2.getWidth();
        int height2=image2.getHeight();
        Canvas canvas = new Canvas(width1+width2,height1);
        canvas.add(image1,0,0);
        canvas.add(image2,image1.getWidth(),0);
       
        
    }
    
    public void imageAddition() 
    {    
        // Please write your code after this line
        
        ColorImage image1 = new ColorImage();
        int width1=image1.getWidth();
        int height1=image1.getHeight();
        
        ColorImage image2 = new ColorImage();
        int width2=image2.getWidth();
        int height2=image2.getHeight();
        
        ColorImage image3;
        image3 = image1.add(image2);
        
        Canvas canvas = new Canvas(width1+width2+width1+20,height1);
        canvas.add(image1,0,0);
        canvas.add(image2,image1.getWidth()+10,0);
        canvas.add(image3,image1.getWidth()+10+image2.getWidth()+10,0);
        
        
    }
   
    public void imageMultiplication() 
    {
        // Please write your code after this line
        
        ColorImage image1 = new ColorImage();
        int width1=image1.getWidth();
        int height1=image1.getHeight();
        
        ColorImage image2 = new ColorImage();
        int width2=image2.getWidth();
        int height2=image2.getHeight();
        
        ColorImage image3;
        image3 = image1.multiply(image2);
        
        Canvas canvas = new Canvas(width1+width2+width1+20,height1);
        canvas.add(image1,0,0);
        canvas.add(image2,image1.getWidth()+10,0);
        canvas.add(image3,image1.getWidth()+10+image2.getWidth()+10,0);
        
        
        
    }
    
    public void changeColor()
    {
        ColorImage image = new ColorImage();
        image.increaseRed(40);
        Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
        canvas.add(image);
        
        //image.save();
    }
}
