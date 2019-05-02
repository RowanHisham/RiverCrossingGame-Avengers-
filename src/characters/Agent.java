package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

class Agent extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/agent" + i + ".png").toString(),
					true));
	}

	Agent(boolean pilot){
		super(CharacterType.PASSIVE, 60, imageArray, pilot);
	}
}
