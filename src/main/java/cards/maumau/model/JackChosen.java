package cards.maumau.model;

import cards.Suit;

class JackChosen extends State<Inner> {
    JackChosen(Inner parent) {
        super(parent, "JackChosen");
        getActionHandler().setGameState(GameState.CHOOSE_SUIT);
    }

    @Override
    void chooseSuit(Suit s) {
        final ActionHandler ah = getActionHandler();
        ah.setChosenSuit(s);
        ah.getGame().getPlayerHandler().nextTurn(1);
        parent.gotoState(new SuitChosen(parent));
    }
}
