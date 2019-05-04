package application;

import characters.CharacterFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
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

import java.io.IOException;

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
    private JFXTextField txt_shipWeight;
    
    @FXML
    private JFXTextField txt_shipChar;

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
    			builder.addStrategy(new WeightStrategy() );
    		
			if(CB_Cap.isSelected()) {
                builder.addCharacter(f.getCharacter("captainamerica", CB_canSailCap.isSelected(), getWeight(txt_cap)));
			}
			
			if(CB_Ironman.isSelected()) {
				builder.addCharacter(f.getCharacter("ironman", CB_canSailIronman.isSelected(), getWeight(txt_ironman)));
			}
			
			if(CB_blackWidow.isSelected()) {
				builder.addCharacter(f.getCharacter("blackwidow", CB_canSailBlackwidow.isSelected(), getWeight(txt_blackWidow)));
			}
			
			if(CB_spiderman.isSelected()) {
				builder.addCharacter(f.getCharacter("spiderman", CB_canSailSpiderman.isSelected(), getWeight(txt_spiderman)));
			}
			
			if(CB_loki.isSelected()) {
				builder.addCharacter(f.getCharacter("loki", CB_canSailLoki.isSelected(), getWeight(txt_loki)));
			}
			
			if(CB_groot.isSelected()) {
				builder.addCharacter(f.getCharacter("groot", CB_canSailGroot.isSelected(), getWeight(txt_groot)));
			}
			
			if(CB_Villain.isSelected()) {
				builder.addCharacter(f.getCharacter("villain", CB_canSailVillain.isSelected(), getWeight(txt_villain)));
			}
			
			if(Integer.parseInt(txt_shipChar.getText()) > 5 || Integer.parseInt(txt_shipChar.getText()) <= 0)
				return;
			
			builder.maxShipCharacters(Integer.parseInt(txt_shipChar.getText()));
			builder.weightCapacity(getWeight(txt_shipWeight));
			
			builder.build();
			
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    	}
    }
    private int getWeight(JFXTextField txt) {
        int weight = -1;
        if(CB_weight.isSelected())
            weight = Integer.parseInt(txt.getText());
        return weight;
    }

}
