// http://w02.hkvu.hk/edX/COMP102x/pa/ss/14076146885751841652/StudentLogic.java

import java.util.Random;
import java.util.*;
import comp102x.ColorImage;
import comp102x.assignment.GameLogic;
import comp102x.assignment.GameRecord;
import comp102x.assignment.Goal;

public class StudentLogic implements GameLogic{
    
    
    public ColorImage generateIntermediateFootballImage(ColorImage[] depthImages, int initialStep, int currentStep, int finalStep, double initialScale, double finalScale, int initialX, int finalX, int initialY, int finalY) {
        // write your code after this line
        int imageIndex = (depthImages.length - 1) * (currentStep - initialStep) / (finalStep - initialStep);
        int xPosition = initialX + (finalX - initialX) * (currentStep - initialStep) / (finalStep - initialStep);
        int yPosition = initialY + (finalY - initialY) * (currentStep - initialStep) / (finalStep - initialStep);
        double scale = initialScale + (finalScale - initialScale) * (currentStep - initialStep) / (finalStep - initialStep);
        
        depthImages[imageIndex].setX(xPosition);
        depthImages[imageIndex].setY(yPosition);
        depthImages[imageIndex].setScale(scale);
        return depthImages[imageIndex];
    }

    private class canMove {
        int x;
        int y;
        public canMove(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public void updateGoalPositions(Goal[][] goals) {
        // write your code after this line
        
        //array where moved goals are stored
        boolean goalMoved[][] = new boolean[goals.length][goals[0].length];
        for (int i=0; i<goals.length; i++)
            for (int j=0; j<goals[i].length; j++) 
                goalMoved[i][j] = false;
        
        for (int i=0; i<goals.length; i++)
            for (int j=0; j<goals[i].length; j++) {
                if (goals[i][j].getType() == Goal.MOVABLE && !goals[i][j].isHit() && !goalMoved[i][j]) {
                    int chanceToSwap = 50;//in percents, range: 0-100
                    Random generator = new Random();
                    if ((generator.nextInt(100)+1) <= chanceToSwap) {
                        ArrayList<canMove> canSwap = new ArrayList<canMove>();
                        for (int x = i==0 ? 0 : i-1; x<(i==(goals.length-1) ? goals.length : i+1); x++) {
                            for (int y = j==0 ? 0 : j-1; y < (j==(goals[i].length-1) ? goals[i].length : j+1); y++) {
                                if (goals[x][y].getType() == Goal.MOVABLE && goals[x][y].isHit())
                                    canSwap.add(new canMove(x,y));
                            }
                        }
                        if (!canSwap.isEmpty()) {
                            int index = generator.nextInt(canSwap.size());
                            Goal g = goals[i][j];
                            canMove cm = canSwap.get(index);
                            goals[i][j] = goals[cm.x][cm.y];
                            goals[cm.x][cm.y] = g;
                            goalMoved[cm.x][cm.y] = true;
                            canSwap.clear();
                        }
                    }
                }
            }
    }

    
    public GameRecord[] updateHighScoreRecords(GameRecord[] highScoreRecords, String name, int level, int score) {
        // write your code after this line
        GameRecord[] hsr;
        GameRecord gr_new = new GameRecord(name, level, score);
        
        if ( highScoreRecords.length == 0 ) {
            hsr = new GameRecord[1];
            hsr[0] = gr_new;
            return hsr;
        }
        
        boolean better = false;
        GameRecord gr_tmp = highScoreRecords[0];
        
        //if player played before and his score is better, replace his previous score
        int i=0;
        for (; i<highScoreRecords.length; i++) {
            gr_tmp = highScoreRecords[i];
            if (gr_new.getName().equals(gr_tmp.getName())) {
                if (gr_new.getScore() > gr_tmp.getScore() ||
                   (gr_new.getScore() == gr_tmp.getScore() && gr_new.getLevel() > gr_tmp.getLevel())) {
                    highScoreRecords[i] = gr_new;
                }
                break;
            }
        }
        
        //increase hsr array if player is new (to store his data)
        if (i >= highScoreRecords.length) {
            hsr = new GameRecord[highScoreRecords.length + 1];
            hsr[highScoreRecords.length] = gr_new;
        } else {
            hsr = new GameRecord[highScoreRecords.length];
        }
        
        //copying
        for (i=0; i<highScoreRecords.length; i++) {
            hsr[i] = highScoreRecords[i];
        }
        
        //sorting
        int j = hsr.length - 1;
        while ( j > 0) {
            if ( hsr[j].getScore() > hsr[j-1].getScore() ||
               ( hsr[j].getScore() == hsr[j-1].getScore() && hsr[j].getLevel() > hsr[j-1].getLevel() )) {
                gr_tmp = hsr[j];
                hsr[j] = hsr[j-1];
                hsr[j-1] = gr_tmp;
            }
            j--;
        }
        
        //build return array of max 10 records
        GameRecord[] result;
        if ( hsr.length > 10 )
            result = new GameRecord[10];
        else
            result = new GameRecord[hsr.length];
        for (i=0; i<10 && i<hsr.length; i++)
            result[i] = hsr[i];
        
        return result;
    }
}

