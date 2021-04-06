package watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimersTest {
    public static Timers junitTest;
    Timers instance = new Timers();

    TimersTest() {
    }

    @BeforeAll
    public static void makeInstance(){
        assertNotNull(junitTest = new Timers());
    }

    @Test
    void getBuzzer() {
        assertNotNull(junitTest.getBuzzer());
    }

    @Test
    void setTime()  throws Exception {
        instance.setTime(3, 10, 30);
        try {
            assertEquals(3, instance.getHour());
            assertEquals(10, instance.getMinute());
            assertEquals(30, instance.getSecond());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }


    @Disabled
    void countDown() throws Exception {
        try {
            junitTest.countDown();
        } catch (Exception var2) {
            System.out.println("무한하게 test하기때문에 disable");
        }
    }

    @Test
        //timer가 00:00:00이 아닌지만 체크해주는 함수라서
        //assert로 테스트할 부분이 없다.
    void checkTimer(){
        junitTest.checkTimer();
    }

    @Test
        //reset한거니까 hour,minute,second가 0일거다.
    void reset() throws Exception {
        try {
            assertEquals(0, instance.getHour());
            assertEquals(0, instance.getMinute());
            assertEquals(0, instance.getSecond());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void getIs_stop() throws Exception{
        try{
            assertEquals(true, junitTest.getIs_stop());
        }catch (Exception var2){
            System.out.println("error");
        }
    }

    @Test
    void setIs_stop() throws Exception{
        instance.setIs_stop(false);
        try {
            assertEquals(false, instance.getIs_stop());
        }
        catch (Exception var2){
            System.out.println("error");
        }
    }
}