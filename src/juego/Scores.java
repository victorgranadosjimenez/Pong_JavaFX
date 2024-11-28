
package juego;

import java.util.ArrayList;
import java.util.Collections;

public class Scores  {

    public static final int MAX_SCORES = 5;
    private ArrayList<Score> scoresList = new ArrayList();

    public ArrayList<Score> getScoresList() {
        return scoresList;
    }

    public void setScoresList(ArrayList<Score> scoresList) {
        this.scoresList = scoresList;
    }

    public void addScore(Score score) {
        scoresList.add(score);
        Collections.sort(scoresList);
        if(scoresList.size() > MAX_SCORES) {
            scoresList.remove(scoresList.size() - 1);
        }
    }

    public int getPosition(Score score) {
        return scoresList.indexOf(score);
    }
    
    @Override
    public String toString() {
        String result = "";
        for(int i=0; i<scoresList.size(); i++) {
            Score score = scoresList.get(i);
            result += (i+1) + "º: " + score.getName() + ": " + score.getPoints() + "\n";
        }
        return result;
    }
       
}
