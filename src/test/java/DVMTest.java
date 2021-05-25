import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DVMTest {


    @Test
    void getDrink_list() {
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg"));

        ArrayList<Drink> drink_list = drinkArrayList;

        assertEquals(drink_list.getClass(),ArrayList.class );
        assertEquals(drink_list.get(0).getClass(), Drink.class);
    }

    @Test
    void getId() {
        int id = 1;
        assertEquals(1, id);

    }

    @Test
    void getAddress() {
        int adress = 101;
        assertEquals(101, adress);
    }

    Message message = new Message();

    @Test
    void makeStockResponseMessageTest1() {
        int dst_id = 1;
        String stockMsg = "9";
        int id = 4;
        message.createMessage(4, dst_id, MsgType.RESPONSE_STOCK, stockMsg);
        assertEquals(Message.class , message.getClass());

    }


    @Test
    void responseStockMessage() {
        //network.responseBroadcastMessage(message)를 반환하는 메소드 -> network 의 메소드에 문제가 없다면 문제없음
    }

    @Test
    void updateStockTest1() {
        //Drink 의 updateStock 호출하는 메소드
        Drink a = new Drink("name", 1000, 1, "image");
        a.updateStock();

        assertEquals(0, a.getStock());

    }

    @Test
    void updateStockTest2() {
        //Drink 의 updateStock 호출하는 메소드
        Drink a = new Drink("name", 1000, 0, "image");
        a.updateStock();

        assertEquals(-1, a.getStock());
                                                                    // 이런 코스는 나올리 없지만 처리해줘야하는가

    }

    @Test
    void makeLocationResponseMessageTest1() {                                                //메시지 객제가 메시지를 만드는
        Message message = new Message();
        message.createMessage(1, 2, MsgType.RESPONSE_LOCATION, "101");

        assertEquals(1,message.getSrc_id());
    }
    @Test
    void makeLocationResponseMessageTest2() {
        Message message = new Message();
        message.createMessage(1, 2, MsgType.RESPONSE_LOCATION, "101");

        assertEquals("101",message.getMsg());
    }

    @Test
    void responseLocationMessageTest1() {
        Message message = new Message();
        message.createMessage(1, 2, MsgType.RESPONSE_LOCATION, "101");

        int address = -1;
        int msg_type = 5;  //RESPONSE_LOCATION
        String msg = message.getMsg();
        if (msg_type == MsgType.RESPONSE_LOCATION) {
            address = Integer.parseInt(msg);
        }
        assertEquals(101, address);
        assertNotEquals(-1, address);
    }

    @Test
    void responseLocationMessageTest2() {
        Message message = new Message();
        message.createMessage(1, 2, MsgType.RESPONSE_LOCATION, "101");


        int address = -1;
        int msg_type = 4;  //not RESPONSE_LOCATION
        String msg = message.getMsg();
        if (msg_type == MsgType.RESPONSE_LOCATION) {
            address = Integer.parseInt(msg);
        }
        assertEquals(-1, address);
    }
}