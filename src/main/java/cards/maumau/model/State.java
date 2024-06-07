package cards.maumau.model;

import cards.Card;
import cards.Suit;

import java.lang.System.Logger;

abstract class State<P extends State<?>> {
    static final Logger LOGGER = System.getLogger(State.class.getName());
    final String name;
    final P parent;

    State(P parent) {
        this(parent, null);
    }

    State(P parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    void entry() {}

    void addPlayer(Player player) {}

    void startGame() {}

    void finishGame() {}

    void cancelGame() {}

    void chooseCard(Card c) {}

    void chooseSuit(Suit s) {}

    void skip() {}

    void no7() {}

    ActionHandler getActionHandler() {
        return parent.getActionHandler();
    }

    @Override
    public String toString() {
        return name == null ? getClass().getName() : name;
    }
}
