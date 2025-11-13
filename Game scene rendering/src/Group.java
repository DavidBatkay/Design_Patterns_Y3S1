import java.util.ArrayList;
import java.util.List;

class Group implements Positionable {
    String id;
    List<CharacterBase> members = new ArrayList<>();
    int x = 0, y = 0; // group's origin; we'll treat members positions relative to group origin when moved as simple strategy
//composite
    Group(String id) {
        this.id = id;
    }

    void add(CharacterBase c) {
        members.add(c);
    }

    // group's bounding box = bounding union of members
    public Rect getBounds() {
        if (members.isEmpty()) return new Rect(x, y, 0, 0);
        int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE, maxy = Integer.MIN_VALUE;
        for (CharacterBase c : members) {
            Rect r = c.getBounds();
            minx = Math.min(minx, r.x);
            miny = Math.min(miny, r.y);
            maxx = Math.max(maxx, r.x + r.w);
            maxy = Math.max(maxy, r.y + r.h);
        }
        return new Rect(minx, miny, maxx - minx, maxy - miny);
    }

    public String renderDescription() {
        return String.format("Group[%s] members=%d bounds=%s", id, members.size(), getBounds());
    }

    // Move group to new origin: compute delta from group's current bounds min corner
    public void moveTo(int nx, int ny) {
        Rect b = getBounds();
        if (b.w == 0 && b.h == 0) {
            // place members starting at nx,ny stacked
            int offsetX = nx;
            int offsetY = ny;
            for (CharacterBase c : members) {
                c.moveTo(offsetX, offsetY);
                offsetX += c.w;
            }
            return;
        }
        int dx = nx - b.x;
        int dy = ny - b.y;
        for (CharacterBase c : members) {
            c.moveTo(c.x + dx, c.y + dy);
        }
    }
}
