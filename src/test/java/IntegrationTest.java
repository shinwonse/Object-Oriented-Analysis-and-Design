import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    void selectDrinkTest_1() {
        //선택한 음료로 결제 진행이 잘 되는지 확인한다.
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(12341234,10000);

        int select_return = testFrame.controller.selectCurrentDrink(1);
        assertNotEquals(0,select_return);
        String pay_return = testFrame.controller.insertCard(testCard.getCard_num(),false);
        System.out.println(pay_return);
        assertNotEquals("",pay_return);
    }
    @Test
    void selectDrinkTest_2() {
        //범위 밖의 음료
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(12341234,10000);
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> {
            testFrame.controller.selectCurrentDrink(21);
        });
        System.out.println("번호를 잘못 입력했습니다.");
    }

    @Test
    void selectDrinkTest_3() {
        //정해진 가격대로 정확히 출력되는지 확인한다.
        //사용할 DVM = 1번 DVM
        MyFrame testFrame = new MyFrame();
        //1번 DVM 음료들의 가격 ( expected value of test )
        ArrayList<Integer> priceList = new ArrayList<>(
                Arrays.asList(1500,1500,1500,1500,1500,1500,1500,1500,1500,1500,
                        1500,1500,1500,1500,1500,1500,1500,1500,1500,1500)
        );
        //테스트 value
        OtherDVMs otherDVMs = new OtherDVMs();
        DVM testDVM = otherDVMs.getDVM(0);
        ArrayList<Drink> testcurrentDrinkList = testDVM.getDrink_list();
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                int index = i * 5 + j;
                Drink drink = testcurrentDrinkList.get(index);
                assertEquals(priceList.get(index),drink.getPrice());
            }
        }
    }

    @Test
    void proceedCardPaymentTest_1(){
        //사용가능한 카드를 입력한다.
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(12341234,10000);
        testFrame.controller.selected_drink = new Drink("코카콜라",1500,10,"");
        boolean isPrepayment = false;
        String testResult = testFrame.controller.insertCard(testCard.getCard_num(),isPrepayment);

        //카드 유효성 검사
        assertEquals(true,
                testFrame.controller.cardPayment.getCard_available(testCard.getCard_num())
        );
    }

    @Test
    void proceedCardPaymentTest_2(){
        //사용 불가능한 카드를 입력한다.
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(43214321,10000);
        testFrame.controller.selected_drink = new Drink("코카콜라",1500,10,"");
        boolean isPrepayment = false;
        String testResult = testFrame.controller.insertCard(testCard.getCard_num(),isPrepayment);

        //카드 유효성 검사
        assertEquals("not available card",testResult);
    }
    @Test
    void proceedCardPaymentTest_3(){
        //카드의 잔액이 충분할 때만 결제가 진행되는지 확인한다.
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(12341234,10000);
        testFrame.controller.selected_drink = new Drink("코카콜라",1500,10,"");
        boolean isPrepayment = false;
        String testResult = testFrame.controller.insertCard(testCard.getCard_num(),isPrepayment);

        assertNotEquals("",testResult);
    }

    @Test
    void proceedCardPaymentTest_4(){
        //카드의 잔액이 부족할 경우 잔액 부족 메시지를 출력하는지 확인한다.
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(11111111,0);
        System.out.println(testCard.getBalance());
        testFrame.controller.selected_drink = new Drink("코카콜라",1500,10,"");
        boolean isPrepayment = false;
        String testResult = testFrame.controller.insertCard(testCard.getCard_num(),isPrepayment);
        assertEquals("insufficient balance",testResult);
    }

    //-------------------재고확인
    @Test
    void checkCurrentStockTest_1(){
        //현재 자판기의 재고가 정확한지 확인한다. - 1번 자판기의 1번 음료가 초기 설정한 10개인지 확인
        MyFrame testFrame = new MyFrame();
        testFrame.controller.selected_drink = new Drink("코카콜라",1500,0,"");
        OtherDVMs otherDVMs = new OtherDVMs();
        DVM testDVM = otherDVMs.getDVM(1);
        ArrayList<Drink> testDVMDrink_list = testDVM.getDrink_list();
        int dvmStock = 0;
        for(int i = 0; i < testDVMDrink_list.size(); i++){
            if(testDVMDrink_list.get(i).getName().equals(testFrame.controller.selected_drink.getName())){
                dvmStock = testDVMDrink_list.get(i).getStock();
            }
        }
        assertEquals(10,dvmStock);
    }

    @Test
    void checkCurrentStockTest_2(){
        //현재 자판기에서 재고가 부족할 때 재고가 부족하다는 메시지를 출력하는 지 확인한다.
        //-> 재고가 없다 or 판매하지 않아서 재고가 없다
        // 1번 자판기의 3번 칠성사이다 재고가 0개인 경우
        MyFrame testFrame = new MyFrame();
        testFrame.controller.selected_drink = new Drink("칠성사이다",1500,1,"");
        //(현재 자판기에 재고가 없음)
        OtherDVMs otherDVMs = new OtherDVMs();
        DVM testDVM = otherDVMs.getDVM(1);
        ArrayList<Drink> testDVMDrink_list = testDVM.getDrink_list();
        int dvmStock = 0;
        for(int i = 0; i < testDVMDrink_list.size(); i++){
            if(testDVMDrink_list.get(i).getName().equals(testFrame.controller.selected_drink.getName())){
                dvmStock = testDVMDrink_list.get(i).getStock();
            }
        }
        assertEquals(0,dvmStock);
        if(dvmStock == 0){
            System.out.println("현재 자판기에 재고가 부족합니다.");
        }

        //( 현재 자판기에 판매하지 않는 음료 : 다른 자판기로 넘어가기 )
        int test_inputNum = 10;
        assertEquals(2,testFrame.controller.selectCurrentDrink(test_inputNum));
        if(testFrame.controller.selectCurrentDrink(test_inputNum) == 2 ) {
            System.out.println("현재 DVM에 해당 음료의 재고가 없지만 다른 DVM에 재고가 존재합니다. 선결제로 넘어갑니다.");
        }
    }

    @Test
    void checkOtherStockTest_1(){
        //자기 외의 다른 자판기의 재고가 정확하게 전달되는지 확인한다.
        MyFrame testFrame = new MyFrame();
        //1번 DVM -> 10번 음료(판매X,재고X)
        int test_drink_status = testFrame.controller.selectOtherDrink(10);
        //다른 DVM에 재고가 있어서 2가 리턴되는지 확인
        assertEquals(2, test_drink_status);
    }
    @Test
    void checkOtherStockTest_2(){
        //다른 자판기의 재고도 모두 비운 뒤 재고 확인이 정확히 이루어지는지 확인한다.
        //모든 자판기에 없는 칠성사이다
        MyFrame testFrame = new MyFrame();
        OtherDVMs otherDVMs = new OtherDVMs();
        DVM testDVM = otherDVMs.getDVM(0);
        testFrame.controller.selected_drink = testDVM.getDrink_list().get(2);

        assertEquals(0,testFrame.controller.selectCurrentDrink(3));
    }

    @Test
    void dvmLocationTest_1(){
        //재고가 있는 자판기의 위치를 올바르게 출력하는 지 확인한다.
        MyFrame testFrame = new MyFrame();

        OtherDVMs otherDVMs = new OtherDVMs();
        DVM testDVM = otherDVMs.getDVM(0);
        testFrame.controller.selected_drink = testDVM.getDrink_list().get(9);
        testFrame.controller.accessible_DVM_list
                = otherDVMs.checkOtherDVMsStock(testFrame.controller.selected_drink, testDVM);

        for(int index =0;index<testFrame.controller.accessible_DVM_list.size();index++) {
            System.out.println(testFrame.controller.accessible_DVM_list.get(index).getAddress());
        }
    }


    //------------코드
    @Test
    void makeCodeTest_1(){
        //랜덤으로 생성된 인증코드가 기존의 인증코드와 중복되지 않는지 확인한다.
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(12341234,10000);
        testFrame.controller.selected_drink = new Drink("코카콜라",1500,10,"");

        //boolean isPrepayment = true;
        //String testResult = testFrame.controller.insertCard(testCard.getCard_num(),isPrepayment);
        CardPayment testCardPayment = new CardPayment();
        Code generatedCode = testCardPayment.generateCode(testFrame.controller.selected_drink);
        ArrayList<Code> code_list = testFrame.controller.code_list;
        assertFalse(code_list.contains(generatedCode));
    }
    @Test
    void makeCodeTest_2(){
        //화면에 출력되는 인증코드가 사용자가 선결제해서 생성된 인증코드와 일치하는지 확인한다.
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(12341234,10000);
        testFrame.controller.selected_drink = new Drink("빡텐션",1500,10,"");
        OtherDVMs otherDVMs = new OtherDVMs();

        boolean isPrepayment = true;
        DVM testDVM2 = otherDVMs.getDVM(2);
        DVM testDVM3 = otherDVMs.getDVM(3);
        ArrayList<Integer> priceList = new ArrayList<>(
                Arrays.asList(1500,1500,1500,1500,1500,1500,1500,1500,1500,1500,
                        1500,1500,1500,1500,1500,1500,1500,1500,1500,1500)
        );
        testFrame.controller.accessible_DVM_list = new ArrayList<>(
                Arrays.asList(testDVM2,testDVM3)
        );
        String testResult = testFrame.controller.insertCard(testCard.getCard_num(),isPrepayment);
        Code generatedCode = testFrame.controller.code_list.get(testFrame.controller.code_list.size()-1);
        System.out.println("생성된 코드 : "+generatedCode.getCode());
        System.out.println("화면 출력 string"+testResult);
    }

    @Test
    void enterCodeTest_1(){
        //코드를 생성한 후 생성한 코드와 같은 코드를 입력해본다.
        MyFrame testFrame = new MyFrame();
        CardPayment testCardPayment = new CardPayment();
        testFrame.controller.selected_drink = new Drink("빡텐션",1500,10,"");
        Code testCode = testCardPayment.generateCode(testFrame.controller.selected_drink);

        //임의로 생성한 코드
        testFrame.controller.code_list.add(testCode);
        System.out.println(testCode.getCode());
        //같은 코드 입력 시 결제완료되는지
        String prepaymentResult = testFrame.controller.enterCode(testCode.getCode());
        System.out.println(prepaymentResult);
        assertEquals("",prepaymentResult);
    }
    @Test
    void enterCodeTest_2(){
        //코드를 생성한 후 생성한 코드와 다른 코드를 입력해본다.
        MyFrame testFrame = new MyFrame();
        CardPayment testCardPayment = new CardPayment();
        testFrame.controller.selected_drink = new Drink("빡텐션",1500,10,"");
        Code testCode = testCardPayment.generateCode(testFrame.controller.selected_drink);

        //임의로 생성한 코드
        testFrame.controller.code_list.add(testCode);
        System.out.println(testCode.getCode());
        //다른 숫자를 코드로 입력 시
        int randominputNum = 99999;
        String prepaymentResult = testFrame.controller.enterCode(randominputNum);
        assertEquals("",prepaymentResult);
    }
    @Test
    void enterCodeTest_3(){
        //생성한 코드가 없는 채로 코드 입력을 시도해본다
        MyFrame testFrame = new MyFrame();

        //코드 생성하지 않고 코드 입력
        int randominputNum = 99999;
        String prepaymentResult = testFrame.controller.enterCode(randominputNum);
        assertEquals("",prepaymentResult);
    }
    @Test
    void checkCodeTest_1(){
        //유효하지 않은 인증코드를 입력해본다
        MyFrame testFrame = new MyFrame();
        CardPayment testCardPayment = new CardPayment();
        testFrame.controller.selected_drink = new Drink("빡텐션",1500,10,"");
        Code testCode = testCardPayment.generateCode(testFrame.controller.selected_drink);
        //다른 숫자를 코드로 입력 시
        int randominputNum = 99999;

        Boolean code_available_test = testFrame.controller.checkCodeAvailable(randominputNum);
        assertEquals(true, code_available_test);
    }
    @Test
    void checkCodeTest_2(){
        //이미 사용한 인증코드를 입력해본다
        MyFrame testFrame = new MyFrame();
        CardPayment testCardPayment = new CardPayment();
        testFrame.controller.selected_drink = new Drink("빡텐션",1500,10,"");
        Code testCode = testCardPayment.generateCode(testFrame.controller.selected_drink);

        //임의로 생성한 코드
        testFrame.controller.code_list.add(testCode);
        System.out.println("생성코드 "+testCode.getCode());
        String prepaymentResult = testFrame.controller.enterCode(testCode.getCode());
        System.out.println("1번째시도\n"+prepaymentResult);
        String prepaymentResult2 = testFrame.controller.enterCode(testCode.getCode());
        if (prepaymentResult2.equals("")){
            System.out.println("\n2번째 시도\n유효하지 않은 코드입니다.");
        }
        assertEquals("",prepaymentResult2);
    }

    @Test
    void checkDrinkTest(){
        MyFrame testFrame = new MyFrame();
        Card testCard = new Card(12341234,10000);

        int select_return = testFrame.controller.selectCurrentDrink(1);
        //선택된 음료의 이름
        Drink selected_drink = testFrame.controller.selected_drink;
        if(select_return == 1 || select_return == 2){
            String pay_return = testFrame.controller.insertCard(testCard.getCard_num(),false);
            //결제 완료한 결과의 문장에, selected_drink.getName 존재하는지 확인
            assertEquals(true,pay_return.contains(selected_drink.getName()));
        }
    }

}
