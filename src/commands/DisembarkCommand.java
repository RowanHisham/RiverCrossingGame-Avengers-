package commands;

import characters.Character;
import levels.Level;

public class DisembarkCommand implements Command {
    private Character character;
    private boolean disembarkAll = false;

    public DisembarkCommand(Character character) {
        this.character = character;
    }
    public DisembarkCommand(boolean disembarkAll) {
        this.disembarkAll = disembarkAll;
    }

    @Override
    public boolean execute() {
        if(disembarkAll) {
            Level level = Level.getInstance();
            level.disembark(level.getShip().getOnBoard().toArray(new Character[0]));
            level.getShip().disembarkAll();
            return true;
        }
        if(character == null)
            return false;
        Level level = Level.getInstance();
        level.disembark(character);
        level.getShip().disembark(character);
        return true;
    }
}
