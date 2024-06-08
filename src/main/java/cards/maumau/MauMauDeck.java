package cards.maumau;

import cards.Card;
import cards.Rank;
import cards.Suit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of cards used in the Mau-Mau game.
 */
public class MauMauDeck {
    /**
     * Private constructor to prevent instantiation of this class.
     */
    private MauMauDeck() { /* do nothing */ }

    /**
     * Generates a deck of Mau-Mau cards and shuffles it.
     *
     * @param numDecks Number of decks to be included in the game.
     * @return A list containing the generated deck of cards.
     */
    public static List<Card> makeDeck(int numDecks) {
        List<Card> res = new ArrayList<>();
        for(int deck =0; deck<numDecks;deck++){
            for(Rank rank:Rank.values() ){
                if(rank.ordinal()>4){
                    for(Suit suit : Suit.values()){
                        res.add(new Card(rank,suit));
                    }
                }
            }
        }
        Collections.shuffle(res);
        return res;
    }
}
