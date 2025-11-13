import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class CharacterBuilder {
    private String id = UUID.randomUUID().toString().substring(0, 6);
    private String type = "Human";
    private int age = 20;
    private int w = 1, h = 1;
    private List<String> abilities = new ArrayList<>();

    public CharacterBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CharacterBuilder type(String t) {
        this.type = t;
        return this;
    }

    public CharacterBuilder age(int a) {
        this.age = a;
        return this;
    }

    public CharacterBuilder size(int w, int h) {
        this.w = w;
        this.h = h;
        return this;
    }

    public CharacterBuilder addAbility(String a) {
        this.abilities.add(a);
        return this;
    }

    public CharacterBase build() {
        CharacterBase c;
        if (type.equalsIgnoreCase("elf")) c = new Elf(id, age, w, h);
        else c = new Human(id, age, w, h);
        c.abilities.addAll(abilities);
        return c;
    }
}
