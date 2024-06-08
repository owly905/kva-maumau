package cards;

/**
 * Represents the ranks of playing cards.
 */
public enum Rank {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");
    private final String symbol;
    Rank(String symbol){
        this.symbol=symbol;
    }

    @Override
    public String toString(){
        return this.symbol;
    }
    /**
     * Returns a string representation of the rank.
     *
     * @return A string representing the rank.
     */

    /**
    @Override
    public String toString() {
        return switch (this) {
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case TEN -> "10";
            case JACK -> "J";
            case QUEEN -> "Q";
            case KING -> "K";
            case ACE -> "A";
        };
    }
    */
}
