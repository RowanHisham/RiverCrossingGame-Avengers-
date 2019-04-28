package gamestate;

import characters.Character;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Memento {
    //TODO memento
    private List<characters.Character> onBoard = new ArrayList<>();
    private List<characters.Character> leftCharacters = new ArrayList<>();
    private List<characters.Character> rightCharacters = new ArrayList<>();
    private int moves;
    
    public Memento(List<characters.Character> onBoard ,List<characters.Character> leftCharacters ,
             List<characters.Character> rightCharacters , int moves ){
        
        this.onBoard = onBoard;
        this.rightCharacters = rightCharacters;
        this.leftCharacters = leftCharacters;
        this.moves = moves;
       
    }
    public List<Character> getOnBoard() {
        return onBoard;
    }

    public List<Character> getLeftCharacters() {
        return leftCharacters;
    }

    public List<Character> getRightCharacters() {
        return rightCharacters;
    }

    public int getMoves() {
        return moves;
    }

}
