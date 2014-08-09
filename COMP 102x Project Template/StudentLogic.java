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
     * Method implimentation <br> <br>  
     * Step0: Variables <br>
     *          log is Goal 2D array to keep long in order to avoid reverse swap in the same call of the method <br> <br>
     * Step1: For every goal (array cell), check following conditions <br>
     *          (A): Its Type (movable (Type:1)/stationary(Type:2)) <br>
     *          (B): Its Hit state (True/False) <br> <br>
     * Step2: Strategy to randomly generate the indices of nearest neighbour of the current goal <br>
     *          (A): Generate random number between -1 and 1 and add it to the current goal i-index. That is row index (t1) of nearest neighbour <br>
     *          (A): Generate random number between -1 and 1 and add it to the current goal j-index. That is column index (t2) of nearest neighbour <br> <br>
     * Step3: Validate randomly picked nearest neighbour goal <br>
     *          (A1): Check the indices (t1,t2) of randomaly picked nearest neighbour are within the boundaries of 2D array such that following conditions are fullfilled <br> 
     *          Conditions: t1 >= 0 (upper boundary) && t2 >= 0 (left boundary) && t1 < row (lower boundary) && t2 < col (right boundary) <br>
     *          (A2): If the current goal i-index and t1 are similar and current goal j-index and t2 are similar, this referes to the current goal itself and is discarded since current goal to itself is not neighbour <br>
     *          (B): Check for the swap with neighbour "no-there" goal. Make sure that reverse swap not allowed. For which check that in the same call to the method, current goal is not equal to its original location before the earlier swap <br>
     *          (C): Check for the swap with neighbour moveable "is-there" goal. Make sure that reverse swap not allowed. For which check that in the same call to the method, current goal is not equal to its original location before the earlier swap <br> <br> 
     * Step4: If conditions in Step3B/3C are satisfied <br>
     *          (A): Put the swapped current goal "is-there" in the 2D array log to check reverse swap in the same call of the method <br>
     *          (B): Put current goal (array cell) in temp <br>
     *          (C): Swap the current goal with the "not there" goal or with moveable "is-there" goal <br>
     *          (C): Put the temp in "not-there" or moveable "is-there" goal <br> <br> 
     * Debug mode: change debug variable to true in function updateGoalPositions to see the littel bit more working details of the function <br> <br> <br>
     *
     * There are three difficulty levels: <br>
     *  Level 1: All the goals are stationary <br>
     *  Level 2: Half of the goals are stationary and half are moveable <br>
     *  Level 3: All the goas are moveable <br>
     * @param goals 2D array of goals
     */
    public void updateGoalPositions(Goal[][] goals) {
        boolean debug=false;
        int row = goals.length;
        int col = goals[0].length;
        Goal[][] log= new Goal[row][col];
        Goal temp;
        int min=-1;
        int max=1;
        Random rand = new Random();
        if (debug) {
            IO.outputln("=======");
        }
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (goals[i][j].getType() == 2 && goals[i][j].isHit() == false) { //Step1
                    int t1 = i + rand.nextInt((max - min) + 1) + min;; //Step2
                    int t2 = j + rand.nextInt((max - min) + 1) + min;; //Step2

                    if (debug) {
                        IO.outputln("Current goal at index: " + i + "-" + j);
                    }

                    if (t1 >= 0 && t2 >= 0 && t1 < row && t2 < col && i != t1 && j != t2) { //Step3A1/3A2

                        if (debug) {
                            IO.outputln("Current goal at index: " + i + "-" + j + "randomaly picked nearest neighbour at index: " + t1 + "-" + t2);
                        }

                        if (goals[t1][t2].isHit() == true && goals[i][j] != log[t1][t2]) { //Step3B
                            if (debug) {
                                IO.outputln("Current goal at index: " + i + "-" + j + "moves to 'no-there' goal at index: " + t1 + "-" + t2);
                            }    
                            log[i][j]=goals[i][j]; //Step4
                            temp=goals[i][j]; //Step4
                            goals[i][j]=goals[t1][t2]; //Step4
                            goals[t1][t2]=temp; //Step4
                        }
                        else if (goals[t1][t2].getType() == 2 && goals[i][j] != log[t1][t2]) { //Step3C
                            if (debug) {
                                IO.outputln("Current goal at index: " + i + "-" + j + "moves to moveable 'is-there' goal at index: " + t1 + "-" + t2);
                            }    
                            log[i][j]=goals[i][j]; //Step4
                            temp=goals[i][j]; //Step4
                            goals[i][j]=goals[t1][t2]; //Step4
                            goals[t1][t2]=temp; //Step4
                        }
                    }
                }        
            } 
        }
    }
    
    /**
     * The method compares the record of the current game player with those of previous game(s) played and update the highscore records. <br> <br>
     * Sorting criteria: <br>
     * A record is better than the other one if it has a higher score, or the two records have the same score, but it has a higher level. 
     * @param highScoreRecords The array of previous players records.
     * @param name The name of the current player.
     * @param level The level current player played.
     * @param score The score current player has made.
     * @return Array of top 10 high score records.
     */
    public GameRecord[] updateHighScoreRecords(GameRecord[] highScoreRecords, String name, int level, int score) {
        int oldLen = highScoreRecords.length;
        GameRecord[] scoreRecords=new GameRecord[oldLen+1];
        GameRecord[] backSorting=new GameRecord[oldLen];
        GameRecord[] intrSorting=new GameRecord[oldLen];
        boolean ans=false;
        int plays=10;
 
        // Rule1: If no records exist before. This is the first play.
        if (oldLen == 0) {
            scoreRecords[oldLen]=new GameRecord(name,level,score);
        }
        // if plays have been played previously
        else {
            // copy the previous records to the current array in this call to method
            for(int i=0; i<oldLen; i++){
                scoreRecords[i]=highScoreRecords[i];
            }

            // Check if the player's name exists in the previous records
            for (int i=0;i<oldLen;i++) {
                String check=highScoreRecords[i].getName();
                if (check.equals(name)) ans=true;
            }

            // Rule2/3: Current player has not played previously
            if (!ans){
                // Rule2: Previously played games are less than 10
                if (oldLen<plays) {
                    // Add new current player record to current records array
                    scoreRecords[oldLen]=new GameRecord(name,level,score);
                    // selection sort to sort the array based on the criteria (Ref. Javadocs)
                    // find the highest score element in the array
                    int minPos;
                    for (int i=oldLen-1;i>0;i--){
                        int mIndex = 0;
                        for (int j = 0; j < i+1; j++) {
                            // Sort in decreasing order according to the score and if two scores are equal then sort according to the level
                            if (scoreRecords[j].getScore() < scoreRecords[mIndex].getScore()) mIndex=j ;
                            else if (scoreRecords[j].getScore() == scoreRecords[mIndex].getScore()) {
                                if (scoreRecords[j].getLevel() < scoreRecords[mIndex].getLevel()) mIndex=j;
                            }
                        }
                        //swap the highest score element and first element
                        GameRecord temp = scoreRecords[mIndex];
                        scoreRecords[mIndex] = scoreRecords[i];
                        scoreRecords[i] = temp;
                    }
                    // Return the updated high score records
                    return scoreRecords;
                }
                // Rule3: There are previous 10 games played
                if (oldLen ==  plays) {
                    // selection sort to sort the array based on the criteria (Ref. Javadocs)
                    // find the highest score element in the array
                    int minPos;
                    for (int i=oldLen-1;i>0;i--){
                        int mIndex = 0;
                        for (int j = 0; j < i+1; j++) {
                            // Sort in decreasing order according to the score and if two scores are equal then sort according to the level
                            if (scoreRecords[j].getScore() < scoreRecords[mIndex].getScore()) mIndex=j ;
                            else if (scoreRecords[j].getScore() == scoreRecords[mIndex].getScore()) {
                                if (scoreRecords[j].getLevel() < scoreRecords[mIndex].getLevel()) mIndex=j;
                            }
                        }
                        //swap the highest score element and first element
                        GameRecord temp = scoreRecords[mIndex];
                        scoreRecords[mIndex] = scoreRecords[i];
                        scoreRecords[i] = temp;
                    }

                    for (int i=0;i<scoreRecords.length-1;i++){
                        backSorting[i]=scoreRecords[i];
                    }
                    return backSorting;
                }
            }
            //Rule4/5: 
            if (ans){
                for (int x=0;x<oldLen;x++){
                    // 
                    if (scoreRecords[x].getName().equals(name)) {
                        // Update according to the score and if two scores are equal then sort according to the level
                        if (scoreRecords[x].getScore() < score) {
                            scoreRecords[x].setName(name);
                            scoreRecords[x].setScore(score);
                            scoreRecords[x].setLevel(level);
                            for (int i=0;i<scoreRecords.length-1;i++) {
                                intrSorting[i]=scoreRecords[i];
                            }
                            break;
                        }
                        // If scores are equals then update the record based on levels played
                        else if (scoreRecords[x].getScore() == score) {
                            if (scoreRecords[x].getLevel() < level) {
                                scoreRecords[x].setName(name);
                                scoreRecords[x].setScore(score);
                                scoreRecords[x].setLevel(level);
                                for (int i=0;i<scoreRecords.length-1;i++) {
                                    intrSorting[i]=scoreRecords[i];
                                }
                                break;
                            }
                        }
                    }
                }

                // selection sort to sort the array based on the criteria (Ref. Javadocs)
                // find the highest score element in the array
                int minPos;
                for (int i=oldLen-1;i>0;i--){
                    int mIndex = 0;
                    for (int j = 0; j < i+1; j++) {
                        // Sort in decreasing order according to the score and if two scores are equal then sort according to the level
                        if (intrSorting[j].getScore() < intrSorting[mIndex].getScore()) mIndex=j ;
                        else if (intrSorting[j].getScore() == intrSorting[mIndex].getScore()) {
                            if (intrSorting[j].getLevel() < intrSorting[mIndex].getLevel()) mIndex=j;
                        }
                    }
                    //swap the highest score element and first element
                    GameRecord temp = intrSorting[mIndex];
                    intrSorting[mIndex] = intrSorting[i];
                    intrSorting[i] = temp;
                }
                return intrSorting;
            }
        }
        return scoreRecords;
    }
}
