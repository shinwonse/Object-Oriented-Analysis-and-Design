import java.util.ArrayList;

public class DVMc implements DVM{

    private ArrayList<Drink> drink_list;
    private int id;
    private int address;

    public DVMc(ArrayList<Drink> drink_list, int id, int address) {
        this.drink_list = drink_list;
        this.id = id;
        this.address = address;
    }

    public ArrayList<Drink> getDrink_list() {
        return drink_list;
    }

    public int getId() {
        return id;
    }

    public int getAddress() {
        return address;
    }

    public Message makeStockResponseMessage(int dst_id, int stock) {
        Message message = new Message();
        String stockMsg = Integer.toString(stock);
        return message.createMessage(getId(), dst_id, MsgType.RESPONSE_STOCK, stockMsg);
    }

    public int responseStockMessage(Network network, Message message) {
        String str = "ResponseBroadCastMessage == src_id: " + message.getSrc_id() + ", dst_id: " + message.getSrc_id() + ", msg_type: " + MsgType.RESPONSE_STOCK + ", msg: " + message.getMsg();
        System.out.println(str);
        return network.responseBroadcastMessage(message);
    }

    public void updateStock(Drink selected_drink) {
        for(Drink drink : drink_list){
            if(drink.getName().equals(selected_drink.getName())){
                drink.updateStock();
            }
        }
    }

    public Message makeLocationResponseMessage(int src_id) {
        Message message = new Message();
        int address = getAddress();
        return message.createMessage(getId(), src_id, MsgType.RESPONSE_LOCATION, Integer.toString(address));
    }

    public int responseLocationMessage(Network network, Message message){
        String str = "ResponseBroadCastMessage == src_id: " + message.getSrc_id() + ", dst_id: " + message.getSrc_id() + ", msg_type: " + MsgType.RESPONSE_LOCATION + ", msg: " + message.getMsg();
        System.out.println(str);
        return network.responseNormalMessage(message);
    }

    @Override
    public Message makeStockRequestMessage(int dst_id, String drink_name) {
        Message message = new Message();
        return message.createMessage(getId(), dst_id, MsgType.REQUEST_STOCK, drink_name);
    }

    @Override
    public Object requestStockMessage(Network network, Message message) {
        String str = "requestStockMessage == src_id: " + getId() + ", msg_type: " + MsgType.REQUEST_STOCK + ", msg: " + message.getMsg();
        System.out.println(str);
        Object result = network.handleRequestMessage(message);
        if(result.getClass() == Integer.class){
            int stock = (int)result;

            return stock;
        }
        else{
            ArrayList<DVM> accessible_DVM_list = (ArrayList<DVM>)network.handleRequestMessage(message);
            return accessible_DVM_list;
        }
    }

    @Override
    public Message makeLocationRequestMessage(int dst_id) {
        Message message = new Message().createMessage(getId(), dst_id, MsgType.REQUEST_LOCATION);

        return message;
    }

    @Override
    public int requestLocationMessage(Network network, Message message) {
        String str = "requestLocationMessage == src_id: " + message.getSrc_id()+ ",dst_id: "+message.getDst_id()
                +", msg_type: " + MsgType.REQUEST_LOCATION + ", msg: " + message.getMsg();
        System.out.println(str);
        int i = (int)network.handleRequestMessage(message);


        return i;
    }
}