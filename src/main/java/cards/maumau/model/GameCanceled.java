package cards.maumau.model;

class GameCanceled extends State<Automaton> {
    GameCanceled(Automaton parent) {
        super(parent, "GameCanceled");
        getActionHandler().setGameState(GameState.GAME_CANCELED);
    }
}
