package cards.maumau.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles players in a MauMau game.
 */
class PlayerHandler {
    private final MauMau game;
    private final List<Player> players = new LinkedList<>();
    private final List<Player> ranking = new ArrayList<>();
    private Player remember;

    /**
     * State pattern.
     */
    private PlayerState waitForNextTurnState = new WaitForNextTurnState();                                                   ;
    private PlayerState waitForMauState = new WaitForMauState();
    private PlayerState waitForMauMauState = new WaitForMauMauState();
    private PlayerState finishedState = new FinishedState();
    private PlayerState currentState;

    /**
     * Constructs a PlayerHandler for the specified MauMau game.
     *
     * @param game The MauMau game instance.
     */
    PlayerHandler(MauMau game) {
        this.game = game;
        this.currentState = waitForNextTurnState;
    }

    /**
     * Initiates the next turn in the game.
     *
     * @param n The number of turns to proceed.
     */
    void nextTurn(int n) {
        this.currentState.nextTurn(n);
    }

    /**
     * Handles a player calling "Mau".
     *
     * @param p The player calling "Mau".
     */
    void mau(Player p) {
        this.currentState.mau(p);
    }

    /**
     * Handles a player calling "Mau-Mau".
     *
     * @param p The player calling "Mau-Mau".
     */
    void maumau(Player p) {
        this.currentState.maumau(p);
    }

    /**
     * Returns the list of players participating in the game.
     *
     * @return The list of players.
     */
    List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the ranking of players based on the order they finished the game.
     *
     * @return The ranking of players.
     */
    List<Player> getRanking() {
        return ranking;
    }

    /**
     * Adds a player to the game.
     *
     * @param player The player to add.
     * @throws IllegalArgumentException if a player with the same name already exists.
     */
    void addPlayer(Player player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
        }
    }

    /**
     * Moves to the next player's turn in the game.
     *
     * @param n The number of turns to proceed.
     */
    private void localNextTurn(int n) {
        for (int index = 0; index < n; index++) {
            this.players.add(this.players.removeFirst());
        }
    }

    /**
     * Finishes a player's participation in the game.
     *
     * @param p The player to finish.
     */
    private void finishPlayer(Player p) {
        if (this.players.contains(p) && !this.ranking.contains(p)) {
            this.ranking.add(p);
            this.players.remove(p);
        }
    }

    /**
     * Returns the current player whose turn it is.
     *
     * @return The current player.
     */
    Player getCurrentPlayer() {
        return players.isEmpty() ? null : players.getFirst();
    }

    /**
     * This abstract inner class defines the player state.
     * It contains all necessary methods to perform as a state pattern.
     */
    private abstract class PlayerState {
        /**
         * This method will be used to change the current player to the next one.
         *
         * @param n as the number of turns as an Integer.
         */
        public abstract void nextTurn(int n);

        /**
         * This method will be called whenever the given parameter p call the mau signal.
         *
         * @param p as the player which calls the mau signal as a Player object.
         */
        public abstract void mau(Player p);

        /**
         * This method will be called whenever the given parameter p call the mau mau signal.
         *
         * @param p as the player which calls the mau mau signal as a Player object.
         */
        public abstract void maumau(Player p);
    }

    /**
     * This inner class defines the wait for next turn state.
     */
    private class WaitForNextTurnState extends PlayerState {
        /**
         * This method will be used to change the current player to the next one.
         *
         * @param n as the number of turns as an Integer.
         */
        @Override
        public void nextTurn(int n) {
            if (getCurrentPlayer().getCards().size() == 1) {
                remember = getCurrentPlayer();
                currentState = waitForMauState;
            } else if (getCurrentPlayer().getCards().isEmpty()) {
                remember = getCurrentPlayer();
                currentState = waitForMauMauState;
            }
            localNextTurn(n);
        }

        /**
         * This method will be called whenever the given parameter p call the mau signal.
         *
         * @param p as the player which calls the mau signal as a Player object.
         */
        @Override
        public void mau(Player p) {
            /* no action in this state */
        }

        /**
         * This method will be called whenever the given parameter p call the mau mau signal.
         *
         * @param p as the player which calls the mau mau signal as a Player object.
         */
        @Override
        public void maumau(Player p) {
            /* no action in this state */
        }
    }

    /**
     * This inner class defines the wait for mau state.
     */
    private class WaitForMauState extends PlayerState {
        /**
         * This method will be used to change the current player to the next one.
         *
         * @param n as the number of turns as an Integer.
         */
        @Override
        public void nextTurn(int n) {
            remember.drawCards(1);
            if (getCurrentPlayer().getCards().isEmpty()) {
                remember = getCurrentPlayer();
                currentState = waitForNextTurnState;
            }
            else if (getCurrentPlayer().getCards().size() == 1) {
                remember = getCurrentPlayer();
                currentState = waitForMauState;
            }
            else {
                currentState = waitForNextTurnState;
            }
            localNextTurn(n);
        }

        /**
         * This method will be called whenever the given parameter p call the mau signal.
         *
         * @param p as the player which calls the mau signal as a Player object.
         */
        @Override
        public void mau(Player p) {
            if (p == remember) {
                currentState = waitForNextTurnState;
            }
        }

        /**
         * This method will be called whenever the given parameter p call the mau mau signal.
         *
         * @param p as the player which calls the mau mau signal as a Player object.
         */
        @Override
        public void maumau(Player p) {
            /* no action in this state */
        }
    }

    /**
     * This inner class defines the wait for mau mau state.
     */
    private class WaitForMauMauState extends PlayerState {
        /**
         * This method will be used to change the current player to the next one.
         *
         * @param n as the number of turns as an Integer.
         */
        @Override
        public void nextTurn(int n) {
            remember.drawCards(1);
            if (getCurrentPlayer().getCards().size() == 1) {
                remember = getCurrentPlayer();
                currentState = waitForMauState;
            }
            else {
                currentState = waitForNextTurnState;
            }
            localNextTurn(n);
        }

        /**
         * This method will be called whenever the given parameter p call the mau signal.
         *
         * @param p as the player which calls the mau signal as a Player object.
         */
        @Override
        public void mau(Player p) {
            /* no action in this state */
        }

        /**
         * This method will be called whenever the given parameter p call the mau mau signal.
         *
         * @param p as the player which calls the mau mau signal as a Player object.
         */
        @Override
        public void maumau(Player p) {
            if (p == remember) {
                finishPlayer(p);
                if (players.size() == 1) {
                    finishPlayer(getCurrentPlayer());
                    game.getActionHandler().finishGame();
                    currentState = finishedState;
                } else {
                    currentState = waitForNextTurnState;
                }
            }
        }
    }

    /**
     * This inner class defines the finished state.
     */
    private class FinishedState extends PlayerState {
        /**
         * This method will be used to change the current player to the next one.
         *
         * @param n as the number of turns as an Integer.
         */
        @Override
        public void nextTurn(int n) {
            /* no action in this state */
        }

        /**
         * This method will be called whenever the given parameter p call the mau signal.
         *
         * @param p as the player which calls the mau signal as a Player object.
         */
        @Override
        public void mau(Player p) {
            /* no action in this state */
        }

        /**
         * This method will be called whenever the given parameter p call the mau mau signal.
         *
         * @param p as the player which calls the mau mau signal as a Player object.
         */
        @Override
        public void maumau(Player p) {
            /* no action in this state */
        }
    }
}
