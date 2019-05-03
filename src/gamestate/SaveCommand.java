package gamestate;

import characters.Character;
import commands.Command;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import characters.*;
import java.util.Collection;
import levels.Level.ShipSide;
import levels.strategies.*;

public class SaveCommand implements Command {
    //TODO save command
    
    private Collection<characters.Character> onBoard ;
    private Collection<characters.Character> leftCharacters ;
    private Collection<characters.Character> rightCharacters ;
    private int moves;
    private int maxCharacters;
    private Collection<LevelStrategy> strategy;
    private ShipSide shipside;
    private int weight;
    
    public SaveCommand(Collection<characters.Character> onBoard ,Collection<characters.Character> leftCharacters ,
             Collection<characters.Character> rightCharacters ,
             int moves, int maxCharacters , Collection<LevelStrategy> strategy, 
             ShipSide shipside, int weight){
        this.onBoard = onBoard;
        this.rightCharacters = rightCharacters;
        this.leftCharacters = leftCharacters;
        this.moves = moves;
        this.maxCharacters = maxCharacters;
        this.strategy = strategy;
        this.shipside = shipside;
        this.weight = weight;
    }
    
    @Override
    public boolean execute() {
        return false;
    }
    
    public Memento save(){
      return new Memento(this.onBoard,this.leftCharacters,this.rightCharacters,this.maxCharacters);
    }

    public void restore(Memento memento){
       /*this.leftCharacters = memento.getLeftCharacters();
       this.rightCharacters = memento.getRightCharacters();
       this.onBoard = memento.getOnBoard();*/
       this.moves = memento.getMoves();
    }
   
    public void saveFile()
    {
        try {
           String temp;
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.newDocument();

           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
           DOMSource source = new DOMSource(doc);
           StreamResult result = new StreamResult(new File("Saves.xml"));
           
           Element rootElement = doc.createElement("game");
           doc.appendChild(rootElement);
           
            Element saveGame = doc.createElement("saveGame");
            rootElement.appendChild(saveGame);
            Element gameStrategy = doc.createElement("gameStrategy");
            for(LevelStrategy s : this.strategy){
                if(s instanceof TypeStrategy){
                    temp = "TypeStrategy";
                    gameStrategy.appendChild(doc.createTextNode(temp +','));
                }
                else if( s instanceof WeightStrategy){
                    temp = "WeightStrategy";
                    gameStrategy.appendChild(doc.createTextNode(temp +','));
                }
                
            }
            saveGame.appendChild(gameStrategy);

            Element maxChar = doc.createElement("maxCharacters");
            maxChar.appendChild(doc.createTextNode(String.valueOf(this.maxCharacters)));
            saveGame.appendChild(maxChar);

            Element numMoves = doc.createElement("numMoves");
            numMoves.appendChild(doc.createTextNode(String.valueOf(this.moves)));
            saveGame.appendChild(numMoves);
            
            Element weightCapacity = doc.createElement("weight");
            weightCapacity.appendChild(doc.createTextNode(String.valueOf(this.weight)));
            saveGame.appendChild(weightCapacity);
            
            Element leftChar = doc.createElement("leftCharacters");
            for(Character x : this.leftCharacters){
                if(x.isPilot())
                    temp = characterName(x).concat("1");
                else
                    temp = characterName(x).concat("0");
                
                leftChar.appendChild(doc.createTextNode((temp) +','));
            }
            saveGame.appendChild(leftChar);

            Element rightChar = doc.createElement("rightCharacters");
            for(Character x : this.rightCharacters){
                if(x.isPilot())
                    temp = characterName(x).concat("1");
                else
                    temp = characterName(x).concat("0");
                
                rightChar.appendChild(doc.createTextNode((temp) +','));
                
            }
            saveGame.appendChild(rightChar);
            
            Element onShip = doc.createElement("onBoard");
            for(Character x : this.onBoard){
                if(x.isPilot())
                    temp = characterName(x).concat("1");
                else
                    temp = characterName(x).concat("0");
                
                onShip.appendChild(doc.createTextNode((temp) +','));
            }
            saveGame.appendChild(onShip);
            
            temp = this.shipside.toString();
            Element shipSide = doc.createElement("ShipSide");
            shipSide.appendChild(doc.createTextNode(temp));
            saveGame.appendChild(shipSide);
            
            transformer.transform(source, result);
             
         } catch (Exception e){           
           e.printStackTrace();
            }
    }
            private String characterName(Character x ){
            
            if(x instanceof CaptainAmerica){
                return "CaptainAmerica";
            }
            else if(x instanceof BlackWidow){
                return "BlackWidow";
            }
            else if(x instanceof Agent){
                return "Agent";
            }
            else if(x instanceof IronMan){
                return "IronMan";
            }
            else if(x instanceof Loki){
                return "Loki";
            }
            else if(x instanceof SpiderMan){
                return "SpiderMan";
            }
            else if(x instanceof Villain){
                return "Villain";
            }
            
           return null;
        }
}
