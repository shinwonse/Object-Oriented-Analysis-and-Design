import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    ArrayList<DVM> dvmList = new ArrayList<>();
    ArrayList<Drink> drinkList = new ArrayList<>();
    ArrayList<Drink> drinkList2 = new ArrayList<>();
    DVM dvm1, dvm2, dvm3, dvm4, dvm5, dvm6, dvm7, dvm8;

    void initDrinkList(){
        drinkList.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkList.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkList.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkList.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkList.add(new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkList.add(new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkList.add(new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkList.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkList.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkList.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkList.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkList.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkList.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkList.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkList.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkList.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkList.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkList.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkList.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkList.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));

        drinkList2.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkList2.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkList2.add(new Drink("칠성사이다", 1500, 999, "src/main/resources/image/3.jpg"));
        drinkList2.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkList2.add(new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkList2.add(new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkList2.add(new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkList2.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkList2.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkList2.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkList2.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkList2.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkList2.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkList2.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkList2.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkList2.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkList2.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkList2.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkList2.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkList2.add(new Drink("마운틴듀", 1500, 999, "src/main/resources/image/20.jpg"));
    }

    NetworkTest() throws IOException {
    }

    @BeforeEach
    void dvmOn() throws IOException {
        initDrinkList();
        dvm1 = new DVM(drinkList, 1, 101);
        dvm1.setServerPort();
        dvm2 = new DVM(drinkList2, 2, 202);
        dvm2.setServerPort();
        dvm3 = new DVM(drinkList, 3, 303);
        dvm3.setServerPort();
        dvm4 = new DVM(drinkList, 4, 404);
        dvm4.setServerPort();
        dvm5 = new DVM(drinkList2, 5, 505);
        dvm5.setServerPort();
        dvm6 = new DVM(drinkList, 6, 606);
        dvm6.setServerPort();
        dvm7 = new DVM(drinkList2, 7, 707);
        dvm7.setServerPort();
        dvm8 = new DVM(drinkList, 8, 808);
        dvm8.setServerPort();
        dvm1.start();
        dvm2.start();
        dvm3.start();
        dvm4.start();
        dvm5.start();
        dvm6.start();
        dvm7.start();
        dvm8.start();

        dvmList.add(dvm1);
        dvmList.add(dvm2);
        dvmList.add(dvm3);
        dvmList.add(dvm4);
        dvmList.add(dvm5);
        dvmList.add(dvm6);
        dvmList.add(dvm7);
        dvmList.add(dvm8);
    }

    @AfterEach
    void dvmOff() throws IOException{
        dvm1.stop();
        dvm1.closeServerPort();
        dvm2.stop();
        dvm2.closeServerPort();
        dvm3.stop();
        dvm3.closeServerPort();
        dvm4.stop();
        dvm4.closeServerPort();
        dvm5.stop();
        dvm5.closeServerPort();
        dvm6.stop();
        dvm6.closeServerPort();
        dvm7.stop();
        dvm7.closeServerPort();
        dvm8.stop();
        dvm8.closeServerPort();
    }

    @Test // 1. REQUEST_STOCK, dst_id != 0 인 경우
    void handleRequestMessageTest1(){
        Network network = new Network(dvmList);
        Message message = new Message();
        message.createMessage(999, 1, MsgType.REQUEST_STOCK, "코카콜라");

        int stock =  (int) network.handleRequestMessage(message);

        assertEquals(10, stock);
    }

    @Test // 2. REQUEST_STOCK, dst_id == 0 인 경우
    void handleRequestMessageTest2(){
        Network network = new Network(dvmList);
        Message message = new Message();
        message.createMessage(999, 0, MsgType.REQUEST_STOCK, "코카콜라");

        ArrayList<DVM> dvmList = (ArrayList<DVM>) network.handleRequestMessage(message);

        assertEquals(8, dvmList.size());
    }

    @Test // 3. REQUEST_LOCATION 인 경우
    void handleRequestMessageTest3(){
        Network network = new Network(dvmList);
        Message message = new Message();
        message.createMessage(999, 1, MsgType.REQUEST_LOCATION);

        int address = (int) network.handleRequestMessage(message);

        assertEquals(101, address);
    }

    @Test // 4. DRINK_SALE_CHECK 인 경우
    void handleRequestMessageTest4(){
        Network network = new Network(dvmList);
        Message message = new Message();
        message.createMessage(999, 1, MsgType.DRINK_SALE_CHECK, "코카콜라");

        int remainedStock = (int) network.handleRequestMessage(message);

        assertEquals(9, remainedStock);
    }

    @Test // 5. 의미없는 msg_type 인 경우
    void handleRequestMessageTest5(){
        Network network = new Network(dvmList);
        Message message = new Message();
        message.createMessage(999, 1, -1, "코카콜라");

        assertNull(network.handleRequestMessage(message));
    }


}
