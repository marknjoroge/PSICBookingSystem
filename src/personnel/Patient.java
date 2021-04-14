package personnel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Patient {

    final String dataBase = "Patient.txt";

    private int phoneNo;
    private String name;
    private String id;
    private String address;

    private String answer;

    private String personInfo = "";

    String path = System.getProperty("user.dir") + "/src/database/";
    // String path = path1.substring(0, (path1.length() - 4)) + "/database/";

    public Scanner sc = new Scanner(System.in);

    public Patient() {
        // actions();
    }

    public Patient(String name, String id, String address, int phoneNo) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phoneNo = phoneNo;

        addToDB(dataBase);
    }

    public void newPatient() {
        System.out.println(path);
        name = qString("Patient's name: ");
        id = qString("Patient's id: ");
        address = qString("Patient's address: ");
        phoneNo = Integer.parseInt(qString("Patient's phone Number: "));

        addToDB(dataBase);
    }

    private String qString(String question) {
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }

    private void addToDB(String file) {
        try {
            path += file;

            FileWriter fr = new FileWriter(path, true);

            personInfo = "\tName  :: " + name + "\tID ::  " 
                + id + "\tAddress   :: " + address  + "\t" 
                + "\tPhone :: " + phoneNo ;
            fr.write(personInfo + "\n");

            System.out.println("Successfully created user");
            fr.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean openDB(String file, String id) {
        path += file;
        try {
            sc = new Scanner(new File(path));
            String str = "";
            do {
                try {
                    str = sc.nextLine();
                } catch (Exception e) {
                    System.out.print("Empty database :(   .   ");
                }
                if (str.length() > id.length()) {
                    if (str.contains(id)) {
                        System.out.println(str);
                        return true;
                    }
                }
            } while (sc.hasNextLine());
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("User not found");
        return false;
    }
}
