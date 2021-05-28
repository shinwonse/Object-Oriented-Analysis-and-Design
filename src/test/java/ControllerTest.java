import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    ArrayList<Code> code_list = new ArrayList<Code>();
    int currentDVMIndex;
    ArrayList<DVM> accessible_DVM_list;
    Card card_info;
    OtherDVMs otherDVMs;
    Drink selected_drink;
    CardPayment cardPayment = new CardPayment();
    CodePayment codePayment = new CodePayment();

    Code testCode = new Code(12345,new Drink("코카콜라",1000,10,""));


    @Test
    @Disabled
    void selectCurrentDrink() {
        otherDVMs = new OtherDVMs();
        DVM testDVM = otherDVMs.getDVM(0);
        boolean current_stock;
        int currentDVMIndex = 0;
        int testDialNum = 3;
        if(testDVM.getDrink_list().get(testDialNum).getStock() > 0){
            current_stock = true;
        }
        else
            current_stock = false;
        assertEquals(false,current_stock);
    }


    @Test
    @Disabled
    void selectOtherDrink() {
        otherDVMs = new OtherDVMs();

        DVM currentDVM2 = otherDVMs.getDVM(0);
        Drink selected_drink = currentDVM2.getDrink_list().get(2);   // 1번 DVM 칠성사이다
        ArrayList<DVM> accessible_DVM_list = otherDVMs.checkOtherDVMsStock(selected_drink, currentDVM2);
        assertEquals(0,accessible_DVM_list.size());

        DVM currentDVM3 = otherDVMs.getDVM(1);
        Drink selected_drink2 = currentDVM3.getDrink_list().get(12);   // 2번 DVM 핫식스
        ArrayList<DVM> accessible_DVM_list2 = otherDVMs.checkOtherDVMsStock(selected_drink2, currentDVM3);
        assertEquals(0,accessible_DVM_list2.size());
        DVM testDVM2 = otherDVMs.getDVM(1);
        DVM testDVM3 = otherDVMs.getDVM(2);
        DVM testDVM6 = otherDVMs.getDVM(5);
        DVM testDVM8 = otherDVMs.getDVM(7);
        ArrayList<DVM> access_test_list = new ArrayList<>(Arrays.asList(testDVM2,testDVM3
                ,testDVM6,testDVM8));
        currentDVMIndex = 0;
        int testDialNum = 10; //1번 DVM 빡텐션
        assertTrue(testDialNum>=8 && testDialNum <=20);
        DVM currentDVM = otherDVMs.getDVM(currentDVMIndex);
        selected_drink = currentDVM.getDrink_list().get(testDialNum-1);
        System.out.println(selected_drink.getName());
        accessible_DVM_list = otherDVMs.checkOtherDVMsStock(selected_drink,currentDVM);
        assertEquals(access_test_list,accessible_DVM_list);
    }

    @Test
    void insertCard() {
        int testCardNum = 12341234;
        boolean isPrepayment = false;
        boolean test_card_avail = cardPayment.getCard_available(testCardNum);
        assertEquals(true, test_card_avail);
        Card testCard = cardPayment.getCard(testCardNum);
        assertEquals(10000,testCard.getBalance());
    }
    @Test
    void insertCard2() {
        otherDVMs = new OtherDVMs();
        int testCardNum = 12341234;
        boolean isPrepayment = true;
        Card testCard = cardPayment.getCard(testCardNum);
        Drink testDrink = new Drink("코카콜라",1000,10,"");
        Code code = cardPayment.generateCode(testDrink);
        assertNotNull(code.getCode());
    }

    @Test
    void enterCode() {
        otherDVMs = new OtherDVMs();
        currentDVMIndex = 1;
        DVM currentDVM = otherDVMs.getDVM(currentDVMIndex);

        Code testCode = new Code(12345,new Drink("코카콜라",1000,10,""));
        code_list.add(testCode);
        Drink testDrink = codePayment.codePayment(testCode);
        assertEquals("코카콜라",testDrink.getName());
        String testResult = otherDVMs.requestDrink(testDrink, currentDVM);
        assertNotEquals("",testResult);
    }

    @Test
    void getCodeInfo() {
        code_list= new ArrayList<Code>();
        Drink testDrink = new Drink("코카콜라",1000,10,"");
        Code testCode = new Code(12345,testDrink);
        Code testCode2 = new Code(54321,testDrink);
        int testCodeNum = 12345;
        code_list.add(testCode); code_list.add(testCode2);
        for(Code code : code_list){
            if(code.getCode() == testCodeNum) {
                assertEquals(true, (code.getCode() == testCodeNum));
            }
        }
    }

    @Test
    void deleteCode() {
        code_list= new ArrayList<Code>();
        Drink testDrink = new Drink("코카콜라",1000,10,"");
        Code testCode = new Code(12345,testDrink);
        Code testCode2 = new Code(54321,testDrink);
        code_list.add(testCode);
        code_list.add(testCode2);
        int testCodeNum = 12345;
        for (Code code : code_list) {
            if(testCodeNum == code.getCode()){
                code_list.remove(code);
            }
        }
        assertEquals(1,code_list.size());
    }

    @Test
    void checkCodeAvailable() {
        Drink testDrink = new Drink("코카콜라",1000,10,"");
        Code testCode = new Code(12345,testDrink);
        code_list.add(testCode);

        int testCodeNum = 12345;
        for (Code code : code_list) {
            assertEquals(testCodeNum,code.getCode());
        }
    }

    @Test
    void selectDVM() {
        int testNum = 1;
        currentDVMIndex = testNum-1;
        otherDVMs = new OtherDVMs();
        DVM testDVM = otherDVMs.getDVM(currentDVMIndex);
        System.out.println(testDVM.getDVMId());
        assertEquals(1,testDVM.getDVMId());

    }

    @Test
    @Disabled
    void startService() {
        otherDVMs = new OtherDVMs();
        int testDVMListSize = 8;

        ArrayList<DVM> dvmList = otherDVMs.getDVMList();
        assertEquals(testDVMListSize,dvmList.size());
        int testAddress = 101;
        for(int i=0; i<dvmList.size(); i++){
            ArrayList<Integer> std = new ArrayList<Integer>();

            assertEquals(i+1,dvmList.get(i).getDVMId());
            std.add(dvmList.get(i).getDVMId());

            assertEquals(testAddress,dvmList.get(i).getAddress());
            testAddress += 101;
            std.add(dvmList.get(i).getAddress());
        }
    }
}