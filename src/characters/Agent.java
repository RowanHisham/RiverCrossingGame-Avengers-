package characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Agent extends Character {
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("agent" + i + ".png", true));
	}
	
	public Agent(boolean canSail){
		super(CharacterType.PASSIVE, 60, imageArray, canSail);
	}
}
