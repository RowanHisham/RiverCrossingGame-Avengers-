package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

class SpiderMan extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/spiderMan" + i + ".png").toString(),
					true));
	}

	SpiderMan(boolean pilot){
		super(CharacterType.HERO, 55, imageArray, pilot);
	}
}
