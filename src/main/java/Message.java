import java.io.Serializable;

public class Message implements Serializable {

    private int src_id;
    private int dst_id;
    private int msg_type;
    private String msg;

    public int getSrc_id() {
        return this.src_id;
    }

    public void setSrc_id(int src_id) {
        this.src_id = src_id;
    }

    public int getDst_id() {
        return this.dst_id;
    }

    public void setDst_id(int dst_id) {
        this.dst_id = dst_id;
    }

    public int getMsg_type() {
        return this.msg_type;
    }

    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }

    public void createMessage(int src_id, int dst_id, int msg_type){
        setDst_id(dst_id);
        setSrc_id(src_id);
        setMsg_type(msg_type);
        setMsg("");
    }

    public void createMessage(int src_id, int dst_id, int msg_type, String msg){
        setDst_id(dst_id);
        setSrc_id(src_id);
        setMsg_type(msg_type);
        setMsg(msg);
    }
}