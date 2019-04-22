package characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Agent extends Character {
	public Agent(boolean canSail){
		setType(CharacterType.PASSIVE);
		setWeight(60);
		setCanSail(canSail);
		List<Image> imgArray = new ArrayList<Image>();
		
		for(int i=0; i<5; i++) 	
			imgArray.add(new Image("@../../application/Resources/agent" + i + ".png", true));

		setImageArray(imgArray);
	}
}
