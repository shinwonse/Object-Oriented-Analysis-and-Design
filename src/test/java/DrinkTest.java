import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {
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

    @Test
    void getName() {
        assertEquals("코카콜라", drink1.getName());

        assertEquals("펩시콜라", drink2.getName());

        assertEquals("칠성사이다", drink3.getName());

        assertEquals("스프라이트", drink4.getName());

        assertEquals("환타오렌지", drink5.getName());

        assertEquals("환타포도", drink6.getName());

        assertEquals("핫식스", drink7.getName());

        assertEquals("레드불", drink8.getName());

        assertEquals("몬스터드링크", drink9.getName());

        assertEquals("빡텐션", drink10.getName());
    }

    @Test
    void getPrice() {
        assertEquals(1500, drink1.getPrice());

        assertEquals(1500, drink2.getPrice());

        assertEquals(1500, drink3.getPrice());

        assertEquals(1500, drink4.getPrice());

        assertEquals(1500, drink5.getPrice());

        assertEquals(1500, drink6.getPrice());

        assertEquals(1500, drink7.getPrice());

        assertEquals(1500, drink8.getPrice());

        assertEquals(1500, drink9.getPrice());

        assertEquals(1500, drink10.getPrice());
    }

//    @Test
//    void setPrice() {
//    }

    @Test
    void getStock() {
        assertEquals(10, drink1.getStock());

        assertEquals(11, drink2.getStock());

        assertEquals(0, drink3.getStock());

        assertEquals(10, drink4.getStock());

        assertEquals(8, drink5.getStock());

        assertEquals(1, drink6.getStock());

        assertEquals(10, drink7.getStock());

        assertEquals(0, drink8.getStock());

        assertEquals(0, drink9.getStock());

        assertEquals(0, drink10.getStock());
    }

    @Test
    void updateStock() {
        drink1.updateStock();
        assertEquals(9, drink1.getStock());

        drink2.updateStock();
        assertEquals(10,drink2.getStock());

        drink3.updateStock(); // 재고 없는거임 예외
        assertEquals(-1,drink3.getStock());

        drink4.updateStock();
        assertEquals(9,drink4.getStock());

        drink5.updateStock();
        assertEquals(7,drink5.getStock());

        drink6.updateStock();
        assertEquals(0,drink6.getStock());

        drink7.updateStock();
        assertEquals(9,drink7.getStock());

        drink8.updateStock(); // 재고 없는거임 예외
        assertEquals(-1,drink8.getStock());

        drink9.updateStock(); // 재고 없는거임 예외
        assertEquals(-1,drink9.getStock());

        drink10.updateStock(); // 재고 없는거임 예외
        assertEquals(-1,drink10.getStock());
    }

    @Test
    void getImgURL() {
        assertEquals("src/main/resources/image/1.jpg",drink1.getImgURL());

        assertEquals("src/main/resources/image/2.jpg",drink2.getImgURL());

        assertEquals("src/main/resources/image/3.jpg",drink3.getImgURL());

        assertEquals("src/main/resources/image/4.jpg",drink4.getImgURL());

        assertEquals("src/main/resources/image/5.jpg",drink5.getImgURL());

        assertEquals("src/main/resources/image/6.jpg",drink6.getImgURL());

        assertEquals("src/main/resources/image/7.jpg",drink7.getImgURL());

        assertEquals("src/main/resources/image/8.jpg",drink8.getImgURL());

        assertEquals("src/main/resources/image/9.jpg",drink9.getImgURL());

        assertEquals("src/main/resources/image/10.jpg",drink10.getImgURL());
    }
}