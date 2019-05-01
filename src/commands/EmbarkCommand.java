package commands;

import levels.Level;
import characters.Character;

public class EmbarkCommand implements Command {
    private Character character;

    public EmbarkCommand(Character character) {
        this.character = character;
    }

    @Override
    public boolean execute() {
        Level level = Level.getInstance();
        if(character != null && level.getShip().embark(character)) {
            level.embark(character);
            return true;
        }
        return false;
    }
}
