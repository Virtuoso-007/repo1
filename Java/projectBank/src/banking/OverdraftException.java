package banking;
public class OverdraftException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;
    private double deficit;

    public OverdraftException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }

    public double getDeficit() {
        return deficit;
    }
}



