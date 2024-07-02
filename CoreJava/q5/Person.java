package CoreJava.q5;

public class Person {

    // Private variables to restrict direct access
    private String name;
    private int age;

    // Public constructor to initialize the Person object
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Public getter for name
    public String getName() {
        return name;
    }

    // Public setter for name with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name");
        }
    }

    // Public getter for age
    public int getAge() {
        return age;
    }

    // Public setter for age with validation
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Invalid age");
        }
    }

    // Method to display person details
    public void displayPersonInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public static void main(String[] args) {
        // Create a new Person object
        Person person = new Person("John Doe", 30);

        // Display initial person information
        person.displayPersonInfo();

        // Modify name and age
        person.setName("Jane Doe");
        person.setAge(25);

        // Display updated person information
        person.displayPersonInfo();

        // Attempt to set invalid values
        person.setName("");
        person.setAge(-5);
    }
}
