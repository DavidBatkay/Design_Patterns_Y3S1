import java.util.ArrayList;
import java.util.List;

class Board {
    int width, height;
    List<House> houses = new ArrayList<>();
    List<CharacterBase> characters = new ArrayList<>();
    List<Group> groups = new ArrayList<>();

    Board(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public boolean isAreaFree(Rect area, Object except) {
        // check houses
        for (House hh : houses) {
            if (hh != except && hh.getBounds().intersects(area)) return false;
        }
        // check characters
        for (CharacterBase c : characters) {
            if (c != except && c.getBounds().intersects(area)) return false;
        }
        // check groups (their members)
        for (Group g : groups) {
            if (g != except) {
                Rect gb = g.getBounds();
                if (gb.intersects(area)) return false;
            }
        }
        // bounds check
        if (area.x < 0 || area.y < 0 || area.x + area.w > width || area.y + area.h > height) return false;
        return true;
    }

    public void render() {
        System.out.println("--- BOARD RENDER: size=" + width + "x" + height + " ---");
        System.out.println("HOUSES:");
        for (House h : houses) System.out.println("  " + h.renderDescription());
        System.out.println("CHARACTERS:");
        for (CharacterBase c : characters) System.out.println("  " + c.renderDescription());
        System.out.println("GROUPS:");
        for (Group g : groups) System.out.println("  " + g.renderDescription());
        System.out.println("-----------------------");
    }
}
