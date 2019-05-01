package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Map;


public class Main extends Application {
	private Media sound;
	private MediaPlayer mediaPlayer;

	@Override
	public void start(Stage primaryStage) {
		sound = new Media(getClass().getResource("/Avengers Suite (Theme).mp3").toString());
		mediaPlayer = new MediaPlayer(sound);
		System.out.println(sound.getDuration());
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.setOnReady(new Runnable() {

			@Override
			public void run() {

				System.out.println("Duration: "+sound.getDuration().toSeconds());

				// display media's metadata
				for (Map.Entry<String, Object> entry : sound.getMetadata().entrySet()){
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}

				// play if you want
				mediaPlayer.setVolume(0.15);
				mediaPlayer.stop();
				mediaPlayer.play();
			}
		});

		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root,1281.73,720);
			//scene.setFill(Color.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}
