package personnel;

import java.util.Scanner;

public class Driver {

    Physician physician = new Physician();

    private String response;

    Scanner scanner = new Scanner(System.in);

    public void init() {
        System.out.println("\t Welocome to PSIC. We care about you."
        +" \nTo select an option enter the number preceeding it.\n");
        response = qString("Please select an option\n1. Book appointment.\n2. Exit");
        
        switch(response) {
            case "1":
                physician.bookAppointment();
                break;
            case "2":
                System.out.println("Thank you for choosing PSIC.");
                System.exit(0);
            default: 
                System.out.println("Please enter a valid option\n");
                init();
        }
    }


    public String qString(String question) {
        System.out.println(question);
        response = scanner.nextLine();
        return response;
    }
}
