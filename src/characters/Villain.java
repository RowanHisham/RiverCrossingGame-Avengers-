package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

class Villain extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/villain" + i + ".png").toString(),
					true));
	}

	Villain(boolean pilot){
		super(CharacterType.VILLAIN, 70, imageArray, pilot);

	}
}
