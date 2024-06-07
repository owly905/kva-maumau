package cards.maumau.model;

import cards.Card;
import cards.Rank;

class SuitChosen extends State<Inner> {
    SuitChosen(Inner parent) {
        super(parent, "SuitChosen");
        getActionHandler().setGameState(GameState.PLAY);
    }

    @Override
    void skip() {
        final PlayerHandler ph = getActionHandler().getGame().getPlayerHandler();
        ph.getCurrentPlayer().drawCards(1);
        ph.nextTurn(1);
    }

    @Override
    void chooseCard(Card c) {
        final ActionHandler ah = getActionHandler();
        final PlayerHandler ph = ah.getGame().getPlayerHandler();
        if (ah.canPlay(c)) {
            ah.setChosenSuit(null);
            ph.getCurrentPlayer().playCard(c);
            if (c.rank() == Rank.SEVEN) {
                ah.increment7Counter();
                ph.nextTurn(1);
                parent.gotoState(new SevenChosen(parent));
            }
            else if (c.rank() == Rank.EIGHT) {
                ph.nextTurn(2);
                parent.gotoState(new Normal(parent));
            }
            else {
                ph.nextTurn(1);
                parent.gotoState(new Normal(parent));
            }
        }
    }
}
