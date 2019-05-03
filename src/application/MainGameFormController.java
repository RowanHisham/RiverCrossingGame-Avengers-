package application;

import characters.Character;
import com.jfoenix.controls.JFXButton;
import commands.Controller;
import commands.DisembarkCommand;
import commands.EmbarkCommand;
import commands.MoveCommand;
import gamestate.LoadCommand;
import gamestate.RedoCommand;
import gamestate.SaveCommand;
import gamestate.UndoCommand;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import levels.Level;

import java.io.IOException;
import java.util.*;

public class MainGameFormController {
	private MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/nick.mp3").toString()));
	private Level level = Level.getInstance();

	@FXML
	private ImageView  imgLeftAnim1, imgLeftAnim2, imgLeftAnim3,imgRightAnim1, imgRightAnim2, imgRightAnim3,imgTopLeft,imgTopRight;

	@FXML
	private Rectangle rectangle;

	@FXML
	private Pane leftCharGroup, rightCharGroup;

	@FXML
	private HBox shipLeftGroup, shipRightGroup;

	@FXML
	private JFXButton btn_cross, btn_save,btn_load,btn_undo,btn_redo,btn_instructions, btn_mainMenu, btn_mainMenu2;

	@FXML
	private Pane pn_Warning, pn_gameWon;

	@FXML
	private Label scoreLabel;

	private Map<ImageView,Character> leftShipMap = new LinkedHashMap<>();
	private Map<ImageView,Character> rightShipMap = new LinkedHashMap<>();
	private Map<ImageView,Character> leftCharMap = new LinkedHashMap<>();
	private Map<ImageView,Character> rightCharMap = new LinkedHashMap<>();

	private boolean shipLeft = true;
	private boolean embarkRunning = false;
	private Image carrierLeftEmpty = new Image(getClass().getResource("/helicarrierLeftEmpty.png").toString());
	private Image carrierRightEmpty = new Image(getClass().getResource("/helicarrierRightEmpty.png").toString());
	private Image carrierRightAvailable = new Image(getClass().getResource("/helicarrierRightAvailable.png").toString());
	private Image carrierLeftAvailable = new Image(getClass().getResource("/helicarrierLeftAvailable.png").toString());


	@FXML
	public void initialize() {
		if(level == null)
			throw new NullPointerException("Level is uninitialized");
		else
			loadLevel();
	}

	private void loadLevel() {
		if(level == null)
			throw new NullPointerException("Level is uninitialized");

		imgLeftAnim1.setVisible(false);
		imgLeftAnim2.setVisible(false);
		imgLeftAnim3.setVisible(false);
		imgRightAnim1.setVisible(false);
		imgRightAnim2.setVisible(false);
		imgRightAnim3.setVisible(false);
		rectangle.setVisible(false);
		pn_Warning.setVisible(false);
		pn_gameWon.setVisible(false);
		
		initializeMap(shipRightGroup.getChildren(), rightShipMap);
		initializeMap(shipLeftGroup.getChildren(), leftShipMap);
		initializeMap(rightCharGroup.getChildren(), rightCharMap);
		initializeMap(leftCharGroup.getChildren(), leftCharMap);

		scoreLabel.setText(String.valueOf(level.getMovesDone()));
		List<Node> shipGroup;
		Map<ImageView, Character> shipMap;
		if(shipLeft = level.getShipSide() == Level.ShipSide.LEFT) {
			shipGroup = shipLeftGroup.getChildren();
			shipMap = leftShipMap;
			hideAll(shipRightGroup.getChildren());
		}
		else {
			shipGroup = shipRightGroup.getChildren();
			shipMap = rightShipMap;
			hideAll(shipLeftGroup.getChildren());
		}
		displayShip();
		loadSide(level.getShip().getOnBoard(), shipMap, true);
		loadSide(level.getLeftCharacters(), leftCharMap);
		loadSide(level.getRightCharacters(), rightCharMap);

	}

	private void hideAll(Collection<Node> nodes) {
		for(Node node: nodes)
			node.setVisible(false);
	}

	private void initializeMap(List<Node> images, Map<ImageView, Character> map) {
		for(Node n: images) {
			map.put((ImageView)n, null);
			n.setVisible(false);
		}
	}
	private void loadSide(Collection<Character> characters, Map<ImageView, Character> map) {
		loadSide(characters, map, false);
	}
	private void loadSide(Collection<Character> characters, Map<ImageView, Character> map, boolean ship) {
		Iterator<Character> characterIterator = characters.iterator();
		for(ImageView n: map.keySet()) {
			if(characterIterator.hasNext()) {
				Character c = characterIterator.next();
				map.put(n, c);
				n.setImage(c.getImageArray().get((ship)?4:0));
				n.setVisible(true);
			}
			else {
				break;
			}
		}
	}


