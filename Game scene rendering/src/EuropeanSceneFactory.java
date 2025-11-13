class EuropeanSceneFactory extends SceneFactory {
    House createHouse(int x, int y, int w, int l) {
        return new House("concrete", x, y, w, l);
    }
}
