package characters;

import java.util.List;

import javafx.scene.image.Image;


public abstract class Character {
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
	
	
	public boolean isCanSail() {
		return canSail;
	}
	public void setCanSail(boolean canSail) {
		this.canSail = canSail;
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
	
	public void setType(CharacterType type) {
		this.type = type;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void setImageArray(List<Image>  imageArray) {
		this.imageArray = imageArray;
	}
	
}
