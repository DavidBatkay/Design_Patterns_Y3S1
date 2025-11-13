// Decorator example: adds an ability label (simple, not changing behaviour)
class AbilityDecorator {
    public static void addAbility(CharacterBase c, String ability) {
        c.abilities.add(ability);
    }
}
