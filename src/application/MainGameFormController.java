package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXButton;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import levels.Level;
import characters.*;
import characters.Character;
public class MainGameFormController {
	private Media sound;
	private MediaPlayer mediaPlayer;
	
	@FXML
	private ImageView  imgLeftAnim1, imgLeftAnim2, imgLeftAnim3,imgRightAnim1, imgRightAnim2, imgRightAnim3,imgTopLeft,imgTopRight;
	
	@FXML
	private Rectangle rectangle;
	
	@FXML
	private Pane leftCharGroup, rightCharGroup;
	
	@FXML
	private HBox shipLeftGroup, shipRightGroup;
	
	@FXML
	private JFXButton btn_cross;
	
	@FXML
	private Pane pn_Warning;
	
	private Map<ImageView,Character> leftShipMap = new LinkedHashMap<>();
	private Map<ImageView,Character> rightShipMap = new LinkedHashMap<>();
	private Map<ImageView,Character> leftCharMap = new LinkedHashMap<>();
	private Map<ImageView,Character> rightCharMap = new LinkedHashMap<>();
	
	boolean shipLeft = true;


	@FXML
	public void initialize() {
		imgLeftAnim1.setVisible(false);
		imgLeftAnim2.setVisible(false);
		imgLeftAnim3.setVisible(false);
		imgRightAnim1.setVisible(false);
		imgRightAnim2.setVisible(false);
		imgRightAnim3.setVisible(false);
		rectangle.setVisible(false);
		
		pn_Warning.setVisible(false);
		
		
		for(Node node: shipLeftGroup.getChildren()) {
			leftShipMap.put((ImageView) node, null);
			node.setVisible(false);
		}
		
		for(Node node: shipRightGroup.getChildren()) {
			rightShipMap.put((ImageView) node, null);
			node.setVisible(false);
		}

		
		for(int i=0; i < rightCharGroup.getChildren().size(); i++ ) {
//			for(Character node: Level.getInstance().getLeftCharacters()) {
//				rightCharMap.put((ImageView)rightCharGroup.getChildren().get(i), node);
//				((ImageView)rightCharGroup.getChildren().get(i)).setVisible(false);
//				i++;
//			}
			rightCharMap.put((ImageView)rightCharGroup.getChildren().get(i), null);
			((ImageView)rightCharGroup.getChildren().get(i)).setVisible(false);

		}
		
		for(int i=0; i < leftCharGroup.getChildren().size(); i++ ) {
//			for(Character node: Level.getInstance().getLeftCharacters()) {
//				leftCharMap.put((ImageView)leftCharGroup.getChildren().get(i), node);
//				((ImageView)leftCharGroup.getChildren().get(i)).setVisible(true);
//				i++;
//			}
			leftCharMap.put((ImageView)leftCharGroup.getChildren().get(i), null);
			((ImageView)leftCharGroup.getChildren().get(i)).setVisible(true);
		}
	}
	

	@FXML
	void buttonOnAction(Event event) throws IOException {
		if( event.getSource() == (ImageView)leftCharGroup.getChildren().get(0)) {
			leftCharAnimation((ImageView)leftCharGroup.getChildren().get(0));
		}else if(event.getSource() == (ImageView)leftCharGroup.getChildren().get(1)) {
			leftCharAnimation((ImageView)leftCharGroup.getChildren().get(1));
		}else if(event.getSource() == (ImageView)leftCharGroup.getChildren().get(2)) {
			leftCharAnimation((ImageView)leftCharGroup.getChildren().get(2));
		}else if(event.getSource() == (ImageView)leftCharGroup.getChildren().get(3)) {
			leftCharAnimation((ImageView)leftCharGroup.getChildren().get(3));
		}else if(event.getSource() == (ImageView)leftCharGroup.getChildren().get(4)) {
			leftCharAnimation((ImageView)leftCharGroup.getChildren().get(4));
		}else if(event.getSource() == (ImageView)leftCharGroup.getChildren().get(5)) {
			leftCharAnimation((ImageView)leftCharGroup.getChildren().get(5));
		}else if(event.getSource() == shipLeftGroup.getChildren().get(0)) {
			leftShipAnimation((ImageView)shipLeftGroup.getChildren().get(0));
		}else if(event.getSource() == shipLeftGroup.getChildren().get(1)) {
			leftShipAnimation((ImageView)shipLeftGroup.getChildren().get(1));
		}else if(event.getSource() == shipLeftGroup.getChildren().get(2)) {
			leftShipAnimation((ImageView)shipLeftGroup.getChildren().get(2));
		}else if(event.getSource() == shipLeftGroup.getChildren().get(3)) {
			leftShipAnimation((ImageView)shipLeftGroup.getChildren().get(3));
		}else if(event.getSource() == shipLeftGroup.getChildren().get(4)) {
			leftShipAnimation((ImageView)shipLeftGroup.getChildren().get(4));
		}else if( event.getSource() == (ImageView)rightCharGroup.getChildren().get(0)) {
			rightCharAnimation((ImageView)rightCharGroup.getChildren().get(0));
		}else if(event.getSource() == (ImageView)rightCharGroup.getChildren().get(1)) {
			rightCharAnimation((ImageView)rightCharGroup.getChildren().get(1));
		}else if(event.getSource() == (ImageView)rightCharGroup.getChildren().get(2)) {
			rightCharAnimation((ImageView)rightCharGroup.getChildren().get(2));
		}else if(event.getSource() == (ImageView)rightCharGroup.getChildren().get(3)) {
			rightCharAnimation((ImageView)rightCharGroup.getChildren().get(3));
		}else if(event.getSource() == (ImageView)rightCharGroup.getChildren().get(4)) {
			rightCharAnimation((ImageView)rightCharGroup.getChildren().get(4));
		}else if(event.getSource() == (ImageView)rightCharGroup.getChildren().get(5)) {
			rightCharAnimation((ImageView)rightCharGroup.getChildren().get(5));
		}else if(event.getSource() == shipRightGroup.getChildren().get(0)) {
			rightShipAnimation((ImageView)shipRightGroup.getChildren().get(0));
		}else if(event.getSource() == shipRightGroup.getChildren().get(1)) {
			rightShipAnimation((ImageView)shipRightGroup.getChildren().get(1));
		}else if(event.getSource() == shipRightGroup.getChildren().get(2)) {
			rightShipAnimation((ImageView)shipRightGroup.getChildren().get(2));
		}else if(event.getSource() == shipRightGroup.getChildren().get(3)) {
			rightShipAnimation((ImageView)shipRightGroup.getChildren().get(3));
		}else if(event.getSource() == shipRightGroup.getChildren().get(4)) {
			rightShipAnimation((ImageView)shipRightGroup.getChildren().get(4));
		}else if(event.getSource() == btn_cross) {
			System.out.println("here");
			animateShip();
		}
	} 


