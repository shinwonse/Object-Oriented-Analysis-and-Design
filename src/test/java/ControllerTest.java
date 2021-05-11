import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void selectCurrentDrink() {
    }

    @Test
    void selectOtherDrink() {
    }

    @Test
    void insertCard() {
    }

    @Test
    void enterCode() {
    }

    @Test
    void getCodeInfo() {
    }

    @Test
    void deleteCode() {
    }

    @Test
    void checkCodeAvailable() {
    }

    @Test
    void selectDVM() {
    }

    OtherDVMs otherDVMs ;
    @Test
    void startService() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<DVM> dvmList = otherDVMs.getDVMList();

        for(int i=0; i<dvmList.size(); i++){
            ArrayList<Integer> std = new ArrayList<Integer>();
            std.add(dvmList.get(i).getId());
            std.add(dvmList.get(i).getAddress());
            result.add(std);

        }


    }
}