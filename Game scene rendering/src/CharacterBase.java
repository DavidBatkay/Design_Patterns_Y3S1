import java.util.ArrayList;
import java.util.List;

// ------------------------ Characters & Builder & Decorator & Factory Method ------------------------
abstract class CharacterBase implements Positionable {
    protected String id;
    protected int age;
    protected int x, y, w, h;
    protected int size; // logical size used in group occupancy
    protected String type;
    protected List<String> abilities = new ArrayList<>();

    CharacterBase(String id, String type, int age, int w, int h) {
        this.id = id;
        this.type = type;
        this.age = age;
        this.x = 0;
        this.y = 0;
        this.w = w;
        this.h = h;
        this.size = w * h;
    }

    public Rect getBounds() {
        return new Rect(x, y, w, h);
    }

    public String renderDescription() {
        return String.format("%s[%s] age=%d at %s abilities=%s", type, id, age, getBounds(), abilities);
    }

    public void moveTo(int nx, int ny) {
        this.x = nx;
        this.y = ny;
    }
}
