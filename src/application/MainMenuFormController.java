package application;

import characters.CharacterFactory;
import com.jfoenix.controls.JFXButton;
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
    private JFXButton btn_loadLevel;
    
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
					.rules("Figure it out yourself")
					.weightCapacity(170)
					.addCharacter(f.getCharacter("captainamerica"),
							f.getCharacter("ironman"),
							f.getCharacter("blackwidow"),
							f.getCharacter("spiderman",false),
							f.getCharacter("loki"))
					.build();
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    		
    	}else if( event.getSource() == btn_level2) {
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    		
    	}else if(event.getSource() == btn_loadLevel) {
    		
    	}else if( event.getSource() == btn_levelMaker) {
    		
    	}
    		
    }

}
