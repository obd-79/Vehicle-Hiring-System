public class NoCancellationYouMustPayException extends Exception {
    public NoCancellationYouMustPayException() {
        super("You cannot cancel booking, you must pay");
    }
}