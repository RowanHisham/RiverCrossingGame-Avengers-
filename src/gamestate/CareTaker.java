package gamestate;

import java.util.Stack;

public class CareTaker {
    private static CareTaker instance = new CareTaker();
    private Stack <Memento> undoStack = new Stack<>();
    private Stack <Memento> redoStack= new Stack<>();
    
    public static CareTaker getInstance() {
        return instance;
    }

    public static void reset() {
        instance = new CareTaker();
    }
    private CareTaker() {
    }
    public void putMemento(Memento memento){
           undoStack.push(memento);
           redoStack.clear();
    }
    
    Memento undo(){
        if(undoStack.size() == 0)
            return null;
        Memento x = undoStack.pop();
        redoStack.push(x);
        return x;
    }
    Memento redo(){
        if(redoStack.size()==0)
            return null;
        Memento y = redoStack.pop();
        undoStack.push(y);
        return y;
    }
}
