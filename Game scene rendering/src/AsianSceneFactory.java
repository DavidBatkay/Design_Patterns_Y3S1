class AsianSceneFactory extends SceneFactory {
    House createHouse(int x, int y, int w, int l) {
        return new House("bamboo", x, y, w, l);
    }
}
