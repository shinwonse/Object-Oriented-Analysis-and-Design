import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class DVM extends Thread {

    private ArrayList<Drink> drink_list;
    private int id;
    private int address;
    private ServerSocket serverSocket = null;
    private Socket receive_socket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private static final int STUB_TEST_ID = 999;

    public DVM(ArrayList<Drink> drink_list, int id, int address) {
        this.drink_list = drink_list;
        this.id = id;
        this.address = address;
    }

    public DVM(ArrayList<Drink> drink_list, int id) {
        this.drink_list = drink_list;
        this.id = id;
    }

    public void dvmOn() throws IOException {
        setServerPort();
        start();
    }

    public void setServerPort() throws IOException {
        serverSocket = new ServerSocket(8000 + getDVMId());
    }

    public void closeServerPort() throws IOException {
        serverSocket.close();
    }


    public void run() {
        try {
            System.out.println("[DVM" + getDVMId() + "] SERVER ON");
            boolean flag = true;
            while (flag) {
                receive_socket= serverSocket.accept();
                //System.out.println("Client connected");

                objectInputStream = new ObjectInputStream(receive_socket.getInputStream());
                Message msg = (Message) objectInputStream.readObject();
                if(msg.getSrc_id() == STUB_TEST_ID)
                    System.out.println("[DVM" + getDVMId() + "] StubTest"
                            + "로부터 메시지 수신(유형: " + msg.getMsg_type() + ", 내용: " + msg.getMsg()+ ")");
                else
                    System.out.println("[DVM" + getDVMId() + "] DVM" + msg.getSrc_id()
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
                    case 999:
                        flag = false;
                        break;
                    default:
                        System.out.println("잘못된 메시지 유형입니다.");
                }
                if(!flag) {
                    receive_socket.close();
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
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

    private void responseStockMessage(Message receivedMessage) {
        try{
            objectOutputStream = new ObjectOutputStream(receive_socket.getOutputStream());
            Message message = new Message();
            message.createMessage(getDVMId(), receivedMessage.getSrc_id(), MsgType.RESPONSE_STOCK, getStock(receivedMessage.getMsg()));
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            if(receivedMessage.getSrc_id() == STUB_TEST_ID)
                System.out.println("[DVM" + getDVMId() + "] StubTest"
                        + "에게 메시지 발신(유형: " + message.getMsg_type() + "(재고 응답), 내용: " + message.getMsg() + ")");
            else
                System.out.println("[DVM" + getDVMId() + "] Controller"
                        + "에게 메시지 발신(유형: " + message.getMsg_type() + "(재고 응답), 내용: " + message.getMsg() + ")");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void responseLocationMessage(Message receivedMessage){
        try{
            objectOutputStream = new ObjectOutputStream(receive_socket.getOutputStream());
            Message message = new Message();
            message.createMessage(getDVMId(), receivedMessage.getSrc_id(), MsgType.RESPONSE_LOCATION, String.valueOf(getAddress()));
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            if(receivedMessage.getSrc_id() == STUB_TEST_ID)
                System.out.println("[DVM" + getDVMId() + "] StubTest"
                        + "에게 메시지 발신(유형: " + message.getMsg_type() + "(위치 응답), 내용: " + message.getMsg() + ")");
            else
                System.out.println("[DVM" + getDVMId() + "] Controller"
                        + "에게 메시지 발신(유형: " + message.getMsg_type() + "(위치 응답), 내용: " + message.getMsg() + ")");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void responseSaleMessage(Message receivedMessage) {
        try{
            objectOutputStream = new ObjectOutputStream(receive_socket.getOutputStream());
            String drinkName = receivedMessage.getMsg();
            for(Drink drink: drink_list){
                if(drink.getName().equals(drinkName)){
                    drink.updateStock();
                    break;
                }
            }
            Message message = new Message();
            message.createMessage(getDVMId(), receivedMessage.getSrc_id(), MsgType.DRINK_SALE_RESPONSE, getStock(drinkName));
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            if(receivedMessage.getSrc_id() == STUB_TEST_ID)
                System.out.println("[DVM" + getDVMId() + "] StubTest"
                        + "에게 메시지 발신(유형: " + message.getMsg_type() + "(판매 확인 응답), 내용: " + message.getMsg() + ")");
            else
                System.out.println("[DVM" + getDVMId() + "] Controller"
                        + "에게 메시지 발신(유형: " + message.getMsg_type() + "(판매 확인 응답), 내용: " + message.getMsg() + ")");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
