package common;

import java.util.AbstractMap;
import java.util.ArrayList;
import myCal.*;

public class GeneralSchedule {

    ArrayList<AbstractMap.SimpleEntry<Integer, String> > days = new ArrayList<AbstractMap.SimpleEntry<Integer, String> >();
    ArrayList<AbstractMap.SimpleEntry<String, String> > hours = new ArrayList<AbstractMap.SimpleEntry<String, String> >();

    final int firstTime = 0000;
    final int lastTime = 2359;
    int startTime;
    int endTime;

    private boolean add;
    String allHourString = "";
    String allDaysString = "";

    public void allDays() {
        days.add(new AbstractMap.SimpleEntry(1, "Sunday"));
        days.add(new AbstractMap.SimpleEntry(2, "Monday"));
        days.add(new AbstractMap.SimpleEntry(3, "Tuesday"));
        days.add(new AbstractMap.SimpleEntry(4, "Wednesday"));
        days.add(new AbstractMap.SimpleEntry(5, "Thursday"));
        days.add(new AbstractMap.SimpleEntry(6, "Friday"));
        days.add(new AbstractMap.SimpleEntry(7, "Saturday"));
    }

    public void allHours() {
        hours.add(new AbstractMap.SimpleEntry("a", "0800 - 0830"));
        hours.add(new AbstractMap.SimpleEntry("b", "0830 - 0900"));
        hours.add(new AbstractMap.SimpleEntry("c", "0900 - 0930"));
        hours.add(new AbstractMap.SimpleEntry("d", "0930 - 1000"));
        hours.add(new AbstractMap.SimpleEntry("e", "1000 - 1030"));
        hours.add(new AbstractMap.SimpleEntry("f", "1030 - 1100"));
        hours.add(new AbstractMap.SimpleEntry("g", "1130 - 1200"));
        hours.add(new AbstractMap.SimpleEntry("h", "1200 - 1230"));
        hours.add(new AbstractMap.SimpleEntry("i", "1230 - 1300"));
        hours.add(new AbstractMap.SimpleEntry("j", "1300 - 1330"));
        hours.add(new AbstractMap.SimpleEntry("k", "1330 - 1400"));
        hours.add(new AbstractMap.SimpleEntry("l", "1430 - 1500"));
        hours.add(new AbstractMap.SimpleEntry("m", "1500 - 1530"));
        hours.add(new AbstractMap.SimpleEntry("n", "1530 - 1600"));
        hours.add(new AbstractMap.SimpleEntry("o", "1600 - 1630"));
        hours.add(new AbstractMap.SimpleEntry("p", "1630 - 1700"));
    }

    public String printAllHours() {
        allHours();
        for (int i = 0; i < hours.size(); i++) {
  
            // get map from list
            AbstractMap.SimpleEntry<String, String>
                map
                = hours.get(i);
  
            // get key from map.getKey();
            String key = map.getKey();
            String value = map.getValue();
  
            allHourString += (key + "\t" + value + "\n");
        }
        return allHourString;
    }

    public String printAllDays() {
        allDays();
        for (int i = 0; i < days.size(); i++) {
            // get map from list
            AbstractMap.SimpleEntry<Integer, String>
                map
                = days.get(i);
  
            // get key from map.getKey();
            int key = map.getKey();
            String value = map.getValue();
  
            allDaysString += (key + "\t" + value + "\n");
        }
        return allDaysString;
    }

    public void addDay(int day, int startTime, int endTime) {

    }

}
