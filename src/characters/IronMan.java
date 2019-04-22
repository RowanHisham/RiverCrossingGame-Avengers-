package characters;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class IronMan  extends Character {

	public IronMan(boolean canSail){
		setType(CharacterType.HERO);
		setWeight(100);
		setCanSail(canSail);
		List<Image> imgArray = new ArrayList<Image>();
		
		for(int i=0; i<5; i++) 	
			imgArray.add(new Image("@../../application/Resources/ironMan" + i + ".png", true));

		setImageArray(imgArray);
	}
}
