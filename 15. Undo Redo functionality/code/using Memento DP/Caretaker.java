import java.util.Stack;

// Caretaker: Manages the Mementos without knowing their internal details
public class Caretaker {
    private final Stack<Memento> mementoStack = new Stack<>(); // not tightly coupled with originator's internal structure (State is not of string type; originator's state is of ype string)

    private final Stack<Memento> redoStack = new Stack<>();

    public void saveMemento(Memento memento) {
        mementoStack.push(memento);
        redoStack.clear(); // Redo is only valid until the next new save
    }

    public Memento getMemento() {
        if (!mementoStack.isEmpty()) {
            Memento memento = mementoStack.pop();
            redoStack.push(memento);
            return memento;
        }
        System.out.println("Empty stack. No state to restore");
        return null;
    }

    public Memento redo() {
        if (!redoStack.isEmpty()) {
            Memento memento = redoStack.pop();
            mementoStack.push(memento);
            return memento;
        }
        System.out.println("Redo stack is empty. No state to redo");
        return null;
    }
}