package watch;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class FitnessDTOTest {
    public static FitnessDTO junitTest;

    FitnessDTOTest(){
    }

    @Test
    void getInstance() throws Exception{
        try {
            junitTest.getInstance();
        }catch (Exception e){
            System.out.println("getInstance failed");
        }
    }

    @Test
    void getMonth() throws Exception {
        try{
            assertEquals( 5, junitTest.getMonth());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getDate() throws Exception {
        try{
            assertEquals(0, junitTest.getDate());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getTotalCalories() throws Exception {
        try{
            assertEquals(0, junitTest.getTotalCalories());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getCount() throws Exception {
        try{
            assertEquals(0, junitTest.getCount());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getCPM()throws Exception {
        try{
            assertEquals(0, junitTest.getCPM("running"));
        }catch (Exception e){
            System.out.println("getCPM: wrong exercise name");
        }
    }

    @Test
    void getNextExercise() throws Exception {
        try{
            assertEquals("null", junitTest.getNextExercise());
        }catch(Exception e){
            System.out.println("error");
        }
    }


    @Test
    void setDate() throws  Exception {
        try{
            junitTest.setDate(11);
            assertEquals(11,junitTest.getDate());
        }catch (Exception e){
            System.out.println("setDate failed");
        }
    }


    @Test
    void setTotalCalories() throws  Exception {
        try{
            junitTest.setTotalCalories(200);
            assertEquals(11,junitTest.getTotalCalories());
        }catch (Exception e){
            System.out.println("setTotalCalories failed");
        }
    }

    @Test
    void setCount() throws  Exception {
        try{
            junitTest.setCount(5);
            assertEquals(11,junitTest.getCount());
        }catch (Exception e){
            System.out.println("setCount failed");
        }
    }
}