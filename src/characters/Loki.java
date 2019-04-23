package characters;

import java.util.ArrayList;
import java.util.List;

import characters.Characters.CharacterType;
import javafx.scene.image.Image;

public class Loki  extends Characters {
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("loki" + i + ".png", true));
	}

	public Loki(boolean canSail){
		super(CharacterType.DOUBLEAGENT, 70, imageArray, canSail);

	}
}
