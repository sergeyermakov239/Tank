
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class Tankgoes1 extends Application {

    private void playSound(){
        Media media = new Media(new File("src/neiz_esten-z_uk-tanka.wav").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }
    private void stopSound(){
        Media media = new Media(new File("src/neiz_esten-z_uk-tanka.wav").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        playSound();
    }
    public void stop(Stage primaryStage) throws Exception {
        playSound();
    }

    public static void main(String[] args) {
        launch();
    }
}
