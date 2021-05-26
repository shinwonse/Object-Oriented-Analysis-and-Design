import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DVM7 extends Thread implements DVM {

    private ArrayList<Drink> drink_list;
    private int id;
    private int address;
    ServerSocket serverSocket = null;
    Socket receive_socket = null;
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;
    private final int STUB_TEST_ID = 999;

    public DVM7(ArrayList<Drink> drink_list, int id, int address) {
        this.drink_list = drink_list;
        this.id = id;
        this.address = address;
    }

    public DVM7(ArrayList<Drink> drink_list, int id) {
        this.drink_list = drink_list;
        this.id = id;
    }

    public static void main(String[] args) {
        ArrayList<Drink> drinkArrayList7 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList7.add(new Drink("핫식스", 1500, 12, "src/main/resources/image/7.jpg"));
        drinkArrayList7.add(new Drink("몬스터드링크", 1500, 32, "src/main/resources/image/9.jpg"));
        drinkArrayList7.add(new Drink("파워에이드", 1500, 7, "src/main/resources/image/13.jpg"));
        drinkArrayList7.add(new Drink("펩시콜라", 1500, 5, "src/main/resources/image/2.jpg"));
        drinkArrayList7.add(new Drink("게토레이", 1500, 20, "src/main/resources/image/12.jpg"));
        drinkArrayList7.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList7.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList7.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList7.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList7.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList7.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList7.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList7.add(new Drink("코카콜라", 1500, 0, "src/main/resources/image/1.jpg"));
        drinkArrayList7.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList7.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));
        drinkArrayList7.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList7.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList7.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList7.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList7.add(new Drink("스프라이트", 1500, 0, "src/main/resources/image/4.jpg"));
        DVM7 dvm7 = new DVM7(drinkArrayList7, 7, 707);
        dvm7.run();
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(8000 + getDVMId());
            System.out.println("[DVM" + getDVMId() + "] SERVER ON");

            while (true) {
                receive_socket= serverSocket.accept();
                //System.out.println("Client connected");

                objectInputStream = new ObjectInputStream(receive_socket.getInputStream());
                Message msg = (Message) objectInputStream.readObject();

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

    public void responseStockMessage(Message receivedMessage) {
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void responseLocationMessage(Message receivedMessage){
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void responseSaleMessage(Message receivedMessage) {
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}