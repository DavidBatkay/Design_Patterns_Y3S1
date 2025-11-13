import java.util.ArrayDeque;
import java.util.Deque;

class GameEngine {
    private static GameEngine instance;
    Board board;
    Deque<Command> history = new ArrayDeque<>();

    private GameEngine() {
    }

    public static GameEngine getInstance() {
        if (instance == null) instance = new GameEngine();
        return instance;
    } //NOTE Singleton

    public void initBoard(int w, int h) {
        board = new Board(w, h);
    }

    public boolean tryMove(Positionable p, int nx, int ny) {
        Rect newBounds;
        if (p instanceof CharacterBase) {
            CharacterBase c = (CharacterBase) p;
            newBounds = new Rect(nx, ny, c.w, c.h);
        } else if (p instanceof Group) {
            Group g = (Group) p; // simulate move
            Rect b = g.getBounds();
            newBounds = new Rect(nx, ny, b.w, b.h);
        } else return false;
        if (!board.isAreaFree(newBounds, p)) {
            System.out.println("Move rejected: target area is occupied or out of bounds: " + newBounds);
            return false;
        }
        Command cmd = new MoveCommand(p, nx, ny);
        boolean ok = cmd.execute();
        if (ok) history.push(cmd);
        System.out.println("Move executed to " + newBounds);
        return ok;
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command c = history.pop();
            c.undo();
            System.out.println("Undo executed.");
        } else System.out.println("Nothing to undo.");
    }
}
