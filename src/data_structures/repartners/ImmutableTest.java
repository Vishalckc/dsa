package data_structures.repartners;

// create an immutable class
public class ImmutableTest {
    public static void main(String[] args) {
        Student s1 = new Student("Vishal");
        System.out.println(s1.getName());
    }
}

final class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
