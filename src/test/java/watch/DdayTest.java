package watch;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DdayTest {
    public static Dday junitTest;

    DdayTest() {
    }

    @BeforeAll
    public static void makeInstance()
    {
        junitTest = new Dday();
        junitTest.setIs_delete(false);
        try{
            assertNotNull(junitTest);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void setDate() throws Exception {
        try{
            junitTest.setDate(2018, 3, 11);
            assertEquals(2018, junitTest.getYear());
            assertEquals(3,junitTest.getMonth());
            assertEquals(11,junitTest.getDate());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void showGoal() throws Exception {
        try {

            assertEquals("stop smoking",junitTest.showGoal("nextGoal"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Disabled
    void calculateDday() throws Exception{
        try {
            junitTest.calculateDday();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: 목록에 디데이가 없습니다(정상)");
        }
    }

    @Test
    void setDday() throws Exception {
        try {
            junitTest.setDate(InstManager.getInstance().getTimekeeping().getYear(), InstManager.getInstance().getTimekeeping().getMonth(), InstManager.getInstance().getTimekeeping().getDate());
            //System.out.println(InstManager.getInstance().getTimekeeping().getYear() +" 월 : "+ InstManager.getInstance().getTimekeeping().getMonth()+" 일 : "+ InstManager.getInstance().getTimekeeping().getDate());
            junitTest.setDday();
            assertEquals(0,junitTest.getDayCount());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }



    @Test
    void getGoal() throws Exception {
        try {
            junitTest.setGoal("stop drinking");
            assertEquals("stop drinking", junitTest.getGoal());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    /*
    @Test
    void getDayCount() throws Exception{
        try {
            junitTest.getDayCount();
            //Assertions.assertEquals();
        }catch (Exception e){
            System.out.println("error");
        }
    }
*/
    /*
    @Test
    void getGoalList() throws Exception {
        try {
            //junitTest.getGoalList();
            //Assertions.assertEquals();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }
*/
/*
    @Test
    void setDate1() throws Exception {
        try {
            junitTest.setDate(24);

        } catch (Exception var2) {
            System.out.println("error");
        }
    }
*/
    @Test
    void setGoal()throws Exception {
        try {
            junitTest.setGoal("stop drinking");
            assertEquals("stop drinking",junitTest.getGoal());

        } catch (Exception var2) {
            System.out.println("error");
        }
    }
/*
    @Test
    void setGoalList()throws Exception {
        String[] arr = new String[]{"happy", "smile"};

        try {
            //junitTest.setGoalList(arr);
        } catch (Exception var3) {
            System.out.println("error");
        }
    }

 */
}