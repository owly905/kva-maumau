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
        if (numDecks > 0) {
            for (int i = 0; i < numDecks; i++) {
                for (Rank r : Rank.values()) {
                    if (sortOut(r)) {
                        for (Suit s : Suit.values()) {
                            res.add(new Card(r, s));
                        }
                    }
                }
            }
        }
        else {
            return makeDeck(1);
        }
        Collections.shuffle(res);
        return res;
    }

    /**
     * retrun true wenn r <= 7
     *
     * @param r
     * @return
     */
    private static boolean sortOut(Rank r) {
        if (r.toString() == "2" || r.toString() == "3" || r.toString() == "4" || r.toString() == "5" || r.toString() == "6") {
            return false;
        }
        return true;
    }
}
