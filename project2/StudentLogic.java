//http://w02.hkvu.hk/edX/COMP102x/pa/ss/14076000459151838838/StudentLogic.java

import comp102x.ColorImage;
import comp102x.assignment.GameLogic;
import comp102x.assignment.GameRecord;
import comp102x.assignment.Goal;

import java.util.Random;

public class StudentLogic implements GameLogic {

    /**
     * This method generates an intermediate football image for the shooting animation
     *
     * @param depthImages  The depth of the Image
     * @param initialStep  The initial step of the shooting animation respectively
     * @param currentStep  The current step of the shooting animation respectively
     * @param finalStep    The final step of the shooting animation respectively
     * @param initialScale The initial scale of the football
     * @param finalScale   The final scale of the football
     * @param initialX     The initial x position of the football
     * @param finalX       The final x position of the football
     * @param initialY     The initial y position of the football
     * @param finalY       The final y position of the football
     * @return the selected depth image of the football
     */
    public ColorImage generateIntermediateFootballImage(ColorImage[] depthImages, int initialStep, int currentStep, int finalStep, double initialScale, double finalScale, int initialX, int finalX, int initialY, int finalY) {

        // Calculate the index of the depth image to be selected
        int imageIndex = (depthImages.length - 1) * (currentStep - initialStep) / (finalStep - initialStep);
        ColorImage football = depthImages[imageIndex];

        //Calculate the x position of the selected depth image
        int x = initialX + (finalX - initialX) * (currentStep - initialStep) / (finalStep - initialStep);

        // Calculate the y position of the selected depth image
        int y = initialY + (finalY - initialY) * (currentStep - initialStep) / (finalStep - initialStep);

        // Calculate the scale of the selected depth image
        double scale = initialScale + (finalScale - initialScale) * (currentStep - initialStep) / (finalStep - initialStep);

        // Set the scale, x and y position of the selected depth image
        football.setScale(scale);
        football.setX(x);
        football.setY(y);

        return football;
    }

    /**
     * Updates the positions of the goals periodically
     *
     * @param goals Represents the goals displayed on the main game screen
     */

    public void updateGoalPositions(Goal[][] goals) {

        // set the rows and cols length of the 2D matrix Goal
        int rows = goals.length;
        int cols = goals[0].length;

        // initiate a 2d matrix to hold the processed values
        int[][] processed = new int[rows][cols];

        // initialize the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                processed[i][j] = 0;
            }
        }

        // check for changes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if ((!goals[i][j].isHit()) && (processed[i][j] == 0) &&
                        (goals[i][j].getType() == 2)) {

                    Random random = new Random();

                    // randomize the adjacent rows and cols
                    int dRow = random.nextInt(3) - 1;
                    int dCol = random.nextInt(3) - 1;
                    while ((dRow == 0) && (dCol == 0)) {
                        dCol = random.nextInt(3) - 1;
                    }
                    int newRow = i + dRow;
                    int newCol = j + dCol;

                    // check if goals are hit then swap
                    if ((newRow >= 0) && (newRow < rows) && (newCol >= 0) && (newCol < cols) && (goals[newRow][newCol].isHit())) {
                        Goal temp = goals[i][j];
                        goals[i][j] = goals[newRow][newCol];
                        goals[newRow][newCol] = temp;
                        processed[newRow][newCol] = 1;
                    }
                }

                // set the processed matrix to stationary if no goal is hit
                processed[i][j] = 1;
            }
        }
    }

    /**
     * Compares the record of the current game play with those of previous game plays
     * and update the high-score records.
     *
     * @param highScoreRecords Represents the 1D array of the GameRecords of PREVIOUS game plays.
     * @param name             The name of the current game
     * @param level            The level of the current game
     * @param score            The score of the current game
     * @return a 1D array of GameRecords after processing the record of the current game play
     */
    public GameRecord[] updateHighScoreRecords(GameRecord[] highScoreRecords, String name, int level, int score) {

        // Check if user already exists
        boolean userExist = isUserExist(highScoreRecords, name);

        // if user exists
        if (userExist) {

            // check for previous high score records and sets the best high score and level
            for (GameRecord highScoreRecord : highScoreRecords) {
                if (highScoreRecord.getName().equals(name)) {
                    if (highScoreRecord.getScore() < score) {
                        highScoreRecord.setScore(score);
                        highScoreRecord.setLevel(level);
                        break;
                    }
                    if ((highScoreRecord.getScore() != score) || (highScoreRecord.getLevel() >= level)) {
                        break;
                    }
                    highScoreRecord.setLevel(level);
                    break;
                }
            }
        }

        // check if there are 10 previous high scores
        else if (highScoreRecords.length == 10) {
            int minIndex = minPos(highScoreRecords, highScoreRecords.length);

            // check if a record is better then the other one
            boolean condition2 = highScoreRecords[minIndex].getScore() < score;

            // check if two records have the same score, but higher level
            boolean condition3 = (highScoreRecords[minIndex].getScore() == score) && (highScoreRecords[minIndex].getLevel() < level);

            // check if any of the conditions is true
            if ((condition2) || (condition3)) {

                // create a new game record
                highScoreRecords[minIndex] = new GameRecord(name, level, score);
            }
        }

        // if the player exists and the current record is the same or worse then the previous score
        else {
            // Create a new array of game records with all the previous records
            GameRecord[] newRecords = new GameRecord[highScoreRecords.length + 1];
            System.arraycopy(highScoreRecords, 0, newRecords, 0, highScoreRecords.length);
            newRecords[(newRecords.length - 1)] = new GameRecord(name, level, score);
            highScoreRecords = newRecords;
        }

        // sort the high scores
        sortArray(highScoreRecords, highScoreRecords.length);


        return highScoreRecords;
    }


    /**
     * Check if a user already exists
     *
     * @param records The records
     * @param user    The user
     * @return true if user exists, false if not
     */
    private boolean isUserExist(GameRecord[] records, String user) {
        for (GameRecord record : records) {
            if (record.getName().equals(user)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Sort an array by highest score
     *
     * @param array The array
     * @param size  The size of the array
     */
    private void sortArray(GameRecord[] array, int size) {
        int remaining = size;
        while (remaining > 1) {
            int minIndex = minPos(array, remaining);
            swapArrayRows(array, minIndex, --remaining);
        }
    }


    /**
     * Get the minimum value of the array
     *
     * @param array The array
     * @param size  The size of the array
     * @return the minimum value of the array
     */
    private int minPos(GameRecord[] array, int size) {
        int minIndex = 0;
        for (int i = 1; i < size; i++) {
            if (array[i].getScore() < array[minIndex].getScore()) {
                minIndex = i;
            } else if ((array[i].getScore() == array[minIndex].getScore()) && (array[i].getLevel() <= array[minIndex].getLevel())) {
                minIndex = i;
            }
        }
        return minIndex;
    }


    /**
     * Swap array rows
     *
     * @param array  The array
     * @param index1 The first index
     * @param index2 The second index
     */
    private void swapArrayRows(GameRecord[] array, int index1, int index2) {
        GameRecord temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
