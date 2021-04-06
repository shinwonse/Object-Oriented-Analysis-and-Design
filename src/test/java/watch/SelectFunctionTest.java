package watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectFunctionTest {
    public static SelectFunction junitTest;

    SelectFunctionTest(){
    }

    @BeforeAll
    public static void makeInstance(){
        junitTest = new SelectFunction();
    }

    @Test
    void getfunctionName() throws Exception{
        try{
            assertEquals("TimeKeeping", junitTest.getfunctionName());
        }catch (Exception e){
            System.out.println("getfunctionName failed");
        }
    }

    @Test
    void getfunctionList() throws Exception{
        try{
            assertEquals(1, junitTest.getfunctionList());
        }catch (Exception e){
            System.out.println("getfunctionList failed");
        }
    }

    @Test
    void getFunctionListBool() throws Exception{
        try{
            assertEquals(true, junitTest.getFunctionListBool(0));
        }catch (Exception e){
            System.out.println("getFunctionListBool failed");
        }
    }

    @Test
    void checkFirstDisplay() throws Exception{
        try{
            assertEquals(true, junitTest.checkFirstDisplay("TimeKeeping"));
        }catch (Exception e){
            System.out.println("checkFirstDisplay failed");
        }
    }

    @Test
    void checkDefaultDisplay() throws Exception{
        try{
            assertEquals(true, junitTest.checkDefaultDisplay());
        }catch (Exception e){
            System.out.println("checkDefaultDisplay failed");
        }
    }

    @Test
    void setFunctionList() throws Exception{
        try{
            assertEquals(true, junitTest.setFunctionList(2));
        }catch (Exception e){
            System.out.println("setFunctionList failed");
        }
    }

    @Disabled
    void check_four_fuction() throws Exception{
        try{
            assertEquals(true, junitTest.check_four_fuction());
        }catch (Exception e){
            System.out.println("check_four_function failed");
        }
    }
}