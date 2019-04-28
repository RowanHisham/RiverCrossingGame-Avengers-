package gamestate;

import commands.Command;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import characters.Character;
import javax.xml.transform.OutputKeys;


public class SaveCommand implements Command {
    //TODO save command
    
    private List<characters.Character> onBoard = new ArrayList<>();
    private List<characters.Character> leftCharacters = new ArrayList<>();
    private List<characters.Character> rightCharacters = new ArrayList<>();
    private int moves;
    private int maxCharacters;
    
    public SaveCommand(List<characters.Character> onBoard ,List<characters.Character> leftCharacters ,
             List<characters.Character> rightCharacters , int moves, int maxCharacters ){
        this.onBoard = onBoard;
        this.rightCharacters = rightCharacters;
        this.leftCharacters = leftCharacters;
        this.moves = moves;
        this.maxCharacters = maxCharacters;
       
    }
    
    @Override
    public boolean execute() {
        return false;
    }
    
    public Memento save(){
      return new Memento(this.onBoard,this.leftCharacters,this.rightCharacters,this.maxCharacters);
    }

    public void restore(Memento memento){
       this.leftCharacters = memento.getLeftCharacters();
       this.rightCharacters = memento.getRightCharacters();
       this.onBoard = memento.getOnBoard();
       this.moves = memento.getMoves();
    }
   
    public void saveFile()
    {
        try {
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

            Element maxChar = doc.createElement("maxCharacters");
            maxChar.appendChild(doc.createTextNode(String.valueOf(this.maxCharacters)));
            saveGame.appendChild(maxChar);

            Element numMoves = doc.createElement("numMoves");
            numMoves.appendChild(doc.createTextNode(String.valueOf(this.moves)));
            saveGame.appendChild(numMoves);

            Element leftChar = doc.createElement("leftCharacters");
            for(Character x : this.leftCharacters){
                leftChar.appendChild(doc.createTextNode(String.valueOf(x.getType()) +','));
            }
            saveGame.appendChild(leftChar);

            Element rightChar = doc.createElement("rightCharacters");
            for(Character x : this.rightCharacters){
                leftChar.appendChild(doc.createTextNode(String.valueOf(x.getType()) +','));
            }
            saveGame.appendChild(rightChar);

            Element onShip = doc.createElement("onBoard");
            for(Character x : this.onBoard){
                leftChar.appendChild(doc.createTextNode(String.valueOf(x.getType()) +','));
            }
            saveGame.appendChild(onShip);
            
            transformer.transform(source, result);
             
         } catch (Exception e){           
           e.printStackTrace();
            }
    }
}
