package cards.maumau.model;

import cards.Card;
import cards.Rank;
import cards.Suit;

/**
 * Manages the actions and state transitions within a MauMau game.
 */
class ActionHandler {
    private final MauMau game;
    private Suit chosenSuit;
    private int ctr7 = 0;
    private final Automaton auto = new Automaton(this);
    private GameState gameState;

    /**
     * Constructs an ActionHandler for the specified MauMau game.
     *
     * @param game The MauMau game instance.
     */
    ActionHandler(MauMau game) {
        this.game = game;
    }

    /**
     * Adds the specified player to the game.
     *
     * @param player The player to be added to the game.
     */
    void addPlayer(Player player) {
        game.getPlayerHandler().addPlayer(player);
    }

    /**
     * Starts the game.
     */
    void startGame() {
        auto.startGame();
    }

    /**
     * Transitions the game state to GAME_OVER.
     */
    void finishGame() {
        auto.finishGame();
    }

    /**
     * Transitions the game state to GAME_CANCELED.
     */
    void cancelGame() {
        auto.cancelGame();
    }

    /**
     * Handles the player's choice of a card in the current state.
     *
     * @param c The card chosen by the player.
     */
    void chooseCard(Card c) {
        auto.chooseCard(c);
    }

    /**
     * Handles the player's choice of a suit in the current state.
     *
     * @param s The suit chosen by the player.
     */
    void chooseSuit(Suit s) {
        auto.chooseSuit(s);
    }

    /**
     * Lets the player skip a round.
     **/
    void skip() {
        auto.skip();
    }

    /**
     * Handles the player saying "no 7" in the current state.
     */
    void no7() {
        auto.no7();
    }

    /**
     * Returns the MauMau game instance associated with this action handler.
     *
     * @return The MauMau game instance.
     */
    MauMau getGame() {
        return game;
    }

    /**
     * Returns the suit chosen by a player after playing a Jack card.
     *
     * @return The chosen suit.
     */
    Suit getChosenSuit() {
        return chosenSuit;
    }

    /**
     * Sets the suit chosen by a player after playing a Jack card.
     *
     * @param chosenSuit The suit chosen by the player.
     */
    void setChosenSuit(Suit chosenSuit) {
        this.chosenSuit = chosenSuit;
    }

    /**
     * Returns the number of number 7 cards played recently.
     *
     * @return The number of number 7 cards played recently.
     */
    int get7Counter() {
        return ctr7;
    }

    /**
     * Resets the counter of number 7 cards played to zero.
     */
    void reset7Counter() {
        ctr7 = 0;
    }

    /**
     * Increments the counter of number 7 cards played by one.
     */
    void increment7Counter() {
        ctr7++;
    }

    /**
     * Returns the current state of the game.
     */
    GameState getGameState() {
        return gameState;
    }

    void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Checks if a card can be played by the current player in the current state.
     *
     * @param c The card being played.
     * @return True if the card can be played, false otherwise.
     */
    boolean canPlay(Card c) {
        State<?> currentState = auto.getState();
        Card topCard = game.getCardHandler().top();

        if (currentState instanceof GamePlay) {
            State<?> innerState = ((GamePlay) currentState).getInnerState();

            if (innerState instanceof SuitChosen) {
                return c.suit() == getChosenSuit() && c.rank() != Rank.JACK;
            }
            else if (innerState instanceof SevenChosen) {
                return c.suit() == topCard.suit() || c.rank() == topCard.rank() || c.rank() == Rank.SEVEN;
            }
            else if (innerState instanceof Normal) {
                return c.suit() == topCard.suit() || c.rank() == topCard.rank() || c.rank() == Rank.JACK;
            }
        }

        return false;
    }
}
