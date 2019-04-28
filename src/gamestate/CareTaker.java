package gamestate;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    //TODO caretaker
    private static CareTaker instance = new CareTaker();
    private List<Memento> savePoint = new ArrayList<>();
    private Memento memento;
    private SaveCommand orginator ;
    
    public static CareTaker getInstance() {
        return instance;
    }
    
    private CareTaker() {
    }
    
    public void saveGame(){
        memento = this.orginator.save();
        savePoint.add(memento);
    }
    
    public void restoreGame(){
        this.orginator.restore(memento);
    }
}
