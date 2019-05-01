package levels;
import characters.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ship {
    public static final int MAX_CHARACTERS = 5;

    private int maxCharacters;
    private int weightCapacity;
    private List<Character> onBoard = new ArrayList<>();

    public void setOnBoard(List<Character> onBoard) {
        this.onBoard = onBoard;
    }

    Ship(int maxCharacters, int weightCapacity) {
        if(maxCharacters > MAX_CHARACTERS)
            throw new UnsupportedOperationException("Cannot create a ship with more than 5 max characters");
        this.maxCharacters = maxCharacters;
        this.weightCapacity = weightCapacity;
    }

    public boolean hasPilot(){
        for(Character character : onBoard){
            if(character.isPilot())
                return true;
        }
        return false;
    }
    public boolean embark(Character character) {
        if(onBoard.size() < maxCharacters){
            onBoard.add(character);
            return true;
        }
        return false;
    }
    public void disembark(Character character) {
        onBoard.remove(character);
    }
    public void disembarkAll() {
        onBoard.clear();
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }
    public int getMaxCharacters() {
        return maxCharacters;
    }
    public List<Character> getOnBoard() {
        return Collections.unmodifiableList(onBoard);
    }
}
