package personnel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Physician {

    private int phoneNo;

    private String name;
    private String id;
    private String address;
    private String expertise;

    private String answer;

    private String personInfo = "";

    String path1 = System.getProperty("user.dir");
    String path = path1.substring(0, (path1.length() - 4)) + "\\database\\";

    Scanner sc = new Scanner(System.in);

    Physician() {

    }

    Physician(String name, String id, String address, int phoneNo) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phoneNo = phoneNo;

        addToDB("Physician.txt");
    }

    public void newPhysician() {
        if(name == null) {
            askDetails();
        }
    }

    public void askDetails() {
        name = qString("Physician's name: ");
        name = qString("Physician's id: ");
        address = qString("Physician's address: ");
        phoneNo = Integer.parseInt(qString("Physician's phone Number: "));
    }

    public String qString(String question) {
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }

    public void addToDB(String file) {

        if(name == null) {
            askDetails();
        }
        try {
            path += file;

            System.out.println(path);

            FileWriter fr = new FileWriter(path, true);

            personInfo = "name :: " + name + "\t" + "id :: " + id + "\t" + "address :: "+ address + "\t" + "expertise :: " + expertise + "\t";
            fr.write(personInfo + "\n");

            System.out.println("Successfully created user");
            fr.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}