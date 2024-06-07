package cards.maumau.model;

import cards.Card;
import cards.Suit;

class GamePlay extends State<Automaton> {
    private final Inner inner;

    GamePlay(Automaton parent) {
        super(parent, "GamePlay");
        inner = new Inner(this);
    }

    @Override
    void entry() {
        inner.entry();
    }

    @Override
    void addPlayer(Player player) {
        inner.addPlayer(player);
    }

    @Override
    void startGame() {
        inner.startGame();
    }

    @Override
    void finishGame() {
        parent.gotoState(new GameFinished(parent));
    }

    @Override
    void cancelGame() {
        parent.gotoState(new GameCanceled(parent));
    }

    @Override
    void chooseCard(Card c) {
        inner.chooseCard(c);
    }

    @Override
    void chooseSuit(Suit s) {
        inner.chooseSuit(s);
    }

    @Override
    void skip() {
        inner.skip();
    }

    @Override
    void no7() {
        inner.no7();
    }

    State<?> getInnerState() {
        return inner.getState();
    }
}
