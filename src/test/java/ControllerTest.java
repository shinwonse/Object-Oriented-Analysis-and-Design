import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    Controller controller;
    ArrayList<Drink> drinkArrayList = new ArrayList<>(); // 전체 음료수 리스트
    ArrayList<Drink> drinkArrayList2 = new ArrayList<>(); // 전체 음료수 리스트

    ControllerTest() throws IOException {
    }
    @BeforeEach
    void networkOn() throws IOException {
        controller = new Controller();
    }

    @AfterEach
    void networkOff() throws IOException{
        for(DVM dvm : controller.getOtherDVMs().getDVMList()){
            dvm.stop();
            dvm.closeServerPort();
        }
    }

    void initDrinkList(){
        drinkArrayList.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));

        drinkArrayList2.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList2.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList2.add(new Drink("칠성사이다", 1500, 999, "src/main/resources/image/3.jpg"));
        drinkArrayList2.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList2.add(new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkArrayList2.add(new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkArrayList2.add(new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkArrayList2.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList2.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList2.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList2.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList2.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList2.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkArrayList2.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList2.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList2.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList2.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList2.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList2.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList2.add(new Drink("마운틴듀", 1500, 999, "src/main/resources/image/20.jpg"));
    }

    @Test
    void startServiceTest() throws IOException {
        ArrayList<ArrayList<Integer>> result = controller.startService();

        assertEquals(1, result.get(0).get(0));
        assertEquals(101, result.get(0).get(1));
        assertEquals(2, result.get(1).get(0));
        assertEquals(202, result.get(1).get(1));
        assertEquals(3, result.get(2).get(0));
        assertEquals(303, result.get(2).get(1));
        assertEquals(4, result.get(3).get(0));
        assertEquals(404, result.get(3).get(1));
        assertEquals(5, result.get(4).get(0));
        assertEquals(505, result.get(4).get(1));
        assertEquals(6, result.get(5).get(0));
        assertEquals(606, result.get(5).get(1));
        assertEquals(7, result.get(6).get(0));
        assertEquals(707, result.get(6).get(1));
        assertEquals(8, result.get(7).get(0));
        assertEquals(808, result.get(7).get(1));
    }

    @Test
    void selectDVMTest() throws IOException {
        initDrinkList();

        DVM currentDVM = controller.selectDVM(1);

        assertEquals(10, currentDVM.getDrink_list().get(0).getStock());
        assertEquals(11, currentDVM.getDrink_list().get(1).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(2).getStock());
        assertEquals(10, currentDVM.getDrink_list().get(3).getStock());
        assertEquals(8, currentDVM.getDrink_list().get(4).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(5).getStock());
        assertEquals(10, currentDVM.getDrink_list().get(6).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(7).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(8).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(9).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(10).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(11).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(12).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(13).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(14).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(15).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(16).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(17).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(18).getStock());
        assertEquals(0, currentDVM.getDrink_list().get(19).getStock());
    }

    @Test // 현재 DVM에 재고가 있는 경우
    void selectCurrentDrinkTest1() throws IOException {
        final int EMPTY_ALL_STOCK = 0; // 모든 DVM의 재고가 0임
        final int CUR_IN_STOCK = 1;    // 현재 DVM에 재고가 있음
        final int OTHER_IN_STOCK = 2;  // 다른 DVM에 재고가 있음

        controller.selectDVM(1);

        int result = controller.selectCurrentDrink(1);
        assertEquals(CUR_IN_STOCK, result);
    }

    @Test // 다른 DVM에 재고가 있는 경우
    void selectCurrentDrinkTest2() throws IOException {
        final int EMPTY_ALL_STOCK = 0; // 모든 DVM의 재고가 0임
        final int CUR_IN_STOCK = 1;    // 현재 DVM에 재고가 있음
        final int OTHER_IN_STOCK = 2;  // 다른 DVM에 재고가 있음
        controller.selectDVM(1);

        int result = controller.selectCurrentDrink(3);

        assertEquals(OTHER_IN_STOCK, result);
    }

    @Test // 모든 DVM에 재고가 없는 경우
    void selectCurrentDrinkTest3() throws IOException {
        final int EMPTY_ALL_STOCK = 0; // 모든 DVM의 재고가 0임
        final int CUR_IN_STOCK = 1;    // 현재 DVM에 재고가 있음
        final int OTHER_IN_STOCK = 2;  // 다른 DVM에 재고가 있음

        int result = controller.selectCurrentDrink(6);

        assertEquals(EMPTY_ALL_STOCK, result);
    }


    @Test // 다른 DVM에 재고가 있는 경우
    void selectOtherDrinkTest1() throws IOException {
        final int EMPTY_ALL_STOCK = 0; // 모든 DVM의 재고가 0임
        final int CUR_IN_STOCK = 1;    // 현재 DVM에 재고가 있음
        final int OTHER_IN_STOCK = 2;  // 다른 DVM에 재고가 있음
        controller.selectDVM(1);

        int result = controller.selectOtherDrink(14);

        assertEquals(OTHER_IN_STOCK, result);
    }

    @Test // 모든 DVM에 재고가 없는 경우
    void selectOtherDrinkTest2() throws IOException {
        final int EMPTY_ALL_STOCK = 0; // 모든 DVM의 재고가 0임
        final int CUR_IN_STOCK = 1;    // 현재 DVM에 재고가 있음
        final int OTHER_IN_STOCK = 2;  // 다른 DVM에 재고가 있음
        controller.selectDVM(1);

        int result = controller.selectOtherDrink(20);

        assertEquals(EMPTY_ALL_STOCK, result);
    }

    @Test // 1. 결제 가능카드 + 재고 존재하는 음료 결제 시도
    void insertCardTest1() {
        controller.selectDVM(1); // 1번 DVM 선택
        controller.selectCurrentDrink(1); // 코카콜라 선택

        String result = controller.insertCard(12341234, false);

        String expectedResult = "       <음료 구매 완료>" +
                "\n구매 진행한 DVM: DVM1"
                + "\n구매한 음료: 코카콜라"
                + "\n음료 가격: 1500원"
                + "\n잔여 재고: 9개"
                + "\n결제 후 카드 잔고: "
                + "8500원";
        assertEquals(expectedResult, result);
    }

    @Test // 2. 유효하지 않은 카드 사용
    void insertCardTest2() {
        controller.selectDVM(1); // 1번 DVM 선택
        controller.selectCurrentDrink(20);

        String result = controller.insertCard(99999999, false);

        String expectedResult = "not available card";
        assertEquals(expectedResult, result);
    }

    @Test // 3. 잔고 부족 사태 발생
    void insertCardTest3() {
        controller.selectDVM(1); // 1번 DVM 선택
        controller.selectCurrentDrink(2); // 1500원짜리 펩시콜라 선택
        for(int i = 0; i < 6; i++)
            controller.insertCard(12341234, false);

        String result = controller.insertCard(12341234, false); //잔고 1000원 상태에서 다시 구매

        String expectedResult = "insufficient balance";
        assertEquals(expectedResult, result);
    }

    @Test // 4. 선결제 성공
    void insertCardTest4() {
        controller.selectDVM(1); // 1번 DVM 선택
        controller.selectCurrentDrink(15);

        String result = controller.insertCard(12341234, true);

        Code code = controller.getCodeList().get(0);
        String expectedResult = "선결제 진행 DVM: 1"
                + "\n선결제한 음료수: 레쓰비"
                + "\n음료 가격: 1500"
                + "\n선결제 후 카드 잔고: 8500원"
                + "\n발급 코드: '" + code.getCode() + "'"
                + "\n\n<해당 음료 구매 가능 DVM 및 DVM 위치>"
                +"\n " + "DVM 명: DVM2 / 위치: 202" +
                "\nDVM 명: DVM5 / 위치: 505\n";
        assertEquals(expectedResult, result);
    }

    @Test // 1. 정상 코드 구매
    void enterCodeTest1(){
        controller.selectDVM(1); // 1번 DVM 선택
        controller.selectCurrentDrink(10);
        controller.insertCard(12341234, true);

        int code = controller.getCodeList().get(0).getCode();
        controller.selectDVM(2); // 1번 DVM 선택
        controller.selectCurrentDrink(1);
        String result = controller.enterCode(code);

        String expectedResult = "       <음료 구매 완료>" +
                "\n구매 진행한 DVM: DVM2"
                + "\n구매한 음료: 빡텐션"
                + "\n음료 가격: 1500원"
                + "\n잔여 재고: 9개"
                + "\n코드 정보: " + code;

        assertEquals(expectedResult, result);
    }

    @Test // 2. 비정상 코드 입력
    void enterCodeTest2(){
        controller.selectDVM(1); // 1번 DVM 선택
        controller.selectCurrentDrink(20);
        controller.insertCard(12341234, true);

        int code = controller.getCodeList().get(0).getCode();
        String result = controller.enterCode(-1);

        String expectedResult = "";
        assertEquals(expectedResult, result);
    }
}