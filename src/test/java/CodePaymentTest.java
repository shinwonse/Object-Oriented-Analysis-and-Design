import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodePaymentTest {
    // 음료수 만들고
    Drink drink_info1 = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
    Drink drink_info2 = new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg");
    Drink drink_info3 = new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg");
    Drink drink_info4 = new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg");
    Drink drink_info5 = new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg");
    Drink drink_info6 = new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg");
    Drink drink_info7 = new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg");
    Drink drink_info8 = new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg");
    Drink drink_info9 = new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg");
    Drink drink_info10 = new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg");

    // 그에 따른 코드도 만들고
    Code code_info1 = new Code(11111, drink_info1);
    Code code_info2 = new Code(22222, drink_info2);
    Code code_info3 = new Code(33333, drink_info3);
    Code code_info4 = new Code(44444, drink_info4);
    Code code_info5 = new Code(55555, drink_info5);
    Code code_info6 = new Code(66666, drink_info6);
    Code code_info7 = new Code(77777, drink_info7);
    Code code_info8 = new Code(88888, drink_info8);
    Code code_info9 = new Code(99999, drink_info9);
    Code code_info10 = new Code(00000, drink_info10);

    CodePayment codePayment1 = new CodePayment(code_info3,true);

    @Test
    void getCode_info() {
        CodePayment codePayment2 = new CodePayment();
        codePayment2.setCode_info(code_info1);

        Code code = codePayment2.getCode_info();

        assertEquals(code_info1, code);
        assertEquals(code_info1.getCode(), code.getCode());
        assertEquals(code_info1.getDrink(), code.getDrink());
    }

    @Test
    void setCode_info() {
        CodePayment codePayment = new CodePayment();
        codePayment.setCode_info(code_info1);
        assertEquals(code_info1,codePayment.getCode_info());

        codePayment.setCode_info(code_info2);
        assertEquals(code_info2,codePayment.getCode_info());

        codePayment1.setCode_info(code_info4);
        assertEquals(code_info4, codePayment1.getCode_info());

    }

    @Test
    void getCodeAvailable() {
        codePayment1.setCode_info(code_info3);
        codePayment1.setCodeAvailable(true);
        assertTrue(codePayment1.getCodeAvailable());
    }

    @Test
    void setCodeAvailable() {
        codePayment1.setCodeAvailable(false);
        assertEquals(false, codePayment1.getCodeAvailable());
    }

    @Test
    void codePayment1() { // drink_info를 반환
        assertEquals(drink_info1, code_info1.getDrink());

        assertEquals(drink_info2, code_info2.getDrink());

        assertEquals(drink_info3, code_info3.getDrink());

        assertEquals(drink_info4, code_info4.getDrink());

        assertEquals(drink_info5, code_info5.getDrink());

        assertEquals(drink_info6, code_info6.getDrink());

        assertEquals(drink_info7, code_info7.getDrink());

        assertEquals(drink_info8, code_info8.getDrink());

        assertEquals(drink_info9, code_info9.getDrink());

        assertEquals(drink_info10, code_info10.getDrink());
    }

    @Test
    void CodePayment2(){
        CodePayment cp1 = new CodePayment();
        CodePayment cp2 = new CodePayment();
        cp1.setCodeAvailable(true);
        cp1.setCode_info(code_info1);
        cp2.setCodeAvailable(false);
        cp2.setCode_info(code_info2);

        assertEquals(true, cp1.getCodeAvailable());
        assertEquals(false, cp2.getCodeAvailable());

        assertEquals(code_info1, cp1.getCode_info());
        assertEquals(code_info2, cp2.getCode_info());
    }

    @Test
    void codePaymentDrinkInfo(){
        CodePayment cp1 = new CodePayment(code_info1, true);
        assertEquals(drink_info1,cp1.codePayment(code_info1));
    }
}