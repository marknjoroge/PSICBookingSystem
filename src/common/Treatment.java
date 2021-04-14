package common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Treatment {

    private String name;
    private String room;
    private String type;
    private String date;
    private String time;
    private String physician;

    String path = System.getProperty("user.dir") + "/src/database/";

    Scanner sc = new Scanner(System.in);

    private String treatmentInfo;

    public Treatment() {

    }

    public void newTreatment() {
        name = qString("Patient's name: ");
        room = qString("\nRoom number: ");
        physician = ("\nPhysician: ");
        type = qString("\nType of treatment: ");

    }

    public String qString(String question) {
        System.out.println(question);
        String answer = sc.nextLine();
        return answer;
    }

    private void addToDB(String file) {

        try {
            path += file;

            FileWriter fr = new FileWriter(path, true);

            treatmentInfo = "\tType of treatment  :: " + type + "\tPatient ::  " 
                + name + "\tRoom Number   :: " + room  + "\tDate and time :: " 
                + date + "  " + time + "\tPhysician  :: " + physician ;
            fr.write(treatmentInfo + "\n");

            System.out.println("Successfully created user");
            fr.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
