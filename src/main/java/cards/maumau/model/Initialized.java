package cards.maumau.model;

class Initialized extends State<Inner> {

    Initialized(Inner parent) {
        super(parent, "Initialized");
        getActionHandler().setGameState(GameState.GAME_INITIALIZED);
    }

    @Override
    void addPlayer(Player p) {
        final ActionHandler ah = getActionHandler();
        ah.getGame().getPlayerHandler().addPlayer(p);
    }

    @Override
    void startGame() {
        final ActionHandler ah = getActionHandler();
        ah.getGame().getCardHandler().dealCards();
        parent.gotoState(new Normal(parent));
    }
}
