package personnel;

public class Session {
    public String day;
    public String hour;
    public String hours;
    public String status;
    public String week;

    public Session(String day, String hour){
        this.day = day;
        this.hour = hour;
        this.hours = hour;
    }

    public Session(String week, String day, String hours, String status){
        this.week = week;
        this.day = day;
        this.hours = hours;
        this.status = status;
    }
}
