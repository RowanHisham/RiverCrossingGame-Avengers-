package levels;
import characters.Character;

import java.util.*;

public class Level {
    public enum ShipSide {
        LEFT,
        RIGHT
    }

    public static final int MAX_CHARACTERS = 6;

    private Collection<LevelStrategy> strategies;
    private Ship ship;
    private Set<Character> leftCharacters;
    private Set<Character> rightCharacters;
    private Set<Character> initialLeftCharacters;
    private ShipSide shipSide = ShipSide.LEFT;

    public Level(List<LevelStrategy> strategies, Ship ship, Set<Character> initialLeftCharacters) {
        if(initialLeftCharacters.size() > MAX_CHARACTERS)
            throw new UnsupportedOperationException("Cannot create a level with more than 6 characters");
        this.strategies = new ArrayList<>(strategies);
        this.ship = ship;
        this.initialLeftCharacters = new LinkedHashSet<>(initialLeftCharacters);
        this.leftCharacters = new LinkedHashSet<>(initialLeftCharacters);
        this.rightCharacters = new LinkedHashSet<>();
    }

    public void embark(Character character) {
        currentSideChars().remove(character);
    }
    public void disembark(Character character) {
        currentSideChars().add(character);
    }
    public void disembark(Collection<Character> characters) {
        currentSideChars().addAll(characters);
    }
    public void moveShip() {
        if(shipSide == ShipSide.LEFT)
            shipSide = ShipSide.RIGHT;
        else if(shipSide == ShipSide.RIGHT)
            shipSide = ShipSide.LEFT;
    }

    private Set<Character> currentSideChars() {
        if(shipSide == ShipSide.LEFT)
            return leftCharacters;
        else if(shipSide == ShipSide.RIGHT)
            return rightCharacters;
        return null;
    }

    public Collection<LevelStrategy> getStrategies() {
        return Collections.unmodifiableCollection(strategies);
    }
    public Set<Character> getInitialLeftCharacters() {
        return Collections.unmodifiableSet(initialLeftCharacters);
    }
    public Set<Character> getLeftCharacters() {
        return Collections.unmodifiableSet(leftCharacters);
    }
    public Set<Character> getRightCharacters() {
        return Collections.unmodifiableSet(rightCharacters);
    }
    public Ship getShip() {
        return ship;
    }
    public ShipSide getShipSide() {
        return shipSide;
    }
}
