package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Groot extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/g" + i + ".png").toString(),
					true));
	}

	Groot(boolean pilot , int weight){
		super(CharacterType.PASSIVE, weight, imageArray, pilot);
	}
}
