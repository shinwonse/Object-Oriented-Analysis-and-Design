import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Network extends Thread {
    ArrayList<DVM> dvmList;
    Network(ArrayList<DVM> dvmList){
        this.dvmList = dvmList;
    }
    // 메시지 종합 핸들러
    public Object handleRequestMessage(Message message){
        int src_id = message.getSrc_id();
        int dst_id = message.getDst_id();
        int msg_type = message.getMsg_type();
        String msg = message.getMsg();
        switch(msg_type){
            case MsgType.REQUEST_STOCK:
                if(dst_id == 0){
                    ArrayList<DVM> accessibleDVMList = (ArrayList<DVM>)handleStockRequest(src_id, 0, msg);
                    return accessibleDVMList;
                }
                else{
                    int stock = (int)handleStockRequest(src_id, dst_id, msg);
                    return stock;
                }

            case MsgType.REQUEST_LOCATION:
                int address = handleLocationRequest(src_id, dst_id);
                return address;

            case MsgType.DRINK_SALE_CHECK:
                int remainedStock = (int)handleSaleRequest(src_id, dst_id, msg);
                return remainedStock;
        }
        return null;
    }

    // 판매 확인 요청
    private Object handleSaleRequest(int src_id, int dst_id, String msg) {
        int remainedStock = 0;
        try {
            Socket socket = new Socket("localhost", 8000 + dst_id);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            Message message = new Message();
            message.createMessage(src_id, dst_id, MsgType.DRINK_SALE_CHECK, msg);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            System.out.println("[Controller] DVM" + dst_id
                    + "에게 메시지 발신(유형: " + message.getMsg_type() + "(판매 확인 요청), 내용: " + message.getMsg() + ")");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message receivedMsg = (Message) objectInputStream.readObject();
            System.out.println("[Controller] DVM" + receivedMsg.getSrc_id()
                    + "으로부터 메시지 수신(유형: " + receivedMsg.getMsg_type() + "(판매 확인 응답), 내용: " + receivedMsg.getMsg() + ")");
            remainedStock = Integer.parseInt(receivedMsg.getMsg());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return remainedStock;
    }

    // 재고 확인 요청
    private Object handleStockRequest(int src_id, int dst_id, String msg) {
        ArrayList<DVM> accessibleDVMList = new ArrayList<>();
        int stock = 0;
        if(dst_id == 0){
            for(int i = 1; i <= dvmList.size(); i++){
                try {
                    Socket socket = new Socket("localhost", 8000 + i);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                    Message message = new Message();
                    message.createMessage(src_id, i - 1, MsgType.REQUEST_STOCK, msg);
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                    System.out.println("[Controller] DVM" + i
                            + "에게 메시지 발신(유형: " + message.getMsg_type() + ", 내용: " + message.getMsg() + ")");
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Message receivedMsg = (Message) objectInputStream.readObject();
                    System.out.println("[Controller] DVM" + receivedMsg.getSrc_id()
                            + "으로부터 메시지 수신(유형: " + receivedMsg.getMsg_type() + ", 내용: " + receivedMsg.getMsg() + ")");
                    stock = Integer.parseInt(receivedMsg.getMsg());
                    if(stock > 0){
                        accessibleDVMList.add(dvmList.get(i - 1));
                    }
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return accessibleDVMList;
        }
        else{
            try {
                Socket socket = new Socket("localhost", 8000 + dst_id);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                Message message = new Message();
                message.createMessage(src_id, dst_id, MsgType.REQUEST_STOCK, msg);
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
                System.out.println("[Controller] DVM" + dst_id
                        + "에게 메시지 발신(유형: " + message.getMsg_type() + "(재고 요청), 내용: " + message.getMsg() + ")");

                /* 재고 여부가 true인 자판기들을 추가 */
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message receivedMsg = (Message) objectInputStream.readObject();
                System.out.println("[Controller] DVM" + receivedMsg.getSrc_id()
                        + "으로부터 메시지 수신(유형: " + receivedMsg.getMsg_type() + "(재고 응답), 내용: " + receivedMsg.getMsg() + ")");
                stock = Integer.parseInt(receivedMsg.getMsg());
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stock;
        }
    }

    //위치 확인 요청
    private int handleLocationRequest(int src_id, int dst_id) {
        int address = -1;
        try {
            Socket socket = new Socket("localhost", 8000 + dst_id);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.createMessage(src_id, dst_id, MsgType.REQUEST_LOCATION);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            System.out.println("[Controller] DVM" + dst_id
                    + "에게 메시지 발신(유형: " + message.getMsg_type() + "(위치 요청))");

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message receivedMsg = (Message) objectInputStream.readObject();
            System.out.println("[Controller] DVM" + receivedMsg.getSrc_id()
                    + "으로부터 메시지 수신(유형: " + receivedMsg.getMsg_type() + "(위치 응답), 내용: " + receivedMsg.getMsg() + ")");
            address = Integer.parseInt(receivedMsg.getMsg());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
}