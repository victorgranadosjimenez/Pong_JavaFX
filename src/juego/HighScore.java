package juego;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HighScore extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 300, 250);
        Label labelName = new Label("Nombre:");
        TextField textFieldName = new TextField();
        Label labelPoints = new Label("Puntuación:");
        TextField textFieldPoints = new TextField();
        TextArea textAreaResults = new TextArea();
        Button btn = new Button("Guardar");
        Label labelPosition = new Label("Posición:");
        TextField textFieldPosition = new TextField();

        textAreaResults.setEditable(false);
        textFieldPosition.setEditable(false);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(labelName, 0, 0);
        gridPane.add(textFieldName, 1, 0);
        gridPane.add(labelPoints, 0, 1);
        gridPane.add(textFieldPoints, 1, 1);
        gridPane.add(btn, 1, 2);
        gridPane.add(labelPosition, 0, 3);
        gridPane.add(textFieldPosition, 1, 3);
        gridPane.add(textAreaResults, 0, 4, 2, 1);

        primaryStage.setTitle("High Scores");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Creación de objetos para almacenar máximas puntuaciones
        Scores scores = new Scores();
        ScoresFile scoresFile = new ScoresFile();
        // Cargar la lista inicial de máximas puntuaciones
        scoresFile.load(scores);
        // Mostrar la lista inicial de máximas puntuaciones
        textAreaResults.setText(scores.toString());

        btn.setOnAction((ActionEvent event) -> {
            // Recoger datos de nueva puntuación desde la ventana
            String playerName = textFieldName.getText();
            int value = Integer.valueOf(textFieldPoints.getText());
            // Crear una nueva puntuación
            Score score = new Score(playerName, value);
            // Añadirla a la lista de puntuaciones
            scores.addScore(score);
            // Mostrar la posición correspondiente a la puntuación en la lista 
            //  o -1 si no está entre los primeros
            textFieldPosition.setText(String.valueOf(scores.getPosition(score) + 1));
            // Mostrar la lista de máximas puntuaciones
            textAreaResults.setText(scores.toString());
            // Almacenar la lista de máximas puntuaciones
            scoresFile.save(scores);
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
