package levels;

import characters.Character;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;


public class Ship {
    public static final int MAX_CHARACTERS = 5;

    private int maxCharacters;
    private int weightCapacity;
    private Collection<Character> onBoard = new LinkedHashSet<>();


    Ship(int maxCharacters, int weightCapacity) {
        if(maxCharacters > MAX_CHARACTERS)
            throw new UnsupportedOperationException("Cannot create a ship with more than 5 max characters");
        this.maxCharacters = maxCharacters;
        this.weightCapacity = weightCapacity;
    }
    void setOnBoard(Collection<Character> onBoard) {
        this.onBoard.clear(); this.onBoard.addAll(onBoard);
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
    public Collection<Character> getOnBoard() {
        return Collections.unmodifiableCollection(onBoard);
    }
}
