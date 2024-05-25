package cards;

/**
 * Represents the suits of playing cards.
 */
public enum Suit {
    /**
     * Represents the Hearts suit.
     */
    HEARTS("♥︎"),
    /**
     * Represents the Diamonds suit.
     */
    DIAMONDS("♦︎"),
    /**
     * Represents the Clubs suit.
     */
    CLUBS("♣︎"),
    /**
     * Represents the Spades suit.
     */
    SPADES("♠︎");

    /**
     * The symbol associated by the card suit.
     */
    private String symbol;

    /**
     * Constructs a new enum instance with the given symbol.
     *
     * @param symbol the symbol that represents the card suit.
     */
    Suit(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns a string representation of the suit.
     *
     * @return A string representing the suit.
     */
    @Override
    public String toString() {
        return this.symbol;
    }
}
