package gamestate;

import characters.Character;
import commands.Command;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import characters.Character;
public class LoadCommand implements Command {
    //TODO load command
    private int moves; 
    private int maxCharacters;
    private List<characters.Character> onBoard = new ArrayList<>();
    private List<characters.Character> leftCharacters = new ArrayList<>();
    private List<characters.Character> rightCharacters = new ArrayList<>();
    
    @Override
    public boolean execute() {
        return false;
    }
    
    public void loadFile(){
        	      
        try {
            File inputFile = new File("Saves.xml");
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
                    isInternal= Boolean.parseBoolean (eElement.getElementsByTagName("isInternal").item(0).getTextContent());
                    stop = Integer.parseInt( eElement.getElementsByTagName("stopType").item(0).getTextContent());
                    Vehicle = eElement.getElementsByTagName("vehicle").item(0).getTextContent();
                   
             }
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

    public List<Character> getOnBoard() {
        return onBoard;
    }

    public List<Character> getLeftCharacters() {
        return leftCharacters;
    }

    public List<Character> getRightCharacters() {
        return rightCharacters;
    }
}
    

