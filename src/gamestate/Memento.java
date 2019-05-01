package gamestate;

import characters.Character;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Memento {
    private Collection<Character> onBoard;
    private Collection<Character> leftCharacters;
    private Collection<Character> rightCharacters;
    private int moves;

    public Memento(Collection<Character> onBoard , Collection<characters.Character> leftCharacters ,
                   Collection<characters.Character> rightCharacters , int moves) {

        this.onBoard = new LinkedHashSet<>(onBoard);
        this.rightCharacters = new LinkedHashSet<>(rightCharacters);
        this.leftCharacters = new LinkedHashSet<>(leftCharacters);
        this.moves = moves;
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

}
