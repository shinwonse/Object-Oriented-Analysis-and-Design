import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DVM8Test {
    ArrayList<Drink> drinkArrayList8 = new ArrayList<>(); // 전체 음료수 리스트

    void initDrinkList(){
        drinkArrayList8.add(new Drink("레드불", 1500, 20, "src/main/resources/image/8.jpg"));
        drinkArrayList8.add(new Drink("빡텐션", 1500, 1, "src/main/resources/image/10.jpg"));
        drinkArrayList8.add(new Drink("솔의눈", 1500, 2, "src/main/resources/image/18.jpg"));
        drinkArrayList8.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList8.add(new Drink("데자와", 1500, 6, "src/main/resources/image/19.jpg"));
        drinkArrayList8.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList8.add(new Drink("환타포도", 1500, 2, "src/main/resources/image/6.jpg"));
        drinkArrayList8.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList8.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList8.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList8.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList8.add(new Drink("스프라이트", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList8.add(new Drink("환타오렌지", 1500, 0, "src/main/resources/image/5.jpg"));
        drinkArrayList8.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList8.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList8.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));
        drinkArrayList8.add(new Drink("펩시콜라", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList8.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList8.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList8.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
    }

    @Test
    void getDrinkListTest(){
        initDrinkList();
        DVM8 dvm = new DVM8(drinkArrayList8, 8, 808);
        ArrayList<Drink> drink_list = dvm.getDrink_list();
        assertEquals(20, drink_list.get(0).getStock());
        assertEquals(1, drink_list.get(1).getStock());
        assertEquals(2, drink_list.get(2).getStock());
        assertEquals(0, drink_list.get(3).getStock());
        assertEquals(6, drink_list.get(4).getStock());
        assertEquals(10, drink_list.get(5).getStock());
        assertEquals(2, drink_list.get(6).getStock());
        assertEquals(0, drink_list.get(7).getStock());
        assertEquals(0, drink_list.get(8).getStock());
        assertEquals(0, drink_list.get(9).getStock());
        assertEquals(0, drink_list.get(10).getStock());
        assertEquals(0, drink_list.get(11).getStock());
        assertEquals(0, drink_list.get(12).getStock());
        assertEquals(0, drink_list.get(13).getStock());
        assertEquals(0, drink_list.get(14).getStock());
        assertEquals(0, drink_list.get(15).getStock());
        assertEquals(0, drink_list.get(16).getStock());
        assertEquals(0, drink_list.get(17).getStock());
        assertEquals(0, drink_list.get(18).getStock());
        assertEquals(0, drink_list.get(19).getStock());
    }

    @Test
    void getDVMIdTest(){
        Drink drink = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
        drinkArrayList8.add(drink);
        DVM8 dvm = new DVM8(drinkArrayList8, 8, 808);
        int id = dvm.getDVMId();
        assertEquals(8, id);
    }

    @Test
    void getAddressTest(){
        Drink drink = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
        drinkArrayList8.add(drink);
        DVM8 dvm = new DVM8(drinkArrayList8, 8, 808);
        int address = dvm.getAddress();
        assertEquals(808, address);
    }

    @Test
    void setAddressTest(){
        Drink drink = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
        drinkArrayList8.add(drink);
        DVM8 dvm = new DVM8(drinkArrayList8, 8, 808);
        int newAddress = 404;
        dvm.setAddress(newAddress);
        assertEquals(newAddress, dvm.getAddress());
    }

    @Test
    void updateStockTest(){
        Drink drink = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
        drinkArrayList8.add(drink);
        DVM8 dvm = new DVM8(drinkArrayList8, 8, 808);
        dvm.updateStock(drink);
        assertEquals(9, dvm.getDrink_list().get(0).getStock());
    }

//    @Test
//    void responseStockMessageTest(){
//        Drink drink = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
//        drinkArrayList.add(drink);
//        DVM1 dvm1 = new DVM1(drinkArrayList, 1, 101);
//        Message message = new Message();
//        message.createMessage(999, 1, MsgType.REQUEST_STOCK, "코카콜라");
//        dvm1.responseStockMessage(message);
//    }

    @Test
    void run_StockResponseTest() throws IOException {
        int SOURCE_ID = 999;
        initDrinkList();
        DVM8 dvm = new DVM8(drinkArrayList8, 8, 808);
        dvm.setServerPort();
        Socket socket = new Socket("localhost", 8000 + dvm.getDVMId());
        Socket socket2 = new Socket("localhost", 8000 + dvm.getDVMId());
        DummySocket dummySocket = new DummySocket();
        Message message = new Message();
        message.createMessage(SOURCE_ID, 8, MsgType.REQUEST_STOCK, "레드불");
        dummySocket.sendSocket(socket, message);
        Message message2 = new Message();
        message2.createMessage(SOURCE_ID, 8, 999);
        dummySocket.sendSocket(socket2, message2);
        dvm.run();
        Message receivedMessage = dummySocket.receiveSocket(socket);
        dvm.closeServerPort();

        assertEquals("20", receivedMessage.getMsg());
        assertEquals(MsgType.RESPONSE_STOCK, receivedMessage.getMsg_type());
        assertEquals(SOURCE_ID, receivedMessage.getDst_id());
        assertEquals(dvm.getDVMId(), receivedMessage.getSrc_id());
    }

    @Test
    void  run_ResponseLocationTest() throws IOException {
        int SOURCE_ID = 999;
        initDrinkList();
        DVM8 dvm = new DVM8(drinkArrayList8, 8, 808);
        dvm.setServerPort();
        Socket socket = new Socket("localhost", 8000 + dvm.getDVMId());
        Socket socket2 = new Socket("localhost", 8000 + dvm.getDVMId());
        DummySocket dummySocket = new DummySocket();
        Message message = new Message();
        message.createMessage(SOURCE_ID, 8, MsgType.REQUEST_LOCATION);
        dummySocket.sendSocket(socket, message);
        Message message2 = new Message();
        message2.createMessage(SOURCE_ID, 8, 999);
        dummySocket.sendSocket(socket2, message2);
        dvm.run();
        Message receivedMessage = dummySocket.receiveSocket(socket);
        dvm.closeServerPort();

        assertEquals("808", receivedMessage.getMsg());
        assertEquals(MsgType.RESPONSE_LOCATION, receivedMessage.getMsg_type());
        assertEquals(SOURCE_ID, receivedMessage.getDst_id());
        assertEquals(dvm.getDVMId(), receivedMessage.getSrc_id());
    }

    @Test
    void run_ResponseSaleCheckTest() throws IOException {
        int SOURCE_ID = 999;
        initDrinkList();
        DVM8 dvm = new DVM8(drinkArrayList8, 8, 808);
        dvm.setServerPort();
        Socket socket = new Socket("localhost", 8000 + dvm.getDVMId());
        Socket socket2 = new Socket("localhost", 8000 + dvm.getDVMId());
        DummySocket dummySocket = new DummySocket();
        Message message = new Message();
        message.createMessage(SOURCE_ID, 8, MsgType.DRINK_SALE_CHECK, "레드불");
        dummySocket.sendSocket(socket, message);
        Message message2 = new Message();
        message2.createMessage(SOURCE_ID, 8, 999);
        dummySocket.sendSocket(socket2, message2);
        dvm.run();
        Message receivedMessage = dummySocket.receiveSocket(socket);
        dvm.closeServerPort();

        assertEquals("19", receivedMessage.getMsg());
        assertEquals(MsgType.DRINK_SALE_RESPONSE, receivedMessage.getMsg_type());
        assertEquals(SOURCE_ID, receivedMessage.getDst_id());
        assertEquals(dvm.getDVMId(), receivedMessage.getSrc_id());
    }
}


