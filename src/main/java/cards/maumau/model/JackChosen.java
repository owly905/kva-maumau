package cards.maumau.model;

import cards.Suit;

public class JackChosen extends InnerState{
    public JackChosen(ActionHandler a){
        super(a);
    }
    @Override
    public void chooseSuit(Suit s){
        actionHandler.setChosenSuit(s);
        actionHandler.getGame().getPlayerHandler().nextTurn(1);
        actionHandler.setState(actionHandler.Game_Play);
    }

}
