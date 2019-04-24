package characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class IronMan  extends Character {
	private static List<Image> imageArray = new ArrayList<Image>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image("/ironMan" + i + ".png", true));
	}
	
	public IronMan(boolean pilot){
		super(CharacterType.HERO, 95, imageArray, pilot);
	}
}
