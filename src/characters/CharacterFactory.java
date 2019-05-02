package characters;

public class CharacterFactory {
	private boolean pilot = true;

	public CharacterFactory(boolean pilot) {
		this.pilot = pilot;
	}

	public CharacterFactory() {
	}

	public Character getCharacter(String character) {
		return getCharacter(character, pilot);
	}
	public Character getCharacter(String character, boolean canSail) {
		if(character == null)
			return null;
		
		if(character.equalsIgnoreCase("Agent")){
			return new Agent(canSail);
		} else if(character.equalsIgnoreCase("BlackWidow")){
			return new BlackWidow(canSail);
		} else if(character.equalsIgnoreCase("CaptainAmerica")){
			return new CaptainAmerica(canSail);
		} else if(character.equalsIgnoreCase("IronMan")){
			return new IronMan(canSail);
		} else if(character.equalsIgnoreCase("Loki")){
			return new Loki(canSail);
		} else if(character.equalsIgnoreCase("SpiderMan")){
			return new SpiderMan(canSail);
		} else if(character.equalsIgnoreCase("Villain")){
			return new Villain(canSail);
		}

		return null;
	}
}

