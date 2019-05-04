package characters;

public class CharacterFactory {
	private boolean pilot = true;

	public CharacterFactory(boolean pilot) {
		this.pilot = pilot;
	}

	public CharacterFactory() {
	}

	public Character getCharacter(String character) {
		return getCharacter(character, pilot, 0);
	}
	public Character getCharacter(String character, boolean canSail, int weight) {
		if(character == null)
			return null;
		
		if(character.equalsIgnoreCase("Groot")){
			return new Groot(canSail,weight);
		} else if(character.equalsIgnoreCase("BlackWidow")){
			return new BlackWidow(canSail,weight);
		} else if(character.equalsIgnoreCase("CaptainAmerica")){
			return new CaptainAmerica(canSail,weight);
		} else if(character.equalsIgnoreCase("IronMan")){
			return new IronMan(canSail,weight);
		} else if(character.equalsIgnoreCase("Loki")){
			return new Loki(canSail,weight);
		} else if(character.equalsIgnoreCase("SpiderMan")){
			return new SpiderMan(canSail,weight);
		} else if(character.equalsIgnoreCase("Villain")){
			return new Villain(canSail,weight);
		}

		return null;
	}
}

