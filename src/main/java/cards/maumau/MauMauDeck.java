package cards.maumau;

import cards.Card;
import cards.Rank;
import cards.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of cards used in the Mau-Mau game.
 */
public class MauMauDeck {

    /**
     * The standard size for a deck, which is 32 (the number of cards in a standard deck).
     */
    public final static int STANDARD_DECK_SIZE = 32;

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
        if (numDecks < 1) {
            throw new IllegalArgumentException("Only positive numbers!");
        }

        List<Card> deck = new ArrayList<>(numDecks * STANDARD_DECK_SIZE);

        for (int i = 0; i < numDecks; i++) {
            for (Rank r : Rank.values()) {
                if (r.ordinal() > 4) {
                    for (Suit s : Suit.values()) {
                        Card card = new Card(r, s);
                        deck.add(card);
                    }
                }
            }
        }

        Collections.shuffle(deck);
        return deck;
    }
}
