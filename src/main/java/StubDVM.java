import java.util.ArrayList;

public class StubDVM implements DVM{

    private ArrayList<Drink> drink_list = new ArrayList<Drink>();
    private int id = 999;
    private int address = 9999;

    public StubDVM() {
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));
        drink_list = drinkArrayList;
    }


    @Override
    public Message makeStockResponseMessage(int dst_id, int stock) {
        Message message = new Message();
        String stockMsg = Integer.toString(stock);
        return message.createMessage(getId(), dst_id, MsgType.RESPONSE_STOCK, stockMsg);
    }

    @Override
    public int responseStockMessage(Network network, Message message) {
        return network.responseBroadcastMessage(message);
    }

    @Override
    public Message makeLocationResponseMessage(int src_id) {
        Message message = new Message();
        int address = getAddress();
        return message.createMessage(getId(), src_id, MsgType.RESPONSE_LOCATION, Integer.toString(address));
    }

    @Override
    public int responseLocationMessage(Network network, Message message) {
        return network.responseNormalMessage(message);
    }

    @Override
    public Message makeLocationRequestMessage(int dst_id) {
        Message message = new Message().createMessage(getId(), dst_id, MsgType.REQUEST_LOCATION);
        return message;
    }

    @Override
    public int requestLocationMessage(Network network, Message message) {
        int i = (int)network.handleRequestMessage(message);
        return i;
    }

    @Override
    public Message makeStockRequestMessage(int dst_id, String drink_name) {
        Message message = new Message();
        return message.createMessage(getId(), dst_id, MsgType.REQUEST_STOCK, drink_name);
    }

    @Override
    public Object requestStockMessage(Network network, Message message) {
        Object result = network.handleRequestMessage(message);
        if(result.getClass() == Integer.class){ // 일반 메시지의 경우 해당 DVM의 재고수가 리턴됨
            int stock = (int)result;
            return stock;
        }
        else{ // broadcast 메시지의 경우 재고가 있는 DVM 리스트가 리턴됨
            ArrayList<DVM> accessible_DVM_list = (ArrayList<DVM>)network.handleRequestMessage(message);
            return accessible_DVM_list;
        }
    }

    @Override
    public ArrayList<Drink> getDrink_list() {
        return this.drink_list;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getAddress() {
        return this.address;
    }

    @Override
    public void updateStock(Drink selected_drink) {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(int address) {
        this.address = address;
    }
}