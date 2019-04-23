package characters;

import java.util.ArrayList;
import java.util.List;

import characters.Character.CharacterType;
import javafx.scene.image.Image;

public class Villain extends Character{
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("villain" + i + ".png", true));
	}

	public Villain(boolean canSail){
		super(CharacterType.VILLAIN, 70, imageArray, canSail);

	}
}
