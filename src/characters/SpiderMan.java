package characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class SpiderMan extends Character {
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("/spiderMan" + i + ".png", true));
	}

	public SpiderMan(boolean pilot){
		super(CharacterType.HERO, 55, imageArray, pilot);
	}
}
