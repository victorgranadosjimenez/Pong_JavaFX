
package juego;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoresFile {

    private final File highScoreFile;

    public ScoresFile() {
        highScoreFile = new File("highscores.dat");
    }

    public void load(Scores scores) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(highScoreFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            scores.setScoresList((ArrayList<Score>)ois.readObject());
        } catch (FileNotFoundException ex) {
            // No existe el fichero. Se creará posteriormente al guardar
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoresFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fis != null) {
                   fis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ScoresFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void save(Scores scores) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(highScoreFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject((ArrayList<Score>)scores.getScoresList());
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(ScoresFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fos != null) {
                   fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ScoresFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
