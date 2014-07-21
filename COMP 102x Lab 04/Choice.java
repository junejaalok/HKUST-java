import comp102x.ColorImage;
import comp102x.Canvas; 
import comp102x.IO; 

/**
 * The Choice class represents a choice made by the player or the computer.
 * It can be either "rock", "paper", or "scissors".
 */
public class Choice
{
    private int type; //stores the choice type: 0=rock, 1=paper, 2=scissors
    private ColorImage choiceImage; //stores the image to be displayed on the canvas
    
    /**
     * The constructor
     * 
     * @param   type   the choice type to be represented by this Choice object
     */
    public Choice(int type)
    {
        //initialize the "type" instance varialble
        this.type = type;
    }
    
    /**
     * Get a number that represents the choice type
     * 
     * @return  a number that represents the choice type: 0=rock, 1=paper, 2=scissors
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Compare "this" with anotherChoice
     * 
     * @param   anotherChoice   the choice to be compared
     * @return  either 1, -1, or 0 which indicates the comparison result: 1 means "this" wins anotherChoice; -1 means "this" loses to anotherChoice; 0 means "this" and anotherChoice are the same
     */
    public int compareWith(Choice anotherChoice)
    {
        // write your code after this line
        int ans=(this.getType()-anotherChoice.getType())%3;
        //IO.outputln(this.getType());
        //IO.outputln(anotherChoice.getType());
        //IO.outputln(this.getType()-anotherChoice.getType()%3);
        if (ans == 1 || ans == -2)
            return 1;
        else if (ans == 2 || ans == -1)
            return -1;
        else
            return 0;
        
        
        
        
        
        
    }
    
    /**
     * Draw the choice image (rock/paper/scissors) on the given canvas
     * 
     * @param   canvas      the canvas to draw on
     * @param   x           the x-position of the choice image to be drawn
     * @param   y           the y-position of the choice image to be drawn
     * @param   rotation    the rotation of the choice image to be drawn
     */ 
    public void draw(Canvas canvas, int x, int y, int rotation)
    {
       // write your code after this line
       //choiceImage.setX(x);
       //choiceImage.setY(y);
       //choiceImage.setRotation(rotation);       
        
       if (this.getType() == 0){
       choiceImage=new ColorImage("rock.png");
       }
       else if (this.getType() == 1) {
       choiceImage=new ColorImage("paper.png");
       }
       else {
       choiceImage=new ColorImage("scissors.png");
       }
       choiceImage.setX(x);
       choiceImage.setY(y);
       choiceImage.setRotation(rotation);
       canvas.add(choiceImage);
     
       
       
       
       
       
    }
}
