package cards.maumau.model;

import cards.Card;
import cards.Suit;

import javax.swing.plaf.nimbus.State;

public abstract class InnerState {
    ActionHandler actionHandler;
    void startGame(){}
    void finishGame(){}
    void cancelGame(){}
    void chooseCard(Card c){}
    void chooseSuit(Suit suit){}
    void skip(){}
    void no7(){}


    public InnerState(ActionHandler a){
        actionHandler=a;
    }



}
