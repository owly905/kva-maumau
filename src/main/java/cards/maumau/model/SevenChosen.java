package cards.maumau.model;

import cards.Card;

public class SevenChosen extends InnerState{
    public SevenChosen(ActionHandler a){
        super(a);
    }

    @Override
    public void chooseCard(Card c){
        if(actionHandler.canPlay(c)){
            actionHandler.getGame().getPlayerHandler().getCurrentPlayer().playCard(c);
            actionHandler.increment7Counter();
            actionHandler.getGame().getPlayerHandler().nextTurn(1);
        }
    }

    @Override
    public void no7(){
        actionHandler.getGame().getPlayerHandler().getCurrentPlayer().drawCards(2*actionHandler.get7Counter());
        actionHandler.reset7Counter();
        actionHandler.getState().setInnerState(actionHandler.getState().getInnerState());
    }

}
