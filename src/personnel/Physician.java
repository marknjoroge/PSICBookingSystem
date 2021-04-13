package personnel;

import java.util.Scanner;

public class Physician {

    private String name;
    private String id;
    private String address;
    private int phoneNo;
    private String expertise;

    private String answer;

    Scanner sc = new Scanner(System.in);

    Physician() {

    }

    Physician(String name, String id, String address, int phoneNo) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public void newPhysician() {
        if(name == null) {
            askDetails();
        }
    }

    public void askDetails() {
        qString("Physician's name");
        
    }

    public String qString(String question) {
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }
}
