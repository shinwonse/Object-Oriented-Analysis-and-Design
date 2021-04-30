import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Test2Test {

    @Test
    void add() {
        Test2.AddNumber addNumber = new Test2.AddNumber();
        assertEquals(addNumber.add(1,3),4);
    }
}