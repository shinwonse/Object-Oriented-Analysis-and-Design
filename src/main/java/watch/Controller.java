package watch;
import View.*;

import java.util.ArrayList;

public class Controller {

    private Timekeeping timekeeping;
    private Timers timer;
    private Alarm alarm;
    private Stopwatch stopwatch;
    private Dday dDay;
    private Fitness fitness;
    private SelectFunction selectFunc;

    private InstManager instManager;
    private FitnessDTO fitDTO;

    private BaseView baseView;

    private int alarmInstNum;
    private int dDayInstNum;

    private int currentFunc;


    public Controller(BaseView bv) {
        this.currentFunc = 0;
        this.baseView = bv;
        this.instManager = InstManager.getInstance();

        this.timekeeping = instManager.getTimekeeping();
        this.timer = instManager.getTimer();
        this.alarm = instManager.getAlarm();
        this.stopwatch = instManager.getStopwatch();
        this.dDay = instManager.getDday();
        this.fitness = instManager.getFitness();
        this.selectFunc = instManager.getSelectFunction();

        this.fitDTO = FitnessDTO.getInstance();



    }

    // common function & select function
    synchronized public void req_countUp(String status) {

        if (status.equals("stopwatch")) {//스탑워치일 때
            stopwatch.setIs_stop(false);
            stopwatch.start();
        } else if (status.equals("fitness")) {//Fitness일 때
            fitness.setIs_stop(false);
            fitness.start();
        }

    }




