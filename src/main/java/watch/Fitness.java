package watch;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.function.DoubleBinaryOperator;

public class Fitness extends Thread implements CountUp{
    private int month;
    private int date;
    private int hour;
    private int minute;
    private int second;
    private int recentMonth;
    private int recentDate;
    private int CPM;
    private int totalCalories;
    //목록 개수
    private int count;
    private String exercise;

    private boolean is_stop;

    DBManager dbManager;
    private FitnessDTO fitDTO;

    public Fitness(){
        this.is_stop = true;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
        this.totalCalories = 0;
        this.CPM=0;
        this.dbManager = DBManager.getInstance();
        this.fitDTO = FitnessDTO.getInstance();
        this.month = fitDTO.getMonth();
        System.out.println("현재 월"+ month);
        this.date = fitDTO.getDate();
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

    public int getCPM() {
        return CPM;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public int getCount() {
        dbManager.selectFitness("look",1);
        count = fitDTO.getCount();
        return count;
    }

    public int getRecentMonth() {
        return recentMonth;
    }



    public String getExercise() {
        return exercise;
    }

    public boolean getIs_stop() {
        return is_stop;
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

    public void setCPM(String exercise) {
        this.CPM = fitDTO.getCPM(exercise);
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public void setIs_stop(boolean is_stop) {
        this.is_stop = is_stop;
    }

    //method

    public void getRecentDate()
    {
        dbManager.selectFitness("finish",count);
        this.recentMonth = fitDTO.getMonth();
        System.out.println(recentMonth);
        this.recentDate= fitDTO.getDate();
    }

    public boolean checkDate(){
        System.out.println(date);

        if((recentMonth==month)&&( recentDate == date))
            return true;
        else
            return false;
    }


    public void showFitnessList(int column){
        dbManager.selectFitness("look",column);
        month = fitDTO.getMonth();
        date = fitDTO.getDate();
        hour = fitDTO.getHour();
        minute =fitDTO.getMinute();
        second = fitDTO.getSecond();
        totalCalories= fitDTO.getTotalCalories();
    }

    @Override
    public void run() {
        countUp();
    }

    @Override
    public void countUp() {

        second = 0;
        minute = 0;
        hour = 0;
        totalCalories =0;
        while (true) {
            if (is_stop == false) {
                second++;
                if (second == 60) {
                    second = 0;
                    minute++;
                    calcultateCalories();
                }
                if (minute == 60) {
                    minute = 0;
                    hour++;
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            //is_stop == true
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




    public void calcultateCalories(){
        this.totalCalories += this.CPM;
    }

    public void finish()
    {
        getRecentDate();
        boolean check = checkDate();
        if(check==false){
            if(count == 30)
            {
                dbManager.deleteFitness();
            }
            try {

                dbManager.insertFitness(month,date,hour,minute,second,totalCalories);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        else{
            updateFitness(hour,minute,second,totalCalories);
        }
        initFitness();
    }

    public void updateFitness(int hour, int minute, int second, int totalCal){
        dbManager.selectFitness("finish",count);//마지막 행의 데이터 가져오기
        int cal = fitDTO.getTotalCalories();
        cal+=this.totalCalories;

        int h = fitDTO.getHour();
        int m = fitDTO.getMinute();
        int s = fitDTO.getSecond();

        s+=this.second;
        if(s>=60)
        {
            s= s-60;
            m++;
        }

        m+=this.minute;

        if(m>=60)
        {
            m = m-60;
            h++;
        }

        h+=this.hour;
        dbManager.updateFitness(h,m,s,cal);

    }

    public void deleteFitness(){
        dbManager.deleteFitness();
    }
    public void initFitness(){
        hour= 0;
        minute = 0;
        second = 0;
        totalCalories = 0;
        CPM = 0;
        is_stop = true;
        exercise = "";

    }



}
