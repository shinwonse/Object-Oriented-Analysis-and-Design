import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class DummySocket {
    public void sendSocket(Socket socket, Message msg){
        try{
            //socket = new Socket("localhost", 8000 + msg.getDst_id());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Message message = msg;
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            System.out.println("[Controller] DVM" + msg.getDst_id()
                    + "에게 메시지 발신(유형: " + message.getMsg_type() + "(판매 확인 요청), 내용: " + message.getMsg() + ")");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Message receiveSocket(Socket socket){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message receivedMsg = (Message) objectInputStream.readObject();
            System.out.println("[Controller] DVM" + receivedMsg.getSrc_id()
                    + "으로부터 메시지 수신(유형: " + receivedMsg.getMsg_type() + "(판매 확인 응답), 내용: " + receivedMsg.getMsg() + ")");

            socket.close();
            return receivedMsg;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}