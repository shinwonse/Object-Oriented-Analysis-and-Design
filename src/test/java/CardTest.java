import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card card1 = new Card(0, 11000);
    Card card2 = new Card(1,12000);
    Card card3 = new Card(2,13000);
    Card card4 = new Card(10,14000);
    Card card5 = new Card(1000,15000);
    Card card6 = new Card(10000,16000);
    Card card7 = new Card(100000,17000);
    Card card8 = new Card(1000000,18000);
    Card card9 = new Card(10000000,19000);
    Card card10 = new Card(100000000,20000);


    @Test
    void getCard_num() { // 카드 번호가 올바르게 전달되는가
        assertEquals(0,card1.getCard_num());

        assertEquals(1,card2.getCard_num());

        assertEquals(2, card3.getCard_num());

        assertEquals(10, card4.getCard_num());

        assertEquals(1000, card5.getCard_num());

        assertEquals(10000, card6.getCard_num());

        assertEquals(100000, card7.getCard_num());

        assertEquals(1000000, card8.getCard_num());

        assertEquals(10000000, card9.getCard_num());

        assertEquals(100000000, card10.getCard_num());
    }

    @Test
    void getBalance() {
        assertEquals(11000,card1.getBalance());

        assertEquals(12000,card2.getBalance());

        assertEquals(13000, card3.getBalance());

        assertEquals(14000, card4.getBalance());

        assertEquals(15000, card5.getBalance());

        assertEquals(16000, card6.getBalance());

        assertEquals(17000, card7.getBalance());

        assertEquals(18000, card8.getBalance());

        assertEquals(19000, card9.getBalance());

        assertEquals(20000, card10.getBalance());
    }

    @Test
    void updateBalance() {
        card1.updateBalance(1500);
        assertEquals(9500,card1.getBalance());

        card2.updateBalance(1500);
        assertEquals(10500,card2.getBalance());

        card3.updateBalance(1500);
        assertEquals(11500, card3.getBalance());

        card4.updateBalance(1500);
        assertEquals(12500, card4.getBalance());

        card5.updateBalance(1500);
        assertEquals(13500, card5.getBalance());

        card6.updateBalance(1500);
        assertEquals(14500, card6.getBalance());

        card7.updateBalance(1500);
        assertEquals(15500, card7.getBalance());

        card8.updateBalance(1500);
        assertEquals(16500, card8.getBalance());

        card9.updateBalance(1500);
        assertEquals(17500, card9.getBalance());

        card10.updateBalance(1500);
        assertEquals(18500, card10.getBalance());
    }
}