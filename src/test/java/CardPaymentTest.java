import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardPaymentTest {


    @Test
    void generateCodeTest1() {
        Drink drink_info = new Drink("음료수1", 1000, 1, null);
        int generatedCodeNum = (int) (Math.random() * (99999 - 10000 + 1)) + 10000;
        Code generatedCode = new Code(generatedCodeNum, drink_info);
        assertTrue(generatedCodeNum > 10000);
        assertTrue(generatedCodeNum < 100000);
    }

    @Test
    void generateCodeTest2() {
        Drink drink_info = new Drink("음료수1", 1000, 1, null);
        int generatedCodeNum = (int) (Math.random() * (99999 - 10000 + 1)) + 10000;
        Code generatedCode = new Code(generatedCodeNum, drink_info);
        assertEquals(Code.class, generatedCode.getClass());
    }


    @Test
    void getCardTest1() {
        Card card1 = new Card(12341234, 10000);
        Card card2 = new Card(11111111, 0);

        ArrayList<Card> tempList = new ArrayList<>();
        tempList.add(card1);
        tempList.add(card2);
        ArrayList<Card> basicCardList = tempList;
        assertTrue(basicCardList.size() == 2);

    }

    @Test
    void getCardTest2() {
        Card card1 = new Card(12341234, 10000);

        ArrayList<Card> tempList = new ArrayList<>();
        tempList.add(card1);
        ArrayList<Card> basicCardList = tempList;
        assertEquals(basicCardList.get(0).getClass(), Card.class);

    }

    @Test
    void getCardTest3() {
        ArrayList<Card> basicCardList;
        int[] basicCardNameList = {12341234, 11111111, 10000000};
        Card card1 = new Card(basicCardNameList[0], 10000);
        Card card2 = new Card(basicCardNameList[1], 0);
        Card card3 = new Card(basicCardNameList[2], 10000);
        ArrayList<Card> tempList = new ArrayList<>();
        tempList.add(card1);
        tempList.add(card2);
        tempList.add(card3);
        basicCardList = tempList;           //미리 초기화 되어있는 basicCardList


        int card_num = 12341234;            //Customer 가 입력한 카드번호

        Card myCard = null;
        for (Card card : basicCardList) {
            if (card.getCard_num() == card_num)
                myCard = card;

        }
        assertEquals(myCard.getClass(), Card.class);
    }

}


