// Factory Method (simple wrapper)
class CharacterFactory {
    public static CharacterBase create(String kind, String id, int age, int w, int h) {
        if (kind.equalsIgnoreCase("elf")) return new Elf(id, age, w, h);
        return new Human(id, age, w, h);
    }
}
