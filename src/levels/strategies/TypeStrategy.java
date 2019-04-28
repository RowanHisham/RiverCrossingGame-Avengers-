package levels.strategies;

import java.util.List;

import characters.Character;
import characters.Character.CharacterType;
import commands.MoveCommand;
import levels.Level;

public class TypeStrategy extends AbstractStrategy {
    @Override
    public boolean validMove() {
    	List<Character> characterList = Level.getInstance().getShip().getOnBoard();
    	boolean containHero = false;
    	boolean containVillain = false;
    	boolean containPassive = false;
    	boolean containDoubleAgent = false;
    	boolean containCustom = false;
    	
    	for( Character character : characterList) {
    		switch (character.getType()){
    		case HERO : containHero = true; break;
    		case VILLAIN: containVillain = true; break;
    		case PASSIVE: containPassive = true; break;
    		case DOUBLE_AGENT: containDoubleAgent = true; break;
    		case CUSTOM: containCustom = true; break;
    		}
    	}
    	
    	if( (containHero && containVillain) || (containVillain && containPassive) || !Level.getInstance().getShip().hasPilot())
    		return false;
    	else 
    		return true;
    	
    }
}
