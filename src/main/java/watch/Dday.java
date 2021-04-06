package watch;

import View.DdayView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Dday extends Thread{
    private int year;
    private int month;
    private int date;
    private String goal;
    private String[] goalList ={"stop drinking","stop smoking","studying","exercising","diet","save money"};
    private int index=0;
    private int dayCount = 0;

    private InstManager im;
    private Timekeeping tk;
    private int currYear;
    private int currMonth;
    private int currDate;
    private Calendar cal = Calendar.getInstance();
    private Calendar cal2 = Calendar.getInstance();

    private int count;
    private boolean is_delete = false;


    public Dday()
    {
        //자바에서 제공하는 타이머. watch package의 Timer class 아님
        im = InstManager.getInstance();
        tk = im.getTimekeeping();
    }

    @Override
    public void run() {
        calculateDday();
    }

    public void setDate(int year, int month, int date){
        this.year = year;
        this.month = month;
        this.date = date;

    }

    public String showGoal(String status){
        if(status.equals("nextGoal"))
            index++;
        else //Down
        {
            index--;
            if ((index < 0)) {
                index = index + 6;
            }
        }
        String goalName = goalList[index%6];
        return goalName;
    }

    public void calculateDday()
    {
        while(is_delete == false){
            try {
                // 주기만큼 잠든다.
                sleep(1000);
                setDday();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setDday()
    {

        currYear = tk.getYear();
        currMonth = tk.getMonth();
        currDate = tk.getDate();

        cal.set(currYear, currMonth, currDate);//오늘

        cal2.set(this.year, this.month, this.date);//설정일

        count = 0;

        if(cal2.after(cal)){
            while (!cal.after(cal2)) {
                count--;
                cal.add(Calendar.DATE, 1);
            }
            dayCount = count+1;
        }
        else {
            while (!cal2.after(cal)) {
                count++;
                cal2.add(Calendar.DATE, 1);
            }
            dayCount = count -1;
        }


        System.out.println("dayCount:"+dayCount);
    }



    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public String getGoal() {
        return goal;
    }

    public int getDayCount() {
        return dayCount;
    }
/*
    public String[] getGoalList() {
        return goalList;
    }
*/
/*
    public void setDate(int date) {
        this.date = date;
    }
*/
    public void setGoal(String goal) {
        this.goal = goal;
    }

    //public void setGoalList(String[] goalList) {
     //   this.goalList = goalList;
    //}

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

}
