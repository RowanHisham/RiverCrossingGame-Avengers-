package characters;

import java.util.ArrayList;
import java.util.List;

import characters.Character.CharacterType;
import javafx.scene.image.Image;

public class BlackWidow extends Character {
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("blackWidow" + i + ".png", true));
	}
	
	public BlackWidow(boolean canSail){
		super(CharacterType.HERO, 60, imageArray, canSail);
	}
}