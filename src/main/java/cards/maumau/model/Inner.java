package cards.maumau.model;

class Inner extends StateMachine<Inner, GamePlay> {
    Inner(GamePlay parent) {
        super(parent, "inner");
    }

    @Override
    Initialized initialState() {
        return new Initialized(this);
    }
}
