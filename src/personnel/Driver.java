package personnel;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    Physician physician = new Physician();

    private String response;

    Scanner scanner = new Scanner(System.in);

    public void init() {
//        ArrayList<Physician> physicians = Physician.loadPhysicians();
        System.out.println("\t Welcome to PSIC. We care about you."
        +" \nTo select an option enter the number preceeding it.\n");
        response = qString("Please select an option\n1. Book appointment\n2. Exit");
        
        switch(response) {
            case "1":
//                physician.bookAppointment();
                checkStatus();
                break;
            case "2":
                System.out.println("Thank you for choosing PSIC.");
                System.exit(0);
            default: 
                System.out.println("Please enter a valid option\n");
                init();
        }

    }

    private void checkStatus() {
        System.out.println("\t Are you a visitor (1) or patient (2)\n");
        response = qString("Please select an option\n1. Visitor\n2. Patient");

        switch(response) {
            case "1":
                Visitor visitor = captureVisitorDetails();
                searchPhysicianByExpertise();
                break;
            case "2":
                Patient patient = capturePatientDetails();
                searchPhysicianByExpertise();
                break;
            default:
                System.out.println("Please enter a valid option\n");
                checkStatus();
        }
    }

    private void searchPhysicianByExpertise() {
        ArrayList<Physician> physicians = Physician.loadPhysicians();
        System.out.print("Enter physician's expertise: ");
        String expertise = scanner.nextLine();
        ArrayList<Physician> matchedPhysicians = new ArrayList<>();
        for (Physician physician : physicians) {
            for(String xpts: physician.getExpertise()){
                if (xpts.equalsIgnoreCase(expertise)) {
                    matchedPhysicians.add(physician);
                }
            }
        }
        if(matchedPhysicians.size() < 1){
            System.out.println("Could not find any physician with expertise " + expertise);
            String choice = qString("Do you want to search again?\n 1. Yes, 2.No :");
            if(choice.equals("1")){
                searchPhysicianByExpertise();
            }else if(choice.equals("2")){
                return;
            }else{
                System.out.println("Invalid entry.");
                searchPhysicianByExpertise();
            }
        }else{
            System.out.println("The following physicians match your selected expertise");
            for (Physician physician : matchedPhysicians) {
                System.out.println(physician.getName());
            }
            String name = qString("Enter name of physician to check availability: ");
            bookPhysicianSchedule(name, physicians);

        }
    }

    private void bookPhysicianSchedule(String name, ArrayList<Physician> physicians) {
        boolean workingSession = false;
        boolean booked = false;
        boolean bookSuccess = false;
        String appointment = "";
        String appointmentWeek = "";
        String appointmentDay = "";
        String appointmentTime = "";
        for(Physician physician: physicians){
            if(physician.getName().equalsIgnoreCase(name)){
                System.out.println("Schedule for Physiciain: " + name);
                System.out.println(physician.getWorkSchedule());
                appointment = qString("Pick appointment week, day and time (e.g. 1.mon.8-10 for Monday 4pm)");
                appointmentWeek = appointment.split("\\.")[0];
                appointmentDay = appointment.split("\\.")[1];
                appointmentTime = appointment.split("\\.")[2];
                for (Session session : physician.getAvailability()) {
                    if(session.week.equalsIgnoreCase(appointmentWeek) && session.day.equalsIgnoreCase(appointmentDay) && session.hours.equalsIgnoreCase(appointmentTime) && session.status.equalsIgnoreCase("available")){
                        bookSuccess = true;
                        session.status = "Booked";
                    }

//                    if(session.day.equalsIgnoreCase(appointmentDay) && session.hour.equalsIgnoreCase(appointmentTime)){
//                        workingSession = true;
//                    }
                }
//                for (Session session : physician.getBookings()) {
//                    if(session.day.equalsIgnoreCase(appointmentDay) && session.hour.equalsIgnoreCase(appointmentTime)){
//                        booked = true;
//                    }
//                }

//                if(!booked && workingSession){
//                    physician.book(appointmentDay, appointmentTime);
//                    updatePhysicianDB(physicians);
//                }
            }
        }
//        for(Physician physician: physicians){
//            if(physician.getName().equalsIgnoreCase(name)){
//                System.out.println("Working Hours: " + physician.getWorkingHours());
//                System.out.println("Bookings: " + physician.getBookings().toString());
//                String appointment = qString("Pick appointment day and time (e.g. mon-16 for Mondday 4pm)");
//                String appointmentDay = appointment.split("-")[0];
//                String appointmentTime = appointment.split("-")[1];
//                if(physician.getWorkingHours().contains(appointmentDay)){
//                    for (String daySchedule : physician.getWorkingHours().split(":")) {
//                        if(daySchedule.contains(appointmentTime)){
//                            for(Session session : physician.getBookings()){
//                                if(session.day.equalsIgnoreCase(appointmentDay) && session.hour.equalsIgnoreCase(appointmentTime)){
//                                    System.out.println("The Physician is booked at that time");
//                                    bookPhysicianSchedule(name, physicians);
//                                }
//                            }
//                            physician.setBooking(appointmentDay, appointmentTime);
//                        }
//                    }
//                }
//            }
//        }
        if(bookSuccess){
            System.out.println("Appointment Successfully booked");
            System.out.println("Details :" + "Week: " + appointmentWeek + "." + appointmentDay + appointmentTime);
        }else{
            System.out.println("The time you provided is not available. Try agin");
            bookPhysicianSchedule(name, physicians);
        }
        updatePhysicianDB(physicians);
    }

    public void updatePhysicianDB(ArrayList<Physician> physicians){
        String name, id, address, expertise, phone, aval;
        try{
            FileWriter writer = new FileWriter("PSICBookingSystem/src/database/Physician.txt");
            writer.write("Name, ID, Expertise, Address, Phone, Availability(WeekDayTime=Availability)" + System.lineSeparator());
            for(Physician physician: physicians){
                name = physician.getName();
                id = physician.getID();
                address = physician.getAddress();
                expertise = "";
                phone = physician.getPhoneNo();
                for (String exp : physician.getExpertise()) {
                    expertise += exp + ":";
                }

                //remove trailing ":" character
                expertise = expertise.substring(0, expertise.length() - 1);
                aval = "";
                for (Session ses : physician.getAvailability()) {
                    aval += ses.week + "." + ses.day + "." + ses.hours + "=" + ses.status + ":";
                }
                // remove trailing ":" character
                aval = aval.substring(0, aval.length() - 1);
                writer.write(name + "," + id + "," + expertise + ","
                        + address + "," + phone + "," + aval + System.lineSeparator());
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Visitor captureVisitorDetails() {
        System.out.print("Enter visitor's name: ");
        String name = scanner.nextLine();
        return new Visitor(name);
    }

    private Patient capturePatientDetails() {
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient's address: ");
        String address = scanner.nextLine();
        return new Patient(name, address);
    }


    public String qString(String question) {
        System.out.println(question);
        response = scanner.nextLine();
        return response;
    }
}
