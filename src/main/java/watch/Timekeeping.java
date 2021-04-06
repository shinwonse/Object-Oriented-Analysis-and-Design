package watch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Timekeeping extends Thread implements CountUp {
    Calendar cal = Calendar.getInstance();

    private int year, month, date;
    private int hour, minute, second ;
    private String day;
    private int dayNum;
    private boolean is_stop;
    private String[] dayName = {"SUN","MON","TUE","WED","THU","FRI","SAT"};

    public Timekeeping(){
        Calendar myCalendar = Calendar.getInstance();
        this.year = myCalendar.get(Calendar.YEAR);
        this.month = myCalendar.get((Calendar.MONTH));
        this.date = myCalendar.get(Calendar.DAY_OF_MONTH);
        this.hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        this.minute = myCalendar.get(Calendar.MINUTE);
        this.second = myCalendar.get(Calendar.SECOND);
        this.dayNum = myCalendar.get(Calendar.DAY_OF_WEEK);
        this.day = dayName[dayNum - 1];
        this.is_stop = false;
        start();

    }

    public void run(){
        countUp();
    }

    //usecse: set_alarm 이랑 count_up
    public int getHour(){
        return this.hour;
    }
    //usecase: set_alarm 이랑 count_up
    public int getMinute(){
        return this.minute;
    }
    //usecase: count_up
    public int getSecond(){
        return this.second;
    }

    //usecase: select_date
    public int getYear(){
        return this.year;
    }
    //usecase: select_date
    public int getMonth(){
        return this.month;
    }
    //usecase: select_date 랑 set_alarm
    public int getDate() {
        return this.date;
    }
    public int getDayNum() {
        return dayNum;
    }

    public boolean getIs_stop() {
        return is_stop;
    }

    public void setIs_stop(boolean is_stop) {
        this.is_stop = is_stop;
    }

    //usecase: look_time 랑 count_up
    @Override
    synchronized public void countUp(){
        while (true) {
            cal.set(year,month,date,hour,minute,second);
            if (is_stop == false) {
                second++;
                System.out.println("timekeeping날짜;"+date);
                cal.set(Calendar.SECOND,second);
                if (second == 60) {
                    second = 0;
                    minute++;
                    cal.set(Calendar.MINUTE,minute);
                }
                if (minute == 60) {
                    minute = 0;
                    hour++;
                    cal.set(Calendar.HOUR_OF_DAY,hour);
                    if (hour == 24) {
                        hour = cal.get(Calendar.HOUR_OF_DAY);
                        date =cal.get(Calendar.DATE);
                        //System.out.println("timekeeping날짜;"+date);
                        month = cal.get(Calendar.MONTH);
                        year = cal.get(Calendar.YEAR);

                        try {
                            calculateDay(year, month, date);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                    System.out.println("interrupt");
                    break;
                }
            }
            else {
                synchronized (this) {
                    try {
                        //System.out.println("wait");
                        this.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        break;
                    }
                }

            }

        }

    }



    //usecase: set_time
    public void setDate(int year, int month, int date){
        this.year = year;
        this.month = month;
        this.date = date;
        cal.set(year,month,date);
    }

    //usecase: set_time
    public void setTime(int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minute);
        cal.set(Calendar.SECOND,second);
    }

    //usecase: set_time
    public String calculateDay (int year, int month, int date) throws Exception {
        cal.set(year,month-1,date);

        int dayNum = cal.get(cal.DAY_OF_WEEK);
        switch(dayNum){
            case 1:
                day = dayName[0];
                break;
            case 2:
                day = dayName[1];
                break;
            case 3:
                day = dayName[2];
                break;
            case 4:
                day = dayName[3];
                break;
            case 5:
                day = dayName[4];
                break;
            case 6:
                day = dayName[5];
                break;
            case 7:
                day = dayName[6];
                break;
            default:
                break;

        }
        return day;
    }




}
