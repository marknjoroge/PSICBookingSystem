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
                if(searchPatient(patient)){
                    searchPhysicianByExpertise();
                }else{
                    System.out.println("Patient match failed. Halting...");
                }
                break;
            default:
                System.out.println("Please enter a valid option\n");
                checkStatus();
        }
    }

    private boolean searchPatient(Patient patientToSearch){
        ArrayList<Patient> patients = Patient.loadPatients();
        for(Patient patient : patients){
            if(patient.getName().equalsIgnoreCase(patientToSearch.getName())){
                System.out.println("Confirm if the details below matches the patient");
                System.out.println("Name: " + patient.getName());
                System.out.println("ID: " + patient.getID());
                System.out.println("Address: " + patient.getAddress());
                System.out.println("Phone: " + patient.getPhone());
                String confirmation =qString("Is this you? Y/N");
                boolean result = confirmation.equalsIgnoreCase("y") ? true : false;
                patientToSearch.setAppointments(patient.getAppointments());
                return result;
            }
        }
        return false;
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
        String decision;
        for(Physician physician: physicians){
            if(physician.getName().equalsIgnoreCase(name)){
                System.out.println("Schedule for Physiciain: " + name);
                System.out.println(physician.getWorkSchedule());
                appointment = qString("Pick appointment week, day and time (e.g. 1.mon.8-10 for Monday 4pm).");
                appointmentWeek = appointment.split("\\.")[0];
                appointmentDay = appointment.split("\\.")[1];
                appointmentTime = appointment.split("\\.")[2];
                for (Session session : physician.getAvailability()) {
                    if(session.week.equalsIgnoreCase(appointmentWeek) && session.day.equalsIgnoreCase(appointmentDay) && session.hours.equalsIgnoreCase(appointmentTime) && session.status.equalsIgnoreCase("available")){
                        // check if patient is available
                        decision = qString("Would you like to cancel? Y/N: ");
                        if(decision.equalsIgnoreCase("y")){
                            System.out.println("Booking cancelled");
                            session.status = "cancelled";
                            updatePhysicianDB(physicians);
                            return;
                        }else{
                            bookSuccess = true;
                            session.status = "booked";
                            updatePhysicianDB(physicians);
                        }
                    }
                }
            }
        }
        if(bookSuccess){
            System.out.println("Appointment Successfully booked");
            System.out.println("Details :" + "Week: " + appointmentWeek + "." + appointmentDay + appointmentTime);
        }else{
            System.out.println("The time you provided is not available. Try picking another time or select a different Physician");
            String choice = qString("D to select a different physician. \nT to select different time for same physician");
            if(choice.equalsIgnoreCase("d")){
                //select different physician
                searchPhysicianByExpertise();
            }else if(choice.equalsIgnoreCase("t")){
                // select different time but same physician
                bookPhysicianSchedule(name, physicians);
            }else{
                // invalid entry.
                bookPhysicianSchedule(name, physicians);
            }
        }
    }

    public void updatePhysicianDB(ArrayList<Physician> physicians){
        String name, id, address, expertise, phone, aval;
        try{
            FileWriter writer = new FileWriter("PSICBookingSystem/src/database/Physicians.txt");
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
