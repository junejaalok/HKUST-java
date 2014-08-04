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
                    IO.outputln("====");
                    //IO.outputln(i +"-" + j + "goal type:" + goals[i][j].getType());
                    //IO.outputln(i +"-" + j + "goal is hit:" + goals[i][j].isHit());
                    //IO.outputln(i +"-" + j + "goal is swaped:" + swaped[i][j]);

                if (goals[i][j].getType() == 2 && goals[i][j].isHit() == false && swaped[i][j] == 0) {
                        int r1 = rand.nextInt((max - min) + 1) + min;
                        int r2 = rand.nextInt((max - min) + 1) + min;
                        int t1=i+r1;
                        int t2=j+r2;
                    
                        if (i == t1 && j == t2) continue;

                        if (t1 >= 0 && t2 >= 0 && t1 < row && t2 < col && goals[t1][t2].isHit() == true && swaped[i][j] == 0) { 
                            IO.outputln(t1 +"-" + t2 + "goal type:" + goals[t1][t2].getType());
                            IO.outputln(t1 +"-" + t2 + "goal is hit:" + goals[t1][t2].isHit());
                            IO.outputln(t1 +"-" + t2 + "goal is swaped:" + swaped[t1][t2]);
                            IO.outputln(t1 +"-" + t2 + "goal is hit :");
                            //if (swaped[i][j] == 0) {//goals[t1][t2].isHit() == false)){
                                //IO.outputln(i +"-" + j + "ijbefore:" + goals[i][j]);
                                //IO.outputln(t1 +"-" + t2 + "t1t2before:" + goals[t1][t2]);
                                temp=goals[i][j];
                                goals[i][j]=goals[t1][t2];
                                goals[t1][t2]=temp;
                                swaped[i][j]=1;
                                //IO.outputln(i +"-" + j + "goal:" + goals[i][j].getType());
                                //IO.outputln(t1 +"-" + t2 + "goal:" + goals[t1][t2].getType());
                                //IO.outputln(t1 +"-" + t2 + "goal is hit:" + goals[t1][t2].isHit());
                                //IO.outputln(i +"-" + j + "ijafter:" + goals[i][j]);
                                //IO.outputln(t1 +"-" + t2 + "t1t2after:" + goals[t1][t2]);
                                //IO.outputln(i + "accept-t1:" + t1);
                                //IO.outputln(j + "accdpt-t2:" + t2);
                                IO.outputln("========");
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
