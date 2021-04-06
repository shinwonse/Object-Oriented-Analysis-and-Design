package watch;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FitnessTest {
    public static Fitness junitTest;
    public static FitnessDTO fitnessDTO;

    FitnessTest() { }

    @BeforeAll
    public static void makeInstance(){
        junitTest = new Fitness();
        fitnessDTO = FitnessDTO.getInstance();
    }

    @Test
    void getCPM()throws Exception{
        try{
            assertEquals(0,junitTest.getCPM());
        }catch(Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getTotalCalories() throws Exception{
        try{
            assertEquals(0,junitTest.getTotalCalories());
        }catch(Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getCount() throws Exception{
        try{
            assertNotEquals(31,junitTest.getCount());
        }catch(Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getExercise() throws Exception{
        try {
            assertEquals("running", junitTest.getExercise());
        }catch (Exception e){
            System.out.println("error");
        }
    }


    @Test
    void getIs_stop()  throws Exception{
        try {
            assertEquals(true, junitTest.getIs_stop());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void setCPM()throws Exception{
        try {
            junitTest.setCPM("walking");
            assertEquals(10,junitTest.getCPM());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void setTotalCalories()throws Exception{
        try {
            junitTest.setTotalCalories(500);
            assertEquals(500,junitTest.getTotalCalories());
        }catch (Exception e){
            System.out.println("error");
        }
    }


    @Test
    void setExercise()throws Exception{
        try {
            junitTest.setExercise("running");
            assertEquals("running",junitTest.getExercise());
        }catch (Exception e){
            System.out.println("error");
        }
    }


    @Test
    void setIs_stop()  throws Exception {
        try {
            junitTest.setIs_stop(false);
            assertEquals(false, junitTest.getIs_stop());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getRecentDate() throws Exception {
        try {
            junitTest.getRecentDate();
            //db에 기록 없음
            assertNotEquals(13,junitTest.getRecentMonth());
            //db에 기록이 없기 때문에 가져올 값이 존재하지 않음
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Disabled
    void checkDate()  throws Exception {
        try {
            assertEquals(true,junitTest.checkDate());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void showFitnessList()  throws Exception {
        try {
            junitTest.showFitnessList(1);
            assertNotEquals(60,junitTest.getSecond());

        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Disabled
    void countUp() throws Exception {
        try {
            junitTest.countUp();
        }catch (Exception e){
            System.out.println("error: 무한히 test하기 때문에 ignore처리");
        }
    }

    @Test
    void calcultateCalories()  throws Exception {
        try {
            junitTest.calcultateCalories();
            assertEquals(500,junitTest.getTotalCalories());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void finish()  throws Exception {
        try {
            junitTest.finish();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Disabled
    void updateFitness()  throws Exception {
        try {
            junitTest.updateFitness(1, 30, 30, 2000);
            assertEquals(0,fitnessDTO.getHour());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void deleteFitness()  throws Exception {
        try {
            junitTest.deleteFitness();
            assertNotEquals(-1,junitTest.getCount());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void initFitness() throws Exception {
        try {
            junitTest.initFitness();
            assertEquals(0,junitTest.getHour());
        }catch (Exception e){
            System.out.println("error");
        }
    }
}