	void leftCharAnimation(ImageView img) {
		System.out.println("Here");
		PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				img.setVisible(false);
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
				rectangle.setVisible(true);

			}
		}
				);
		pause3.play();

		PauseTransition pause4 = new PauseTransition(Duration.seconds(2));
		pause4.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				imgLeftAnim3.setVisible(false);
				rectangle.setVisible(false);
				

				for(Node node: shipLeftGroup.getChildren()) {
					if(!node.isVisible()) {
						node.setVisible(true);
						break;
					}
				}
			}
		}
				);
		pause4.play();
	}
	
	void rightCharAnimation(ImageView img) {
		System.out.println("Here");
		PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				img.setVisible(false);
				imgRightAnim1.setVisible(true);
			}
		}
				);
		pause.play();


		PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
		pause2.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				imgRightAnim1.setVisible(false);
				imgRightAnim2.setVisible(true);
			}
		}
				);
		pause2.play();	

		PauseTransition pause3 = new PauseTransition(Duration.seconds(1.5));
		pause3.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				imgRightAnim2.setVisible(false);
				imgRightAnim3.setVisible(true);
				rectangle.setVisible(true);

			}
		}
				);
		pause3.play();

		PauseTransition pause4 = new PauseTransition(Duration.seconds(2));
		pause4.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				imgRightAnim3.setVisible(false);
				rectangle.setVisible(false);
				

				for(Node node: shipRightGroup.getChildren()) {
					if(!node.isVisible()) {
						node.setVisible(true);
						break;
					}
				}
			}
		}
				);
		pause4.play();
	}
	
	void leftShipAnimation(ImageView img) {
		img.setVisible(false);
		
		for(Node node: leftCharGroup.getChildren()) {
			if(!node.isVisible()) {
				node.setVisible(true);
				break;
			}
		}
	}
	
	void rightShipAnimation(ImageView img) {
		img.setVisible(false);
		
		for(Node node: rightCharGroup.getChildren()) {
			if(!node.isVisible()) {
				node.setVisible(true);
				break;
			}
		}
		
	}
	
	void animateShip() {
		invalidMoveAnimation();
		
		 
		if(shipLeft) {
			Image image = new Image("/helicarrierLeftEmpty.png");
			imgTopLeft.setImage(image);
			image = new Image("/helicarrierRightAvailable.png");
			imgTopRight.setImage(image);	
			shipLeft = false;
		}else {
			Image image = new Image("/helicarrierLeftAvailable.png");
			imgTopLeft.setImage(image);
			image = new Image("/helicarrierRightEmpty.png");
			imgTopRight.setImage(image);
			shipLeft = true;
		}
	}
	
	void invalidMoveAnimation() {
		String musicFile = "nick.mp3";     // For example
		sound = new Media(new File(musicFile).toURI().toString());
		 mediaPlayer = new MediaPlayer(sound);
		 System.out.println(sound.getDuration());
		
		 mediaPlayer.setOnReady(new Runnable() {

		        @Override
		        public void run() {

		            System.out.println("Duration: "+sound.getDuration().toSeconds());

		            // display media's metadata
		            for (Map.Entry<String, Object> entry : sound.getMetadata().entrySet()){
		                System.out.println(entry.getKey() + ": " + entry.getValue());
		            }

		            // play if you want
		            mediaPlayer.stop();
		            mediaPlayer.play();
		            pn_Warning.setVisible(true);
		        }
		    });
		 PauseTransition pause = new PauseTransition(Duration.seconds(7));
			pause.setOnFinished(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent t) {
					pn_Warning.setVisible(false);
				}
			}
					);
			pause.play();
	}
}