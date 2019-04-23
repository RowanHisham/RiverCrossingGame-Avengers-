package characters;

import java.util.ArrayList;
import java.util.List;

import characters.Character.CharacterType;
import javafx.scene.image.Image;

public class CaptainAmerica extends Character {
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("cap" + i + ".png", true));
	}
	
	public CaptainAmerica(boolean canSail){
		super(CharacterType.HERO, 85, imageArray, canSail);
	}
}
