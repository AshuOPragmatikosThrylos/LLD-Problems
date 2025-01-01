package commandImpl;

import Interfaces.Command;
import src.Receiver;

public class ActionOneCommand implements Command {
    private Receiver receiver;

    public ActionOneCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.actionOne();
    }

    @Override
    public void undo() {
        System.out.println("Undoing Action One");
    }
}