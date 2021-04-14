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
    static GeneralSchedule generalSchedule = new GeneralSchedule();
    static Driver driver = new Driver();

    public static void main(String[] args) {
        

        driver.init();
    //     System.out.println("Welocome. PSIC cares abut you. Please select an option to proceed.\n"
    //         + "1. Add physician to the system.\n"
    //         + "2. Find physician.\n"
    //         + "3. Add patient to the system.\n"
    //         + "4. Find patient.\n"
    //         + "5. Book Doctor's appointment.\n"
    //         + "6. Add room\n"
    //     );
        
    //     res = quest.nextInt();

    //     actions(res);
    // }
    // public static void actions(int ans) {
    //     switch(ans) {
    //         case 1:
    //             physician.newPhysician();
    //             break;
    //         case 2:
    //             id = physician.qString("Enter doctor's ID");
    //             physician.openDB("Physician.txt", id);
    //         case 3:
    //             patient.newPatient();
    //             break;
    //         case 4:
    //             id = physician.qString("Enter Patient's ID");
    //             break;
    //         case 5:
    //             TODO: A lot of stuff
    //         case 6:
    //             room.newRoom();
    //             break;
    //         case 7:
    //             System.out.println(generalSchedule.printAllDays());
    //     }
    }
}
