package application;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MainGameFormController {


	@FXML
	ImageView imgLeftChar1, imgLeftChar2, imgLeftAnim1, imgLeftAnim2, imgLeftAnim3, imgLeftShip1;

	@FXML
	public void initialize() {
		imgLeftAnim1.setVisible(false);
		imgLeftAnim2.setVisible(false);
		imgLeftAnim3.setVisible(false);
		imgLeftShip1.setVisible(false);
	}


	@FXML
	void buttonOnAction(Event event) throws IOException {
		if( event.getSource() == imgLeftChar1) {
			leftCharAnimation();
		}else if(event.getSource() == imgLeftChar2) {
			leftCharAnimation();
		}
	}


	//takes parameter character
	void leftCharAnimation() {
		System.out.println("Here");
		PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				imgLeftChar1.setVisible(false);
				imgLeftAnim1.setVisible(true);
			}
		}
				);
		pause.play();


		PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
		pause2.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				imgLeftAnim1.setVisible(false);
				imgLeftAnim2.setVisible(true);
			}
		}
				);
		pause2.play();	

		PauseTransition pause3 = new PauseTransition(Duration.seconds(1.5));
		pause3.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				imgLeftAnim2.setVisible(false);
				imgLeftAnim3.setVisible(true);
			}
		}
				);
		pause3.play();

		PauseTransition pause4 = new PauseTransition(Duration.seconds(2));
		pause4.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				imgLeftAnim3.setVisible(false);
				imgLeftShip1.setVisible(true);
			}
		}
				);
		pause4.play();

	}
}


