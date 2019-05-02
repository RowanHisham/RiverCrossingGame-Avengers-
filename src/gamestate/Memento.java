package gamestate;

import characters.Character;
import levels.Level;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Memento {
    private Collection<Character> onBoard;
    private Collection<Character> leftCharacters;
    private Collection<Character> rightCharacters;
    private int moves;
    private Level.ShipSide shipSide;

    public Memento(Collection<Character> onBoard, Collection<Character> leftCharacters,
                   Collection<Character> rightCharacters, int moves) {
        this(onBoard, leftCharacters, rightCharacters, moves, null);
    }
    public Memento(Collection<Character> onBoard, Collection<characters.Character> leftCharacters,
                   Collection<characters.Character> rightCharacters, int moves, Level.ShipSide shipSide) {
        this.onBoard = new LinkedHashSet<>(onBoard);
        this.rightCharacters = new LinkedHashSet<>(rightCharacters);
        this.leftCharacters = new LinkedHashSet<>(leftCharacters);
        this.moves = moves;
        this.shipSide = shipSide;
    }
    public Collection<Character> getOnBoard() {
        return onBoard;
    }
    public Collection<Character> getLeftCharacters() {
        return leftCharacters;
    }
    public Collection<Character> getRightCharacters() {
        return rightCharacters;
    }
    public int getMoves() {
        return moves;
    }
    public Level.ShipSide getShipSide() {
        return shipSide;
    }
}
