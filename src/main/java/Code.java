public class Code {

    private int code;
    private final Drink drink;

    public Code(int code, Drink drink_info) {
        this.code = code;
        this.drink = drink_info;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public Drink getDrink() {
        return drink;
    }

}