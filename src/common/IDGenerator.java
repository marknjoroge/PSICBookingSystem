package common;

public class IDGenerator {
    public String id;
    public IDGenerator(String fName) {
        id = fName + Math.random() * 10000;
    }

    public String toString() {
        return id;
    }
}
