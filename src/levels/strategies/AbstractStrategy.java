package levels.strategies;

import levels.Level;

public abstract class AbstractStrategy implements LevelStrategy {
    @Override
    public boolean levelComplete() {
        return Level.getInstance().getLeftCharacters().isEmpty() && Level.getInstance().getShip().getOnBoard().isEmpty();
    }
}
