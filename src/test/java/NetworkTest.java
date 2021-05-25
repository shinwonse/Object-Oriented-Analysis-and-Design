import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    Message message = new Message();


    @Test
    void handleRequestMessageTest(){
        ArrayList<Drink> drinks = new ArrayList<>();
        Drink drink = new Drink("펩시콜라", 0, 3, "");
        drinks.add(drink);
        ArrayList<DVM> dvms = new ArrayList<>();
        DVMc dvm1 = new DVMc(drinks,1, 101);
        DVMc dvm2 = new DVMc(drinks,2, 202);
        dvms.add(dvm1);
        dvms.add(dvm2);
        Network network = new Network(dvms);

        Object o = network.handleRequestMessage(message.createMessage(1, 2, MsgType.REQUEST_STOCK, "펩시콜라"));
        assertEquals(Integer.class, o.getClass());
        assertEquals(3, (int)o);

        Object o2 = network.handleRequestMessage(message.createMessage(1, 0, MsgType.REQUEST_STOCK, "펩시콜라"));
        assertEquals(ArrayList.class, o2.getClass());
        assertEquals(101, ((ArrayList<DVM>)o2).get(0).getAddress());


    }

    @Test
    void responseBroadcastMessageTest() {
        ArrayList<Drink> drinks = new ArrayList<>();
        Drink drink = new Drink("펩시콜라", 0, 3, "");
        drinks.add(drink);
        ArrayList<DVM> dvms = new ArrayList<>();
        DVMc dvm1 = new DVMc(drinks,1, 101);
        DVMc dvm2 = new DVMc(drinks,2, 202);
        dvms.add(dvm1);
        dvms.add(dvm2);
        Network network = new Network(dvms);

        int stock = network.responseBroadcastMessage(message.createMessage(2, 1, MsgType.RESPONSE_STOCK, "3"));
        assertEquals(3, stock);


    }

    @Test
    void responseNormalMessageTest() {
        ArrayList<Drink> drinks = new ArrayList<>();
        Drink drink = new Drink("펩시콜라", 0, 3, "");
        drinks.add(drink);
        ArrayList<DVM> dvms = new ArrayList<>();
        DVMc dvm1 = new DVMc(drinks,1, 101);
        DVMc dvm2 = new DVMc(drinks,2, 202);
        dvms.add(dvm1);
        dvms.add(dvm2);
        Network network = new Network(dvms);

        int stock = network.responseNormalMessage(message.createMessage(2, 1, MsgType.RESPONSE_STOCK, "3"));
        assertEquals(3, stock);

        int address = network.responseNormalMessage(message.createMessage(2, 1, MsgType.RESPONSE_LOCATION, "101"));
        assertEquals(101, address);
    }

}