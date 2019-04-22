package characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Loki  extends Character {
	public Loki(boolean canSail){
		setType(CharacterType.DOUBLEAGENT);
		setWeight(75);
		setCanSail(canSail);
		List<Image> imgArray = new ArrayList<Image>();
		
		for(int i=0; i<5; i++) 	
			imgArray.add(new Image("@../../application/Resources/loki" + i + ".png", true));

		setImageArray(imgArray);
	}
}
