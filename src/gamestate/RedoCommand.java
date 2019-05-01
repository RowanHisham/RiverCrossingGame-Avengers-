package gamestate;

import commands.Command;
import levels.Level;

public class RedoCommand implements Command {
    @Override
    public boolean execute() {
        Memento state = CareTaker.getInstance().redo();
        if(state == null)
            return false;
        Level.getInstance().setState(state);
        return true;
    }
}
