package characters;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class CaptainAmerica extends Character {
	private static List<Image> imageArray = new ArrayList<>();
	static {
		for(int i=0; i<5; i++) 	
			imageArray.add(new Image(
					Character.class.getResource("/cap" + i + ".png").toString(),
					true));
	}

	CaptainAmerica(boolean pilot, int weight){
		super(CharacterType.HERO, weight, imageArray, pilot);
	}
}
