public class OverWeightException extends Exception {
    public OverWeightException() {
        super("cannot add more load");
    }
}