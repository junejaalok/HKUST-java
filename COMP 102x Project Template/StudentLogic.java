import java.util.Random;

import comp102x.ColorImage;
import comp102x.assignment.GameLogic;
import comp102x.assignment.GameRecord;
import comp102x.assignment.Goal;
import comp102x.IO;

public class StudentLogic implements GameLogic{
    /**
     * Generates an intermediate football image for the shooting animation based on the x and y positions of the ball from the place it has been hit. 
     * The size of the images scales down as it moves away. <br>
     * factor = (currentStep - initialStep) / (finalStep - initialStep), is used to select the index to be used to extract of the image from depthImages array, to calculate
     * the x and y positions of the ball and to scale the size of the image as the ball moves away.
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
        double fact=((double)(currentStep - initialStep)) / ((double)(finalStep - initialStep));
        //  The index of the depth image to be selected depthImages array       
        int imageIndex = (int)((depthImages.length -1) * fact);
        // x and y positions of the selected depth image
        int xPos=(int)(initialX+(finalX-initialX) * fact);
        int yPos=(int)(initialY+(finalY-initialY) * fact);
        // Scaling factor used for scaling the images as it goes away
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
     * Debug mode: change debug variable to true in method updateGoalPositions to see the littel bit more working details of the method <br> <br> <br>
     *
     * There are three difficulty levels: <br>
     *  Level 1: All the goals are stationary <br>
     *  Level 2: Half of the goals are stationary and half are moveable <br>
     *  Level 3: All the goas are moveable <br>
     * @param goals 2D array of goals
     */
    public void updateGoalPositions(Goal[][] goals) {
        // varialbe debug to ittel bit more working details of the method
        boolean debug=false;
        // No. of rows and columns in 2D array goals
        int row = goals.length;
        int col = goals[0].length;
        // log is Goal 2D array to keep long in order to avoid reverse swap in the same call of the method
        Goal[][] log= new Goal[row][col];
        Goal temp;
        int min=-1;
        int max=1;
        Random rand = new Random();
        if (debug) {
            IO.outputln("=======");
        }
        // For the method implementation, refer to the Step number of the corresponding statement in the Javadocs
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
     * A record is better than the other one if it has a higher score, or the two records have the same score, but it has a higher level. <br>
     * For sorting selection sort algorithm is implemented.<br>
     * Inline comments describe the step-by step implementation of the method in detail.<br>
     * If this is first play, return the single game record
     * If the current player has not played before, add the current player, do the selection sort and check for following conditions
     * If game has been played less than 10 times previously, return the current playercheck if current player's name exist, if not add the palyer to the previous player records. Do the selection sort to return the high score records in decreasing order
     * If game has been played 10 times previously, check if current player's name exist, if not then check if current players has performed better than previous players if so then replace the bad performed player with current player. Do the selection sort to return the high score records in decreasing order     
     * 
     * 
     * @param highScoreRecords The array of previous players records.
     * @param name The name of the current player.
     * @param level The level current player played.
     * @param score The score current player has made.
     * @return Array of top 10 high score records.
     */
    public GameRecord[] updateHighScoreRecords(GameRecord[] highScoreRecords, String name, int level, int score) {
        int oldLen = highScoreRecords.length;
        GameRecord[] scoreRecords=new GameRecord[oldLen+1]; // Array of the previous game records and current player's record
        GameRecord[] sortedShrink=new GameRecord[oldLen]; // Array of 10 elements to be returned to the caller
        boolean ans=false;
        // Maximum number of records that can be returned to the caller
        int plays=10;
 
        // In the method implementation, rule numbers are mentioned which are according to the description
        // of Project Task 3.
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
                // Add new current player record to current records array
                scoreRecords[oldLen]=new GameRecord(name,level,score);
                // selection sort to sort the array
                // find the highest score element in the array
                int minPos;
                for (int i=oldLen;i>0;i--){
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

                // Rule2: Previously played games are less than 10
                if (oldLen<plays) {
                    return scoreRecords;
                }
                // Rule3: There are 10 games played previously
                if (oldLen ==  plays) {
                    // extract the top 10 records from updated highest score records that will be returned to the caller
                    for (int i=0;i<scoreRecords.length-1;i++){
                        sortedShrink[i]=scoreRecords[i];
                    }
                    return sortedShrink;
                }
            }
            //Rule4/5: Current player's name exists in the previous records
            if (ans){
                for (int x=0;x<oldLen;x++){
                    // Find out the place where update needs to be made
                    if (scoreRecords[x].getName().equals(name)) {
                        // Update according to the score and if two scores are equal then sort according to the level
                        if (scoreRecords[x].getScore() < score) {
                            scoreRecords[x].setName(name);
                            scoreRecords[x].setScore(score);
                            scoreRecords[x].setLevel(level);
                        }
                        // If scores are equals then update the record based on levels played
                        else if (scoreRecords[x].getScore() == score) {
                            if (scoreRecords[x].getLevel() < level) {
                                scoreRecords[x].setName(name);
                                scoreRecords[x].setScore(score);
                                scoreRecords[x].setLevel(level);
                            }
                        }
                        // extract the top 10 records from updated highest score records that will be returned to the caller
                        for (int i=0;i<scoreRecords.length-1;i++) {
                            sortedShrink[i]=scoreRecords[i];
                        }
                    }
                }

                // selection sort to sort the array
                // find the highest score element in the array
                int minPos;
                for (int i=oldLen-1;i>0;i--){
                    int mIndex = 0;
                    for (int j = 0; j < i+1; j++) {
                        // Sort in decreasing order according to the score and if two scores are equal then sort according to the level
                        if (sortedShrink[j].getScore() < sortedShrink[mIndex].getScore()) mIndex=j ;
                        else if (sortedShrink[j].getScore() == sortedShrink[mIndex].getScore()) {
                            if (sortedShrink[j].getLevel() < sortedShrink[mIndex].getLevel()) mIndex=j;
                        }
                    }
                    //swap the highest score element and first element
                    GameRecord temp = sortedShrink[mIndex];
                    sortedShrink[mIndex] = sortedShrink[i];
                    sortedShrink[i] = temp;
                }
                return sortedShrink;
            }
        }
        return scoreRecords;
    }
}
