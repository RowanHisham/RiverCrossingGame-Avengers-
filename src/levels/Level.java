package levels;
import characters.Character;
import gamestate.Memento;
import levels.strategies.LevelStrategy;

import java.util.*;

public class Level {
    private static Level instance = null;
    public static Level getInstance() {
        return instance;
    }

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
    private int movesDone = 0;
    private String rules;

    private Level(Collection<LevelStrategy> strategies, Ship ship, Collection<Character> initialLeftCharacters) {
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
    public void disembark(Character... characters) {
        currentSideChars().addAll(Arrays.asList(characters));
    }
    public void moveShip() {
        movesDone++;
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
    public int getMovesDone() {
        return movesDone;
    }
    public String getRules() {
        return rules;
    }

    public static class Builder {
        private Collection<LevelStrategy> strategies;
        private Collection<Character> initialLeftCharacters;
        private int maxShipCharacters = 5;
        private int weightCapacity = -1;
        private int movesDone = 0;
        private String rules;

        public Builder addStrategy(LevelStrategy... strategies) {
            if(this.strategies == null)
                this.strategies = new ArrayList<>();
            this.strategies.addAll(Arrays.asList(strategies));
            return this;
        }
        public Builder addCharacter(Character... characters) {
            if(initialLeftCharacters == null)
                initialLeftCharacters = new ArrayList<>();
            initialLeftCharacters.addAll(Arrays.asList(characters));
            return this;
        }
        public Builder weightCapacity(int weightCapacity) {
            this.weightCapacity = weightCapacity;
            return this;
        }
        public Builder maxShipCharacters(int maxShipCharacters) {
            this.maxShipCharacters = maxShipCharacters;
            return this;
        }
        public Builder movesDone(int movesDone) {
            this.movesDone = movesDone;
            return this;
        }
        public Builder rules(String rules) {
            this.rules = rules;
            return this;
        }

        public Level build() {
            instance = new Level(strategies, new Ship(maxShipCharacters, weightCapacity), initialLeftCharacters);
            instance.movesDone = this.movesDone;
            instance.rules = this.rules;
            return instance;
        }
    }

    public Memento getState() {
        //TODO get game state
        
        Memento memento = new Memento(ship.getOnBoard(),leftCharacters, rightCharacters,movesDone);
        return memento;
    }
    public void setState(Memento state) {
        //TODO set game state
        
        this.leftCharacters = state.getLeftCharacters();
        this.movesDone = state.getMoves();
        this.rightCharacters =  state.getRightCharacters();
        ship.setOnBoard(state.getOnBoard());
        
    }
}
