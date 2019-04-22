package characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class BlackWidow extends Character {
	
	public BlackWidow(boolean canSail){
		setType(CharacterType.HERO);
		setWeight(50);
		setCanSail(canSail);
		List<Image> imgArray = new ArrayList<Image>();
		
		for(int i=0; i<5; i++) 	
			imgArray.add(new Image("@../../application/Resources/blackWidow" + i + ".png", true));

		setImageArray(imgArray);
	}
}