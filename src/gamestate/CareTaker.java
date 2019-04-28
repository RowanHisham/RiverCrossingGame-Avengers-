package gamestate;

public class CareTaker {
    //TODO caretaker
    private static CareTaker instance = new CareTaker();
    public static CareTaker getInstance() {
        return instance;
    }

    private CareTaker() {
    }
}