    synchronized public void req_continue(String status){
        if (status.equals("stopwatch")) {//스탑워치일 때
            synchronized (this.stopwatch) {
                try {
                    stopwatch.setIs_stop(false);
                    stopwatch.notify();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else if (status.equals("fitness")) {//Fitness일 때
            synchronized (this.fitness) {
                try {
                    fitness.setIs_stop(false);
                    fitness.notify();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }


        }
        else if(status.equals("timekeeping")){
            synchronized (this.timekeeping) {
                try {
                    timekeeping.setIs_stop(false);
                    timekeeping.notify();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else if (status.equals("timer")) {//Timer일 때
            synchronized (this.timer) {
                try {
                    timer.setIs_stop(false);
                    timer.notify();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


    }



    public void req_stopBuzzer(String status){
        if(status.equals("timer")){//타이머상태
            timer.getBuzzer().stopBuzzer();
        }
        else if(status.equals("alarm")){//알람상태
            alarm.getBuzzer().stopBuzzer();
        }
    }

    public void req_changeMode(){

        int temp = selectFunc.getfunctionList();

        if(temp==0){
            baseView.change_view(0);
            currentFunc = 0;
        }
        else if(temp==1){
            baseView.change_view(1);
            currentFunc = 1;
        }
        else if(temp==2){
            baseView.change_view(2);
            currentFunc = 2;
        }
        else if(temp==3){
            baseView.change_view(3);
            currentFunc = 3;
        }
        else if(temp==4){
            baseView.change_view(4);
            currentFunc = 4;
        }
        else if(temp==5){
            baseView.change_view(5);
            currentFunc = 5;
        }
        else {
            baseView.change_view(6);
            currentFunc = 6;
        }


    }

    public boolean req_lookFunc(){
        //기능 조합을 위해 기능들을 보기
        if(selectFunc.checkDefaultDisplay()==true){
            return true;
        }
        else return false;
    }


    public String req_funcList(){
        return selectFunc.getfunctionName();
    }
    public boolean req_selectFunc(int index){
        boolean tmp = selectFunc.setFunctionList(index);
        return tmp;

    }

    public boolean req_finishSelect(){
        if(selectFunc.check_four_fuction() == true){
            if(selectFunc.checkDefaultDisplay()){
                if(selectFunc.getFunctionListBool(2) == false){
                    int i;
                    for(i=0; i<instManager.getAlarmInstNum(); i++){
                        instManager.getAlarmList().get(i).setStatus(false);
                    }
                }
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }

    public boolean req_isFunctionSelected(int i){
        return  selectFunc.getFunctionListBool(i);
    }

    //timekeeping function

    public void req_setDate(String status, int year, int month, int date){

        if(status.equals("timekeeping")) {
            timekeeping.setDate(year, month, date);

            try {
                timekeeping.calculateDay(year, month, date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(status.equals("dDay")){
            dDay.setDate(year,month,date);
            dDay.setDday();
        }
    }

    public void req_setTime(String status,int hour, int minute, int second){
        if(status.equals("timekeeping")) {
            timekeeping.setTime(hour, minute, second);
        }
        else if(status.equals("timer")) {
            timer.setTime(hour, minute, second);
        }
    }

    public void req_countDown(){
        timer.setIs_stop(false);
        timer.start();

    }
    synchronized public void req_pause(String status){

        if(status.equals("timer") == true){
            timer.setIs_stop(true);
        }

        else if(status.equals("stopwatch") == true) {
            stopwatch.setIs_stop(true);
        }
        //pause 유스케이스 인터렉션 다이어그램이 스탑워치에서랑 타이머에서가 다름.
        //통일해주어야 할 것 같다.
        else if(status.equals("fitness") == true){
            fitness.setIs_stop(true);
            //System.out.println("3");
        }
        else if(status.equals("timekeeping") == true){
            timekeeping.setIs_stop(true);
            //System.out.println("4");
        }

    }





    public void req_reset(){
        timer.setIs_stop(true);
        timer.reset();
    }

    //alarm function
    //View에서 alarmIndex = -1로 초기화
    public Alarm req_alarmList(){

        alarmInstNum = instManager.getAlarmInstNum();
        if(alarmInstNum == 0){
            //alarmList is null
            return null;
        }
        else{
            instManager.setAlarmIndex((instManager.getAlarmIndex()+1)%alarmInstNum);
            this.alarm = instManager.getAlarm();
            return alarm;
        }
    }
    public Alarm req_setAlarm(){
        alarm = (Alarm) instManager.createInst("alarm");
        if(alarm == null){
            return null;
        }
        else{
            return alarm;
        }

    }

    public void req_setDate(ArrayList<Integer> checkDayList, int dayListNum, int cycle, int hour, int minute){
        int i;
        for(i = 0; i < dayListNum; i ++){
            alarm.setDay(checkDayList.get(i));
        }

        alarm.setCycle(cycle);
        alarm.setHour(hour);
        alarm.setMinute(minute);
    }
    public void req_onOff(){

        alarm.OnOffAlarm();
    }

    public void req_deleteAlarm(){

        instManager.deleteInst("alarm");
    }

    //stopwatch function
    public void req_record(int count){
        this.stopwatch.record(count);
    }
    public void req_pause(){
        this.stopwatch.setIs_stop(true);
    }
    public void req_recordList(int column){
        this.stopwatch.showRecord(column);
    }
    public void req_finish(String status)
    {
        if(status.equals("stopwatch") == true){
            this.stopwatch.reset();
        }
        else if(status.equals("fitness") == true){

            this.fitness.finish();
        }
    }

    public BaseView getBaseView() {
        return baseView;
    }


    //dDay function
    public Dday req_selectDate(){
        dDay = (Dday) instManager.createInst("dDay");
        if(dDay == null){
            return null;
        }
        else{
            return dDay;
        }
    }

    /*
    public String req_nextGoal(String status){
        return dDay.showGoal(status);
    }
*/
    public void req_setGoal(String currGoal){
        this.dDay.setGoal(currGoal);
    }

    //View에서 dDayIndex = -1로 초기화
    public Dday req_DdayList(){

        dDayInstNum = instManager.getdDayInstNum();

        if(dDayInstNum == 0){
            //dDayList is null
            return null;
        }
        else{
            instManager.setdDayIndex((instManager.getdDayIndex()+1)%dDayInstNum);
            this.dDay = instManager.getDday();
            return dDay;
        }
    }

    public void req_deleteDday(){
        //재검토
        instManager.deleteInst("dDay");

    }



    //fitness function
    public void req_fitnessList(int column){
        this.fitness.showFitnessList(column);
    }
    public String req_nextExercise(){
        return this.fitDTO.getNextExercise();
    }
    public void req_setExercise(String exercise){
        this.fitness.setExercise(exercise);
        this.fitness.setCPM(exercise);
    }

    public InstManager getInstManager() {
        return instManager;
    }



}
