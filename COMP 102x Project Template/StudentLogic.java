import java.util.Random;

import comp102x.ColorImage;
import comp102x.assignment.GameLogic;
import comp102x.assignment.GameRecord;
import comp102x.assignment.Goal;
import comp102x.IO;

public class StudentLogic implements GameLogic{
    /**
     * Generates an intermediate football image for the shooting animation.
     * 
     * @param depthImages The array contains an array of football images of different depths.
     * @param initialStep The initial step of the shooting animation.
     * @param currentStep The current step of the shooting animation
     * @param finalStep The final step of the shooting animation.
     * @param initialScale The initial scale of the football 
     * @param finalScale The final scale of the football 
     * @param initialX The initial x position of the football.
     * @param finalX The final x positions of the football.
     * @param initialY The initial y position of the football.
     * @param finalY The final y positions of the football.
     * @return The selected depth image of football.
     */
    public ColorImage generateIntermediateFootballImage(ColorImage[] depthImages, int initialStep, int currentStep, int finalStep, double initialScale, double finalScale, int initialX, int finalX, int initialY, int finalY) {
        // write your code after this line

        double fact=((double)(currentStep - initialStep)) / ((double)(finalStep - initialStep));
        
        int imageIndex = (int)((depthImages.length -1) * fact);
        int xPos=(int)(initialX+(finalX-initialX) * fact);
        int yPos=(int)(initialY+(finalY-initialY) * fact);
        double scale=initialScale+(finalScale-initialScale) * fact;
        
        ColorImage fbImage=depthImages[imageIndex];
        fbImage.setX(xPos);
        fbImage.setY(yPos);
        fbImage.setScale(scale);

        return fbImage;
    }

    /**
     * Step1: At every array cell, check three conditions <br>
     *          A: its Type (if it movable/stationary) <br>
     *          B: its Hit state (True/False) <br>
     *          C: and whether it has been swaped once or not in the current method call <br> <br>
     * Step2: Generate randome number between -1 and 1 and assign to varialbes r1 and r2 respecitvely <br> <br>
     * Step3: Sum up current cell i-index and r1 and assign it to varialbe t1 and j-index and r2 <br> <br>
     *        Sum up current cell j-index and r2 and assign it to varialbe t2 <br> <br>
     * Step4: Strategy to pick the nearest neighbours <br>
     *          A: if the current cell i-index and t1 are similar and j-index and t2 are similar, this <br>
     *          referes to the cell itself and is discarded and cell to itself is not neighbour. <br>
     *          B: check the bound such that following conditions are fullfilled <br>
     *             t1 >= 0 && t2 >= 0 && t1 < row && t2 < col <br> <br>
     * Step5: In the if statement of Step4B, check if the goal is not being Hit (.)
     * https://courses.edx.org/courses/HKUSTx/COMP102x/2T2014/discussion/forum/i4x-HKUSTx-COMP102x-course-2014-Project/threads/53d0d8822b8b56357e000f3f
     * @param goals .
     * @return void
     */
    public void updateGoalPositions(Goal[][] goals) {
        // write your code after this line
        // Type: 1 is stationary
        // Type: 2 is movable
        // at difficulty level 1: all are type 1
        // at difficulty level 2: all are type 1(50%) and type 2(50%)
        // at difficulty level 3: all are type 2
        
        int row = goals.length;
        int col = goals[0].length;
        int [][] swaped = new int [row][col];
        Goal temp;
        int min=-1;
        int max=1;
        Random rand = new Random();
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (goals[i][j].getType() == 2 && goals[i][j].isHit() == false && swaped[i][j] == 0) {
                    int r1 = rand.nextInt((max - min) + 1) + min;
                    int r2 = rand.nextInt((max - min) + 1) + min;
                    int t1=i+r1;
                    int t2=j+r2;
                    
                    if (i == t1 && j == t2) continue;

                    if (t1 >= 0 && t2 >= 0 && t1 < row && t2 < col && goals[t1][t2].isHit() == true && swaped[i][j] == 0) { 
                        temp=goals[i][j];
                        goals[i][j]=goals[t1][t2];
                        goals[t1][t2]=temp;
                        swaped[i][j]=1;
                    }
                }        
            } 
        }
    }
        
    
    public GameRecord[] updateHighScoreRecords(GameRecord[] highScoreRecords, String name, int level, int score) {
        // write your code after this line
        
        
        return highScoreRecords;
        
        
    }
    
    
}
