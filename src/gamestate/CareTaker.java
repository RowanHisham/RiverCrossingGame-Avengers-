package gamestate;

import levels.Level;

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
    public void removeLast() {
        undoStack.pop();
    }
    
    Memento undo(){
        if(undoStack.size() == 0)
            return null;
        redoStack.push(Level.getInstance().getState());
        return undoStack.pop();
    }
    Memento redo(){
        if(redoStack.size()==0)
            return null;
        undoStack.push(Level.getInstance().getState());
        return redoStack.pop();
    }
}
