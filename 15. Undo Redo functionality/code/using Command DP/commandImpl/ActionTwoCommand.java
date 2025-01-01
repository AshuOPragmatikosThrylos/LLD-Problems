package commandImpl;

import Interfaces.Command;
import src.Receiver;

public class ActionTwoCommand implements Command {
    private Receiver receiver;

    public ActionTwoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.actionTwo();
    }

    @Override
    public void undo() {
        System.out.println("Undoing Action Two");
    }
}