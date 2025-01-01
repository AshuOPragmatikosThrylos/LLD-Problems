// Originator: Creates and restores its own Mementos
public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
        System.out.println("State set to: " + state);
    }

    public Memento saveStateToMemento() {
        System.out.println("Saving State: " + state);
        return new Memento(state);
    }

    public void restoreStateFromMemento(Memento memento) {
        if (memento == null) {
            return;
        }
        this.state = memento.getState();
        System.out.println("State restored to: " + state);
    }
}