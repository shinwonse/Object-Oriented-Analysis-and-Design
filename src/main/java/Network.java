import java.util.ArrayList;

public class Network {
    ArrayList<DVM> dvmList;
    Network(ArrayList<DVM> dvmList){
        this.dvmList = dvmList;
    }
    public Object handleRequestMessage(Message message){
        int src_id = message.getSrc_id();
        int dst_id = message.getDst_id();
        int msg_type = message.getMsg_type();
        String msg = message.getMsg();
        if(msg_type == MsgType.REQUEST_STOCK){
            if(dst_id == 0){
                ArrayList<DVM> accessibleDVMList = (ArrayList<DVM>)handleStockRequest(src_id, 0, msg);
                return accessibleDVMList;
            }
            else{
                int stock = (int)handleStockRequest(src_id, dst_id, msg);
                return stock;
            }

        }
        else if(msg_type == MsgType.REQUEST_LOCATION){
            int address = handleLocationRequest(src_id, dst_id);
            return address;
        }
        return null;
    }

    private Object handleStockRequest(int src_id, int dst_id, String msg) {
        ArrayList<DVM> accessibleDVMList = new ArrayList<>();
        if(dst_id == 0){
            for(DVM dvm : dvmList){
                boolean isInStock = false;
                for(Drink drink: dvm.getDrink_list()){
                    if(drink.getName().equals(msg)){
                        Message message = dvm.makeStockResponseMessage(src_id, drink.getStock()); // DVM이 응답 메세지를 만듬
                        int stock = dvm.responseStockMessage(this, message); // DVM이 Network를 통해 응답메시지를 전달하고 Network 내부에서 stock 값을 찾아 리턴해줌
                        if(stock != 0){
                            isInStock = true;
                            break;
                        }
                    }
                }
                if(isInStock){
                    accessibleDVMList.add(dvm);
                }
            }
        }
        else{
            for(DVM dvm : dvmList) {
                if (dvm.getId() == dst_id){
                    for (Drink drink : dvm.getDrink_list()) {
                        if (drink.getName().equals(msg)) {
                            Message message = dvm.makeStockResponseMessage(src_id, drink.getStock()); // DVM이 응답 메세지를 만듬
                            int stock = dvm.responseStockMessage(this, message); // DVM이 Network를 통해 응답메시지를 전달하고 Network 내부에서 stock 값을 찾아 리턴해줌
                            return stock;
                        }
                    }
                }
            }
        }
        return accessibleDVMList;
    }

    public int responseBroadcastMessage(Message message) {
        int src_id = message.getSrc_id();
        int dst_id = message.getDst_id();
        String msg = message.getMsg();
        int stock = Integer.parseInt(msg);

        return stock;

    }

    public int responseNormalMessage(Message message) {
        int data = -1;
        int msg_type = message.getMsg_type();
        String msg = message.getMsg();
        if (msg_type == MsgType.RESPONSE_LOCATION) {
            data = Integer.parseInt(msg);
        }
        else if(msg_type == MsgType.RESPONSE_STOCK){
            data = Integer.parseInt(msg);
        }
        return data;
    }

    private int handleLocationRequest(int src_id, int dst_id) {
        int address = -1;
        for(DVM dvm : dvmList){
            if(dvm.getId() == dst_id) {
                Message message = dvm.makeLocationResponseMessage(src_id);
                address = dvm.responseLocationMessage(this, message);
                break;
            }
        }
        return address;
    }
}