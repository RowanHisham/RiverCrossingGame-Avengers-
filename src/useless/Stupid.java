package useless;

import characters.Character;
import characters.CharacterFactory;
import commands.Controller;
import commands.MoveCommand;
import gamestate.RedoCommand;
import gamestate.UndoCommand;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import levels.Level;
import levels.strategies.AbstractStrategy;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stupid {
    private static Level.Builder builder = new Level.Builder();
    private static Level level;

    private static ICrosser charToCrosser(Character c) {
        return new ICrosser() {
            private boolean canSail = c.isPilot();
            private double weight = c.getWeight();
            private int eatingRank = c.getType().ordinal();
            private BufferedImage[] images;
            {
                images = new BufferedImage[c.getImageArray().size()];
                int i = 0;
                for(Image image: c.getImageArray()) {
                    images[i++] = SwingFXUtils.fromFXImage(image, null);
                }
            }

            @Override
            public boolean canSail() {
                return canSail;
            }

            @Override
            public double getWeight() {
                return weight;
            }

            @Override
            public int getEatingRank() {
                return eatingRank;
            }

            @Override
            public BufferedImage[] getImages() {
                return images;
            }

            @Override
            public ICrosser makeCopy() {
                return charToCrosser(c);
            }

            @Override
            public void setLabelToBeShown(String label) {

            }

            @Override
            public String getLabelToBeShown() {
                return null;
            }
        };
    }

    public static IRiverCrossingController controller = new IRiverCrossingController() {

        private List<ICrosser> getRightCrossers() {
            List<ICrosser> ret = new ArrayList<>();
            for(Character c: level.getRightCharacters()) {
                ret.add(charToCrosser(c));
            }
            return ret;
        }
        private List<ICrosser> getLeftCrossers() {
            List<ICrosser> ret = new ArrayList<>();
            for(Character c: level.getLeftCharacters()) {
                ret.add(charToCrosser(c));
            }
            return ret;
        }
        private List<ICrosser> getBoatRiders() {
            List<ICrosser> ret = new ArrayList<>();
            for(Character c: level.getShip().getOnBoard()) {
                ret.add(charToCrosser(c));
            }
            return ret;
        }
        private Character[] crossersToChars(List<ICrosser> crossers) {
            Character[] ret = new Character[crossers.size()];
            int i = 0;
            CharacterFactory f = new CharacterFactory();
            for(ICrosser crosser: crossers) {
                Character.CharacterType type = Character.CharacterType.PASSIVE;
                switch(crosser.getEatingRank()) {
                    case 0: type = Character.CharacterType.PASSIVE; break;
                    case 1: type = Character.CharacterType.VILLAIN; break;
                    case 2: type = Character.CharacterType.HERO; break;
                    case 3: type = Character.CharacterType.DOUBLE_AGENT; break;
                }
                List<Image> images = new ArrayList<>();
                for(BufferedImage bimg: crosser.getImages()) {
                    images.add(SwingFXUtils.toFXImage(bimg, null));
                }
                ret[i] = new Character(type, (int)crosser.getWeight(), images, crosser.canSail()) {};
            }
            return ret;
        }

        @Override
        public void newGame(ICrossingStrategy gameStrategy) {
            builder.addStrategy(new AbstractStrategy() {
                private ICrossingStrategy s = gameStrategy;
                @Override
                public boolean validMove() {
                    return s.isValid(getRightCrossers(), getLeftCrossers(), getBoatRiders());
                }
            });
            builder.rules(Arrays.toString(gameStrategy.getInstructions()));
            builder.addCharacter(crossersToChars(gameStrategy.getInitialCrossers()));
            level = builder.build();
        }

        @Override
        public void resetGame() {
            level.reset();
        }

        @Override
        public String[] getInstructions() {
            String[] ret = new String[1];
            ret[0] = level.getRules();
            return ret;
        }

        @Override
        public List<ICrosser> getCrossersOnRightBank() {
            return getRightCrossers();
        }

        @Override
        public List<ICrosser> getCrossersOnLeftBank() {
            return getLeftCrossers();
        }

        @Override
        public boolean isBoatOnTheLeftBank() {
            return level.getShipSide() == Level.ShipSide.LEFT;
        }

        @Override
        public int getNumberOfSails() {
            return level.getMovesDone();
        }

        @Override
        public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
            return level.getStrategies().iterator().next().validMove();
        }

        @Override
        public void doMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
            Controller.executeCommand(new MoveCommand());
        }

        @Override
        public boolean canUndo() {
            boolean undo = Controller.executePassiveCommand(new UndoCommand());
            if(undo)
                Controller.executePassiveCommand(new RedoCommand());
            return undo;
        }

        @Override
        public boolean canRedo() {
            boolean redo = Controller.executePassiveCommand(new RedoCommand());
            if(redo)
                Controller.executePassiveCommand(new UndoCommand());
            return redo;
        }

        @Override
        public void undo() {
            Controller.executePassiveCommand(new UndoCommand());
        }

        @Override
        public void redo() {
            Controller.executePassiveCommand(new RedoCommand());
        }

        @Override
        public void saveGame() {

        }

        @Override
        public void loadGame() {

        }
    };
}
