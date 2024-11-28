package juego;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

  

/**
 * El clásico Pong de dos jugadores donde el jugador de la izquierda mueve la
 * pala o raqueta con las teclas W y S, y el jugador de la derecha la mueve con
 * las teclas de arriba y abajo del cursor.
 * 
 * @author profesorado
 */
public class PongFX extends Application {
    
    /** Tamaño de la pantalla en ancho */
    public static final int WIDTH = 800;
    /** Tamaño de la pantalla en alto */
    public static final int HEIGHT = 600;
    
    /** Anchura de cada pala */
    public static final int ANCHURA_PALA = 7 ;
    /** Altura de cada pala */
    public static final int ALTURA_PALA = 50 ;
    
    /** Tamaño de la pelota  */
    public static final int TAMANO_PELOTA = 6 ;
    
    /** Coordenadas del eje X donde se ubica laa pala izquierda */
    public static final int POSICION_X_PALA_IZQUIERDA = 100 ;
    /** Coordenadas del eje X donde se ubica laa pala derecha */
    public static final int POSICION_X_PALA_DERECHA = 700 ;
    
    /** Número de vidas, en este caso de juegos para terminar la partida */
    public static final int NUM_VIDAS = 3 ;
    
    /** Tamaño del texto para los marcadores */
    public static final int TEXT_SIZE = 30 ;
    
    /** URL del sonido que se reproduce al marcar punto alguno de los dos jugadores */
    public static final String SONIDO_PUNTO = "src/recursos/336913__the-sacha-rush__coin1.wav" ;
    /** URL del sonido que se reproduce al rebotar la pelota */
    public static final String SONIDO_REBOTE = "src/recursos/rebote.wav" ;
    /** URL del sonido que se reproduce al terminar la partida */
    public static final String SONIDO_GAMEOVER = "src/recursos/pacman-dies.wav" ;
    public static final String ICONO = "file:src/recursos/palas.jpg" ;
   
    
    
    // Coordenada X de la pelota. La primera vez la pelota se iniciará desde la
    // izquierda más un poco
    private int posXpelota = POSICION_X_PALA_IZQUIERDA + 10 ;
    // La pelota irá avanzando en X según este atributo
    private int velocidadActualX = 3 ;
    
    // Coordenada Y de la pelota. 
    private int posYpelota = 30 ;
    // La pelota irá avanzando en Y según este atributo
    private int velocidadActualY = 3 ;
    
    /** Atributos de posición y velocidad de la pala derecha */
    private int posicionYpalaDerecha = (HEIGHT - ALTURA_PALA) / 2;
    private int velocidadPalaDerecha = 0 ;
    
    /** Atributos de posición y velocidad de la pala izquierda */
    private int posicionYpalaIzquierda = (HEIGHT - ALTURA_PALA) / 2;
    private int velocidadPalaIzquierda = 0 ;
    
    // Contador de puntos de cada jugador
    private int puntosJugadorDerecho ;
    private int puntosJugadorIzquierdo ;
    
    // Texto para los puntos de cada jugador
    private Text puntosDer, puntosIzq ;
   
    // Objetos text
    Text textScore, textScore2;
    Text titleScore, titleScore2;
    Text textTitleHighScore;
    Text textHighScore;
    
    int score=0;
    int score2=0;    
    int highScore;
    
    Rectangle leftStick, rightStick;
    Circle bola;
    Shape rightColision, leftColision;
    
    Pane panelDeJuego;
    
    
    /**
     * Cuando uno de los dos jugadores gane el punto,se ejecuta este método para
     * sacar la pelota de nuevo, estableciendo los valores posXpelota,
     * posYpelota y velocidadActualX
     */
    private void iniciarSiguientePunto() {
        // Establecer la posición X de la pelota como POSICION_X_PALA_IZQUIERDA + 10 por ejemplo
        
        // Establecer la velocidad actual X a 3
        
        // Establecer la posición Y de la polota como un número aleatorio entero entre 0 y HEIGHT      
            
    }
   
    
    
    /**
     * Dibuja la red y los marcadores de cada jugador
     * 
     * @param root Panel donde se representa el juego
     */
    private void dibujarRedYmarcador(Pane panelDeJuego) {
        
        
        // DIBUJAR RED
        for (int i=0 ; i < HEIGHT ; i+=30){
 
        Line line = new Line (WIDTH/2, i, WIDTH/2, i+10);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(4);
        panelDeJuego.getChildren().add(line);
        }
           
        // DIBUJAR MARCADOR
        HBox ScorePanel = new HBox();
        ScorePanel.setTranslateY(20);
        ScorePanel.setMinWidth(WIDTH);
        ScorePanel.setAlignment(Pos.CENTER);
        ScorePanel.setSpacing(160);
        panelDeJuego.getChildren().add(ScorePanel);
        
        HBox currentScore = new HBox();
        currentScore.setSpacing(10);
        ScorePanel.getChildren().add(currentScore);
        
        HBox highScore = new HBox();
        highScore.setSpacing(10);
        ScorePanel.getChildren().add(highScore);
        
        titleScore = new Text("Player A:");
        titleScore.setFont(Font.font(TEXT_SIZE));
        titleScore.setFill(Color.BLUE);

        textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.BLUE);                   
        
