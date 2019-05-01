package gamestate;

import commands.Command;
import levels.Level;

public class UndoCommand implements Command {
    @Override
    public boolean execute() {
        Memento state = CareTaker.getInstance().undo();
        if(state == null)
            return false;
        Level.getInstance().setState(state);
        return true;
    }
}
