package cards.maumau.model;

class Automaton extends StateMachine<Automaton, Automaton> {
    private final ActionHandler actionHandler;

    Automaton(ActionHandler actionHandler) {
        super(null, "Automaton");
        this.actionHandler = actionHandler;
        entry();
    }

    @Override
    ActionHandler getActionHandler() {
        return actionHandler;
    }

    @Override
    GamePlay initialState() {
        return new GamePlay(this);
    }
}
