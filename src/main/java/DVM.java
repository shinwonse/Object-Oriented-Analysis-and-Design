import java.util.ArrayList;

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
}