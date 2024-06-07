package cards.maumau.model;

import cards.Card;

class SevenChosen extends State<Inner> {
    SevenChosen(Inner parent) {
        super(parent, "SevenChosen");
        getActionHandler().setGameState(GameState.PLAY);
    }

    @Override
    void chooseCard(Card c) {
        final ActionHandler ah = getActionHandler();
        if (ah.canPlay(c)) {
            final PlayerHandler ph = ah.getGame().getPlayerHandler();
            ph.getCurrentPlayer().playCard(c);
            ah.increment7Counter();
            ph.nextTurn(1);
        }
    }

    @Override
    void no7() {
        final ActionHandler ah = getActionHandler();
        final PlayerHandler ph = ah.getGame().getPlayerHandler();
        ph.getCurrentPlayer().drawCards(2 * ah.get7Counter());
        ah.reset7Counter();
        if (ah.getGameState() != GameState.GAME_CANCELED) { parent.gotoState(new Normal(parent)); }
    }
}
