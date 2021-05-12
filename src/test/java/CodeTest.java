import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {
    // 음료수 만들고
    Drink drink1 = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
    Drink drink2 = new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg");
    Drink drink3 = new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg");
    Drink drink4 = new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg");
    Drink drink5 = new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg");
    Drink drink6 = new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg");
    Drink drink7 = new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg");
    Drink drink8 = new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg");
    Drink drink9 = new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg");
    Drink drink10 = new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg");

    // 그에 따른 코드도 만들고
    Code code1 = new Code(11111, drink1);
    Code code2 = new Code(22222, drink2);
    Code code3 = new Code(33333, drink3);
    Code code4 = new Code(44444, drink4);
    Code code5 = new Code(55555, drink5);
    Code code6 = new Code(66666, drink6);
    Code code7 = new Code(77777, drink7);
    Code code8 = new Code(88888, drink8);
    Code code9 = new Code(99999, drink9);
    Code code10 = new Code(00000, drink10);


    @Test
    void getCode() { // 코드가 맞게 반환되는지
        assertEquals(11111, code1.getCode());

        assertEquals(22222, code2.getCode());

        assertEquals(33333, code3.getCode());

        assertEquals(44444, code4.getCode());

        assertEquals(55555, code5.getCode());

        assertEquals(66666, code6.getCode());

        assertEquals(77777, code7.getCode());

        assertEquals(88888, code8.getCode());

        assertEquals(99999, code9.getCode());

        assertEquals(00000, code10.getCode());
    }

//    @Test // 안 썼고
//    void setCode() {
//    }

    @Test
    void getDrink() { // 음료 정보가 맞게 반환되는지

        assertEquals(drink1, code1.getDrink());

        assertEquals(drink2, code2.getDrink());

        assertEquals(drink3, code3.getDrink());

        assertEquals(drink4, code4.getDrink());

        assertEquals(drink5, code5.getDrink());

        assertEquals(drink6, code6.getDrink());

        assertEquals(drink7, code7.getDrink());

        assertEquals(drink8, code8.getDrink());

        assertEquals(drink9, code9.getDrink());

        assertEquals(drink10, code10.getDrink());
    }
}