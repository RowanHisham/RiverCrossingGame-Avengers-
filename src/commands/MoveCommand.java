package commands;

import levels.Level;
import levels.strategies.LevelStrategy;

public class MoveCommand implements Command {

    @Override
    public boolean execute() {
        Level level = Level.getInstance();
        for(LevelStrategy strategy: level.getStrategies()) {
            if(!strategy.validMove())
                return false;
        }
        level.moveShip();
        return true;
    }
}
