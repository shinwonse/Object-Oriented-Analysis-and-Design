import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OtherDVMsTest {
    Controller controller = new Controller();
    OtherDVMs otherDVMs = new OtherDVMs();
    @Test
    void getDVMTest() {
        DVM dvm = otherDVMs.getDVM(0);
        assertEquals(1, dvm.getDVMId());
    }

    @Test
    void getDVMListTest() {
        ArrayList<DVM> dvmList = otherDVMs.getDVMList();
        assertEquals(8, dvmList.size());
        assertEquals(8, dvmList.get(dvmList.size() - 1).getDVMId());
    }

    @Test
    void checkCurrentDVMsStockTest() {
        Drink drink = new Drink("빡텐션", 0, 0, "");
        DVM currentDVM = otherDVMs.getDVM(0);

        boolean b = otherDVMs.checkCurrentDVMsStock(drink, currentDVM);
        assertFalse(b);
    }

    @Test
    void checkOtherDVMsStockTest() {
        Drink drink = new Drink("빡텐션", 0, 0, "");

        DVM currentDVM = otherDVMs.getDVM(0);
        ArrayList<DVM> dvms = otherDVMs.checkOtherDVMsStock(drink, currentDVM);

        assertNotEquals(dvms.size(), 0);
    }

    @Test
    void requestDrinkTest() {
        Drink drink = new Drink("코카콜라", 0, 0, "");
        String s = otherDVMs.requestDrink(drink, otherDVMs.getDVM(0));

        assertNotEquals(s, "");

    }

    @Test
    void showAccessibleDVMsLocationTest() {
        ArrayList<DVM> accessibleList = new ArrayList<>();
        Drink drink = new Drink("코카콜라", 0, 1, "");
        ArrayList<Drink> drinks = new ArrayList<>();
        drinks.add(drink);
        DVM dvm1 = new DVM1(drinks, 1, 101);
        DVM dvm2 = new DVM2(drinks, 2, 202);
        accessibleList.add(dvm1);
        accessibleList.add(dvm2);

        String s = otherDVMs.showAccessibleDVMsLocation(accessibleList, otherDVMs.getDVM(0));

        String expectedString = "DVM 명: DVM1 / 위치: 101\nDVM 명: DVM2 / 위치: 202\n";

        assertEquals(expectedString, s);
    }
}