public class Test2 {

    public static void main(String[] args){
        AddNumber addn = new AddNumber();

        System.out.println(addn.add(1,2));
    }

    static class AddNumber{
        public int add(int a, int b) {
            return a + b;
        }
    }

}
