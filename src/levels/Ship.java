package levels;
import characters.*;
import java.util.ArrayList;
import java.util.List;

public class Ship {
    //TODO ship
    
    private boolean pilotOnBoard;
    private int maxCharacters = 5;
    private int weightCapacity;
    private List<Characters> onBoard = new ArrayList<>();

    
    public boolean hasPilot(){
        for(Characters x : onBoard){
            if(x.isCanSail()){
                pilotOnBoard = true;
            }
            else{
                pilotOnBoard = false;
            }
        }
        return pilotOnBoard;
    }
    
    public boolean embark(Characters character) {
        if(onBoard.size() < maxCharacters){
            onBoard.add(character);
            return true;
        }
        return false;
    }
    
    public void disembark(Characters character) {
         onBoard.remove(character);
    }
    
    public int getWeightCapacity() {
        return weightCapacity;
    }
    
    public void setWeightCapacity(int weightCapacity){
        this.weightCapacity = weightCapacity;
    }
}
