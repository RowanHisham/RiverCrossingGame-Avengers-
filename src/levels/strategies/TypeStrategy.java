package levels.strategies;

import characters.Character;
import levels.Level;
import levels.Level.ShipSide;

import java.util.Collection;

public class TypeStrategy extends AbstractStrategy {
    @Override
    public boolean validMove() {
    	boolean containHero = false;
    	boolean containVillain = false;
    	boolean containPassive = false;
    	boolean containDoubleAgent = false;
    	
    	Collection<Character> onBoard = Level.getInstance().getShip().getOnBoard();
    	Collection<Character> leftCharacters = Level.getInstance().getLeftCharacters();
    	Collection<Character> rightCharacters = Level.getInstance().getRightCharacters();
    	
    	if( Level.getInstance().getShipSide() == ShipSide.LEFT)
    		rightCharacters.addAll(onBoard);
    	else
    		leftCharacters.addAll(onBoard);
    	
    	
    	for( Character character : rightCharacters) {
    		switch (character.getType()){
    		case HERO : containHero = true; break;
    		case VILLAIN: containVillain = true; break;
    		case PASSIVE: containPassive = true; break;
    		case DOUBLE_AGENT: containDoubleAgent = true; break;
    		}
    		if(containDoubleAgent)
    			break;
    	}
    	

    	if(Level.getInstance().getShipSide() == ShipSide.LEFT ) {
    		if(!containDoubleAgent)
    			return false;
    	}else {
    		if( (containHero && containVillain) || (containVillain && containPassive) )
        		return false;
    	}
    	
    	
    	for( Character character : leftCharacters) {
    		switch (character.getType()){
    		case HERO : containHero = true; break;
    		case VILLAIN: containVillain = true; break;
    		case PASSIVE: containPassive = true; break;
    		case DOUBLE_AGENT: containDoubleAgent = true; break;
    		}
    		if(containDoubleAgent)
    			break;
    	}
    	
    	if(Level.getInstance().getShipSide() == ShipSide.RIGHT ) {
    		if(!containDoubleAgent)	
    			return false;
    	}else {
    		if( (containHero && containVillain) || (containVillain && containPassive) )
        		return false;
    	}
    	
    	return true;
    }
}
