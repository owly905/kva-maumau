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
    private State state;

    public State getState() {return state;}

    public void setState(State state) {
        State formerstate = this.state;
        this.state = state;
        if (state == Game_intialized) {
            state.setInnerState(state.getInnerState());
            gameState = GameState.GAME_INITIALIZED;
        } // init

        if (formerstate == Game_intialized) {
            state.setInnerState(state.getInnerState());
            gameState = GameState.PLAY;
        }// Übergang von init zu play

        if (formerstate == Game_Play && state == Choose_Suit) {
            state.setInnerState(state.getInnerState());
            gameState = GameState.CHOOSE_SUIT;
        } // Übergang von play zu choose suit

        if (formerstate == Choose_Suit) {
            state.setInnerState(state.getInnerState2());
            gameState = GameState.PLAY;
        } // Übergang von choose suit zu play

        if (state == Game_Canceled) {
            state.setInnerState(state.getInnerState());
            gameState = GameState.GAME_CANCELED;
        }

        if (state == Game_Over) {
            state.setInnerState(state.getInnerState());
            gameState = GameState.GAME_OVER;
        }
    }

    private GameState gameState;

    public abstract class State {
        private ActionHandler actionHandler;

        public ActionHandler getActionHandler() {return actionHandler;}

        private InnerState innerState;

        public InnerState getInnerState0() {
            return innerState;
        }

        public void setInnerState(InnerState innerstate) {
            this.innerState = innerstate;
        }

        void startGame() {innerState.startGame();}

        void finishGame() {innerState.finishGame();}

        void cancelGame() {innerState.cancelGame();}

        void chooseCard(Card c) {innerState.chooseCard(c);}

        void chooseSuit(Suit suit) {innerState.chooseSuit(suit);}

        void skip() {innerState.skip();}

        void no7() {innerState.no7();}

        public InnerState getInnerState() {return null;}

        public InnerState getInnerState2() {return null;}

        public InnerState getInnerState3() {return null;}

        State(ActionHandler a) {
            actionHandler = a;
        }
    }

    public final State Game_intialized = new State(this) {
        final Initialzed initialzed = new Initialzed(getActionHandler());

        @Override
        public InnerState getInnerState() {return initialzed;}
    };

    public final State Game_Play = new State(this) {
        final Normal normal = new Normal(getActionHandler());
        final SevenChosen sevenChosen = new SevenChosen(getActionHandler());
        final SuitChosen suitChosen = new SuitChosen(getActionHandler());

        @Override
        public InnerState getInnerState() {return normal;}

        @Override
        public InnerState getInnerState2() {return suitChosen;}

        @Override
        public InnerState getInnerState3() {return sevenChosen;}
    };

    public final State Game_Over = new State(this) {
        final GameFinished gameFinished = new GameFinished(getActionHandler());

        @Override
        public InnerState getInnerState() {return gameFinished;}
    };

    public final State Game_Canceled = new State(this) {
        private final GameCanceled gameCanceled = new GameCanceled(getActionHandler());

        @Override
        public InnerState getInnerState() {return gameCanceled;}
    };

    public final State Choose_Suit = new State(this) {
        private final JackChosen jackChosen = new JackChosen(getActionHandler());

        @Override
        public InnerState getInnerState() {return jackChosen;}
    };

    /**
     * Keine Ahnung nicht löschen
     *
     * @return
     */
    public State getGame_intialized() {
        return Game_intialized;
    }

    public State getGame_Play() {
        return Game_Play;
    }

    public State getGame_Over() {
        return Game_Over;
    }

    public State getGame_Canceled() {
        return Game_Canceled;
    }

    public State getChoose_Suit() {
        return Choose_Suit;
    }

    /**
     * Constructs an ActionHandler for the specified MauMau game.
     *
     * @param game The MauMau game instance.
     */
    ActionHandler(MauMau game) {
        this.game = game;
        setState(getGame_intialized());
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
        setState(getGame_intialized());
        state.startGame();
    }

    /**
     * Transitions the game state to GAME_OVER.
     */
    void finishGame() {
        state.finishGame();
    }

    /**
     * Transitions the game state to GAME_CANCELED.
     */
    void cancelGame() {
        state.cancelGame();
    }

    /**
     * Handles the player's choice of a card in the current state.
     *
     * @param c The card chosen by the player.
     */
    void chooseCard(Card c) {
        state.chooseCard(c);
        /*
        if(game.getPlayerHandler().getCurrentPlayer().canPlay(c)){
            game.getPlayerHandler().getCurrentPlayer().playCard(c);
            setChosenSuit(null);
        }
        else{

        }
        */

    }

    /**
     * Handles the player's choice of a suit in the current state.
     *
     * @param suit The suit chosen by the player.
     */
    void chooseSuit(Suit suit) {
        state.chooseSuit(suit);
    }

    /**
     * Lets the player skip a round.
     **/
    void skip() {
        state.skip();
        /*
        game.getCardHandler().drawCard();
        game.getPlayerHandler().nextTurn(1);

         */
    }

    /**
     * Handles the player saying "no 7" in the current state.
     */
    void no7() {
        state.no7();
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

    /**
     * Checks if a card can be played by the current player in the current state.
     *
     * @param c The card being played.
     * @return True if the card can be played, false otherwise.
     */
    boolean canPlay(Card c) {
        State currentState = getState();
        Card topCard = game.getCardHandler().top();

        if (currentState == this.getGame_Play()) {
            InnerState innerState = currentState.innerState;

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