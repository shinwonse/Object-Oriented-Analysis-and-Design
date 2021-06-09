import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DVM6Test {
    ArrayList<Drink> drinkArrayList6 = new ArrayList<>(); // 전체 음료수 리스트

    void initDrinkList(){
        drinkArrayList6.add(new Drink("스파클링", 1500, 9, "src/main/resources/image/16.jpg"));
        drinkArrayList6.add(new Drink("레드불", 1500, 5, "src/main/resources/image/8.jpg"));
        drinkArrayList6.add(new Drink("빡텐션", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList6.add(new Drink("코카콜라", 1500, 130, "src/main/resources/image/1.jpg"));
        drinkArrayList6.add(new Drink("스프라이트", 1500, 22, "src/main/resources/image/4.jpg"));
        drinkArrayList6.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList6.add(new Drink("포카리스웨트", 1500, 10, "src/main/resources/image/11.jpg"));
        drinkArrayList6.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList6.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList6.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList6.add(new Drink("펩시콜라", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList6.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList6.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList6.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList6.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList6.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList6.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList6.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));
        drinkArrayList6.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList6.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
    }

    @Test
    void getDrinkListTest(){
        initDrinkList();
        DVM6 dvm = new DVM6(drinkArrayList6, 6, 606);
        ArrayList<Drink> drink_list = dvm.getDrink_list();
        assertEquals(9, drink_list.get(0).getStock());
        assertEquals(5, drink_list.get(1).getStock());
        assertEquals(10, drink_list.get(2).getStock());
        assertEquals(130, drink_list.get(3).getStock());
        assertEquals(22, drink_list.get(4).getStock());
        assertEquals(10, drink_list.get(5).getStock());
        assertEquals(10, drink_list.get(6).getStock());
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
        drinkArrayList6.add(drink);
        DVM6 dvm = new DVM6(drinkArrayList6, 6, 606);
        int id = dvm.getDVMId();
        assertEquals(6, id);
    }

    @Test
    void getAddressTest(){
        Drink drink = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
        drinkArrayList6.add(drink);
        DVM6 dvm = new DVM6(drinkArrayList6, 6, 606);
        int address = dvm.getAddress();
        assertEquals(606, address);
    }

    @Test
    void setAddressTest(){
        Drink drink = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
        drinkArrayList6.add(drink);
        DVM6 dvm = new DVM6(drinkArrayList6, 6, 606);
        int newAddress = 404;
        dvm.setAddress(newAddress);
        assertEquals(newAddress, dvm.getAddress());
    }

    @Test
    void updateStockTest(){
        Drink drink = new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg");
        drinkArrayList6.add(drink);
        DVM6 dvm = new DVM6(drinkArrayList6, 6, 606);
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
        DVM6 dvm = new DVM6(drinkArrayList6, 6, 606);
        dvm.setServerPort();
        Socket socket = new Socket("localhost", 8000 + dvm.getDVMId());
        Socket socket2 = new Socket("localhost", 8000 + dvm.getDVMId());
        DummySocket dummySocket = new DummySocket();
        Message message = new Message();
        message.createMessage(SOURCE_ID, 6, MsgType.REQUEST_STOCK, "스파클링");
        dummySocket.sendSocket(socket, message);
        Message message2 = new Message();
        message2.createMessage(SOURCE_ID, 6, 999);
        dummySocket.sendSocket(socket2, message2);
        dvm.run();
        Message receivedMessage = dummySocket.receiveSocket(socket);
        dvm.closeServerPort();

        assertEquals("9", receivedMessage.getMsg());
        assertEquals(MsgType.RESPONSE_STOCK, receivedMessage.getMsg_type());
        assertEquals(SOURCE_ID, receivedMessage.getDst_id());
        assertEquals(dvm.getDVMId(), receivedMessage.getSrc_id());
    }

    @Test
    void  run_ResponseLocationTest() throws IOException {
        int SOURCE_ID = 999;
        initDrinkList();
        DVM6 dvm = new DVM6(drinkArrayList6, 6, 606);
        dvm.setServerPort();
        Socket socket = new Socket("localhost", 8000 + dvm.getDVMId());
        Socket socket2 = new Socket("localhost", 8000 + dvm.getDVMId());
        DummySocket dummySocket = new DummySocket();
        Message message = new Message();
        message.createMessage(SOURCE_ID, 6, MsgType.REQUEST_LOCATION);
        dummySocket.sendSocket(socket, message);
        Message message2 = new Message();
        message2.createMessage(SOURCE_ID, 6, 999);
        dummySocket.sendSocket(socket2, message2);
        dvm.run();
        Message receivedMessage = dummySocket.receiveSocket(socket);
        dvm.closeServerPort();

        assertEquals("606", receivedMessage.getMsg());
        assertEquals(MsgType.RESPONSE_LOCATION, receivedMessage.getMsg_type());
        assertEquals(SOURCE_ID, receivedMessage.getDst_id());
        assertEquals(dvm.getDVMId(), receivedMessage.getSrc_id());
    }

    @Test
    void run_ResponseSaleCheckTest() throws IOException {
        int SOURCE_ID = 999;
        initDrinkList();
        DVM6 dvm = new DVM6(drinkArrayList6, 6, 606);
        dvm.setServerPort();
        Socket socket = new Socket("localhost", 8000 + dvm.getDVMId());
        Socket socket2 = new Socket("localhost", 8000 + dvm.getDVMId());
        DummySocket dummySocket = new DummySocket();
        Message message = new Message();
        message.createMessage(SOURCE_ID, 6, MsgType.DRINK_SALE_CHECK, "스파클링");
        dummySocket.sendSocket(socket, message);
        Message message2 = new Message();
        message2.createMessage(SOURCE_ID, 6, 999);
        dummySocket.sendSocket(socket2, message2);
        dvm.run();
        Message receivedMessage = dummySocket.receiveSocket(socket);
        dvm.closeServerPort();

        assertEquals("8", receivedMessage.getMsg());
        assertEquals(MsgType.DRINK_SALE_RESPONSE, receivedMessage.getMsg_type());
        assertEquals(SOURCE_ID, receivedMessage.getDst_id());
        assertEquals(dvm.getDVMId(), receivedMessage.getSrc_id());
    }
}


