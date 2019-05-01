package commands;

import characters.Character;
import levels.Level;

public class DisembarkCommand implements Command {
    private Character character;

    public DisembarkCommand(Character character) {
        this.character = character;
    }

    @Override
    public boolean execute() {
        if(character == null)
            return false;
        Level level = Level.getInstance();
        level.disembark(character);
        level.getShip().disembark(character);
        return true;
    }
}
