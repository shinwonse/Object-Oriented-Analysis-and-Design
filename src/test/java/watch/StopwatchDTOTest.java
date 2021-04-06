package watch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StopwatchDTOTest {
    public static StopwatchDTO junitTest;

    StopwatchDTOTest(){
    }


    @Test
    void getInstance(){
        assertNotNull(junitTest.getInstance());
    }


    @Test
    void getNum()  throws Exception {
        try {
            assertEquals(0, junitTest.getNum());
        }catch (Exception e){
            System.out.println("getNum failed");
        }
    }


    @Test
    void setNum()  throws  Exception{
        try{
            junitTest.setNum(3);
            assertEquals(3, junitTest.getNum());
        }catch (Exception e){
            System.out.println("setNum failed");
        }
    }
}