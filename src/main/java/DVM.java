import java.util.ArrayList;
import java.util.List;

public class DVM {

    private ArrayList<Drink> drink_list;
    private int id;
    private int address;

    public DVM(ArrayList<Drink> drink_list, int id, int address) {
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
        return network.responseNormalMessage(message);
    }

<<<<<<< Updated upstream
=======
public interface DVM {

    //public DVM(ArrayList<Drink> drink_list, int id, int address);

    public ArrayList<Drink> getDrink_list();

    public int getDVMId();

    public int getAddress();

    public void setAddress(int address);

    public void responseStockMessage(Message message);

    public void updateStock(Drink selected_drink);

    public void responseLocationMessage(Message message);

    public void run();

    public void responseSaleMessage(Message message);
>>>>>>> Stashed changes
}