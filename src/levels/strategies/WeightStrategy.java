package levels.strategies;

import java.util.List;
import levels.*;
import characters.Character;

public class WeightStrategy extends AbstractStrategy {
    @Override
    public boolean validMove() {
    	List<Character> characterList = Level.getInstance().getShip().getOnBoard();
    	double totalWeight = 0;
    	for( Character character : characterList)
    		totalWeight += character.getWeight();
    	
    	if(totalWeight > Level.getInstance().getShip().getWeightCapacity() ||  !Level.getInstance().getShip().hasPilot())
    		return false;
    	else
    		return true;
    }
}
