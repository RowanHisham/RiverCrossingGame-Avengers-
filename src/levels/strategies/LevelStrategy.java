package levels.strategies;

import commands.EmbarkCommand;
import commands.MoveCommand;
import levels.Level;

public interface LevelStrategy {
    boolean validMove();
    boolean levelComplete();
}
