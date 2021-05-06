import java.util.ArrayList;
import java.util.List;

public class Drink {

    private String name;
    private int price;
    private int stock;
    private String imgURL;

    public Drink(String name, int price, int stock, String imgURL){
        super();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void updateStock(int count){

    }

    public int getStock(Drink drink_info){
        return drink_info.stock;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }

//    public static List<Drink> drinkList = new ArrayList<Drink>();


}
