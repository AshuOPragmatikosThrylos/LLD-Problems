import Interfaces.Command;
import commandImpl.ActionOneCommand;
import commandImpl.ActionTwoCommand;
import src.Receiver;
import src.Invoker;

public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();

        Command actionOne = new ActionOneCommand(receiver);
        Command actionTwo = new ActionTwoCommand(receiver);

        Invoker invoker = new Invoker();

        System.out.println("\n--- Execute Commands ---");
        invoker.setCommand(actionOne);
        invoker.executeCommand();

        invoker.setCommand(actionTwo);
        invoker.executeCommand();

        System.out.println("\n--- Undo Commands ---");
        invoker.undo();
        invoker.undo();

        System.out.println("\n--- Redo Commands ---");
        invoker.redo();
        invoker.redo();

        System.out.println("\n--- Boundary Conditions ---");
        invoker.undo();
        invoker.undo();
        invoker.undo();

        invoker.redo();
        invoker.redo();
        invoker.redo();
    }
}

// setCommand() is useful for GUI buttons or remotes where the same invoker(button) performs different actions based on user input