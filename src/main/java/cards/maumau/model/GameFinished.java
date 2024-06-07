package cards.maumau.model;

class GameFinished extends State<Automaton> {
    GameFinished(Automaton parent) {
        super(parent, "GameFinished");
        getActionHandler().setGameState(GameState.GAME_OVER);
    }
}
