import java.util.HashMap;
import java.util.Map;

class MoveCommand implements Command {
    Positionable target;
    int toX, toY;
    // store previous positions
    Map<CharacterBase, int[]> previous = new HashMap<>();

    MoveCommand(Positionable target, int toX, int toY) {
        this.target = target;
        this.toX = toX;
        this.toY = toY;
    }

    public boolean execute() {
        if (target instanceof CharacterBase) {
            CharacterBase c = (CharacterBase) target;
            previous.put(c, new int[]{c.x, c.y});
            c.moveTo(toX, toY);
            return true;
        }
        if (target instanceof Group) {
            Group g = (Group) target; // store previous
            for (CharacterBase m : g.members) previous.put(m, new int[]{m.x, m.y});
            g.moveTo(toX, toY);
            return true;
        }
        return false;
    }

    public void undo() {
        for (Map.Entry<CharacterBase, int[]> e : previous.entrySet()) {
            int[] p = e.getValue();
            e.getKey().moveTo(p[0], p[1]);
        }
    }
}
