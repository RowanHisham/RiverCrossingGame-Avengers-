package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import characters.CharacterFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import levels.Level;
import levels.Level.Builder;
import levels.strategies.TypeStrategy;
import levels.strategies.WeightStrategy;

public class LevelMakerController {


    @FXML
    private JFXButton btn_play;

    @FXML
    private JFXCheckBox CB_Cap;

    @FXML
    private JFXCheckBox CB_Ironman;

    @FXML
    private JFXCheckBox CB_blackWidow;

    @FXML
    private JFXCheckBox CB_loki;

    @FXML
    private JFXCheckBox CB_spiderman;

    @FXML
    private JFXCheckBox CB_groot;

    @FXML
    private JFXCheckBox CB_Villain;

    @FXML
    private JFXCheckBox CB_canSailCap;
    
    @FXML
    private JFXCheckBox CB_canSailBlackwidow;

    @FXML
    private JFXCheckBox CB_canSailIronman;

    @FXML
    private JFXCheckBox CB_canSailLoki;

    @FXML
    private JFXCheckBox CB_canSailSpiderman;

    @FXML
    private JFXCheckBox CB_canSailGroot;

    @FXML
    private JFXCheckBox CB_canSailVillain;

    @FXML
    private JFXTextField txt_cap;

    @FXML
    private JFXTextField txt_ironman;

    @FXML
    private JFXTextField txt_blackWidow;

    @FXML
    private JFXTextField txt_loki;

    @FXML
    private JFXTextField txt_spiderman;

    @FXML
    private JFXTextField txt_groot;

    @FXML
    private JFXTextField txt_villain;

    @FXML
    private JFXButton btn_cancel;

    @FXML
    private JFXCheckBox CB_type;

    @FXML
    private JFXCheckBox CB_weight;

    @FXML
    void buttonOnAction(ActionEvent event) throws IOException {
    	if(event.getSource() == btn_cancel) {
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainMenuForm.fxml"));
			Scene customerMainFormScene = new Scene(root);
			Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
			window.setScene(customerMainFormScene);
			window.show();
    	}else if( event.getSource() == btn_play) {
    		
    		CharacterFactory f = new CharacterFactory(true);
    		Builder builder = new Level.Builder();
    		if(!CB_type.isSelected() && !CB_weight.isSelected())
    			return;
    		
    		if(CB_type.isSelected()) 
    			builder.addStrategy(new TypeStrategy() );
    		
    		
    		if( CB_weight.isSelected())
    			builder.addStrategy(new TypeStrategy() );
    		
			if(CB_Cap.isSelected()) {
				builder.addCharacter(f.getCharacter("captainamerica", CB_canSailCap.isSelected(), Integer.parseInt(txt_cap.getText())));
			}
			
			if(CB_Ironman.isSelected()) {
				builder.addCharacter(f.getCharacter("ironman", CB_canSailIronman.isSelected(), Integer.parseInt(txt_ironman.getText())));
			}
			
			if(CB_blackWidow.isSelected()) {
				builder.addCharacter(f.getCharacter("blackwidow", CB_canSailBlackwidow.isSelected(), Integer.parseInt(txt_ironman.getText())));
			}
			
			
			new Level.Builder().addStrategy(new WeightStrategy())
					.maxShipCharacters(2)
					.rules("Captain America: can sail, weight = 90\n"
							+ "IronMan: can sail, weight = 80\n"
							+ "Black Widow: can sail, weight = 60\n"
							+ "Loki: can sail, weight = 60\n"
							+ "SpiderMan: can't sail, weight = 45\n\n"
							+ "Ship Max Weight = 140\n"
							+ "Ship Max Characters = 2")
					.weightCapacity(160)
					.addCharacter(f.getCharacter("captainamerica",true,90),
							f.getCharacter("ironman",true,80),
							f.getCharacter("blackwidow",true,60),
							f.getCharacter("spiderman",false,45),
							f.getCharacter("loki",true,60))
					.build();
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    	}
    }

}
