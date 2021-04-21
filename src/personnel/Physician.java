package personnel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Physician extends Client{

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


    private String answer;

    private String personInfo = "";

    String path = System.getProperty("user.dir") + "/database/";
    // String path = path1.substring(0, (path1.length() - 4)) + "/database/";

    public Scanner sc = new Scanner(System.in);

    public Physician() {
        // default object
    }


    public Physician(String name, String id, String expertise, String address, String phone, String availability) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phoneNo = phone;
        this.workingHours = availability;
        this.booking = booking;
        populateAvailability(availability);
        populateExpertise(expertise);
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
        String file = "Physicians.txt";
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
                    String address = str.split(",")[3];
                    String phone = str.split(",")[4];
                    String availability = str.split(",")[5];
//                    String bookings = str.split(",")[6];
                    Physician physician = new Physician(name, ID, expertise, address, phone, availability);
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

    public String getAddress(){
        return this.address;
    }

    public String getID(){
        return this.id;
    }

    public String qString(String question) {
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }

    public ArrayList<String> getExpertise(){
        return this.expertise;
    }

    public String getPhoneNo(){
        return this.phoneNo;
    }

    public String getName(){
        return this.name;
    }

    public String getWorkingHours(){
        return this.workingHours;
    }
}