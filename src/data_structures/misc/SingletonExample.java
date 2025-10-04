package data_structures.misc;

// Make a constructor private.
//Write a static method that has the return type object of this singleton class.
// Here, the concept of Lazy initialization is used to write this static method.
// A singleton class is can create at most one single object
//Used predominantly for maintaining single DB sessions or patterns where we need one object for an entire app lifecycle
public class SingletonExample {
    private String name;
    private String type;
    private static SingletonExample se;

    private SingletonExample() {
        name="Singleton name";
        type="Singleton type";
    }

    public static SingletonExample getInstance() {

        if (se == null) {
            SingletonExample.se = new SingletonExample();
        }
        return SingletonExample.se;

    }
}
