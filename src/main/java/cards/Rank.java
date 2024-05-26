package cards;

/**
 * Represents the ranks of playing cards.
 */
public enum Rank {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    /**
     * Returns a string representation of the rank.
     *
     * @return A string representing the rank.
     */
    @Override
    public String toString() {
<<<<<<< HEAD
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
=======
        try {
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
        catch(Exception e){
            try {
                throw new Exception(e);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
>>>>>>> 37c802971aed3d192252022e6d33155f69b90d02
    }
}
