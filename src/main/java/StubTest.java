import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class StubTest {

    public StubTest() throws IOException{
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList.add(new Drink("코카콜라", 1500, 10, "image/1.jpg"));
        drinkArrayList.add(new Drink("펩시콜라", 1500, 11, "image/2.jpg"));
        drinkArrayList.add(new Drink("칠성사이다", 1500, 0, "image/3.jpg"));
        drinkArrayList.add(new Drink("스프라이트", 1500, 10, "image/4.jpg"));
        drinkArrayList.add(new Drink("환타오렌지", 1500, 8, "image/5.jpg"));
        drinkArrayList.add(new Drink("환타포도", 1500, 1, "image/6.jpg"));
        drinkArrayList.add(new Drink("핫식스", 1500, 10, "image/7.jpg"));
        drinkArrayList.add(new Drink("레드불", 1500, 0, "image/8.jpg"));
        drinkArrayList.add(new Drink("몬스터드링크", 1500, 0, "image/9.jpg"));
        drinkArrayList.add(new Drink("빡텐션", 1500, 0, "image/10.jpg"));
        drinkArrayList.add(new Drink("포카리스웨트", 1500, 0, "image/11.jpg"));
        drinkArrayList.add(new Drink("게토레이", 1500, 0, "image/12.jpg"));
        drinkArrayList.add(new Drink("파워에이드", 1500, 0, "image/13.jpg"));
        drinkArrayList.add(new Drink("밀키스", 1500, 0, "image/14.jpg"));
        drinkArrayList.add(new Drink("레쓰비", 1500, 0, "image/15.jpg"));
        drinkArrayList.add(new Drink("스파클링", 1500, 0, "image/16.jpg"));
        drinkArrayList.add(new Drink("비락식혜", 1500, 0, "image/17.jpg"));
        drinkArrayList.add(new Drink("솔의눈", 1500, 0, "image/18.jpg"));
        drinkArrayList.add(new Drink("데자와", 1500, 0, "image/19.jpg"));
        drinkArrayList.add(new Drink("마운틴듀", 1500, 0, "image/20.jpg"));

        ArrayList<Drink> drinkArrayList2 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList2.add(new Drink("빡텐션", 1500, 10, "image/10.jpg"));
        drinkArrayList2.add(new Drink("파워에이드", 1500, 120, "image/13.jpg"));
        drinkArrayList2.add(new Drink("밀키스", 1500, 10, "image/14.jpg"));
        drinkArrayList2.add(new Drink("레쓰비", 1500, 12, "image/15.jpg"));
        drinkArrayList2.add(new Drink("스파클링", 1500, 0, "image/16.jpg"));
        drinkArrayList2.add(new Drink("데자와", 1500, 10, "image/19.jpg"));
        drinkArrayList2.add(new Drink("코카콜라", 1500, 10, "image/1.jpg"));
        drinkArrayList2.add(new Drink("펩시콜라", 1500, 0, "image/2.jpg"));
        drinkArrayList2.add(new Drink("칠성사이다", 1500, 0, "image/3.jpg"));
        drinkArrayList2.add(new Drink("스프라이트", 1500, 0, "image/4.jpg"));
        drinkArrayList2.add(new Drink("환타오렌지", 1500, 0, "image/5.jpg"));
        drinkArrayList2.add(new Drink("환타포도", 1500, 0, "image/6.jpg"));
        drinkArrayList2.add(new Drink("핫식스", 1500, 0, "image/7.jpg"));
        drinkArrayList2.add(new Drink("레드불", 1500, 0, "image/8.jpg"));
        drinkArrayList2.add(new Drink("몬스터드링크", 1500, 0, "image/9.jpg"));
        drinkArrayList2.add(new Drink("포카리스웨트", 1500, 0, "image/11.jpg"));
        drinkArrayList2.add(new Drink("게토레이", 1500, 0, "image/12.jpg"));
        drinkArrayList2.add(new Drink("비락식혜", 1500, 0, "image/17.jpg"));
        drinkArrayList2.add(new Drink("솔의눈", 1500, 0, "image/18.jpg"));
        drinkArrayList2.add(new Drink("마운틴듀", 1500, 0, "image/20.jpg"));

        ArrayList<Drink> drinkArrayList3 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList3.add(new Drink("환타오렌지", 1500, 10, "image/5.jpg"));
        drinkArrayList3.add(new Drink("포카리스웨트", 1500, 10, "image/11.jpg"));
        drinkArrayList3.add(new Drink("레드불", 1500, 0, "image/8.jpg"));
        drinkArrayList3.add(new Drink("빡텐션", 1500, 13, "image/10.jpg"));
        drinkArrayList3.add(new Drink("파워에이드", 1500, 10, "image/13.jpg"));
        drinkArrayList3.add(new Drink("밀키스", 1500, 10, "image/14.jpg"));
        drinkArrayList3.add(new Drink("게토레이", 1500, 20, "image/12.jpg"));
        drinkArrayList3.add(new Drink("비락식혜", 1500, 0, "image/17.jpg"));
        drinkArrayList3.add(new Drink("솔의눈", 1500, 0, "image/18.jpg"));
        drinkArrayList3.add(new Drink("레쓰비", 1500, 0, "image/15.jpg"));
        drinkArrayList3.add(new Drink("스파클링", 1500, 0, "image/16.jpg"));
        drinkArrayList3.add(new Drink("데자와", 1500, 0, "image/19.jpg"));
        drinkArrayList3.add(new Drink("코카콜라", 1500, 0, "image/1.jpg"));
        drinkArrayList3.add(new Drink("펩시콜라", 1500, 0, "image/2.jpg"));
        drinkArrayList3.add(new Drink("칠성사이다", 1500, 0, "image/3.jpg"));
        drinkArrayList3.add(new Drink("스프라이트", 1500, 0, "image/4.jpg"));
        drinkArrayList3.add(new Drink("환타포도", 1500, 0, "image/6.jpg"));
        drinkArrayList3.add(new Drink("핫식스", 1500, 0, "image/7.jpg"));
        drinkArrayList3.add(new Drink("몬스터드링크", 1500, 0, "image/9.jpg"));
        drinkArrayList3.add(new Drink("마운틴듀", 1500, 0, "image/20.jpg"));

        ArrayList<Drink> drinkArrayList4 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList4.add(new Drink("펩시콜라", 1500, 11, "image/2.jpg"));
        drinkArrayList4.add(new Drink("칠성사이다", 1500, 4, "image/3.jpg"));
        drinkArrayList4.add(new Drink("스프라이트", 1500, 8, "image/4.jpg"));
        drinkArrayList4.add(new Drink("환타오렌지", 1500, 10, "image/5.jpg"));
        drinkArrayList4.add(new Drink("포카리스웨트", 1500, 10, "image/11.jpg"));
        drinkArrayList4.add(new Drink("스파클링", 1500, 4, "image/16.jpg"));
        drinkArrayList4.add(new Drink("게토레이", 1500, 21, "image/12.jpg"));
        drinkArrayList4.add(new Drink("비락식혜", 1500, 0, "image/17.jpg"));
        drinkArrayList4.add(new Drink("솔의눈", 1500, 0, "image/18.jpg"));
        drinkArrayList4.add(new Drink("레쓰비", 1500, 0, "image/15.jpg"));
        drinkArrayList4.add(new Drink("데자와", 1500, 0, "image/19.jpg"));
        drinkArrayList4.add(new Drink("코카콜라", 1500, 0, "image/1.jpg"));
        drinkArrayList4.add(new Drink("환타포도", 1500, 0, "image/6.jpg"));
        drinkArrayList4.add(new Drink("핫식스", 1500, 0, "image/7.jpg"));
        drinkArrayList4.add(new Drink("몬스터드링크", 1500, 0, "image/9.jpg"));
        drinkArrayList4.add(new Drink("마운틴듀", 1500, 0, "image/20.jpg"));
        drinkArrayList4.add(new Drink("밀키스", 1500, 0, "image/14.jpg"));
        drinkArrayList4.add(new Drink("레드불", 1500, 0, "image/8.jpg"));
        drinkArrayList4.add(new Drink("빡텐션", 1500, 0, "image/10.jpg"));
        drinkArrayList4.add(new Drink("파워에이드", 1500, 0, "image/13.jpg"));

        ArrayList<Drink> drinkArrayList5 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList5.add(new Drink("레쓰비", 1500, 12, "image/15.jpg"));
        drinkArrayList5.add(new Drink("펩시콜라", 1500, 32, "image/2.jpg"));
        drinkArrayList5.add(new Drink("칠성사이다", 1500, 1, "image/3.jpg"));
        drinkArrayList5.add(new Drink("마운틴듀", 1500, 23, "image/20.jpg"));
        drinkArrayList5.add(new Drink("밀키스", 1500, 5, "image/14.jpg"));
        drinkArrayList5.add(new Drink("스프라이트", 1500, 7, "image/4.jpg"));
        drinkArrayList5.add(new Drink("환타오렌지", 1500, 10, "image/5.jpg"));
        drinkArrayList5.add(new Drink("데자와", 1500, 0, "image/19.jpg"));
        drinkArrayList5.add(new Drink("코카콜라", 1500, 0, "image/1.jpg"));
        drinkArrayList5.add(new Drink("포카리스웨트", 1500, 0, "image/11.jpg"));
        drinkArrayList5.add(new Drink("스파클링", 1500, 0, "image/16.jpg"));
        drinkArrayList5.add(new Drink("게토레이", 1500, 0, "image/12.jpg"));
        drinkArrayList5.add(new Drink("비락식혜", 1500, 0, "image/17.jpg"));
        drinkArrayList5.add(new Drink("솔의눈", 1500, 0, "image/18.jpg"));
        drinkArrayList5.add(new Drink("환타포도", 1500, 0, "image/6.jpg"));
        drinkArrayList5.add(new Drink("핫식스", 1500, 0, "image/7.jpg"));
        drinkArrayList5.add(new Drink("몬스터드링크", 1500, 0, "image/9.jpg"));
        drinkArrayList5.add(new Drink("레드불", 1500, 0, "image/8.jpg"));
        drinkArrayList5.add(new Drink("빡텐션", 1500, 0, "image/10.jpg"));
        drinkArrayList5.add(new Drink("파워에이드", 1500, 0, "image/13.jpg"));

        ArrayList<Drink> drinkArrayList6 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList6.add(new Drink("스파클링", 1500, 9, "image/16.jpg"));
        drinkArrayList6.add(new Drink("레드불", 1500, 5, "image/8.jpg"));
        drinkArrayList6.add(new Drink("빡텐션", 1500, 10, "image/10.jpg"));
        drinkArrayList6.add(new Drink("코카콜라", 1500, 130, "image/1.jpg"));
        drinkArrayList6.add(new Drink("스프라이트", 1500, 22, "image/4.jpg"));
        drinkArrayList6.add(new Drink("환타오렌지", 1500, 10, "image/5.jpg"));
        drinkArrayList6.add(new Drink("포카리스웨트", 1500, 10, "image/11.jpg"));
        drinkArrayList6.add(new Drink("게토레이", 1500, 0, "image/12.jpg"));
        drinkArrayList6.add(new Drink("비락식혜", 1500, 0, "image/17.jpg"));
        drinkArrayList6.add(new Drink("솔의눈", 1500, 0, "image/18.jpg"));
        drinkArrayList6.add(new Drink("펩시콜라", 1500, 0, "image/2.jpg"));
        drinkArrayList6.add(new Drink("칠성사이다", 1500, 0, "image/3.jpg"));
        drinkArrayList6.add(new Drink("레쓰비", 1500, 0, "image/15.jpg"));
        drinkArrayList6.add(new Drink("데자와", 1500, 0, "image/19.jpg"));
        drinkArrayList6.add(new Drink("환타포도", 1500, 0, "image/6.jpg"));
        drinkArrayList6.add(new Drink("핫식스", 1500, 0, "image/7.jpg"));
        drinkArrayList6.add(new Drink("몬스터드링크", 1500, 0, "image/9.jpg"));
        drinkArrayList6.add(new Drink("마운틴듀", 1500, 0, "image/20.jpg"));
        drinkArrayList6.add(new Drink("밀키스", 1500, 0, "image/14.jpg"));
        drinkArrayList6.add(new Drink("파워에이드", 1500, 0, "image/13.jpg"));

        ArrayList<Drink> drinkArrayList7 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList7.add(new Drink("핫식스", 1500, 12, "image/7.jpg"));
        drinkArrayList7.add(new Drink("몬스터드링크", 1500, 32, "image/9.jpg"));
        drinkArrayList7.add(new Drink("파워에이드", 1500, 7, "image/13.jpg"));
        drinkArrayList7.add(new Drink("펩시콜라", 1500, 5, "image/2.jpg"));
        drinkArrayList7.add(new Drink("게토레이", 1500, 20, "image/12.jpg"));
        drinkArrayList7.add(new Drink("비락식혜", 1500, 0, "image/17.jpg"));
        drinkArrayList7.add(new Drink("환타오렌지", 1500, 10, "image/5.jpg"));
        drinkArrayList7.add(new Drink("포카리스웨트", 1500, 0, "image/11.jpg"));
        drinkArrayList7.add(new Drink("스파클링", 1500, 0, "image/16.jpg"));
        drinkArrayList7.add(new Drink("데자와", 1500, 0, "image/19.jpg"));
        drinkArrayList7.add(new Drink("솔의눈", 1500, 0, "image/18.jpg"));
        drinkArrayList7.add(new Drink("레쓰비", 1500, 0, "image/15.jpg"));
        drinkArrayList7.add(new Drink("코카콜라", 1500, 0, "image/1.jpg"));
        drinkArrayList7.add(new Drink("환타포도", 1500, 0, "image/6.jpg"));
        drinkArrayList7.add(new Drink("마운틴듀", 1500, 0, "image/20.jpg"));
        drinkArrayList7.add(new Drink("빡텐션", 1500, 0, "image/10.jpg"));
        drinkArrayList7.add(new Drink("밀키스", 1500, 0, "image/14.jpg"));
        drinkArrayList7.add(new Drink("레드불", 1500, 0, "image/8.jpg"));
        drinkArrayList7.add(new Drink("칠성사이다", 1500, 0, "image/3.jpg"));
        drinkArrayList7.add(new Drink("스프라이트", 1500, 0, "image/4.jpg"));


        ArrayList<Drink> drinkArrayList8 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList8.add(new Drink("레드불", 1500, 20, "image/8.jpg"));
        drinkArrayList8.add(new Drink("빡텐션", 1500, 1, "image/10.jpg"));
        drinkArrayList8.add(new Drink("솔의눈", 1500, 2, "image/18.jpg"));
        drinkArrayList8.add(new Drink("레쓰비", 1500, 0, "image/15.jpg"));
        drinkArrayList8.add(new Drink("데자와", 1500, 6, "image/19.jpg"));
        drinkArrayList8.add(new Drink("코카콜라", 1500, 10, "image/1.jpg"));
        drinkArrayList8.add(new Drink("환타포도", 1500, 2, "image/6.jpg"));
        drinkArrayList8.add(new Drink("포카리스웨트", 1500, 0, "image/11.jpg"));
        drinkArrayList8.add(new Drink("스파클링", 1500, 0, "image/16.jpg"));
        drinkArrayList8.add(new Drink("게토레이", 1500, 0, "image/12.jpg"));
        drinkArrayList8.add(new Drink("비락식혜", 1500, 0, "image/17.jpg"));
        drinkArrayList8.add(new Drink("스프라이트", 1500, 0, "image/4.jpg"));
        drinkArrayList8.add(new Drink("환타오렌지", 1500, 0, "image/5.jpg"));
        drinkArrayList8.add(new Drink("핫식스", 1500, 0, "image/7.jpg"));
        drinkArrayList8.add(new Drink("몬스터드링크", 1500, 0, "image/9.jpg"));
        drinkArrayList8.add(new Drink("마운틴듀", 1500, 0, "image/20.jpg"));
        drinkArrayList8.add(new Drink("펩시콜라", 1500, 0, "image/2.jpg"));
        drinkArrayList8.add(new Drink("칠성사이다", 1500, 0, "image/3.jpg"));
        drinkArrayList8.add(new Drink("밀키스", 1500, 0, "image/14.jpg"));
        drinkArrayList8.add(new Drink("파워에이드", 1500, 0, "image/13.jpg"));

        /*
         * 이 곳에 정의된 DVM들은 주소 정보가 없음
         * 주소 정보를 얻기 위해선 마찬가지로 네트워킹을 통해서만 알 수 있음
         * */
        DVM dvm1 = new DVM(drinkArrayList, 1, 101);
        DVM dvm2 = new DVM(drinkArrayList2, 2, 202);
        DVM dvm3 = new DVM(drinkArrayList3, 3, 303);
        DVM dvm4 = new DVM(drinkArrayList4, 4, 404);
        DVM dvm5 = new DVM(drinkArrayList5, 5, 505);
        DVM dvm6 = new DVM(drinkArrayList6, 6, 606);
        DVM dvm7 = new DVM(drinkArrayList7, 7, 707);
        DVM dvm8 = new DVM(drinkArrayList8, 8, 808);
        dvm1.dvmOn();
        dvm2.dvmOn();
        dvm3.dvmOn();
        dvm4.dvmOn();
        dvm5.dvmOn();
        dvm6.dvmOn();
        dvm7.dvmOn();
        dvm8.dvmOn();
    }

    public static void main(String[] args) throws IOException {
        int STUB_TEST_ID = 999;
        StubTest stubTest = new StubTest();
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("=======<Stub Test>=======");
            System.out.print("메시지를 보내고자 하는 DVM ID: ");
            int dstId = scan.nextInt();
            System.out.print("메시지 타입: ");
            int msgType = scan.nextInt();
            System.out.print("메시지 내용: ");
            Scanner scan2 = new Scanner(System.in);
            if(dstId == -1) break;
            String msg = scan2.nextLine();
            stubTest.test(STUB_TEST_ID, dstId, msgType, msg);
        }
    }

    public void test(int src_id, int dst_id, int msg_type, String msg){
        Message message = new Message();
        message.createMessage(src_id, dst_id, msg_type, msg);
        if(dst_id == 0){
            for(int i = 1; i <= 8; i++){
                try (Socket socket = new Socket("localhost", 8000 + i)) {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                    System.out.println("[StubTest] DVM" + i
                            + "에게 메시지 발신(메시지 유형: " + message.getMsg_type() + ", 메시지 내용: " + message.getMsg() + ")");
                    switch (message.getMsg_type()) {
                        case MsgType.REQUEST_STOCK:
                        case MsgType.REQUEST_LOCATION:
                        case MsgType.DRINK_SALE_CHECK:
                            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                            Message temp = (Message) objectInputStream.readObject();
                            System.out.println("[StubTest] DVM" + temp.getSrc_id()
                                    + "로부터 메시지 수신(메시지 유형: " + temp.getMsg_type() + ", 메시지 내용: " + temp.getMsg() + ")");
                            break;
                        case MsgType.RESPONSE_STOCK:
                        case MsgType.RESPONSE_LOCATION:
                        case MsgType.DRINK_SALE_RESPONSE:
                            System.out.println("[StubTest] 발신 완료");
                            break;
                        default:
                            System.out.println("잘못된 메시지 타입입니다.");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            try{
                Socket socket = new Socket("localhost", 8000 + dst_id);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
                System.out.println("[StubTest] DVM" + message.getDst_id()
                        + "에게 메시지 발신(메시지 유형: " + message.getMsg_type() + ", 메시지 내용: "+message.getMsg() + ")");
                switch (message.getMsg_type()){
                    case MsgType.REQUEST_STOCK:
                    case MsgType.REQUEST_LOCATION:
                    case MsgType.DRINK_SALE_CHECK:
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Message temp = (Message)objectInputStream.readObject();
                        System.out.println("[StubTest] DVM" + temp.getSrc_id()
                                + "로부터 메시지 수신(메시지 유형: " + temp.getMsg_type() + ", 메시지 내용: "+temp.getMsg() + ")");
                        break;
                    case MsgType.RESPONSE_STOCK:
                    case MsgType.RESPONSE_LOCATION:
                    case MsgType.DRINK_SALE_RESPONSE:
                        System.out.println("[StubTest] 발신 완료");
                        break;
                    default:
                        System.out.println("잘못된 메시지 타입입니다.");
                }
                socket.close();
            }catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}