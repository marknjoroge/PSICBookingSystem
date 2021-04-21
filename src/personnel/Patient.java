package personnel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends Client{

//    final String dataBase = "Patients.txt";

    private String phoneNo;
    private String name;
    private String id;
    private String address;
    private ArrayList<Session> appointments = new ArrayList<>();

    private String answer;

    private String personInfo = "";

    String path = System.getProperty("user.dir") + "/database/";
    // String path = path1.substring(0, (path1.length() - 4)) + "/database/";

    public Scanner sc = new Scanner(System.in);

    public Patient() {
        // default patient object
    }

    public Patient(String name, String address){
        this.name = name;
        this.address = address;
    }

    public Patient(String name, String id, String address, String phoneNo, String appointments) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phoneNo = phoneNo;
        populateAppointments(appointments);
    }

    public String getName(){
        return this.name;
    }

    public String getID(){
        return this.id;
    }

    public String getPhone(){
        return this.phoneNo;
    }

    public String getAddress(){
        return this.address;
    }
    public void populateAppointments(String appointments){
        String week, day, time, status;
        for(String d: appointments.split(":")){
            week = d.split("\\.")[0];
            day = d.split("\\.")[1];
            time = d.split("=")[0].split("\\.")[2];
            status = d.split("=")[1];
            this.appointments.add(new Session(week, day, time, status));
        }
    }

    public static ArrayList<Patient> loadPatients(){
        ArrayList<Patient> patients= new ArrayList<Patient>();
        String path = System.getProperty("user.dir") + "/PSICBookingSystem/src/database/";
        String file = "Patients.txt";
        path += file;
        try {
            Scanner sc = new Scanner(new File(path));
            String str = "";
            boolean firstLine = true;
            do {
                try {
                    str = sc.nextLine().toLowerCase();
                } catch (Exception e) {
                    System.out.print("Empty database :(   .   ");
                }
                if (!firstLine) {
                    String name = str.split(",")[0];
                    String ID = str.split(",")[1];
                    String address = str.split(",")[2];
                    String phone = str.split(",")[3];
                    String appointments = str.split(",")[4];
                    Patient patient = new Patient(name, ID, address, phone, appointments);
                    patients.add(patient);
                } else {
                    // skip first line (header row)
                    firstLine = false;
                }
            } while (sc.hasNextLine());
        } catch (IOException e) {
            System.out.println(e);
        }
        return patients;
    }

    public void setAppointments(ArrayList<Session> newAppointments){
        this.appointments = newAppointments;
    }

    public ArrayList<Session> getAppointments(){
        return this.appointments;
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
