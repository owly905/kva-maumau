package cards.maumau.model;

public class Initialzed extends InnerState{
    public Initialzed(ActionHandler a){
        super(a);
    }

    @Override
    public void startGame(){
        actionHandler.getGame().getCardHandler().dealCards();
    }
}
