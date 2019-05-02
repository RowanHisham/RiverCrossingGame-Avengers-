package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

class Loki  extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/loki" + i + ".png").toString(),
					true));
	}

	Loki(boolean pilot) {
		super(CharacterType.DOUBLE_AGENT, 70, imageArray, pilot);
	}
}
