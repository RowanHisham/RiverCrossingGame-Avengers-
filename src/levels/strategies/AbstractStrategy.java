package levels.strategies;

import levels.Level;

abstract class AbstractStrategy implements LevelStrategy {
    @Override
    public boolean levelComplete(Level level) {
        return level.getLeftCharacters().isEmpty() && level.getShip().getOnBoard().isEmpty();
    }
}
