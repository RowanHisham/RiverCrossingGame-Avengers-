package gamestate;

import characters.*;
import commands.Command;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import levels.*;
import levels.strategies.*;
import levels.strategies.LevelStrategy;
import characters.CharacterFactory;
import levels.Level.ShipSide;

public class LoadCommand implements Command {
    //TODO load command
    private int moves;
    private int maxCharacters;
    private int weight;
    private Collection<characters.Character> onBoard = new ArrayList<>();
    private Collection<characters.Character> leftCharacters =new ArrayList<>();
    private Collection<characters.Character> rightCharacters =new ArrayList<>();
    private Collection<LevelStrategy> strategy=new ArrayList<>();
    private Collection<String> strat = new ArrayList<>();
    CharacterFactory chFactory = new CharacterFactory();
    ShipSide shipSide ;
    
    @Override
    public boolean execute() {
        return false;
    }

    public void loadFile(){

        try {
            String temp ;
            String splitTemp[];
            char lastIndex;
            boolean isPilot;
            File inputFile = new File("Saves.xml");
            if(inputFile.exists()){
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("saveGame");

                for (int i = 0; i < nList.getLength(); i++) {
                    Node nNode = nList.item(i);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        this.moves = Integer.parseInt(eElement.getElementsByTagName("numMoves").item(0).getTextContent());
                        this.maxCharacters = Integer.parseInt( eElement.getElementsByTagName("maxCharacters").item(0).getTextContent());
                       this.weight = Integer.parseInt(eElement.getElementsByTagName("weight").item(0).getTextContent());
                        temp = eElement.getElementsByTagName("leftCharacters").item(0).getTextContent();
                        splitTemp = temp.split(",");
                        for(String s : splitTemp){
                            if(s != null && s != ""){
                                lastIndex = s.charAt(s.length()-1);
                                if(lastIndex == '0' ){
                                    isPilot = false;
                                    s = s.substring(0, s.length()-1);
                                    this.leftCharacters.add(chFactory.getCharacter(s,isPilot)); 
                                }
                                else if(lastIndex == '1'){
                                    isPilot = true;
                                     s = s.substring(0, s.length()-1);
                                    this.leftCharacters.add(chFactory.getCharacter(s,isPilot));
                                }
                            }
                            
                        }
                        temp = eElement.getElementsByTagName("rightCharacters").item(0).getTextContent();
                        splitTemp = temp.split(",");
                        for(String s : splitTemp){
                            if(s != null && s != ""){
                                lastIndex = s.charAt(s.length()-1);
                                if(lastIndex == '0' ){
                                    isPilot = false;
                                    s = s.substring(0, s.length()-1);
                                    this.rightCharacters.add(chFactory.getCharacter(s,isPilot)); 
                                }
                                else if(lastIndex == '1'){
                                    isPilot = true;
                                     s = s.substring(0, s.length()-1);
                                    this.rightCharacters.add(chFactory.getCharacter(s,isPilot));
                                }
                            }
                        }
                        temp = eElement.getElementsByTagName("onBoard").item(0).getTextContent();
                        splitTemp = temp.split(",");
                        for(String s : splitTemp){
                            if(s != null && s != ""){
                                lastIndex = s.charAt(s.length()-1);
                                if(lastIndex   == '0' ){
                                    isPilot = false;
                                    s = s.substring(0, s.length()-1);
                                    this.onBoard.add(chFactory.getCharacter(s,isPilot)); 
                                }
                                else if(lastIndex == '1'){
                                    isPilot = true;
                                     s = s.substring(0, s.length()-1);
                                    this.onBoard.add(chFactory.getCharacter(s,isPilot));
                                }
                            }
                        }
                        temp = eElement.getElementsByTagName("gameStrategy").item(0).getTextContent();
                        splitTemp = temp.split(",");
                        for(String s : splitTemp){
                            this.strat.add(s);
                            if(s.compareToIgnoreCase("TypeStrategy") == 0 ){
                                 TypeStrategy typeStrat = new TypeStrategy();
                                 this.strategy.add(typeStrat);
                            }
                            else if(s.compareToIgnoreCase("WeightStrategy") == 0){
                                 WeightStrategy weightStrat = new WeightStrategy();
                                 this.strategy.add(weightStrat);
                            }
                        }
                        temp = eElement.getElementsByTagName("ShipSide").item(0).getTextContent();
                        if(temp.compareToIgnoreCase("Left") ==0)
                            this.shipSide = ShipSide.LEFT;
                        else if(temp.compareToIgnoreCase("Right") ==0)
                            this.shipSide = ShipSide.RIGHT;
                    }
                }
            }
            else{
                System.out.println("File doesn't exist yet");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public int getMoves() {
        return moves;
    }

    public int getMaxCharacters() {
        return maxCharacters;
    }

    public Collection<characters.Character> getOnBoard() {
        return onBoard;
    }

    public Collection<characters.Character> getLeftCharacters() {
        return leftCharacters;
    }

    public Collection<characters.Character> getRightCharacters() {
        return rightCharacters;
    }
    
    public Memento getState() {
        return new Memento(this.onBoard, this.leftCharacters, this.rightCharacters, this.moves,this.shipSide);
    }
    
    public Level updateLevel(){
        
        LevelStrategy[] strategyArray = this.strategy.toArray(new LevelStrategy[this.strategy.size()]);
        characters.Character[] leftInitial = this.leftCharacters.toArray(new characters.Character[this.leftCharacters.size()]);        
        Level.Builder builder = new Level.Builder();
        builder.maxShipCharacters(this.maxCharacters);
        builder.movesDone(this.moves);
        builder.addCharacter(leftInitial);
        builder.weightCapacity(this.weight);
        builder.addStrategy(strategyArray);
        return builder.build();
        
        
    }
    public void setState(){
        Level level = Level.getInstance();
        level.setState(getState());
    }
}
    

