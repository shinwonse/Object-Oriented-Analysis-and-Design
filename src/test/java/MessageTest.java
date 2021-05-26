import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    Message msg1 = new Message();
    Message msg3 = new Message();
    Message msg5 = new Message();
    Message msg7 = new Message();
    Message msg9 = new Message();

    @Test
    void getSrc_id() {
        msg1.createMessage(1,1,1);
        assertEquals(1,msg1.getSrc_id());
        msg3.createMessage(10,1,1);
        assertEquals(10,msg3.getSrc_id());
        msg5.createMessage(100,1,1);
        assertEquals(100,msg5.getSrc_id());
        msg7.createMessage(1000,1,1);
        assertEquals(1000,msg7.getSrc_id());
        msg9.createMessage(10000,1,1);
        assertEquals(10000,msg9.getSrc_id());
    }


    @Test
    void getDst_id() {
        msg1.createMessage(1,1,1);
        assertEquals(1,msg1.getDst_id());
        msg3.createMessage(10,10,1);
        assertEquals(10,msg3.getDst_id());
        msg5.createMessage(100,100,1);
        assertEquals(100,msg5.getDst_id());
        msg7.createMessage(1000,1000,1);
        assertEquals(1000,msg7.getDst_id());
        msg9.createMessage(10000,10000,1);
        assertEquals(10000,msg9.getDst_id());
    }

//    @Test
//    void setDst_id() {
//    }

    @Test
    void getMsg_type() {
        msg1.createMessage(1,1,1);
        assertEquals(1,msg1.getMsg_type());
        msg3.createMessage(10,10,2);
        assertEquals(2,msg3.getMsg_type());
        msg5.createMessage(100,100,3);
        assertEquals(3,msg5.getMsg_type());
        msg7.createMessage(1000,1000,4);
        assertEquals(4,msg7.getMsg_type());
        msg9.createMessage(10000,10000,5);
        assertEquals(5,msg9.getMsg_type());
    }

    @Test
    void getMsg() {
        msg1.createMessage(1,1,1,"안녕하세요");
        assertEquals("안녕하세요",msg1.getMsg());
        msg3.createMessage(10,10,2,"반갑습니다");
        assertEquals("반갑습니다",msg3.getMsg());
        msg5.createMessage(100,100,3,"잘부탁드립니다");
        assertEquals("잘부탁드립니다",msg5.getMsg());
        msg7.createMessage(1000,1000,4,"MS129");
        assertEquals("MS129",msg7.getMsg());
        msg9.createMessage(10000,10000,5,"DVM");
        assertEquals("DVM",msg9.getMsg());
    }

    @Test
    void createMessage() {
        Message 메시지1 = new Message();
        메시지1.setDst_id(1);
        메시지1.setSrc_id(1);
        메시지1.setMsg_type(1);
        메시지1.setMsg("");
        assertEquals(메시지1,메시지1);
        Message 메시지2 = new Message();
        메시지2.setDst_id(1421);
        메시지2.setSrc_id(14351);
        메시지2.setMsg_type(12);
        메시지2.setMsg("");
        assertEquals(메시지2,메시지2);
        Message 메시지3 = new Message();
        메시지3.setDst_id(1463);
        메시지3.setSrc_id(143);
        메시지3.setMsg_type(1234);
        메시지3.setMsg("");
        assertEquals(메시지3,메시지3);
        Message 메시지4 = new Message();
        메시지4.setDst_id(58671);
        메시지4.setSrc_id(1658);
        메시지4.setMsg_type(165);
        메시지4.setMsg("");
        assertEquals(메시지4,메시지4);
        Message 메시지5 = new Message();
        메시지5.setDst_id(179087);
        메시지5.setSrc_id(6581);
        메시지5.setMsg_type(165);
        메시지5.setMsg("");
        assertEquals(메시지5,메시지5);
    }

    @Test
    void testCreateMessage() {
        Message 메시지1 = new Message();
        메시지1.setDst_id(1);
        메시지1.setSrc_id(1);
        메시지1.setMsg_type(1);
        메시지1.setMsg("안녕하세요");
        assertEquals(메시지1,메시지1);
        Message 메시지2 = new Message();
        메시지2.setDst_id(1421);
        메시지2.setSrc_id(14351);
        메시지2.setMsg_type(12);
        메시지2.setMsg("반갑습니다");
        assertEquals(메시지2,메시지2);
        Message 메시지3 = new Message();
        메시지3.setDst_id(1463);
        메시지3.setSrc_id(143);
        메시지3.setMsg_type(1234);
        메시지3.setMsg("잘부탁드립니다");
        assertEquals(메시지3,메시지3);
        Message 메시지4 = new Message();
        메시지4.setDst_id(58671);
        메시지4.setSrc_id(1658);
        메시지4.setMsg_type(165);
        메시지4.setMsg("MS129");
        assertEquals(메시지4,메시지4);
        Message 메시지5 = new Message();
        메시지5.setDst_id(179087);
        메시지5.setSrc_id(6581);
        메시지5.setMsg_type(165);
        메시지5.setMsg("DVM");
        assertEquals(메시지5,메시지5);
    }
}