package personnel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Physician {

    private String name;
    private String id;
    private String address;
    private ArrayList<String> expertise = new ArrayList<>();
    private String phoneNo;

    private String consultationHrs;
    private String consultationDays;

    private int daysAvailable;
    private String workingHours;
    private ArrayList<Session> availability = new ArrayList<>();
    private String booking;
    private ArrayList<Session> bookings = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();

    private String personType;
    private String availablePeriod;
    private String daysToDB;

    // available days, from monday to sunday
    private boolean[] availableDays = {false,false,false,false,false,false,false};
    // available hours, from 8am to 6pm
    private boolean[] availableHours = {false, false, false, false, false, false, false, false, false, false, false};



    private String answer;

    private String personInfo = "";

    String path = System.getProperty("user.dir") + "/database/";
    // String path = path1.substring(0, (path1.length() - 4)) + "/database/";

    public Scanner sc = new Scanner(System.in);

    public Physician() {
        // actions();
    }

//    public Physician(String name, String expertise) {
//        this.name = name;
//        this.expertise = expertise;
//    }

    public Physician(String name, String id, String expertise, String phone, String availability) {
        this.name = name;
        this.id = id;
//        this.expertise = expertise;
        this.phoneNo = phone;
        this.workingHours = availability;
        this.booking = booking;
        populateAvailability(availability);
        populateExpertise(expertise);
//        populateBookings(booking);
    }

    public  void populateExpertise(String expertise){
        for(String xpts: expertise.split(":")){
            this.expertise.add(xpts);
        }
    }

    public void populateAvailability(String availability){
        String week, day, time, status;
        for(String d: availability.split(":")){
            week = d.split("\\.")[0];
            day = d.split("\\.")[1];
            time = d.split("=")[0].split("\\.")[2];
            status = d.split("=")[1];
            this.availability.add(new Session(week, day, time, status));
        }
    }
    
    public String getWorkSchedule(){
        String schedule = "";
        for (Session session : availability) {
            schedule += session.week + "." + session.day + "." + session.hours + "-" + session.status + "\n";
        }
        return schedule;
    }

    public void populateBookings(String booking){
        String weekDay, time;
        for(String day: booking.split(":")){
            weekDay = day.split("-")[0];
            for(int i = 1; i<day.split("-").length; i++){
                time = day.split("-")[i];
                this.bookings.add(new Session(weekDay, time));
            }
        }
    }

    public ArrayList<Session> getAvailability(){
        return this.availability;
    }

    public static ArrayList<Physician> loadPhysicians(){
        ArrayList<Physician> physicians= new ArrayList<Physician>();
        String path = System.getProperty("user.dir") + "/PSICBookingSystem/src/database/";
        String file = "Physician.txt";
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
                    String expertise = str.split(",")[2];
                    String phone = str.split(",")[3];
                    String availability = str.split(",")[4];
//                    String bookings = str.split(",")[5];
                    Physician physician = new Physician(name, ID, expertise, phone, availability);
                    physicians.add(physician);
                } else {
                    // skip first line (header row)
                    firstLine = false;
                }
            } while (sc.hasNextLine());
        } catch (IOException e) {
            System.out.println(e);
        }
        return physicians;
    }

    public void bookAppointment() {
        personType = qString("Are you a visitor or a patient?\n1. Visitor\n2. Patient");
        switch(personType){
            case "1":
                //TODO some stuff here
                break;
            case "2":
//                patient.patientBook();
                break;
            default:
                //TODO: more stuff
                break;
        }
    }
//        addToDB("Physician.txt");
//    }


    public void newPhysician() {
        System.out.println(path);
        name = qString("Physician's name: ");
        id = qString("Physician's id: ");
        address = qString("Physician's address: ");
//        phoneNo = Integer.parseInt(qString("Physician's phone Number: "));
//        expertise = qString("Doctor's expertise");

        consultationDays = qString("Days of the week available (in 'Mon Tue Wed' format): ");
        consultationHrs = qString("Time of the day available: ");

//        addPhysicianSchedule();
//        IDGenerator idGenerator = new IDGenerator(lName);
//        id = idGenerator.toString();


        addToDB("Physician.txt");
    }


//    public void addPhysicianSchedule() {
//        daysAvailable = Integer.parseInt(qString("How many days of the week will you be available?"));
//        if (daysAvailable > 7) addPhysicianSchedule();
//        System.out.println("Please select the days, pressing enter each time you select a day.");
//        System.out.println(generalSchedule.printAllDays());;
//        for(int i = 1; i <= daysAvailable; i++) {
//            availableDays[i-1] = Integer.parseInt(qString("Day " + i + ": "));
//            System.out.println("Available sessions(Enter letters to show when available, pressing enter each time you select a day. Press 'z' when done.");
//            System.out.println(generalSchedule.printAllHours());
//            String y = "";
//            int j = 0;
//
//            while (y != "z")
//            {
//                availableHours[j] = y;
//                y = sc.next();
//                System.out.println(y);
//                j++;
//            }
//            // generalSchedule.addDay(x, availablePeriod, availableTo);
//        }
//        addScheduleToDB(availableDays);
//    }


    public String qString(String question) {
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }


    public ArrayList<String> getExpertise(){
        return this.expertise;
    }

    public String getName(){
        return this.name;
    }

    public String getWorkingHours(){
        return this.workingHours;
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

            personInfo = "\tName  :: " + name + "\tID ::  " 
                + id + "\tAddress   :: " + address + "\tExpertise :: "
                + expertise + "\t" + "\tPhone :: " + phoneNo + "\tFree on :: "
                + consultationDays + "\tat :: " + consultationHrs;
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

    public void setBooking(String appointmentDay, String appointmentTime) {
        Session session = new Session(appointmentDay, appointmentTime);
        bookings.add(session);
    }

    public ArrayList<Session> getBookings(){
        return this.bookings;
    }

    public void book(String appointmentDay, String appointmentTime) {
        this.bookings.add(new Session(appointmentDay, appointmentTime));
    }
}