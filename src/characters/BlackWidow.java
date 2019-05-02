package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class BlackWidow extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/blackWidow" + i + ".png").toString(),
							true));
	}

	BlackWidow(boolean pilot){
		super(CharacterType.HERO, 60, imageArray, pilot);
	}
}