        textTitleHighScore = new Text("Max.Score:");
        textTitleHighScore.setFont(Font.font(TEXT_SIZE));
        textTitleHighScore.setFill(Color.WHITE);   
        
        textHighScore = new Text("0");
        textHighScore.setFont(Font.font(TEXT_SIZE));
        textHighScore.setFill(Color.WHITE);   
        
        titleScore2 = new Text("Player B:");
        titleScore2.setFont(Font.font(TEXT_SIZE));
        titleScore2.setFill(Color.RED);

        textScore2 = new Text("0");
        textScore2.setFont(Font.font(TEXT_SIZE));
        textScore2.setFill(Color.RED); 
        
        currentScore.getChildren().add(titleScore);
        currentScore.getChildren().add(textScore);
        currentScore.getChildren().add(titleScore2);
        currentScore.getChildren().add(textScore2);
        highScore.getChildren().add(textTitleHighScore);
        highScore.getChildren().add(textHighScore);
        
    }
           
    
    
    // EL MÉTODO START ES EL QUE SE INICIA CUANDO EJECUTAMOS EL PROGRAMA
    /**
     * Método de arranque de la aplicación
     * @param escenario Escenario de la aplicación
     */
    @Override
    public void start(Stage escenario) {
        
               
        // CREAMOS UN OBJETO PANEL/LAYOUT/CONTENEDOR
        panelDeJuego = new Pane();
        
        // CREAMOS UN OBJETO SCENE
        Scene scene = new Scene (panelDeJuego, WIDTH, HEIGHT) ;
  
              
        // FONDO NEGRO
        panelDeJuego.setStyle("-fx-background-color:black") ;
        
        
        // Dibujar red y marcador
        this.dibujarRedYmarcador(panelDeJuego);
        
        // Dibujas palas
        this.dibujarPalas();
        
        // Definir las teclas para el movimiento de las palas
        this.definirMovimientoTeclas(scene);
        

        // CREAMOS OBJETO LA PELOTA Y LO AÑADIMOS DE HIJO AL LAYOUT
        bola = new Circle(posXpelota, posYpelota, 7, Color.WHITE);
        panelDeJuego.getChildren().add(bola);
           
     
        
        
        
        // Se usa una línea de tiempo para definir una animación.
        // La idea básica en una animaciones JavaFX es manipular el valor de una
        // o más propiedades de un nodo a intervalos regulares. Por ejemplo, 
        // en el caso que nos ocupa, tenemos un círculo que representa una bola
        // y se desea moverlo desde un lado de la pantalla hacia el otro.
        Timeline animacion = new Timeline();
        // Crear un KeyFrame, un punto de referencia que se agregará a la línea
        // de tiempo. Una línea de tiempo funciona empleando KeyFrames como 
        // puntos de referencia en la animación.
        // Observa que en la creación del objeto se pasa por parámetro
        // un objeto KeyFrame al que se le indica cada cuánto tiempo se 
        // ejecutarán las acciones, se indica 0.017 para que se ejecute cada 
        // 0.017 segundos que equivale a unos 60 frames por segundo.
        KeyFrame kf = new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {

            
            // Define la posición horizontal del centro de la pelota en pixels.
            // Incrementamos el valor de atributo de la posición horizontal            
            bola.setCenterX(posXpelota);
            posXpelota+= velocidadActualX;
            
            
            // cambiar la dirección si se ha llegado al final o al principio de 
            // la pantalla a la derecha o a la izquierda            
            if (posXpelota >= WIDTH){
                
                if(score > highScore){
                    highScore = score;
                    textHighScore.setText(String.valueOf(highScore));
                }
                score =0;
                textScore.setText(String.valueOf(score));
                textScore2.setText(String.valueOf(score));
                posXpelota = 10;                
                velocidadActualY = 3;             
            }
            if (posXpelota <= 0){
                if(score > highScore){
                    highScore = score;
                    textHighScore.setText(String.valueOf(highScore));
                }
                score =0;
                textScore.setText(String.valueOf(score));
                textScore2.setText(String.valueOf(score));
                posYpelota = 10;                
                velocidadActualX = 3;           
            }            
            
                 
            // Define la posición vertical del centro de la pelota en pixels.
            // Incrementamos el valor de atributo de la posición vertical
            bola.setCenterY(posYpelota);
            posYpelota+= velocidadActualY;            
            
            // Comprobar si la pelota debe rebotar, es decir cambiar la dirección
            if (posYpelota >= HEIGHT){
                velocidadActualY = -3;             
            }
            if (posYpelota <= 0){
                velocidadActualY = 3;             
            }      
   
                    
            // Actualizar la posición de las palas cuidando que no se salgan de la pantalla
            //actualizarPosicionPalas(palaIzquierda, palaDerecha) ; 
            posicionYpalaDerecha += velocidadPalaDerecha;
            this.actualizarPosicionPalas(leftStick, leftStick);
                    
            // COMPROBAR COLISION
            this.getZonaColisionEnLaPala();
                 
                       }
        );
        
        
        
        // Añadir el keyframe a la animación
        animacion.getKeyFrames().addAll(kf);
        // Establecer el ciclo de la nimación indefinido
        animacion.setCycleCount(Timeline.INDEFINITE);
        // Poner en marcha la animación        
        animacion.play();

        // AL OBJETO TIPO STAGE INTRODUCIDO EN LA VARIABLE
        // LE PONEMOS TITULO, EL OBEJTO SCENE QUE ACABAMOS DE DECLARAR
        // Y LO MOSTRAMOS.
        escenario.setTitle("Pong con JavaFX de Víctor");
        escenario.getIcons().add(new Image(ICONO));     
        escenario.setScene(scene);
        escenario.show();
    }
    
    
    /** 
     * Método para averiguar la zona de contacto de la pelota con la pala.
     * La variable offsetBallStick almacena la distancia que se detecta entre 
     * la posición del rectángulo (la pala) y centro de la bola. Si ese valor 
     * es menor que el 10% de la altura de la pala se considerará que ha
     * contactado en la zona 1. Si no es ese caso, se comprueba si ha contactado
     * de la mitad de la pala hacia arriba, en cuyo caso sería la zona 2.
     * Se considera que ha contactado en la zona 3 si la posición de la bola es
     * mayor al centro de la pala y menor que el 90% de su altura. La zona de
     * contacto se considera la 4 si el punto de contacto es mayor que el 90% 
     * del tamaño de la pala. Ver en: https://javiergarciaescobedo.es/programacion-en-java/93-proyectos-de-aula/478-programacion-java-desarrollando-el-juego-pong-con-javafx-parte-ix
     * 
     * @param pelota    Pelota
     * @param pala      Pala
     * @return          Valor 0 si no hay contacto o bien 1, 2 o 3 según la zona.
     */
    private void getZonaColisionEnLaPala() {
        
        rightColision = Shape.intersect(bola, rightStick);
        boolean noRightColision = rightColision.getBoundsInLocal().isEmpty();
        if (noRightColision == false && velocidadActualX > 0) {
            velocidadActualX = -3;
            score++;
            textScore.setText(String.valueOf(score));

        }
        leftColision = Shape.intersect(bola, leftStick);
        boolean noLeftColision = leftColision.getBoundsInLocal().isEmpty();
        if (noLeftColision == false && velocidadActualX < 0) {
            velocidadActualX = 3;
            score2++;
            textScore2.setText(String.valueOf(score2));
        }

    }

    

    /**
     * Según la zona de la pala derecha donde impacte la pelota se modifican los 
     * atributos de velocidadActualX y velocidadActualY de la pelota para
     * que en la animación se vayan incrementando o decrementando la posición,
     * las coordenadas de la pelota segúnm corresponda.
     * 
     * @param zonaDeColision Entero de 0 a 4 que representa la zona de la pala
     *                donde impacta la pelota.
     */
    private void calcularVelocidadPelotaPalaDer(int zonaDeColision) {
        
        switch (zonaDeColision) {    
            // No hay colisión de la pala con la pelota.
            case 0:    
              
                
            // Hay colisión en la esquina superior de la pala
            case 1: 
               
                
            // Hay colisión en el lado superior de la pala
            case 2: 
                
                
            // Hay colisión en el lado inferior de la pala
            case 3: 
                
                
            // Hay colisión en la esquina inferior de la pala
            case 4: 
               
        }
    }
    
    
    /**
     * Según la zona de la pala izquierda donde impacte la pelota se modifican los 
     * atributos de velocidadActualX y velocidadActualY de la pelota para
     * que en la animación se vayan incrementando o decrementando la posición,
     * las coordenadas de la pelota segúnm corresponda.
     * 
     * @param zonaDeColision Entero de 0 a 4 que representa la zona de la pala
     *                donde impacta la pelota.
     */
    private void calcularVelocidadPelotaPalaIzq(int zonaDeColision) {
        
        // Según la zona de colisión
        switch (zonaDeColision) {
            //No hay colisión
            case 0:
               
                
            // Hay colisión en la esquina superior
            case 1: 
                
                
            // Hay colisión en el lado superior
            case 2: 
                
                
            // Hay colisión en el lado inferior    
            case 3: 
               
              
            // Hay colisión en la esquina inferior
            case 4: 
               
        }
    }
    

    
    /**
     * Actualizar la posición de las palas cuidando que no se salgan de la pantalla.
     * 
     * @param palaIzquierda Objeto de la clase Rectangle que es la pala izquierda.
     * @param palaDerecha   Objeto de la clase Rectangle que es la pala derecha.
     */
    private void actualizarPosicionPalas(Rectangle palaIzquierda, Rectangle palaDerecha) {
        
        if (posicionYpalaDerecha < 0) {
            posicionYpalaDerecha = 0;
        } else {
            if (posicionYpalaDerecha > HEIGHT - ALTURA_PALA) {
                posicionYpalaDerecha = HEIGHT - ALTURA_PALA;
            }
            rightStick.setY(posicionYpalaDerecha);
        }

        posicionYpalaIzquierda += velocidadPalaIzquierda;
        if (posicionYpalaIzquierda < 0) {
            posicionYpalaIzquierda = 0;
        } else {
            if (posicionYpalaIzquierda > HEIGHT - ALTURA_PALA) {
                posicionYpalaIzquierda = HEIGHT - ALTURA_PALA;
            }
            leftStick.setY(posicionYpalaIzquierda);
            }        
        
        
        
    }
    
    
    /**
     * Reproducir el sonido cuya ruta se indique en el parámetro.
     * 
     * @param nombreSonido Ruta con el nombre del sonido a reproducir
     */
    public void reproducirSonido(String nombreSonido){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
           System.err.println("Error al reproducir el sonido.");
       }
     }
    
