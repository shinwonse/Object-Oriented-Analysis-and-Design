import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    @Test
    void handleRequestMessageTest(){
        ArrayList<Drink> drinks = new ArrayList<>();
        Drink drink = new Drink("코카콜라", 0, 10, "");
        drinks.add(drink);
        Network network;
        DVM dvm1 = new DVM1(drinks, 1,101);
        DVM dvm2 = new DVM2(drinks, 2,202);
        ArrayList<DVM> dvms = new ArrayList<>();
        dvms.add(dvm1);
        dvms.add(dvm2);
        network = new Network(dvms);

        //현재 재고 확인 메세지 테스트
        Message msg0 = new Message();
        msg0.createMessage(1,1,MsgType.REQUEST_STOCK, "펩시콜라");
        Object o0 = network.handleRequestMessage(msg0);
        assertEquals(Integer.class, o0.getClass());
        assertEquals(11, (int)o0);
        
        //재고 확인 메세지 테스트
        Message msg1 = new Message();
        msg1.createMessage(1,2,MsgType.REQUEST_STOCK, "펩시콜라");
        Object o1 = network.handleRequestMessage(msg1);
        assertEquals(Integer.class, o1.getClass());
        assertEquals(0, (int)o1);

        //주소 요청-확인 메세지 테스트
        Message msg2 = new Message();
        msg2.createMessage(1,2,MsgType.REQUEST_LOCATION);
        Object o2 = network.handleRequestMessage(msg2);
        assertEquals(Integer.class, o2.getClass());
        assertEquals(202, (int)o2);
    }


}
