package cards.maumau.model;

import cards.Card;
import cards.Rank;

public class Normal extends InnerState {
    public Normal(ActionHandler a) {
        super(a);
    }

    @Override
    public void skip() {
        actionHandler.getGame().getPlayerHandler().getCurrentPlayer().drawCards(1);
        actionHandler.getGame().getPlayerHandler().nextTurn(1);
    }

    @Override
    public void chooseCard(Card c) {
        System.out.println("war hier");
        if(actionHandler.canPlay(c)){

            actionHandler.getGame().getPlayerHandler().getCurrentPlayer().playCard(c);
            if(c.rank() == Rank.JACK){
                actionHandler.setState(actionHandler.getChoose_Suit());
            }
            else if(c.rank()==Rank.SEVEN){
                actionHandler.increment7Counter();
                actionHandler.getGame().getPlayerHandler().nextTurn(1);
                actionHandler.getState().setInnerState(actionHandler.getState().getInnerState3());
            }
            else if(c.rank()==Rank.EIGHT){
                actionHandler.getGame().getPlayerHandler().nextTurn(2);
            }
            else{
                System.out.println("War hier");
                actionHandler.getGame().getPlayerHandler().nextTurn(1);
            }
        }
    }
}
