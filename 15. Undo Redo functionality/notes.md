- How to know if have to implement using command or memento DP?
    - Are we undoing actions or reverting to a previous state?
        - This helps you understand whether the focus is on actions (Command) or state (Memento)
    - Is there a history of commands or checkpoints?
        - If actions are tracked as commands, use Command. If checkpoints are saved, use Memento