public void dibujarPalas(){
    // Dibujar la pala derecha y añadirla al panel
    rightStick = new Rectangle(WIDTH * 0.9, posicionYpalaDerecha,
            ANCHURA_PALA, ALTURA_PALA);
    rightStick.setFill(Color.WHITE);
    panelDeJuego.getChildren().add(rightStick);

    // Dibular la pala izquierda y añadirla al panel
    leftStick = new Rectangle(WIDTH * 0.1, posicionYpalaIzquierda,
            ANCHURA_PALA, ALTURA_PALA);
    leftStick.setFill(Color.WHITE);
    panelDeJuego.getChildren().add(leftStick);
}    
    

    
    /**
     * Definir las teclas para mover las palas arriba y abajo o dejar de 
     * moverlas al dejar de presionar las teclas.
     * 
     * @param scene La escena donde se desarrolla el juego
     */
    private void definirMovimientoTeclas(Scene scene) {
        
        // Tratar los eventos de teclado en la escena
        scene.setOnKeyPressed((KeyEvent t) -> {
            switch (t.getCode()) {
                case UP:
                    velocidadPalaDerecha = -6;           
                    break;
                    
                case DOWN:
                    velocidadPalaDerecha = 6;           
                    break;
                    
                case W:
                    velocidadPalaIzquierda = -6;           
                    break;
                    
                case S:
                    velocidadPalaIzquierda = 6;           
                    break; 
                    
            }
        });
        
        
        
        /**
         * Definir qué pasa cuando se dejen de pulsar las teclas.
         * Cuando se libere la pulsación de las teclas de la pala, se dejarán
         * de desplazar por la pantalla, por tanto, dado que el movimiento es
         * en la vertical, velocidadPalaDerecha o bien velocidadPalaIzquierda
         * será establecido a 0, según la pala que sea, o sea, según la tecla
         * de la pala que se haya dejadp de pulsar
         */
        scene.setOnKeyReleased((KeyEvent t) -> {
            // Detenemos el movimiento de la pala al dejar de presionar
            velocidadPalaDerecha = 0;
            velocidadPalaIzquierda = 0;
            switch (t.getCode()) {
                case UP:
                    // Soltada tecla arriba pala derecha
                    
                    
                case DOWN:
                    // Soltada tecla abajo pala derecha
                    
                    
                case W:
                    // Soltada tecla arriba pala izquierda
                    
                    
                case S:
                    // Soltada tecla arriba pala izquierda
                    
                    
            }
        });
    }
  
    /**
     * Programa principal, lanza la aplicación.
     * @param args Argumentos o parámetros (no hay en este caso)
     */
    public static void main(String[] args) {
        launch(args);
    }
}