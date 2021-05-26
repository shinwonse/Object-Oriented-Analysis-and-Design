import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class StubTest {
    public static void main(String[] args){
        final int STUB_TEST_ID = 999;
        StubTest stubTest = new StubTest();
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("=======<Stub Test>=======");
            System.out.print("메시지를 보내고자 하는 DVM ID: ");
            int dstId = scan.nextInt();
            System.out.print("메시지 타입: ");
            int msgType = scan.nextInt();
            System.out.print("메시지 내용: ");
            Scanner scan2 = new Scanner(System.in);
            String msg = scan2.nextLine();

            stubTest.test(STUB_TEST_ID, dstId, msgType, msg);
        }
    }

    public void test(int src_id, int dst_id, int msg_type, String msg){
        Message message = new Message();
        message.createMessage(src_id, dst_id, msg_type, msg);
        if(dst_id == 0){
            for(int i = 1; i <= 8; i++){
                try{
                    Socket socket = new Socket("localhost", 8000 + i);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                    System.out.println("[StubTest] DVM" + i
                            + "에게 메시지 발신(메시지 유형: " + message.getMsg_type() + ", 메시지 내용: "+message.getMsg() + ")");
                    switch (message.getMsg_type()){
                        case MsgType.REQUEST_STOCK:
                        case MsgType.REQUEST_LOCATION:
                        case MsgType.DRINK_SALE_CHECK:
                            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                            Message temp = (Message)objectInputStream.readObject();
                            System.out.println("[StubTest] DVM" + temp.getSrc_id()
                                    + "로부터 메시지 수신(메시지 유형: " + temp.getMsg_type() + ", 메시지 내용: "+temp.getMsg() + ")");
                            break;
                        case MsgType.RESPONSE_STOCK:
                        case MsgType.RESPONSE_LOCATION:
                        case MsgType.DRINK_SALE_RESPONSE:
                            System.out.println("[StubTest] 발신 완료");
                            break;
                    }
                    socket.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        else{
            try{
                Socket socket = new Socket("localhost", 8000 + dst_id);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
                System.out.println("[StubTest] DVM" + message.getDst_id()
                        + "에게 메시지 발신(메시지 유형: " + message.getMsg_type() + ", 메시지 내용: "+message.getMsg() + ")");
                switch (message.getMsg_type()){
                    case MsgType.REQUEST_STOCK:
                    case MsgType.REQUEST_LOCATION:
                    case MsgType.DRINK_SALE_CHECK:
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Message temp = (Message)objectInputStream.readObject();
                        System.out.println("[StubTest] DVM" + temp.getSrc_id()
                                + "로부터 메시지 수신(메시지 유형: " + temp.getMsg_type() + ", 메시지 내용: "+temp.getMsg() + ")");
                        break;
                    case MsgType.RESPONSE_STOCK:
                    case MsgType.RESPONSE_LOCATION:
                    case MsgType.DRINK_SALE_RESPONSE:
                        System.out.println("[StubTest] 발신 완료");
                        break;
                }
                socket.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}