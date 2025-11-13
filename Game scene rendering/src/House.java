class House implements Positionable {
    String material;
    int x, y, w, l;
//Abstract Factory
    House(String material, int x, int y, int w, int l) {
        this.material = material;
        this.x = x;
        this.y = y;
        this.w = w;
        this.l = l;
    }

    public Rect getBounds() {
        return new Rect(x, y, w, l);
    }

    public String renderDescription() {
        return String.format("House(%s) at %s", material, getBounds());
    }
}
