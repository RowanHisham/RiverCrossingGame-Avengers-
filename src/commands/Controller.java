package commands;

import gamestate.CareTaker;
import levels.Level;

public class Controller {
    private Controller() {}
    public static boolean executeCommand(Command command) {
        CareTaker careTaker = CareTaker.getInstance();
        careTaker.putMemento(Level.getInstance().getState());
        boolean result = command.execute();
        if(!result)
            careTaker.removeLast();
        return result;
    }
    public static boolean executePassiveCommand(Command command) {
        return command.execute();
    }
}
