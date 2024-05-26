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
     * Constructs a PlayerHandler for the specified MauMau game.
     *
     * @param game The MauMau game instance.
     */
    PlayerHandler(MauMau game) {
        this.game = game;
    }

    /**
     * Initiates the next turn in the game.
     *
     * @param n The number of turns to proceed.
     */
    void nextTurn(int n) {
        if(getCurrentPlayer().getCards().size()==1){
            remember=getCurrentPlayer();
            localNextTurn(n);
            //TODO
        }
        else if(getCurrentPlayer().getCards().isEmpty()){
            remember=getCurrentPlayer();
            localNextTurn(n);
            //TODO
        }
        else{
            localNextTurn(n);
            //TODO
        }
    }

    /**
     * Handles a player calling "Mau".
     *
     * @param p The player calling "Mau".
     */
    void mau(Player p) {
        if(p==remember){
            //TODO
        }
    }

    /**
     * Handles a player calling "Mau-Mau".
     *
     * @param p The player calling "Mau-Mau".
     */
    void maumau(Player p) {
        if(p==remember){
            finishPlayer(p);
            if(players.size()==1){
                finishPlayer(getCurrentPlayer());
                game.getActionHandler().finishGame();
                //TODO
            }
        }
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
        players.add(player);
    }

    /**
     * Moves to the next player's turn in the game.
     *
     * @param n The number of turns to proceed.
     */
    private void localNextTurn(int n) {
        for(int i=0;i<n;i++){
            Player p = players.getFirst();
            players.remove(0);
            players.add(players.size(),p);
        }
    }

    /**
     * Finishes a player's participation in the game.
     *
     * @param p The player to finish.
     */
    private void finishPlayer(Player p) {
        for(int i =0;i<players.size();i++){
            if(players.get(i)==p){
                players.remove(i);
                ranking.add(p);
                break;
            }
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
}
