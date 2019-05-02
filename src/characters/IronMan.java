package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class IronMan  extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/ironMan" + i + ".png").toString(),
					true));
	}
	
	public IronMan(boolean pilot){
		super(CharacterType.HERO, 95, imageArray, pilot);
	}
}
