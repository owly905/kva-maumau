package cards;

/**
 * Represents the ranks of playing cards.
 */
public enum Rank {
    /**
     * Card rank representing "2".
     */
    TWO("2"),
    /**
     * Card rank representing "3".
     */
    THREE("3"),
    /**
     * Card rank representing "4".
     */
    FOUR("4"),
    /**
     * Card rank representing "5".
     */
    FIVE("5"),
    /**
     * Card rank representing "6".
     */
    SIX("6"),
    /**
     * Card rank representing "7".
     */
    SEVEN("7"),
    /**
     * Card rank representing "8".
     */
    EIGHT("8"),
    /**
     * Card rank representing "9".
     */
    NINE("9"),
    /**
     * Card rank representing "10".
     */
    TEN("10"),
    /**
     * Card rank representing "J".
     */
    JACK("J"),
    /**
     * Card rank representing "Q".
     */
    QUEEN("Q"),
    /**
     * Card rank representing "K".
     */
    KING("K"),
    /**
     * Card rank representing "A".
     */
    ACE("A");

    /**
     * The symbol associated by the card rank.
     */
    private final String symbol;

    /**
     * Constructs a new enum instance with the given symbol.
     *
     * @param symbol the symbol that represents the card rank.
     */
    Rank(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns a string representation of the rank.
     *
     * @return A string representing the rank.
     */
    @Override
    public String toString() {
        return this.symbol;
    }
}
