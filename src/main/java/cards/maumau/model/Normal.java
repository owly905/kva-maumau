package cards.maumau.model;

import cards.Card;
import cards.Rank;

class Normal extends State<Inner> {
    Normal(Inner parent) {
        super(parent, "Normal");
        getActionHandler().setGameState(GameState.PLAY);
    }

    @Override
    void skip() {
        final ActionHandler ah = getActionHandler();
        final PlayerHandler ph = ah.getGame().getPlayerHandler();
        ph.getCurrentPlayer().drawCards(1);
        ph.nextTurn(1);
    }

    @Override
    void chooseCard(Card c) {
        final ActionHandler ah = getActionHandler();
        if (ah.canPlay(c)) {
            final PlayerHandler ph = ah.getGame().getPlayerHandler();
            ph.getCurrentPlayer().playCard(c);
            if (c.rank() == Rank.JACK) {
                parent.gotoState(new JackChosen(parent));
            }
            else if (c.rank() == Rank.SEVEN) {
                ah.increment7Counter();
                ph.nextTurn(1);
                parent.gotoState(new SevenChosen(parent));
            }
            else if (c.rank() == Rank.EIGHT) {
                ph.nextTurn(2);
            }
            else {
                ph.nextTurn(1);
            }
        }
    }
}
