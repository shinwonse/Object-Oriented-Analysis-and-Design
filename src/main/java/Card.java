public class Card {
    private int card_num;
    private int balance;

    public Card(int card_num, int balance) {
        this.card_num = card_num;
        this.balance = balance;
    }

    public int getCard_num() { return card_num; }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int price){
        this.balance -= price;
    }
}