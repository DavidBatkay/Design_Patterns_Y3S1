// ------------------------ Command pattern for moves ------------------------
interface Command {
    boolean execute();

    void undo();
}
