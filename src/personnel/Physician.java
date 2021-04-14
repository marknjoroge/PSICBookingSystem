package personnel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import common.GeneralSchedule;
import common.IDGenerator;

public class Physician {

    private String phoneNo;
    private String fName;
    private String lName;
    private String id;
    private String address;
    private String expertise;
    private int daysAvailable;
    private String availablePeriod;
    private String daysToDB;

    private int[] availableDays = {0,0,0,0,0,0,0};
    private String[] availableHours = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

    private String answer;

    private String personInfo = "";

    public GeneralSchedule generalSchedule = new GeneralSchedule();

    String path = System.getProperty("user.dir") + "/src/database/";
    //uncomment the 2 lines below and comment the line above if you are using windows
    // String path1 = System.getProperty("user.dir");
    // String path = path1.substring(0, (path1.length() - 4)) + "\\database\\";

    public Scanner sc = new Scanner(System.in);

    public Physician() {
        // actions();
    }

    public void newPhysician() {
        System.out.println(path);
        fName = qString("Physician's first name: ");
        lName = qString("Physician's last name: ");
        address = qString("Physician's address: ");
        phoneNo = qString("Physician's phone Number: ");
        expertise = qString("Doctor's expertise");
        addPhysicianSchedule();
        IDGenerator idGenerator = new IDGenerator(lName);
        id = idGenerator.toString();

        addToDB("Physician.txt");
    }

    public void addPhysicianSchedule() {
        daysAvailable = Integer.parseInt(qString("How many days of the week will you be available?"));
        if (daysAvailable > 7) addPhysicianSchedule();
        System.out.println("Please select the days, pressing enter each time you select a day.");
        System.out.println(generalSchedule.printAllDays());;
        for(int i = 1; i <= daysAvailable; i++) {
            availableDays[i-1] = Integer.parseInt(qString("Day " + i + ": "));
            System.out.println("Available sessions(Enter letters to show when available, pressing enter each time you select a day. Press 'z' when done.");
            System.out.println(generalSchedule.printAllHours());
            String y = "";
            int j = 0;

            while (y != "z")
            {
                availableHours[j] = y;
                y = sc.next();
                System.out.println(y);
                j++;
            }
            // generalSchedule.addDay(x, availablePeriod, availableTo);
        }
        addScheduleToDB(availableDays);
    }

    public String qString(String question) {
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }

    private void addScheduleToDB(int [] x) {
        for(int i = 0; i < 7; i++) {
            daysToDB += x[i];
        }
        try {
            path += "PhysicianTime.txt";

            FileWriter fr = new FileWriter(path, true);

            fr.write(personInfo);

            System.out.println("Can't add to db");
            fr.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void addToDB(String file) {
        try {
            path += file;

            FileWriter fr = new FileWriter(path, true);

            personInfo = "\tName  :: " + fName + lName + "\tID ::  " 
                + id + "\tAddress   :: " + address + "\tExpertise :: "
                + expertise + "\t" + "\tPhone :: " + phoneNo;
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