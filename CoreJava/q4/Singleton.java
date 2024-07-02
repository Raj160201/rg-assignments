package CoreJava.q4;

public class Singleton {

    // The single instance of the class
    private static Singleton instance;

    // Private constructor to prevent instantiation from other classes
    private Singleton() {
        // Initialization code here
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        // Create the instance if it does not exist yet
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }

    public static void main(String[] args) {
        // Get the single instance of Singleton
        Singleton singleton = Singleton.getInstance();
        // Call the example method
        singleton.showMessage();
    }
}
