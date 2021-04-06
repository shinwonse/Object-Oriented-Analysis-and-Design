package watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstManagerTest {
    public static InstManager junitTest;
    InstManagerTest(){
    }

    @Test
    void getInstance() {
        junitTest = InstManager.getInstance();
        try {
            assertNotNull(junitTest);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void getAlarmIndex() throws Exception{
        try{
            assertEquals(0, junitTest.getAlarmIndex());
        }catch (Exception e){
            System.out.println("getAlarmIndex failed");
        }
    }

    @Test
    void getdDayIndex()  throws Exception{
        try{
            assertEquals(0, junitTest.getdDayIndex());
        }catch (Exception e){
            System.out.println("getdDayIndex failed");
        }
    }

    @Test
    void setAlarmIndex()  throws Exception{
        try{
            junitTest.setAlarmIndex(3);
            assertEquals(3,junitTest.getAlarmIndex());
        }catch (Exception e){
            System.out.println("setAlarmIndex failed");
        }
    }

    @Test
    void setdDayIndex()  throws Exception{
        try{
            junitTest.setdDayIndex(2);
            assertEquals(2, junitTest.getdDayIndex());
        }catch (Exception e){
            System.out.println("setdDayIndex failed");
        }
    }

    @Test
    void getAlarmInstNum()  throws Exception{
        try{
            junitTest.getAlarmInstNum();
            assertEquals(0,junitTest.getAlarmInstNum());
        }catch (Exception e){
            System.out.println("getAlarmInstNum failed");
        }
    }
/*
    @Test
    void getdDayInstNum()  throws Exception{
        try{
            junitTest.getdDayInstNum();
        }catch (Exception e){
            System.out.println("getdDayInstNum failed");
        }
    }
*/
    @Test
    void getTimekeeping()  throws Exception{
        try{
            assertNotNull(junitTest.getTimekeeping());
        }catch (Exception e){
            System.out.println("getTimekeeping failed");
        }
    }

    @Test
    void getTimer()  throws Exception{
        try{
            assertNotNull(junitTest.getTimer());
        }catch (Exception e){
            System.out.println("getTimer failed");
        }
    }

    @Test
    void getAlarm()  throws Exception{
        try{
            junitTest.createInst("alarm");
            junitTest.setAlarmIndex(0);
            assertNotNull(junitTest.getAlarm());
        }catch (Exception e){
            System.out.println("getAlarm failed");
        }
    }

    @Test
    void getStopwatch() throws Exception{
        try{
            assertNotNull(junitTest.getStopwatch());
        }catch (Exception e){
            System.out.println("getStopwatch failed");
        }
    }

    @Test
    void getDday()  throws Exception{
        try{
            junitTest.createInst("dDay");
            junitTest.setdDayIndex(0);
            assertNotNull(junitTest.getDday());
        }catch (Exception e){
            System.out.println("getDday failed");
        }
    }

    @Test
    void getFitness()  throws Exception{
        try{
            assertNotNull(junitTest.getFitness());
        }catch (Exception e){
            System.out.println("getFitness failed");
        }
    }

    @Test
    void getSelectFunction()  throws Exception{
        try{
            assertNotNull(junitTest.getSelectFunction());
        }catch (Exception e){
            System.out.println("getSelectFunction failed");
        }
    }

    @Test
    void deleteInst() throws Exception{
        try{
            junitTest.deleteInst("alarm");
            assertEquals(0,junitTest.getAlarmInstNum());
        }catch (Exception e){
            System.out.println("deleteInst failed");
        }
    }

    /*
    @Test
    void createInst() throws Exception{
        try{
            junitTest.createInst("dDay");
        }catch (Exception e){
            System.out.println("createInst failed");
        }
    }
    */

}