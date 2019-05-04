package application;

import characters.CharacterFactory;
import com.jfoenix.controls.JFXButton;
import gamestate.LoadCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import levels.Level;
import levels.strategies.TypeStrategy;
import levels.strategies.WeightStrategy;

import java.io.IOException;

public class MainMenuFormController {

    @FXML
    private JFXButton btn_level1;

    @FXML
    private JFXButton btn_level2;

    @FXML
    private JFXButton btn_levelMaker;

    @FXML
    private JFXButton btn_loadLevel, btn_close;
    
    @FXML
    private ImageView img_logo;
    
    
    @FXML
	public void initialize() {
    	img_logo.setVisible(false);

    	btn_level1.hoverProperty().addListener((ov, oldValue, newValue) -> {
    	    if (newValue) {
    	    	btn_level1.setStyle("-fx-background-color:  #ed1919 ; -fx-border-color:  #000000; -fx-border-width:  5 ");
    	    
    	    } else {
    	    	btn_level1.setStyle("-fx-background-color:  #fedd10; -fx-border-color:  #000000; -fx-border-width:  5 ");
    	    }
    	});
    	
    	btn_level2.hoverProperty().addListener((ov, oldValue, newValue) -> {
    	    if (newValue) {
    	    	btn_level2.setStyle("-fx-background-color:  #ed1919 ; -fx-border-color:  #000000; -fx-border-width:  5 ");
    	    
    	    } else {
    	    	btn_level2.setStyle("-fx-background-color:  #fedd10; -fx-border-color:  #000000; -fx-border-width:  5 ");
    	    }
    	});
    	
    	btn_loadLevel.hoverProperty().addListener((ov, oldValue, newValue) -> {
    	    if (newValue) {
    	    	btn_loadLevel.setStyle("-fx-background-color:  #ed1919 ; -fx-border-color:  #000000; -fx-border-width:  5 ");
    	    
    	    } else {
    	    	btn_loadLevel.setStyle("-fx-background-color:  #fedd10; -fx-border-color:  #000000; -fx-border-width:  5 ");
    	    }
    	});
    	
    	btn_levelMaker.hoverProperty().addListener((ov, oldValue, newValue) -> {
    	    if (newValue) {
    	    	btn_levelMaker.setStyle("-fx-background-color:  #ed1919 ; -fx-border-color:  #000000; -fx-border-width:  5 ");
    	    
    	    } else {
    	    	btn_levelMaker.setStyle("-fx-background-color:  #fedd10; -fx-border-color:  #000000; -fx-border-width:  5 ");
    	    }
    	});
    	
    	btn_close.hoverProperty().addListener((ov, oldValue, newValue) -> {
    	    if (newValue) {
    	    	btn_close.setStyle("-fx-background-color:  #ed1919 ; -fx-border-color:  #000000; -fx-border-width:  4 ");
    	    
    	    } else {
    	    	btn_close.setStyle("-fx-background-color:  #ffffff; -fx-border-color:  #000000; -fx-border-width:  4; -fx-background-radius: 100; -fx-border-radius: 100 ");
    	    }
    	});
    	
//    	PauseTransition pause = new PauseTransition(Duration.seconds(9));
//		pause.setOnFinished(new EventHandler<ActionEvent>() {
//								@Override public void handle(ActionEvent t) {
//									img_logo.setVisible(false);
//								}
//							}
//		);
//		pause.play();
		
	}

    @FXML
    void buttonOnAction(ActionEvent event) throws IOException {
    	if(event.getSource() == btn_level1) {
			CharacterFactory f = new CharacterFactory(true);
			new Level.Builder().addStrategy(new WeightStrategy())
					.maxShipCharacters(2)
					.rules("Captain America: can sail, weight = 80\n"
							+ "Iron Man: can sail, weight = 90\n"
							+ "Black Widow: can sail, weight = 60\n"
							+ "Spiderman: can sail, weight = 40\n"
							+ "Groot: can't sail, weight = 20\n\n"
							+ "Ship Max Weight = 100\n"
							+ "Ship Max Characters = 2")
					.weightCapacity(100)
					.addCharacter(f.getCharacter("captainamerica",true,80),
							f.getCharacter("ironman",true,90),
							f.getCharacter("blackwidow",true,60),
							f.getCharacter("spiderman",true,40),
							f.getCharacter("groot",false,20))
					.build();
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    		
    	}else if( event.getSource() == btn_level2) {
    		CharacterFactory f = new CharacterFactory(true);
			new Level.Builder().addStrategy(new TypeStrategy())
					.maxShipCharacters(2)
					.rules("Loki: only one who can sail, can travel with anyone\n"
							+ "Captain America: hero - can be with Groot, can't be with villain\n"
							+ "Villain: Villain - can't be with Captain America or Groot\n"
							+ "Groot: passive - can ride with Captain America, can't be with villain")
					.addCharacter(f.getCharacter("captainamerica",false,90),
							f.getCharacter("villain",false,60),
							f.getCharacter("groot",false,45),
							f.getCharacter("loki",true,60))
					.build();
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    		
    	}else if( event.getSource() == btn_close) {
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.close();
    		
    	}else if(event.getSource() == btn_loadLevel) {
                LoadCommand load = new LoadCommand();
                load.loadFile();
                load.updateLevel();
                load.setState();
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    		
    	}else if( event.getSource() == btn_levelMaker) {
    		
    	}
    		
    }

}
