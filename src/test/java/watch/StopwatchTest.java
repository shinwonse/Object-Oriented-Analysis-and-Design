package watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StopwatchTest {
    public static Stopwatch junitTest;
    Stopwatch instance = new Stopwatch();

    StopwatchTest(){
    }

    @BeforeAll
    public static void makeInstance(){
        assertNotNull(junitTest = new Stopwatch());
    }


    @Test
    void getIs_stop() throws Exception {
        try {
            assertEquals(true, junitTest.getIs_stop());
        }catch (Exception e){
            System.out.println("error");
        }
    }


    @Test
    void setIs_stop() throws Exception{
        instance.setIs_stop(true);
        try {
            assertEquals(true, instance.getIs_stop());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Disabled
    void countUp()  throws Exception{
        try{
            junitTest.countUp();
        }catch (Exception e){
            System.out.println("error:무한히 test되므로 disable");
        }
    }

    @Test
    void reset() throws Exception {
        try {
            assertEquals(0, instance.getHour());
            assertEquals(0, instance.getMinute());
            assertEquals(0, instance.getSecond());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
        //assert로 테스트할 부분이 없다.
    void showRecord(){
        junitTest.showRecord(0);
        assertEquals(0, junitTest.getHour());
        assertEquals(0, junitTest.getMinute());
        assertEquals(0, junitTest.getSecond());
    }

    @Test
        //assert로 테스트할 부분이 없다.
    void record() {
        junitTest.record(1);
        assertEquals(0, junitTest.getHour());
        assertEquals(0, junitTest.getMinute());
        assertEquals(0, junitTest.getSecond());
    }
}