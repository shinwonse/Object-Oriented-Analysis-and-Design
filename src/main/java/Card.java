public class Card {

    private int card_num;
    private int balance;
    private Boolean card_available = false;

    public Card(int card_num, int balance, Boolean card_available) {
        this.card_num = card_num;
        this.balance = balance;
        this.card_available = card_available;
    }

    public int getCard_num() {
        return card_num;
    }

    public int getBalance() {
        return balance;
    }

    public Boolean getCard_available() {
        return this.card_available;
    }

    public void setCard_available(Boolean card_available) {
        this.card_available = card_available;
    }

    public void updateBalance(int price){
        this.balance -= price;
    }
}