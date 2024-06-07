package cards.maumau.model;

import cards.Card;
import cards.Suit;

import java.lang.System.Logger.Level;

abstract class StateMachine<S extends StateMachine<S, P>, P extends State<?>> extends State<P> {
    private State<S> state;

    StateMachine(P parent) {
        super(parent);
    }

    StateMachine(P parent, String name) {
        super(parent, name);
    }

    abstract State<S> initialState();

    void gotoState(State<S> newState) {
        LOGGER.log(Level.DEBUG, "{0}: {1} --> {2}", this, state, newState);
        enter(newState);
    }

    @Override
    void entry() {
        final State<S> newState = initialState();
        LOGGER.log(Level.DEBUG, "{0}: initial state={1}", this, newState);
        enter(newState);
    }

    private void enter(State<S> newState) {
        if (newState.parent != this)
            throw new IllegalArgumentException("Wrong state: " + newState + " belongs to " + newState.parent + " instead of " + this);
        state = newState;
        state.entry();
    }

    @Override
    void addPlayer(Player player) {
        state.addPlayer(player);
    }

    @Override
    void startGame() {
        state.startGame();
    }

    @Override
    void finishGame() {
        state.finishGame();
    }

    @Override
    void cancelGame() {
        state.cancelGame();
    }

    @Override
    void chooseCard(Card c) {
        state.chooseCard(c);
    }

    @Override
    void chooseSuit(Suit s) {
        state.chooseSuit(s);
    }

    @Override
    void skip() {
        state.skip();
    }

    @Override
    void no7() {
        state.no7();
    }

    @Override
    public String toString() {
        return super.toString() + "(in " + state + ")";
    }

    State<S> getState() {
        return state;
    }
}
