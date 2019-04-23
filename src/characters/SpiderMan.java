package characters;

import java.util.ArrayList;
import java.util.List;

import characters.Characters.CharacterType;
import javafx.scene.image.Image;

public class SpiderMan extends Characters{
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("spiderMan" + i + ".png", true));
	}

	public SpiderMan(boolean canSail){
		super(CharacterType.HERO, 55, imageArray, canSail);
	}
}
