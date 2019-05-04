package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Villain extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/villain" + i + ".png").toString(),
					true));
	}

	Villain(boolean pilot, int weight){
		super(CharacterType.VILLAIN, weight, imageArray, pilot);

	}
}
