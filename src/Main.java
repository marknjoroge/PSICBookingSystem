import personnel.*;
import java.util.Scanner;

public class Main {
    static Physician physician = new Physician();
    static Scanner quest = new Scanner(System.in);
    static int res;
    public static void main(String[] args) {


        System.out.println("Welocome. PSIC cares abut you. Please select an option to proceed.\n"
        + "1.Add physician to system."
        + "2. Find physician.");
        
        res = quest.nextInt();

        physician.actions(res);
    }
}
