package watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {
    public static DBManager junitTest;
    public static FitnessDTO fitnessDTO;
    public static StopwatchDTO stopwatchDTO;

    DBManagerTest(){
    }

    @BeforeAll
    public static void makeInstance(){
        junitTest = DBManager.getInstance();
        fitnessDTO = FitnessDTO.getInstance();
        stopwatchDTO = StopwatchDTO.getInstance();
    }

    @Test
    void selectFitness() throws Exception{
        try{
            junitTest.selectFitness("look", 0);
            assertNotEquals(31,fitnessDTO.getCount());

        }catch (Exception e){
            System.out.println("selectFitness failed");
        }
    }

    @Disabled
    void insertFitness() throws Exception {
        try{
            junitTest.insertFitness(3,11, 9,30,00, 100);
            int tmp = fitnessDTO.getCount();
            junitTest.insertFitness(3,11, 9,30,00, 100);
            assertEquals(tmp+1,fitnessDTO.getCount());

        }catch (Exception e){
            System.out.println("insertFitness failed");
        }
    }

    @Disabled
    void updateFitness() throws Exception{
        try {
            junitTest.updateFitness(9,30,00, 300);
            assertEquals(9,fitnessDTO.getHour());
        }catch (Exception e){
            System.out.println("updateFitness failed");
        }
    }

    @Disabled
    void deleteFitness() throws Exception {
        try{
            junitTest.insertFitness(3,11, 9,30,00, 100);
            int tmp = fitnessDTO.getCount();
            junitTest.deleteFitness();
            assertEquals(tmp-1,fitnessDTO.getCount());
        }catch (Exception e){
            System.out.println("deleteFitness failed");
        }
    }

    @Test
    void selectStopwatch() throws Exception {
        try{
            junitTest.selectStopwatch(0);


        }catch (Exception e){
            System.out.println("selectStopwatch failed");
        }

    }

    @Disabled
    void insertStopwatch() throws Exception {
        try{
            junitTest.insertStopwatch(9,30,00, 1);
            assertEquals(9,stopwatchDTO.getHour());

        }catch (Exception e){
            System.out.println("insertStopwatch failed");
        }
    }

    @Disabled
    void deleteStopwatch() throws Exception {
        try{
            junitTest.deleteStopwatch();
            int tmp = stopwatchDTO.getNum();
            junitTest.deleteStopwatch();
            assertEquals(tmp-1,stopwatchDTO.getNum());

        }catch (Exception e){
            System.out.println("deleteStopwatch failed");
        }
    }

    @Test
    void resetStopwatch() throws Exception {
        try{
            junitTest.resetStopwatch();
            assertEquals(0,stopwatchDTO.getNum());

        }catch (Exception e){
            System.out.println("resetStopwatch failed");
        }
    }
}