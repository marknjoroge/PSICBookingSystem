package common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Room {

    public String purpose;
    private String roomNo;
    private String roomInfo;
    private String dataBase;

    private String answer;

    Scanner sc = new Scanner(System.in);

    String path = System.getProperty("user.dir") + "/src/database/";

    public Room() {

    }

    public void newRoom() {
        System.out.println(path);
        roomNo = qString("Room Number: ");
        purpose = qString("Room's purpose: ");
        roomInfo = qString("Room : ");

        addToDB();
    }

    public String qString(String question) {
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }

    public void addToDB() {
        try {
            path += dataBase;

            FileWriter fr = new FileWriter(path, true);

            roomInfo = "RoomNo  :: " + roomNo + "\tPurpose ::  " 
                + roomInfo;
            fr.write(roomInfo + "\n");

            System.out.println("Successfully created user");
            fr.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
