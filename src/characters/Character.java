package characters;

import java.util.Collections;
import java.util.List;

import javafx.scene.image.Image;


public abstract class Character {
	public enum CharacterType{
		HERO,
		VILLAIN,
		PASSIVE,
		CUSTOM,
		DOUBLE_AGENT,
	}
	
	private CharacterType type;
	private int weight;
	private List<Image> imageArray;
	private boolean pilot;
	
	protected Character(CharacterType type, int weight, List<Image> imageArray, boolean pilot){
		this.type = type;
		this.weight = weight;
		this.imageArray = imageArray;
		this.pilot = pilot;
	}
	
	public boolean isPilot() {
		return pilot;
	}
	public CharacterType getType() {
		return type;
	}
	public int getWeight() {
		return weight;
	}
	public List<Image> getImageArray() {
		return Collections.unmodifiableList(imageArray);
	}
	
}
