package watch;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FitnessDTO {

    private int month;
    private int date;
    private int hour;
    private int minute;
    private int second;
    private int totalCalories;
    private int count;
    private int CPMList []= {5, 10, 15};
    private int index = -1;
    private String exerciseList [] ={"cycling", "walking", "running"};

    private static FitnessDTO ourInstance;

    public static FitnessDTO getInstance() {
        if(ourInstance==null)
            ourInstance= new FitnessDTO();
        return ourInstance;
    }

    private FitnessDTO() {
        GregorianCalendar today = new GregorianCalendar ( );

        month = today.get(today.MONTH)+1;
        date = today.get(today.DATE);
        count =0;

    }

    //getter
    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }


    public int getTotalCalories() {
        return totalCalories;
    }

    public int getCount() {
        return count;
    }

    public int getCPM(String exercise) {
        if(exercise.equals(exerciseList[0]) == true){
            return CPMList[0];
        }
        else if(exercise.equals(exerciseList[1]) == true){
            return CPMList[1];
        }
        else if(exercise.equals(exerciseList[2]) == true){
            return CPMList[2];
        }
        else {
            System.out.println("getCPM() : Wrong Exercise Name");
            return 0;
        }
    }

    public String getNextExercise() {
        index++;
        return exerciseList[index%3];

    }

    //setter

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }


    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
