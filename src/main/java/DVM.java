import java.util.ArrayList;
import java.util.List;

public class DVM {

    private Boolean state;
    private ArrayList<Drink> drink_list;
    private int id;
    private int address;
    // private Message stateMessage;

    public DVM(Boolean state, ArrayList<Drink> drink_list, int id, int address) {
        this.state = state;
        this.drink_list = drink_list;
        this.id = id;
        this.address = address;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public ArrayList<Drink> getDrink_list() {
        return drink_list;
    }

    public void setDrink_list(ArrayList<Drink> drink_list) {
        this.drink_list = drink_list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

//    public Boolean requestDVMState(Message stateBroadCastMessage){
//
//    }
//
//    public Drink getDrinkInfo(int dialNum){
//        return drink_list.get(dialNum);
//    }
//
//    public void requestDrink(Drink drink){
//
//    }
//
//    public void provideDrink(){
//        // 음료 사용자에게 전달
//    }

//    public int getDrinkStcok(Drink drink_info,cur_DVM_info){
//
//    }

    public Message requestStock(Message stockBroadcastMessage){
        String drinkName = stockBroadcastMessage.getMsg();
        int stock = 0;
        ArrayList<Drink> drinkList = getDrink_list();
        for (int i = 0; i < getDrink_list().size(); i++) {
            if(drinkName.equals(drinkList.get(i).getName())){
                stock = drinkList.get(i).getStock();
            }
        }
        Message responseStockMessage = new Message().createMessage(getId(), stockBroadcastMessage.getSrc_id(), MsgType.RESPONSE_STOCK, String.valueOf(stock));
        return responseStockMessage;
    }

    public Message requestLocation(Message locationRequestMessage){
        Message responseLocationMessage = new Message().createMessage(getId(), locationRequestMessage.getSrc_id(), MsgType.REQUEST_LOCATION, String.valueOf(getAddress()));
        return responseLocationMessage;
    }

    public Message makeStockResponseMessage(int dst_id, int stock) {
        Message message = new Message();
        String stockMsg = Integer.toString(stock);
        return message.createMessage(getId(), dst_id, MsgType.RESPONSE_STOCK, stockMsg);
    }

    public int responseStockMessage(OtherDVMs.Network network, Message message) {
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

    public int responseLocationMessage(OtherDVMs.Network network, Message message){
        return network.responseNormalMessage(message);
    }

//    //getLocation 은 위에 getAddress로!
}