class AfricanSceneFactory extends SceneFactory {
    House createHouse(int x, int y, int w, int l) {
        return new House("mud", x, y, w, l);
    }
}
