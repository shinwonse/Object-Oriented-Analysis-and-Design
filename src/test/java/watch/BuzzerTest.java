package watch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuzzerTest {
    public static Buzzer junitTest;
    BuzzerTest() {
    }
    public boolean is_stop = false;

    @Test
    void getInstance() throws Exception {
        assertNotNull(junitTest.getInstance());
    }

    @Disabled
        //gui관련
    void beep(){
        junitTest.beep();
    }

    @Test
    void ringBuzzer(){
        Buzzer instance = new Buzzer();
        assertEquals(false, instance.getIs_stop());
    }

    @Test
    void stopBuzzer(){
        Buzzer instance = new Buzzer();
        assertEquals(false, instance.getIs_stop());
    }

    @Test
    void getIs_stop() throws Exception {
        try {
            assertEquals(false, junitTest.getIs_stop());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void setIs_stop() throws Exception {
        Buzzer instance = new Buzzer();
        instance.setIs_stop(false);
        try {
            assertEquals(false, instance.getIs_stop());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }
}