package commands;

import gamestate.CareTaker;
import levels.Level;

public class Controller {
    private Controller() {}
    public static boolean executeCommand(Command command) {
        boolean result = command.execute();
        if(result)
            CareTaker.getInstance().putMemento(Level.getInstance().getState());
        return result;
    }
    public static boolean executePassiveCommand(Command command) {
        return command.execute();
    }
}
