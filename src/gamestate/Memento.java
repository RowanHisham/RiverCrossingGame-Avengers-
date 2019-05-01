package gamestate;

import characters.Character;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Memento {
    //TODO memento
    private List<characters.Character> onBoard ;
    private Set<characters.Character> leftCharacters ;
    private Set<characters.Character> rightCharacters ;
    private int moves;

    public List<Character> getOnBoard() {
        return onBoard;
    }

    public Set<Character> getLeftCharacters() {
        return leftCharacters;
    }

    public Set<Character> getRightCharacters() {
        return rightCharacters;
    }

    public int getMoves() {
        return moves;
    }
    
    public Memento(List<characters.Character> onBoard ,Set<characters.Character> leftCharacters ,
             Set<characters.Character> rightCharacters , int moves ){
        
        this.onBoard = onBoard;
        this.rightCharacters = rightCharacters;
        this.leftCharacters = leftCharacters;
        this.moves = moves;
       
    }

}
