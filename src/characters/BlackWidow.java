package characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class BlackWidow extends Character {
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("/blackWidow" + i + ".png", true));
	}
	
	public BlackWidow(boolean pilot){
		super(CharacterType.HERO, 60, imageArray, pilot);
	}
}