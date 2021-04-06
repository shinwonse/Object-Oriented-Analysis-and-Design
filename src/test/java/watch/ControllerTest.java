package watch;

import View.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    public static Controller junitTest;

    ControllerTest(){
    }

    @Disabled
    public static void makeInstance(){
        BaseView bv = new BaseView();
        bv.controller = new Controller(bv);
        Buzzer.getInstance().setBaseView(bv);
        bv.tkv = new TimeKeepingView(bv);
        bv.tmv = new TimersView(bv);
        bv.alarmView = new AlarmView(bv);
        bv.selectView = new SelectView(bv);
        bv.stopWatchView = new StopWatchView(bv);
        bv.fitnessView = new FitnessView(bv);
        bv.ddayView = new DdayView(bv);
        junitTest = new Controller(bv);
    }

    @Disabled
    void req_countUp() throws Exception {
        try{
            junitTest.req_countUp("stopwatch");
            assertNotNull(junitTest.getInstManager().getStopwatch());
        }catch(Exception e){
            System.out.println("req_countUp failed");
        }
    }

    @Disabled
    void req_continue()  throws Exception {
        try{
            junitTest.req_continue("stopwatch");
          assertNotNull(junitTest.getInstManager().getStopwatch());
        }catch(Exception e){
            System.out.println("req_continue failed");
        }
    }

    @Disabled
    void req_stopBuzzer()  throws Exception {
        try{
            junitTest.req_stopBuzzer("alarm");
           assertNotNull(junitTest.getInstManager().getAlarm());
        }catch(Exception e){
            System.out.println("req_stopBuzzer failed");
        }
    }

    @Disabled
    void req_changeMode()  throws Exception {
        try{
            junitTest.req_changeMode();
           assertNotNull(junitTest.getInstManager().getSelectFunction());
        }catch(Exception e){
            System.out.println("req_changeMode failed");
        }
    }

    @Disabled
    void req_lookFunc() throws Exception {
        try{
            junitTest.req_lookFunc();
           assertNotNull(junitTest.getInstManager().getSelectFunction());
        }catch(Exception e){
            System.out.println("req_lookFunc failed");
        }
    }

    @Disabled
    void req_funcList() throws Exception {
        try{
            junitTest.req_funcList();
          assertNotNull(junitTest.getInstManager().getSelectFunction());
        }catch(Exception e){
            System.out.println("req_funcList failed");
        }
    }

    @Disabled
    void req_selectFunc() throws Exception {
        try{
            junitTest.req_selectFunc(3);
           assertNotNull(junitTest.getInstManager().getSelectFunction());
        }catch(Exception e){
            System.out.println("req_selectFunc failed");
        }
    }

    @Disabled
    void req_finishSelect() throws Exception{
        try{
            assertEquals(true, junitTest.req_finishSelect());

        }catch (Exception e){
            System.out.println("req_finishSelect failed");
        }
    }

    @Disabled
    void req_setDate() throws Exception {
        try{
            junitTest.req_setDate("dDay", 2019, 5, 30);
           assertNotNull(junitTest.getInstManager().getDday());
        }catch (Exception e){
            System.out.println("req_setDate failed");
        }
    }

    @Disabled
    void req_setTime() throws Exception {
        try{
            junitTest.req_setTime("timer", 13, 5, 30);
           assertNotNull(junitTest.getInstManager().getTimer());
        }catch (Exception e){
            System.out.println("req_setTime failed");
        }
    }

    @Disabled
    void req_countDown() throws Exception{
        try {
            junitTest.req_countDown();
           assertNotNull(junitTest.getInstManager().getTimekeeping());
        }catch(Exception e){
            System.out.println("req_counDown failed");
        }
    }

    @Disabled
    void req_pause() throws Exception {
        try {
            junitTest.req_pause("timer");
            assertNotNull(junitTest.getInstManager().getTimer());
        }catch (Exception e){
            System.out.println("req_pause failed");
        }
    }

    @Disabled
    void req_reset() throws Exception {
        try {
            junitTest.req_reset();
           assertNotNull(junitTest.getInstManager().getTimer());
        }catch (Exception e){
            System.out.println("req_reset failed");
        }
    }

    @Disabled
    void req_alarmList() throws Exception {
        try {
            junitTest.req_alarmList();
           assertNotNull(junitTest.getInstManager());
        }catch (Exception e){
            System.out.println("req_alarmList failed");
        }
    }

    @Disabled
    void req_setAlarm() throws Exception {
        try {
            junitTest.req_setAlarm();
            assertNotNull(junitTest.getInstManager().getAlarm());
        }catch (Exception e){
            System.out.println("req_setAlarm failed");
        }
    }

    @Disabled
    void req_setDate1()throws Exception {
        try {
            junitTest.req_setDate(null, 3, 5, 9, 30);
          assertNotNull(junitTest.getInstManager().getAlarm());
        }catch (Exception e){
            System.out.println("req_setDate1 failed");
        }
    }

    @Disabled
    void req_onOff() throws Exception {
        try {
            junitTest.req_onOff();
           assertNotNull(junitTest.getInstManager().getAlarm());
        }catch (Exception e){
            System.out.println("req_onoff failed");
        }
    }

    @Disabled
    void req_deleteAlarm()throws Exception {
        try {
            junitTest.req_deleteAlarm();
           assertNotNull(junitTest.getInstManager().getAlarm());
        }catch (Exception e){
            System.out.println("req_deleteAlarm failed");
        }
    }

    @Disabled
    void req_record() throws Exception {
        try {
            junitTest.req_record(1);
          assertNotNull(junitTest.getInstManager().getStopwatch());
        }catch (Exception e){
            System.out.println("req_record failed");
        }
    }

    @Disabled
    void req_pause1() throws Exception {
        try {
            junitTest.req_pause();
          assertNotNull(junitTest.getInstManager().getStopwatch());
        }catch (Exception e){
            System.out.println("req_pause1 failed");
        }
    }

    @Disabled
    void req_recordList() throws Exception {
        try {
            junitTest.req_recordList(1);
           assertNotNull(junitTest.getInstManager().getStopwatch());
        }catch (Exception e){
            System.out.println("req_recordList failed");
        }
    }

    @Disabled
    void req_finish() throws Exception {
        try {
            junitTest.req_finish("fitness");
            assertNotNull(junitTest.getInstManager().getFitness());
        }catch (Exception e){
            System.out.println("req_finish failed");
        }
    }

    @Disabled
    void getBaseView() throws Exception {
        try {
            assertNotNull(junitTest.getBaseView());

        }catch (Exception e){
            System.out.println("getBaseView failed");
        }
    }

    @Disabled
    void req_selectDate() throws Exception {
        try {
            junitTest.req_selectDate();
            assertNotNull(junitTest.getInstManager().getDday());
        }catch (Exception e){
            System.out.println("req_selectDate failed");
        }
    }


    @Disabled
    void req_setGoal() throws Exception {
        try {
            junitTest.req_setGoal("stop smoking");
            assertEquals("stop smoking",junitTest.getInstManager().getDday().getGoal());
        }catch (Exception e){
            System.out.println("req_setGoal failed");
        }
    }

    @Disabled
    void req_DdayList()throws Exception {
        try {
            junitTest.req_DdayList();
            assertNotNull(junitTest.getInstManager().getDday());
        }catch (Exception e){
            System.out.println("req_DdayList failed");
        }
    }

    @Disabled
    void req_deleteDday() throws Exception {
        try {
            junitTest.req_deleteDday();
            assertNotNull(junitTest.getInstManager().getDday());
        }catch (Exception e){
            System.out.println("req_deleteDday failed");
        }
    }

    @Disabled
    void req_fitnessList() throws Exception {
        try {
            junitTest.req_fitnessList(1);
            assertNotNull(junitTest.getInstManager().getFitness());

        }catch (Exception e){
            System.out.println("req_fitnessList failed");
        }
    }

    @Disabled
    void req_nextExercise() throws Exception {
        try {
            junitTest.req_nextExercise();
          assertNotNull(junitTest.getInstManager().getFitness());
        }catch (Exception e){
            System.out.println("req_nextExercise failed");
        }
    }

    @Disabled
    void req_setExercise()throws Exception {
        try {
            junitTest.req_setExercise("running");
          assertEquals("running",junitTest.getInstManager().getFitness().getExercise());
        }catch (Exception e){
            System.out.println("req_setExercise failed");
        }
    }

    @Disabled
    void getInstManager()  {
        junitTest.getInstManager();
       assertNotNull(junitTest.getInstManager());
    }
}