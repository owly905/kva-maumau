package cards;

/**
 * Represents a playing card with a rank and a suit.
 *
 * @param rank The rank of a playing card.
 * @param suit The suit of a playing card.
 */
public record Card<parameter>(Rank rank, Suit suit) implements Comparable<Card> {

    /**
     * Returns a string representation of the card.
     *
     * @return A string representing the card's rank followed by its suit.
     */
    @Override
    public String toString() {
        return rank.toString() + suit;
    }

    /**
     * Compares this card with another card for order.
     *
     * @param other The card to be compared.
     * @return A negative integer, zero, or a positive integer if this card is less than, equal to, or greater than the specified card.
     * @throws NullPointerException - if the specified card is null.
     */
    @Override
    public int compareTo(Card other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to a null object!");
        }

        int rankComparison = this.rank.compareTo(other.rank);
        if (rankComparison != 0) {
            return rankComparison;
        }
        else {
            return this.suit.compareTo(other.suit);
        }
    }
}
