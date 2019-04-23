package characters;

import java.util.List;

import javafx.scene.image.Image;


public abstract class Characters {
	public enum CharacterType{
		HERO,
		VILLAIN,
		PASSIVE,
		CUSTOM,
		DOUBLEAGENT,
	}
	
	private CharacterType type;
	private float weight;
	private List<Image> imageArray;
	private boolean canSail;
	
	protected Characters(CharacterType type, float weight, List<Image> imageArray, boolean canSail){
		this.type = type;
		this.weight = weight;
		this.imageArray = imageArray;
		this.canSail = canSail;
	}
	
	public boolean isCanSail() {
		return canSail;
	}
	public CharacterType getType() {
		return type;
	}
	public float getWeight() {
		return weight;
	}
	public List<Image> getImageArray() {
		return imageArray;
	}
	
}
