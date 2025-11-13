class Rect {
    int x, y, w, h;

    Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    boolean intersects(Rect o) {
        return !(x + w <= o.x || o.x + o.w <= x || y + h <= o.y || o.y + o.h <= y);
    }

    public String toString() {
        return String.format("x=%d y=%d w=%d h=%d", x, y, w, h);
    }
}
