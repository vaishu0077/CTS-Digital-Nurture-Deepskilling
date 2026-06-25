class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("Logger instance created.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

public class SingletonPatternExample {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("First message");
        logger2.log("Second message");

        System.out.println("Are both objects same? " + (logger1 == logger2));
        System.out.println("Logger 1 hashCode: " + logger1.hashCode());
        System.out.println("Logger 2 hashCode: " + logger2.hashCode());
    }
}