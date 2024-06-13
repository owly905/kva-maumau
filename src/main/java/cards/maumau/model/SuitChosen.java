package cards.maumau.model;

import cards.Card;
import cards.Rank;

public class SuitChosen extends InnerState {
    public SuitChosen(ActionHandler a) {
        super(a);
    }
    @Override
    public void skip(){
        actionHandler.getGame().getPlayerHandler().getCurrentPlayer().drawCards(1);
        actionHandler.getGame().getPlayerHandler().nextTurn(1);
    }

    @Override
    public void chooseCard(Card c){
        if(actionHandler.canPlay(c)){
            actionHandler.setChosenSuit(null);
            actionHandler.getGame().getPlayerHandler().getCurrentPlayer().playCard(c);
            if(c.rank() == Rank.SEVEN){
                actionHandler.increment7Counter();
                actionHandler.getGame().getPlayerHandler().nextTurn(1);
                actionHandler.getState().setInnerState(actionHandler.getState().getInnerState3());
            }
            if(c.rank()==Rank.EIGHT){
                actionHandler.getGame().getPlayerHandler().nextTurn(2);
                actionHandler.getState().setInnerState(actionHandler.getState().getInnerState());
            }
            else{
                actionHandler.getState().setInnerState(actionHandler.getState().getInnerState());
            }
        }
    }

}
