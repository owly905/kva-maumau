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

    /**
     * State pattern.
     */
    private final GamePlayState initializedState = new InitializedState();
    private final GamePlayState normalState = new NormalState();
    private final GamePlayState sevenChosenState = new SevenChosenState();
    private final GamePlayState suitChosenState = new SuitChosenState();
    private final GamePlayState jackChosenState = new JackChosenState();
    private final GamePlayState gameFinishedState = new GameFinishedState();
    private final GamePlayState gameCanceledState = new GameCanceledState();
    private GamePlayState gamePlayState;

    /**
     * Constructs an ActionHandler for the specified MauMau game.
     *
     * @param game The MauMau game instance.
     */
    ActionHandler(MauMau game) {
        this.game = game;
        this.gamePlayState = initializedState;
    }

    /**
     * Adds the specified player to the game.
     *
     * @param player The player to be added to the game.
     */
    void addPlayer(Player player) {
        this.gamePlayState.addPlayer(player);
    }

    /**
     * Starts the game.
     */
    void startGame() {
        this.gamePlayState.startGame();
    }

    /**
     * Transitions the game state to GAME_OVER.
     */
    void finishGame() {
        this.gamePlayState.finishGame();
    }

    /**
     * Transitions the game state to GAME_CANCELED.
     */
    void cancelGame() {
        this.gamePlayState.cancelGame();
    }

    /**
     * Handles the player's choice of a card in the current state.
     *
     * @param c The card chosen by the player.
     */
    void chooseCard(Card c) {
        this.gamePlayState.chooseCard(c);
    }

    /**
     * Handles the player's choice of a suit in the current state.
     *
     * @param suit The suit chosen by the player.
     */
    void chooseSuit(Suit suit) {
        this.gamePlayState.chooseSuit(suit);
    }

    /**
     * Lets the player skip a round.
     **/
    void skip() {
        this.gamePlayState.skip();
    }

    /**
     * Handles the player saying "no 7" in the current state.
     */
    void no7() {
        this.gamePlayState.no7();
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
        return switch (gamePlayState) {
            case InitializedState s -> GameState.GAME_INITIALIZED;
            case JackChosenState s -> GameState.CHOOSE_SUIT;
            case GameFinishedState s -> GameState.GAME_OVER;
            case GameCanceledState s -> GameState.GAME_CANCELED;
            default -> GameState.PLAY;
        };
    }

    /**
     * Checks if a card can be played by the current player in the current state.
     *
     * @param c The card being played.
     * @return True if the card can be played, false otherwise.
     */
    boolean canPlay(Card c) {
        if (gamePlayState == suitChosenState) {
            return c.suit() == getChosenSuit() && c.rank() != Rank.JACK;
        } else if (gamePlayState == sevenChosenState) {
            return c.rank() == Rank.SEVEN;
        } else if (gamePlayState == normalState) {
            Card top = game.getCardHandler().getDiscardPile().getFirst();
            return c.rank() == top.rank() || c.suit() == top.suit() || c.rank() == Rank.JACK;
        }
        return false;
    }

    /**
     * This abstract inner class defines the game state.
     * It contains all necessary methods to perform as a state pattern.
     */
    private abstract class GamePlayState {
        /**
         * This method will be used to add the given player parameter to the game.
         *
         * @param player as the new player for a game as a Player object.
         */
        public abstract void addPlayer(Player player);

        /**
         * This method will be used to start the game.
         */
        public abstract void startGame();

        /**
         * This method will be used to choose the given c parameter.
         *
         * @param c as the chosen card as a Card object.
         */
        public abstract void chooseCard(Card c);

        /**
         * This method will be used to skip the current turn.
         */
        public abstract void skip();

        /**
         * This method will be used to choose the given suit parameter.
         *
         * @param suit as the new suit as a Suit enumeration.
         */
        public abstract void chooseSuit(Suit suit);

        /**
         * This method will be used whenever a card with the number seven was played.
         */
        public abstract void no7();

        /**
         * This method will be used to finish the game.
         */
        public abstract void finishGame();

        /**
         * This method will be used to cancel the game.
         */
        public abstract void cancelGame();
    }

    private class InitializedState extends GamePlayState {
        /**
         * This method will be used to add the given player parameter to the game.
         *
         * @param player as the new player for a game as a Player object.
         */
        @Override
        public void addPlayer(Player player) {
            game.getPlayerHandler().addPlayer(player);
        }

        /**
         * This method will be used to start the game.
         */
        @Override
        public void startGame() {
            gamePlayState = normalState;
            game.getCardHandler().dealCards();
        }

        /**
         * This method will be used to choose the given c parameter.
         *
         * @param c as the chosen card as a Card object.
         */
        @Override
        public void chooseCard(Card c) {
            /* no action in this state */
        }

        /**
         * This method will be used to skip the current turn.
         */
        @Override
        public void skip() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given suit parameter.
         *
         * @param suit as the new suit as a Suit enumeration.
         */
        @Override
        public void chooseSuit(Suit suit) {
            /* no action in this state */
        }

        /**
         * This method will be used whenever a player can't play a card with the number seven.
         */
        @Override
        public void no7() {
            /* no action in this state */
        }

        /**
         * This method will be used to finish the game.
         */
        @Override
        public void finishGame() {
            gamePlayState = gameFinishedState;
        }

        /**
         * This method will be used to cancel the game.
         */
        @Override
        public void cancelGame() {
            gamePlayState = gameCanceledState;
        }
    }

    /**
     * This inner class defines the normal state.
     */
    private class NormalState extends GamePlayState {
        /**
         * This method will be used to add the given player parameter to the game.
         *
         * @param player as the new player for a game as a Player object.
         */
        @Override
        public void addPlayer(Player player) {
            /* no action in this state */
        }

        /**
         * This method will be used to start the game.
         */
        @Override
        public void startGame() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given c parameter.
         *
         * @param c as the chosen card as a Card object.
         */
        @Override
        public void chooseCard(Card c) {
            if (canPlay(c)) {
                game.getPlayerHandler().getCurrentPlayer().playCard(c);
                if (c.rank() == Rank.JACK) {
                    gamePlayState = jackChosenState;
                } else if (c.rank() == Rank.SEVEN) {
                    increment7Counter();
                    game.getPlayerHandler().nextTurn(1);
                    gamePlayState = sevenChosenState;
                } else if (c.rank() == Rank.EIGHT) {
                    game.getPlayerHandler().nextTurn(2);
                } else {
                    game.getPlayerHandler().nextTurn(1);
                }
            }
        }

        /**
         * This method will be used to skip the current turn.
         */
        @Override
        public void skip() {
            game.getPlayerHandler().getCurrentPlayer().drawCards(1);
            game.getPlayerHandler().nextTurn(1);
        }

        /**
         * This method will be used to choose the given suit parameter.
         *
         * @param suit as the new suit as a Suit enumeration.
         */
        @Override
        public void chooseSuit(Suit suit) {
            /* no action in this state */
        }

        /**
         * This method will be used whenever a player can't play a card with the number seven.
         */
        @Override
        public void no7() {
            /* no action in this state */
        }

        /**
         * This method will be used to finish the game.
         */
        @Override
        public void finishGame() {
            gamePlayState = gameFinishedState;
        }

        /**
         * This method will be used to cancel the game.
         */
        @Override
        public void cancelGame() {
            gamePlayState = gameCanceledState;
        }
    }

    private class SevenChosenState extends GamePlayState {
        /**
         * This method will be used to add the given player parameter to the game.
         *
         * @param player as the new player for a game as a Player object.
         */
        @Override
        public void addPlayer(Player player) {
            /* no action in this state */
        }

        /**
         * This method will be used to start the game.
         */
        @Override
        public void startGame() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given c parameter.
         *
         * @param c as the chosen card as a Card object.
         */
        @Override
        public void chooseCard(Card c) {
            if (canPlay(c)) {
                game.getPlayerHandler().getCurrentPlayer().playCard(c);
                increment7Counter();
                game.getPlayerHandler().nextTurn(1);
            }
        }

        /**
         * This method will be used to skip the current turn.
         */
        @Override
        public void skip() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given suit parameter.
         *
         * @param suit as the new suit as a Suit enumeration.
         */
        @Override
        public void chooseSuit(Suit suit) {
            /* no action in this state */
        }

        /**
         * This method will be used whenever a player can't play a card with the number seven.
         */
        @Override
        public void no7() {
            if (game.getCardHandler().getDiscardPile().size() + game.getCardHandler().getDrawPile().size() - 1 < 2 * get7Counter()) {
                gamePlayState = gameCanceledState;
            } else {
                game.getPlayerHandler().getCurrentPlayer().drawCards(2 * get7Counter());
                reset7Counter();
                gamePlayState = normalState;
            }
        }

        /**
         * This method will be used to finish the game.
         */
        @Override
        public void finishGame() {
            gamePlayState = gameFinishedState;
        }

        /**
         * This method will be used to cancel the game.
         */
        @Override
        public void cancelGame() {
            gamePlayState = gameCanceledState;
        }
    }

    /**
     * This inner class defines the suit chosen state.
     */
    private class SuitChosenState extends GamePlayState {
        /**
         * This method will be used to add the given player parameter to the game.
         *
         * @param player as the new player for a game as a Player object.
         */
        @Override
        public void addPlayer(Player player) {
            /* no action in this state */
        }

        /**
         * This method will be used to start the game.
         */
        @Override
        public void startGame() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given c parameter.
         *
         * @param c as the chosen card as a Card object.
         */
        @Override
        public void chooseCard(Card c) {
            if (canPlay(c)) {
                setChosenSuit(null);
                game.getPlayerHandler().getCurrentPlayer().playCard(c);
                if (c.rank() == Rank.SEVEN) {
                    increment7Counter();
                    game.getPlayerHandler().nextTurn(1);
                    gamePlayState = sevenChosenState;
                } else if (c.rank() == Rank.EIGHT) {
                    game.getPlayerHandler().nextTurn(2);
                    gamePlayState = normalState;
                } else {
                    game.getPlayerHandler().nextTurn(1);
                    gamePlayState = normalState;
                }
            }
        }

        /**
         * This method will be used to skip the current turn.
         */
        @Override
        public void skip() {
            game.getPlayerHandler().getCurrentPlayer().drawCards(1);
            game.getPlayerHandler().nextTurn(1);
        }

        /**
         * This method will be used to choose the given suit parameter.
         *
         * @param suit as the new suit as a Suit enumeration.
         */
        @Override
        public void chooseSuit(Suit suit) {
            /* no action in this state */
        }

        /**
         * This method will be used whenever a card with the number seven was played.
         */
        @Override
        public void no7() {
            /* no action in this state */
        }

        /**
         * This method will be used to finish the game.
         */
        @Override
        public void finishGame() {
            gamePlayState = gameFinishedState;
        }

        /**
         * This method will be used to cancel the game.
         */
        @Override
        public void cancelGame() {
            gamePlayState = gameCanceledState;
        }
    }

    /**
     * This inner class defines the jack chosen state.
     */
    private class JackChosenState extends GamePlayState {
        /**
         * This method will be used to add the given player parameter to the game.
         *
         * @param player as the new player for a game as a Player object.
         */
        @Override
        public void addPlayer(Player player) {
            /* no action in this state */
        }

        /**
         * This method will be used to start the game.
         */
        @Override
        public void startGame() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given c parameter.
         *
         * @param c as the chosen card as a Card object.
         */
        @Override
        public void chooseCard(Card c) {
            /* no action in this state */
        }

        /**
         * This method will be used to skip the current turn.
         */
        @Override
        public void skip() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given suit parameter.
         *
         * @param suit as the new suit as a Suit enumeration.
         */
        @Override
        public void chooseSuit(Suit suit) {
            setChosenSuit(suit);
            game.getPlayerHandler().nextTurn(1);
            gamePlayState = suitChosenState;
        }

        /**
         * This method will be used whenever a card with the number seven was played.
         */
        @Override
        public void no7() {
            /* no action in this state */
        }

        /**
         * This method will be used to finish the game.
         */
        @Override
        public void finishGame() {
            gamePlayState = gameFinishedState;
        }

        /**
         * This method will be used to cancel the game.
         */
        @Override
        public void cancelGame() {
            gamePlayState = gameCanceledState;
        }
    }

    private class GameFinishedState extends GamePlayState {
        /**
         * This method will be used to add the given player parameter to the game.
         *
         * @param player as the new player for a game as a Player object.
         */
        @Override
        public void addPlayer(Player player) {
            /* no action in this state */
        }

        /**
         * This method will be used to start the game.
         */
        @Override
        public void startGame() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given c parameter.
         *
         * @param c as the chosen card as a Card object.
         */
        @Override
        public void chooseCard(Card c) {
            /* no action in this state */
        }

        /**
         * This method will be used to skip the current turn.
         */
        @Override
        public void skip() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given suit parameter.
         *
         * @param suit as the new suit as a Suit enumeration.
         */
        @Override
        public void chooseSuit(Suit suit) {
            /* no action in this state */
        }

        /**
         * This method will be used whenever a card with the number seven was played.
         */
        @Override
        public void no7() {
            /* no action in this state */
        }

        /**
         * This method will be used to finish the game.
         */
        @Override
        public void finishGame() {

        }

        /**
         * This method will be used to cancel the game.
         */
        @Override
        public void cancelGame() {
            /* no action in this state */
        }
    }

    private class GameCanceledState extends GamePlayState {
        /**
         * This method will be used to add the given player parameter to the game.
         *
         * @param player as the new player for a game as a Player object.
         */
        @Override
        public void addPlayer(Player player) {
            /* no action in this state */
        }

        /**
         * This method will be used to start the game.
         */
        @Override
        public void startGame() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given c parameter.
         *
         * @param c as the chosen card as a Card object.
         */
        @Override
        public void chooseCard(Card c) {
            /* no action in this state */
        }

        /**
         * This method will be used to skip the current turn.
         */
        @Override
        public void skip() {
            /* no action in this state */
        }

        /**
         * This method will be used to choose the given suit parameter.
         *
         * @param suit as the new suit as a Suit enumeration.
         */
        @Override
        public void chooseSuit(Suit suit) {
            /* no action in this state */
        }

        /**
         * This method will be used whenever a card with the number seven was played.
         */
        @Override
        public void no7() {
            /* no action in this state */
        }

        /**
         * This method will be used to finish the game.
         */
        @Override
        public void finishGame() {
            /* no action in this state */
        }

        /**
         * This method will be used to cancel the game.
         */
        @Override
        public void cancelGame() {

        }
    }
}
