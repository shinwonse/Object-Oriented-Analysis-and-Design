import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OtherDVMsTest {
    OtherDVMs otherDVMs;


    OtherDVMsTest() throws IOException {
    }

    @BeforeEach
    void dvmOn() throws IOException {
        otherDVMs = new OtherDVMs();
    }

    @AfterEach
    void dvmOff() throws IOException{
        for(DVM dvm : otherDVMs.getDVMList()){
            dvm.stop();
            dvm.closeServerPort();
        }
    }

    @Test
    void getDVMTest(){
        DVM dvm = otherDVMs.getDVM(0);

        assertEquals(1, dvm.getDVMId());
        assertEquals(101, dvm.getAddress());
        assertEquals(10, dvm.getDrink_list().get(0).getStock());
    }

    @Test
    void getDVMListTest(){
        ArrayList<DVM> dvmList = otherDVMs.getDVMList();

        assertEquals(1, dvmList.get(0).getDVMId());
        assertEquals(101, dvmList.get(0).getAddress());
        assertEquals(10, dvmList.get(0).getDrink_list().get(0).getStock());
    }

    @Test
    void checkCurrentDVMsStockTest1(){
        Drink drink = otherDVMs.getDVM(0).getDrink_list().get(0);
        DVM currentDVM = otherDVMs.getDVM(0);

        boolean result = otherDVMs.checkCurrentDVMsStock(drink, currentDVM);

        assertTrue(result);
    }

    @Test
    void checkCurrentDVMsStockTest2(){
        Drink drink = otherDVMs.getDVM(0).getDrink_list().get(15);
        DVM dvm = otherDVMs.getDVM(0);

        boolean result = otherDVMs.checkCurrentDVMsStock(drink, dvm);

        assertFalse(result);
    }

    @Test
    void requestDrinkTest(){
        Drink drink = otherDVMs.getDVM(0).getDrink_list().get(0);
        DVM dvm = otherDVMs.getDVM(0);

        String result = otherDVMs.requestDrink(drink, dvm);

        String expectedResult = "       <음료 구매 완료>" +
                "\n구매 진행한 DVM: DVM1"
                + "\n구매한 음료: 코카콜라"
                + "\n음료 가격: 1500원"
                + "\n잔여 재고: 9개";
        assertEquals(expectedResult, result);
    }

    @Test
    void showAccessibleDVMsLocationTest(){
        ArrayList<DVM> dvmList = new ArrayList<>();
        dvmList.add(otherDVMs.getDVM(0));
        dvmList.add(otherDVMs.getDVM(1));
        DVM dvm = otherDVMs.getDVM(0);

        String result = otherDVMs.showAccessibleDVMsLocation(dvmList, dvm);

        String expectedResult = "DVM 명: DVM1 / 위치: 101\nDVM 명: DVM2 / 위치: 202\n";
        assertEquals(expectedResult, result);
    }
}