public class GameApp {
    public static void main(String[] args){
        GameEngine ge = GameEngine.getInstance();
        ge.initBoard(20,20);

        // choose scene factory
        SceneFactory sf = new EuropeanSceneFactory();
        // add houses
        for(int i=0;i<4;i++){
            House h = sf.createHouse(2 + i*5, 2, 2, 2);
            ge.board.houses.add(h);
        }

        // create characters w/ builder and factory
        CharacterBase c1 = new CharacterBuilder().withId("H1").type("human").age(30).size(1,1).build();
        CharacterBase c2 = new CharacterBuilder().withId("H2").type("human").age(25).size(1,1).build();
        CharacterBase e1 = new CharacterBuilder().withId("E1").type("elf").age(120).size(1,1).build();

        ge.board.characters.add(c1); ge.board.characters.add(c2); ge.board.characters.add(e1);

        // group composite
        Group g1 = new Group("G1"); g1.add(c1); g1.add(c2);
        ge.board.groups.add(g1);

        // decorate ability
        AbilityDecorator.addAbility(e1, "invisibility");

        // initial render
        ge.board.render();

        // try moving a single character to place occupied by a house (should be rejected)
        System.out.println("Attempt moving E1 to (2,2) — occupied by house:");
        ge.tryMove(e1,2,2);
        System.out.println("TEST:");
        System.out.println(c1.renderDescription());
        c1.moveTo(2,2);
        System.out.println(c1.renderDescription());


        // move single character to free spot
        System.out.println("Move E1 to (0,0):");
        ge.tryMove(e1,0,0);
        ge.board.render();

        // move group to a space — group position will be validated using group's bounding box
        System.out.println("Move group G1 to (5,2) (should collide with 2nd house):");
        ge.tryMove(g1,5,2);

        System.out.println("Move group G1 to (10,10) (free):");
        ge.tryMove(g1,10,10);
        ge.board.render();

        // undo last move
        ge.undo();
        ge.board.render();

        // create more houses based on user input would follow same pattern
        System.out.println("Demo finished.");
    }
}
