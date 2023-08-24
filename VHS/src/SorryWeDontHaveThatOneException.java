public class SorryWeDontHaveThatOneException extends Exception {
    public SorryWeDontHaveThatOneException() {
        super("Car is not available to book");
    }
}
