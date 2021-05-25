import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Network extends Thread {
    ArrayList<DVM> dvmList;

    Network(ArrayList<DVM> dvmList){
        this.dvmList = dvmList;
    }

<<<<<<< Updated upstream
    public int requestNormalMessage(Message message) {
        int address = -1;
=======
    // ë©”ì‹œì§€ ì¢…í•© í•¸ë“¤ëŸ¬
    public Object handleRequestMessage(Message message){
>>>>>>> Stashed changes
        int src_id = message.getSrc_id();
        int dst_id = message.getDst_id();
        int msg_type = message.getMsg_type();
        String msg = message.getMsg();
<<<<<<< Updated upstream
        if (msg_type == MsgType.REQUEST_LOCATION) {
            address = handleLocationRequest(src_id, dst_id);
        }
        return address;
    }

    // BroadCastMessage ¸¦ »ç¿ëÇÏ´Â ÄÉÀÌ½º´Â ½ÇÁúÀûÀ¸·Î Àç°í¿äÃ»¶§¹Û¿¡ ¾øÀ½
    public ArrayList<DVM> requestBroadcastMessage(Message broadCastMessage) {
        int src_id = broadCastMessage.getSrc_id();
        int msg_type = broadCastMessage.getMsg_type();
        String msg = broadCastMessage.getMsg();
        if(msg_type == MsgType.REQUEST_STOCK){
            ArrayList<DVM> accessibleDVMList = handleStockRequest(src_id, msg);
            return accessibleDVMList;
=======
        switch(msg_type){
            case MsgType.REQUEST_STOCK:
                if(dst_id == 0){
                    ArrayList<DVM> accessibleDVMList = (ArrayList<DVM>)handleStockRequest(src_id, 0, msg);
                    return accessibleDVMList;
                }
                else{
                    int stock = (int)handleStockRequest(src_id, dst_id, msg);
                    return stock;
                }

            case MsgType.REQUEST_LOCATION:
                int address = handleLocationRequest(src_id, dst_id);
                return address;

            case MsgType.DRINK_SALE_CHECK:
                int remainedStock = (int)handleSaleRequest(src_id, dst_id, msg);
                return remainedStock;

>>>>>>> Stashed changes
        }
        return null;
    }

<<<<<<< Updated upstream
    private ArrayList<DVM> handleStockRequest(int src_id, String msg) {
        ArrayList<DVM> accessibleDVMList = new ArrayList<>();
        for(DVM dvm : dvmList){
            boolean isInStock = false;
            for(Drink drink: dvm.getDrink_list()){
                if(drink.getName().equals(msg)){
                    Message message = dvm.makeStockResponseMessage(src_id, drink.getStock()); // DVMÀÌ ÀÀ´ä ¸Ş¼¼Áö¸¦ ¸¸µë
                    int stock = dvm.responseStockMessage(this, message); // DVMÀÌ Network¸¦ ÅëÇØ ÀÀ´ä¸Ş½ÃÁö¸¦ Àü´ŞÇÏ°í Network ³»ºÎ¿¡¼­ stock °ªÀ» Ã£¾Æ ¸®ÅÏÇØÁÜ
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
        return accessibleDVMList;
    }

    public int responseBroadcastMessage(Message message) {
        int src_id = message.getSrc_id();
        String msg = message.getMsg();
        int stock = Integer.parseInt(msg);
        return stock;
    }

    public int responseNormalMessage(Message message) {
        int address = -1;
        int msg_type = message.getMsg_type();
        String msg = message.getMsg();
        if (msg_type == MsgType.RESPONSE_LOCATION) {
            address = Integer.parseInt(msg);
        }
        return address;
=======
    // íŒë§¤ í™•ì¸ ìš”ì²­
    private Object handleSaleRequest(int src_id, int dst_id, String msg) {
        int remainedStock = 0;
        try {
            Socket socket = new Socket("localhost", 8000 + dst_id);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            Message message = new Message();
            message.createMessage(src_id, dst_id, MsgType.DRINK_SALE_CHECK, msg);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            System.out.println("[Controller] DVM" + (dst_id + 1)
                    + "ì—ê²Œ ë©”ì‹œì§€ ë°œì‹ (ìœ í˜•: " + message.getMsg_type() + "(íŒë§¤ í™•ì¸ ìš”ì²­), ë‚´ìš©: " + message.getMsg() + ")");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message receivedMsg = (Message) objectInputStream.readObject();
            System.out.println("[Controller] DVM" + (receivedMsg.getSrc_id() + 1)
                    + "ìœ¼ë¡œë¶€í„° ë©”ì‹œì§€ ìˆ˜ì‹ (ìœ í˜•: " + receivedMsg.getMsg_type() + "(íŒë§¤ í™•ì¸ ì‘ë‹µ), ë‚´ìš©: " + receivedMsg.getMsg() + ")");
            remainedStock = Integer.parseInt(receivedMsg.getMsg());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return remainedStock;
    }

    // ì¬ê³  í™•ì¸ ìš”ì²­
    private Object handleStockRequest(int src_id, int dst_id, String msg) {
        ArrayList<DVM> accessibleDVMList = new ArrayList<>();
        int stock = 0;
        if(dst_id == 0){
            for(int i = 1; i <= dvmList.size(); i++){
                try {
                    Socket socket = new Socket("localhost", 8000 + i);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                    Message message = new Message();
                    message.createMessage(src_id, i - 1, MsgType.REQUEST_STOCK, msg);
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                    System.out.println("[Controller] DVM" + i
                            + "ì—ê²Œ ë©”ì‹œì§€ ë°œì‹ (ìœ í˜•: " + message.getMsg_type() + ", ë‚´ìš©: " + message.getMsg() + ")");
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Message receivedMsg = (Message) objectInputStream.readObject();
                    System.out.println("[Controller] DVM" + receivedMsg.getSrc_id()
                            + "ìœ¼ë¡œë¶€í„° ë©”ì‹œì§€ ìˆ˜ì‹ (ìœ í˜•: " + receivedMsg.getMsg_type() + ", ë‚´ìš©: " + receivedMsg.getMsg() + ")");
                    stock = Integer.parseInt(receivedMsg.getMsg());
                    if(stock > 0){
                        accessibleDVMList.add(dvmList.get(i - 1));
                    }
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return accessibleDVMList;
        }
        else{
            try {
                Socket socket = new Socket("localhost", 8000 + dst_id);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                Message message = new Message();
                message.createMessage(src_id, dst_id, MsgType.REQUEST_STOCK, msg);
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
                System.out.println("[Controller] DVM" + dst_id
                        + "ì—ê²Œ ë©”ì‹œì§€ ë°œì‹ (ìœ í˜•: " + message.getMsg_type() + "(ì¬ê³  ìš”ì²­), ë‚´ìš©: " + message.getMsg() + ")");

                /* ì¬ê³  ì—¬ë¶€ê°€ trueì¸ ìíŒê¸°ë“¤ì„ ì¶”ê°€ */
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message receivedMsg = (Message) objectInputStream.readObject();
                System.out.println("[Controller] DVM" + receivedMsg.getSrc_id()
                        + "ìœ¼ë¡œë¶€í„° ë©”ì‹œì§€ ìˆ˜ì‹ (ìœ í˜•: " + receivedMsg.getMsg_type() + "(ì¬ê³  ì‘ë‹µ), ë‚´ìš©: " + receivedMsg.getMsg() + ")");
                stock = Integer.parseInt(receivedMsg.getMsg());
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stock;
        }
>>>>>>> Stashed changes
    }

    //ìœ„ì¹˜ í™•ì¸ ìš”ì²­
    private int handleLocationRequest(int src_id, int dst_id) {
        int address = -1;
        try {
            Socket socket = new Socket("localhost", 8000 + dst_id);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.createMessage(src_id, dst_id, MsgType.REQUEST_LOCATION);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            System.out.println("[Controller] DVM" + dst_id
                    + "ì—ê²Œ ë©”ì‹œì§€ ë°œì‹ (ìœ í˜•: " + message.getMsg_type() + "(ìœ„ì¹˜ ìš”ì²­))");

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message receivedMsg = (Message) objectInputStream.readObject();
            System.out.println("[Controller] DVM" + receivedMsg.getSrc_id()
                    + "ìœ¼ë¡œë¶€í„° ë©”ì‹œì§€ ìˆ˜ì‹ (ìœ í˜•: " + receivedMsg.getMsg_type() + "(ìœ„ì¹˜ ì‘ë‹µ), ë‚´ìš©: " + receivedMsg.getMsg() + ")");
            address = Integer.parseInt(receivedMsg.getMsg());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
}