import java.util.ArrayList;
import java.util.List;

public class DVM {

    private Boolean state;
    private ArrayList<Drink> drink_list;
    private int id;
    private int address;
    // private Message stateMessage;

    public DVM(Boolean state, ArrayList<Drink> drink_list, int id, int address) {
        super();
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

//    public Message requestStock(Message stockBroadcastMessage){
//
//    }

//    //getLocation 은 위에 getAddress로!
}