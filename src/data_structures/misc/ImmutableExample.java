package data_structures.misc;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;

import java.util.HashMap;
/*
Declare the class as final so that it can’t be extended.
Make all fields private so that direct access is not allowed.
Don’t provide setter methods for variables.
Make all mutable fields final so that its value can be assigned only once.
Initialize all the fields via a constructor performing deep copy.
Perform cloning of objects in the getter methods to return a copy rather than returning the actual object reference.
* */

public final class ImmutableExample {


    private final int name;
    private final String type;

    public ImmutableExample(int name, String type) {
        this.name = name;
        this.type = type;
    }

    public int getName() {
        return name;
    }

    public String getType() {
        return type;
    }


    public static void main(String[] args) {

    }

}
