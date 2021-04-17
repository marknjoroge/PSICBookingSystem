import personnel.*;
import common.*;
import java.util.Scanner;

public class Main {

    static Patient patient = new Patient();
    static Physician physician = new Physician();
    static Room room = new Room();
    static Scanner quest = new Scanner(System.in);
    static int res;
    static String id;

    public static void main(String[] args) {
        Driver driver = new Driver();

        driver.init();
    }
}