	@FXML
	void buttonOnAction(Event event) throws IOException {
		if(embarkRunning)
			return;
		if(event.getSource() == btn_cross) {
			if(Controller.executeCommand(new MoveCommand())) {
				scoreLabel.setText(String.valueOf(Level.getInstance().getMovesDone()));
				shipLeft = !shipLeft;
				displayShip();
				disembarkAll();
			}
			else
				invalidMoveAnimation();
		}else if(event.getSource() == btn_undo) {
			System.out.println("undo");
			if(Controller.executePassiveCommand(new UndoCommand()))
				loadLevel();
		}else if(event.getSource() == btn_redo) {
			System.out.println("redo");
			if(Controller.executePassiveCommand(new RedoCommand()))
				loadLevel();
		}else if(event.getSource() == btn_load) { //TODO save/load
                        System.out.println("load");
                        LoadCommand load = new LoadCommand();
                        load.loadFile();
                        level = load.updateLevel();
                        load.setState();
                        loadLevel();
        }else if(event.getSource() == btn_save) {
            System.out.println("save");
            SaveCommand save = new SaveCommand(level.getShip().getOnBoard(),
                    level.getLeftCharacters(),level.getRightCharacters(),level.getMovesDone(),
                    level.getShip().getMaxCharacters(),level.getStrategies(), 
                    level.getShipSide(),level.getShip().getWeightCapacity());
            save.saveFile();
		}else if(event.getSource() == btn_instructions) {
			System.out.println("instruction");
			Alert alert = new Alert(AlertType.INFORMATION, level.getRules(), ButtonType.CLOSE);
			alert.setHeaderText(null);
			alert.setGraphic(null);
			alert.setTitle(null);
			alert.showAndWait();
		}else if(event.getSource() ==  btn_mainMenu || event.getSource() == btn_mainMenu2) {
			Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainMenuForm.fxml"));
			Scene customerMainFormScene = new Scene(root);
			Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
			window.setScene(customerMainFormScene);
			window.show();
		}
	}

	@FXML
	void shipOnAction(Event event) {
		if(embarkRunning)
			return;
		ImageView source = (ImageView) event.getSource();
		Map<ImageView, Character> shipMap, charMap;
		if(shipLeft) {
			shipMap = leftShipMap;
			charMap = leftCharMap;
		}
		else {
			shipMap = rightShipMap;
			charMap = rightCharMap;
		}
		Character character = shipMap.get(source);
		if(Controller.executeCommand(new DisembarkCommand(character))) {
			shipMap.put(source, null);
			disembarkAnimation(source, character, charMap);
		}
				
	}

	@FXML
	void charOnAction(Event event) {
		if(embarkRunning)
			return;
		ImageView source = (ImageView) event.getSource();
		Map<ImageView, Character> charMap = (shipLeft)? leftCharMap: rightCharMap;
		Character character = charMap.get(source);
		if(Controller.executeCommand(new EmbarkCommand(character))) {
			charMap.put(source, null);
			embarkAnimation(source, character);
		}
	}

	private void embarkAnimation(ImageView img, Character character) {
		embarkRunning = true;
		System.out.println("Here");
		Map<ImageView, Character> charMap;
		ImageView imgAnim1, imgAnim2, imgAnim3;
		if(shipLeft) {
			charMap = leftShipMap;
			imgAnim1 = imgLeftAnim1;
			imgAnim2 = imgLeftAnim2;
			imgAnim3 = imgLeftAnim3;
		}
		else {
			charMap = rightShipMap;
			imgAnim1 = imgRightAnim1;
			imgAnim2 = imgRightAnim2;
			imgAnim3 = imgRightAnim3;
		}
		PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
								@Override public void handle(ActionEvent t) {
									img.setVisible(false);
									imgAnim1.setImage(character.getImageArray().get(1));
									imgAnim1.setVisible(true);
								}
							}
		);
		pause.play();


		PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
		pause2.setOnFinished(new EventHandler<ActionEvent>() {
								 @Override public void handle(ActionEvent t) {
									 imgAnim1.setVisible(false);
									 imgAnim2.setImage(character.getImageArray().get(2));
									 imgAnim2.setVisible(true);
								 }
							 }
		);
		pause2.play();

		PauseTransition pause3 = new PauseTransition(Duration.seconds(1.5));
		pause3.setOnFinished(new EventHandler<ActionEvent>() {
								 @Override public void handle(ActionEvent t) {
									 imgAnim2.setVisible(false);
									 imgAnim3.setImage(character.getImageArray().get(3));
									 imgAnim3.setVisible(true);
									 rectangle.setVisible(true);

								 }
							 }
		);
		pause3.play();

		PauseTransition pause4 = new PauseTransition(Duration.seconds(2));
		pause4.setOnFinished(new EventHandler<ActionEvent>() {
								 @Override public void handle(ActionEvent t) {
									 imgAnim3.setVisible(false);
									 rectangle.setVisible(false);
									 for(ImageView imageView: charMap.keySet()) {
										 if(charMap.get(imageView) == null) {
											 charMap.put(imageView, character);
											 imageView.setImage(character.getImageArray().get(4));
											 imageView.setVisible(true);
											 embarkRunning = false;
											 break;
										 }
									 }
								 }
							 }
		);
		pause4.play();
	}


	private void disembarkAnimation(ImageView img, Character character, Map<ImageView, Character> charMap) {
		img.setVisible(false);
		for(ImageView imageView: charMap.keySet()) {
			if(charMap.get(imageView) == null) {
				charMap.put(imageView, character);
				imageView.setImage(character.getImageArray().get(0));
				imageView.setVisible(true);
				break;
			}
		}
	}
	private void disembarkAll() {
		if(!Controller.executeCommand(new DisembarkCommand(true)))
			return;
		Map<ImageView, Character> charMap, shipMap;
		if(shipLeft) {
			charMap = leftCharMap;
			shipMap = rightShipMap;
		}
		else {
			charMap = rightCharMap;
			shipMap = leftShipMap;
		}
		for(ImageView view: shipMap.keySet()) {
			Character c = shipMap.get(view);
			if(c != null) {
				shipMap.put(view, null);
				disembarkAnimation(view, c, charMap);
			}
		}
		
		if(Level.getInstance().getStrategies().iterator().next().levelComplete())
			pn_gameWon.setVisible(true);
	}

	private void displayShip() {
		if(shipLeft) {
			imgTopLeft.setImage(carrierLeftAvailable);
			imgTopRight.setImage(carrierRightEmpty);
		}
		else {
			imgTopLeft.setImage(carrierLeftEmpty);
			imgTopRight.setImage(carrierRightAvailable);
		}
	}

	private void invalidMoveAnimation() {
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