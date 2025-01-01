public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State 1");
        caretaker.saveMemento(originator.saveStateToMemento());

        originator.setState("State 2");
        caretaker.saveMemento(originator.saveStateToMemento());

        originator.setState("State 3");
        System.out.println("Note: State 3 is not saved");

        // Undo to previous states
        System.out.println("\n--- Undo ---");
        originator.restoreStateFromMemento(caretaker.getMemento());
        originator.restoreStateFromMemento(caretaker.getMemento());

        System.out.println("\n--- Redo ---");
        originator.restoreStateFromMemento(caretaker.redo());
        originator.restoreStateFromMemento(caretaker.redo());

        System.out.println("\n--- Boundary Conditions ---");
        originator.restoreStateFromMemento(caretaker.getMemento());
        originator.restoreStateFromMemento(caretaker.getMemento());
        originator.restoreStateFromMemento(caretaker.getMemento());

        originator.restoreStateFromMemento(caretaker.redo());
        originator.restoreStateFromMemento(caretaker.redo());
        originator.restoreStateFromMemento(caretaker.redo());
    }
}

// - The client (neither the caretaker) does not directly interact with the Originator's state. Instead, it handles Memento objects that encapsulate the state details, preserving encapsulation
// - If the Originator's state becomes more complex, the changes are localized to the Memento and Originator classes. The Caretaker remains unaffected
