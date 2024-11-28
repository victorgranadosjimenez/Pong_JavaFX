
package juego;
import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable {

    private String name;
    private int points;

    public Score(String name, int score) {
        this.name = name;
        this.points = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(Score o) {
        if (this.points < o.points) {
            return 1;
        } else if (this.points > o.points) {
            return -1;
        } else {
            return 0;
        }
    }
        
}
