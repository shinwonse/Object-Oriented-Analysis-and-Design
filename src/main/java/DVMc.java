import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DVMc extends Thread implements DVM {

    private ArrayList<Drink> drink_list;
    private int id;
    private int address;
    ServerSocket serverSocket = null;
    Socket receive_socket = null;
    Socket send_socket = null;


    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;

    public DVMc(ArrayList<Drink> drink_list, int id, int address) {
        this.drink_list = drink_list;
        this.id = id;
        this.address = address;
    }

    public DVMc(int id, int address){
        this.id = id;
        this.address = address;
    }


    public void run() {
        try {
            serverSocket = new ServerSocket(8000 + getDVMId() + 1);
            System.out.println("Server Running");

            while (true) {
                receive_socket= serverSocket.accept();
                //System.out.println("Client connected");

                objectInputStream = new ObjectInputStream(receive_socket.getInputStream());
                Message msg = (Message) objectInputStream.readObject();

                System.out.println("[DVM" + (getDVMId() + 1) + "] DVM" + msg.getSrc_id()
                        + "로부터 메시지 수신(유형: " + msg.getMsg_type() + ", 내용: " + msg.getMsg()+ ")");
                int type = msg.getMsg_type();
                switch (type) {
                    case MsgType.REQUEST_STOCK:
                        responseStockMessage(msg);
                        break;
                    case MsgType.REQUEST_LOCATION:
                        responseLocationMessage(msg);
                        break;
                    case MsgType.DRINK_SALE_CHECK:
                        responseSaleMessage(msg);
                        break;
                }
                receive_socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Drink> getDrink_list() {
        return drink_list;
    }

    private String getStock(String drinkName){
        for(Drink drink : drink_list){
            if(drink.getName().equals(drinkName))
                return String.valueOf(drink.getStock());
        }
        return "-999";
    }

    public int getDVMId() {
        return id;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public void updateStock(Drink selected_drink) {
        for(Drink drink : drink_list){
            if(drink.getName().equals(selected_drink.getName())){
                drink.updateStock();
            }
        }
    }

    public void responseStockMessage(Message msg) {
        try{
            objectOutputStream = new ObjectOutputStream(receive_socket.getOutputStream());
            Message sendMsg1 = new Message();
            sendMsg1.createMessage(getDVMId(), msg.getSrc_id(), MsgType.RESPONSE_STOCK, getStock(msg.getMsg()));
            objectOutputStream.writeObject(sendMsg1);
            objectOutputStream.flush();
            System.out.println("[DVM" + getDVMId()+"] Controller"
                    + "에게 메시지 발신(유형: " + sendMsg1.getMsg_type() + "(재고 응답), 내용: " + sendMsg1.getMsg() + ")");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void responseLocationMessage(Message message){
        try{
            objectOutputStream = new ObjectOutputStream(receive_socket.getOutputStream());
            /* Stub true/false 수정 가능 */
            Message sendMsg = new Message();
            sendMsg.createMessage(getDVMId(), message.getSrc_id(), MsgType.RESPONSE_LOCATION, String.valueOf(getAddress()));
            //sendMsg = new Msg(myID, msg.getSrc_id(), 5, "false");
            objectOutputStream.writeObject(sendMsg);
            objectOutputStream.flush();
            System.out.println("[DVM" + getDVMId()+"] Controller"
                    + "에게 메시지 발신(유형: " + sendMsg.getMsg_type() + "(위치 응답), 내용: " + sendMsg.getMsg() + ")");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void responseSaleMessage(Message message) {
        try{
            objectOutputStream = new ObjectOutputStream(receive_socket.getOutputStream());
            String drinkName = message.getMsg();
            for(Drink drink: drink_list){
                if(drink.getName().equals(drinkName)){
                    drink.updateStock();
                    break;
                }
            }
            Message sendMsg = new Message();
            sendMsg.createMessage(getDVMId(), message.getSrc_id(), MsgType.DRINK_SALE_RESPONSE, getStock(drinkName));
            objectOutputStream.writeObject(sendMsg);
            objectOutputStream.flush();
            System.out.println("[DVM" + getDVMId()+"] Controller"
                    + "에게 메시지 발신(유형: " + sendMsg.getMsg_type() + "(판매 확인 응답), 내용: " + sendMsg.getMsg() + ")");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}