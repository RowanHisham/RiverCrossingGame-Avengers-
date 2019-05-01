package application;

import characters.Character;
import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
public class MainGameFormController {
	private Media sound = new Media(getClass().getResource("/nick.mp3").toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(sound);

	@FXML
	private ImageView  imgLeftAnim1, imgLeftAnim2, imgLeftAnim3,imgRightAnim1, imgRightAnim2, imgRightAnim3,imgTopLeft,imgTopRight;

	@FXML
	private Rectangle rectangle;

	@FXML
	private Pane leftCharGroup, rightCharGroup;

	@FXML
	private HBox shipLeftGroup, shipRightGroup;

	@FXML
	private JFXButton btn_cross, btn_save,btn_load,btn_undo,btn_redo,btn_instructions;

	@FXML
	private Pane pn_Warning;

	@FXML
	private Label lbl_score;

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
		 if(event.getSource() == btn_cross) {
			System.out.println("here");
			animateShip();
		}else if(event.getSource() == btn_undo) {
			System.out.println("undo");
		}else if(event.getSource() == btn_redo) {
			System.out.println("redo");
		}else if(event.getSource() == btn_load) {
			System.out.println("load");
		}else if(event.getSource() == btn_save) {
			System.out.println("save");
		}else if(event.getSource() == btn_instructions) {
			System.out.println("instruction");
			Alert alert = new Alert(AlertType.INFORMATION, "Instructions", ButtonType.CLOSE);
			alert.setHeaderText(null);
			alert.setGraphic(null);
			alert.setTitle(null);
			alert.showAndWait();
		}
	}
	
	@FXML
	void shipRightOnAction(Event event) {
		rightShipAnimation((ImageView)event.getSource());
	}

	@FXML
	void shipLeftOnAction(Event event) {
		leftShipAnimation((ImageView)event.getSource());
	}

	@FXML
	void rightCharOnAction(Event event) {
		rightCharAnimation((ImageView)(ImageView)event.getSource());
	}

	@FXML
	void leftCharOnAction(Event event) {
		leftCharAnimation((ImageView)(ImageView)event.getSource());
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
		mediaPlayer.stop();
		mediaPlayer.play();
		pn_Warning.setVisible(true);

		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
								@Override public void handle(ActionEvent t) {
									pn_Warning.setVisible(false);
								}
							}
		);
		pause.play();
	}
}