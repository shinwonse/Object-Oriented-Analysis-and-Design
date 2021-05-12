import java.util.ArrayList;

public interface DVM {

    //public DVM(ArrayList<Drink> drink_list, int id, int address);

    public ArrayList<Drink> getDrink_list();

    public int getId();

    public int getAddress();

    public Message makeStockResponseMessage(int dst_id, int stock);

    public int responseStockMessage(Network network, Message message);

    public void updateStock(Drink selected_drink);

    public Message makeLocationResponseMessage(int src_id);

    public int responseLocationMessage(Network network, Message message);

    public Message makeStockRequestMessage(int dst_id, String drink_name);

    public Object requestStockMessage(Network network, Message message);

    public Message makeLocationRequestMessage(int dst_id);

    public int requestLocationMessage(Network network, Message message);
}