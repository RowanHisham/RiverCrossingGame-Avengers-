package levels.strategies;

import characters.Character;
import levels.Level;

import java.util.Collection;

public class WeightStrategy extends AbstractStrategy {
    @Override
    public boolean validMove() {
    	Collection<Character> characterList = Level.getInstance().getShip().getOnBoard();
    	double totalWeight = 0;
    	for( Character character : characterList)
    		totalWeight += character.getWeight();
    	
    	return !(totalWeight > Level.getInstance().getShip().getWeightCapacity() ||  !Level.getInstance().getShip().hasPilot());
    }
}
