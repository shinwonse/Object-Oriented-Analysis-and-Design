import java.util.ArrayList;
import java.util.Arrays;

<<<<<<< Updated upstream
public class OtherDVMs {
    ArrayList<DVM> dvmList;
=======
public class OtherDVMs extends Thread{
    ArrayList<DVM> dvmList =new ArrayList<DVM>();
>>>>>>> Stashed changes
    Network network;


    OtherDVMs(){
        init();
    }

    DVM getDVM(int index){
        DVM currentDVM = dvmList.get(index);
        for(Drink drink : currentDVM.getDrink_list()){
            Message message = new Message();
            message.createMessage(currentDVM.getDVMId(), currentDVM.getDVMId(), MsgType.REQUEST_STOCK, drink.getName());
            int stock = (int)network.handleRequestMessage(message);
            drink.setStock(stock);
        }
        return currentDVM;
    }

<<<<<<< Updated upstream
    ArrayList<DVM> getDVMList(){return dvmList;}
=======
    public ArrayList<DVM> getDVMList(){
        for(DVM dvm : dvmList){
            Message message = new Message();
            message.createMessage(dvm.getDVMId(), dvm.getDVMId(), MsgType.REQUEST_LOCATION);
            int address = (int)network.handleRequestMessage(message);
            dvm.setAddress(address);
        }
        return dvmList;
    }
>>>>>>> Stashed changes


    public boolean checkCurrentDVMsStock(Drink selectedDrink, DVM currentDVM) {
        ArrayList<Drink> drink_list = currentDVM.getDrink_list();
        int dvmStock = 0;
        for(int i = 0; i < drink_list.size(); i++){
            if(drink_list.get(i).getName().equals(selectedDrink.getName())){
                dvmStock = drink_list.get(i).getStock();
            }
        }
        if(dvmStock > 0)
            return true;
        else
            return false;
    }

    ArrayList<DVM> checkOtherDVMsStock(Drink drink_info, DVM currentDVM){
<<<<<<< Updated upstream
        Message stockBroadCastMessage = new Message().createMessage(currentDVM.getId(), 0, 1, drink_info.getName());
        ArrayList<DVM> accessible_DVM_list = network.requestBroadcastMessage(stockBroadCastMessage);
=======
        //Message stockBroadCastMessage = currentDVM.makeStockRequestMessage(0, drink_info.getName());
        Message message = new Message();
        message.createMessage(currentDVM.getDVMId(), 0, MsgType.REQUEST_STOCK, drink_info.getName());
        ArrayList<DVM> accessible_DVM_list = (ArrayList<DVM>) network.handleRequestMessage(message);
        //ArrayList<DVM> accessible_DVM_list = (ArrayList<DVM>) currentDVM.requestStockMessage(network, stockBroadCastMessage);
>>>>>>> Stashed changes

        return accessible_DVM_list;
    }

<<<<<<< Updated upstream
    public String requestDrink(Drink selected_drink, int currentDVMIndex) {
        dvmList.get(currentDVMIndex).updateStock(selected_drink);
        String result = "       <���� ���� �Ϸ�>" +
                "\n���� ������ DVM: DVM" + String.valueOf(currentDVMIndex + 1)
                + "\n������ ����: "+ selected_drink.getName()
                + "\n���� ����: " + selected_drink.getPrice() + "��";
=======
    public String requestDrink(Drink selected_drink, DVM currentDVM) {
        Message message = new Message();

        message.createMessage(currentDVM.getDVMId(), currentDVM.getDVMId(), MsgType.DRINK_SALE_CHECK, selected_drink.getName());
        int remainedStock = (int)network.handleRequestMessage(message);
//        dvmList.get(currentDVMIndex).updateStock(selected_drink);
        String result = "       <음료 구매 완료>" +
                "\n구매 진행한 DVM: DVM" + (currentDVM.getDVMId() + 1)
                + "\n구매한 음료: "+ selected_drink.getName()
                + "\n음료 가격: " + selected_drink.getPrice() + "원"
                + "\n잔여 재고: " + remainedStock + "개";
>>>>>>> Stashed changes
        return result;
    }

    String showAccessibleDVMsLocation(ArrayList<DVM> accessibleDVMList, DVM currentDVM){
        ArrayList<Integer> dvmLocationList = new ArrayList<>();
<<<<<<< Updated upstream
        for(int i = 0; i < accessibleDVMList.size(); i++){
            DVM currentDVM = getDVM(currentDVMIndex);
            Message locationRequestMessage = new Message().createMessage(currentDVM.getId(), accessibleDVMList.get(i).getId(), MsgType.REQUEST_LOCATION);
            int address = network.requestNormalMessage(locationRequestMessage);
=======
        for(int i = 0; i < accessibleDVMList.size(); i++) {
            Message message = new Message();
            message.createMessage(currentDVM.getDVMId(), accessibleDVMList.get(i).getDVMId(), MsgType.REQUEST_LOCATION);
            int address = (int)network.handleRequestMessage(message);
>>>>>>> Stashed changes
            dvmLocationList.add(address);
        }
        StringBuilder locationListStr = new StringBuilder();
        for(int i = 0; i < accessibleDVMList.size(); i++){
<<<<<<< Updated upstream
            locationListStr.append("DVM ��: DVM").append((accessibleDVMList.get(i).getId() + 1)).append(" / ��ġ: ").append(dvmLocationList.get(i)).append("\n");
=======
            locationListStr.append("DVM 명: DVM").append((accessibleDVMList.get(i).getDVMId() + 1)).append(" / 위치: ").append(dvmLocationList.get(i)).append("\n");
>>>>>>> Stashed changes
        }
        return String.valueOf(locationListStr);
    }

    private void init() {
<<<<<<< Updated upstream
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // ��ü ����� ����Ʈ
        drinkArrayList.add(new Drink("��ī�ݶ�", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("����ݶ�", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("ĥ�����̴�", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("��������Ʈ", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("ȯŸ������", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("ȯŸ����", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("�ֽĽ�", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("�����", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("���͵帵ũ", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("���ټ�", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("��ī������Ʈ", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("���䷹��", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("�Ŀ����̵�", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("��Ű��", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("������", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("����Ŭ��", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("�������", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("���Ǵ�", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("���ڿ�", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("����ƾ��", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList2 = new ArrayList<>(); // ��ü ����� ����Ʈ
        drinkArrayList2.add(new Drink("���ټ�", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList2.add(new Drink("�Ŀ����̵�", 1500, 10, "src/main/resources/image/13.jpg"));
        drinkArrayList2.add(new Drink("��Ű��", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList2.add(new Drink("������", 1500, 10, "src/main/resources/image/15.jpg"));
        drinkArrayList2.add(new Drink("����Ŭ��", 1500, 10, "src/main/resources/image/16.jpg"));
        drinkArrayList2.add(new Drink("���ڿ�", 1500, 10, "src/main/resources/image/19.jpg"));
        drinkArrayList2.add(new Drink("��ī�ݶ�", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList2.add(new Drink("����ݶ�", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList2.add(new Drink("ĥ�����̴�", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList2.add(new Drink("��������Ʈ", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList2.add(new Drink("ȯŸ������", 1500, 0, "src/main/resources/image/5.jpg"));
        drinkArrayList2.add(new Drink("ȯŸ����", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList2.add(new Drink("�ֽĽ�", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList2.add(new Drink("�����", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList2.add(new Drink("���͵帵ũ", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList2.add(new Drink("��ī������Ʈ", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList2.add(new Drink("���䷹��", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList2.add(new Drink("�������", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList2.add(new Drink("���Ǵ�", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList2.add(new Drink("����ƾ��", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList3 = new ArrayList<>(); // ��ü ����� ����Ʈ
        drinkArrayList3.add(new Drink("ȯŸ������", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList3.add(new Drink("��ī������Ʈ", 1500, 10, "src/main/resources/image/11.jpg"));
        drinkArrayList3.add(new Drink("�����", 1500, 10, "src/main/resources/image/8.jpg"));
        drinkArrayList3.add(new Drink("���ټ�", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList3.add(new Drink("�Ŀ����̵�", 1500, 10, "src/main/resources/image/13.jpg"));
        drinkArrayList3.add(new Drink("��Ű��", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList3.add(new Drink("���䷹��", 1500, 20, "src/main/resources/image/12.jpg"));
        drinkArrayList3.add(new Drink("�������", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList3.add(new Drink("���Ǵ�", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList3.add(new Drink("������", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList3.add(new Drink("����Ŭ��", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList3.add(new Drink("���ڿ�", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList3.add(new Drink("��ī�ݶ�", 1500, 0, "src/main/resources/image/1.jpg"));
        drinkArrayList3.add(new Drink("����ݶ�", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList3.add(new Drink("ĥ�����̴�", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList3.add(new Drink("��������Ʈ", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList3.add(new Drink("ȯŸ����", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList3.add(new Drink("�ֽĽ�", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList3.add(new Drink("���͵帵ũ", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList3.add(new Drink("����ƾ��", 1500, 0, "src/main/resources/image/20.jpg"));

        DVM dvm1 = new DVM(drinkArrayList, 0, 101);
        DVM dvm2 = new DVM(drinkArrayList2, 1, 202);
        DVM dvm3 = new DVM(drinkArrayList3, 2, 303);
        DVM dvm4 = new DVM(drinkArrayList3, 3, 404);
        DVM dvm5 = new DVM(drinkArrayList3, 4, 505);
        DVM dvm6 = new DVM(drinkArrayList3, 5, 606);
        DVM dvm7 = new DVM(drinkArrayList3, 6, 707);
        DVM dvm8 = new DVM( drinkArrayList3, 7, 808);
=======
        /*
        * 이 곳에 정의된 Drink들과 Drink리스트들은 재고에 대한 정보를 담지 않고 있음
        * 재고 정보는 반드시 네트워킹을 통해서만 전달받을 수 있음
        * 따라서 화면에 출력되는 재고 정보들은 전부 소켓통신을 통한 결과값을 출력받는 것임
        * */
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList.add(new Drink("코카콜라", 1500, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("펩시콜라", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("칠성사이다", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("스프라이트", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("환타오렌지", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("환타포도", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("핫식스", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("레드불", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("몬스터드링크", 1500, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("빡텐션", 1500, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("포카리스웨트", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("게토레이", 1500, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("파워에이드", 1500,  "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("밀키스", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("레쓰비", 1500, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("스파클링", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("비락식혜", 1500, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("솔의눈", 1500, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("데자와", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("마운틴듀", 1500,  "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList2 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList2.add(new Drink("빡텐션", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList2.add(new Drink("파워에이드", 1500, "src/main/resources/image/13.jpg"));
        drinkArrayList2.add(new Drink("밀키스", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList2.add(new Drink("레쓰비", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList2.add(new Drink("스파클링", 1500, "src/main/resources/image/16.jpg"));
        drinkArrayList2.add(new Drink("데자와", 1500, "src/main/resources/image/19.jpg"));
        drinkArrayList2.add(new Drink("코카콜라", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList2.add(new Drink("펩시콜라", 1500, "src/main/resources/image/2.jpg"));
        drinkArrayList2.add(new Drink("칠성사이다", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList2.add(new Drink("스프라이트", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList2.add(new Drink("환타오렌지", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList2.add(new Drink("환타포도", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList2.add(new Drink("핫식스", 1500, "src/main/resources/image/7.jpg"));
        drinkArrayList2.add(new Drink("레드불", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList2.add(new Drink("몬스터드링크", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList2.add(new Drink("포카리스웨트", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList2.add(new Drink("게토레이", 1500, "src/main/resources/image/12.jpg"));
        drinkArrayList2.add(new Drink("비락식혜", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList2.add(new Drink("솔의눈", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList2.add(new Drink("마운틴듀", 1500, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList3 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList3.add(new Drink("환타오렌지", 1500, "src/main/resources/image/5.jpg"));
        drinkArrayList3.add(new Drink("포카리스웨트", 1500, "src/main/resources/image/11.jpg"));
        drinkArrayList3.add(new Drink("레드불", 1500, "src/main/resources/image/8.jpg"));
        drinkArrayList3.add(new Drink("빡텐션", 1500, "src/main/resources/image/10.jpg"));
        drinkArrayList3.add(new Drink("파워에이드", 1500, "src/main/resources/image/13.jpg"));
        drinkArrayList3.add(new Drink("밀키스", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList3.add(new Drink("게토레이", 1500, "src/main/resources/image/12.jpg"));
        drinkArrayList3.add(new Drink("비락식혜", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList3.add(new Drink("솔의눈", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList3.add(new Drink("레쓰비", 1500, "src/main/resources/image/15.jpg"));
        drinkArrayList3.add(new Drink("스파클링", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList3.add(new Drink("데자와", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList3.add(new Drink("코카콜라", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList3.add(new Drink("펩시콜라", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList3.add(new Drink("칠성사이다", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList3.add(new Drink("스프라이트", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList3.add(new Drink("환타포도", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList3.add(new Drink("핫식스", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList3.add(new Drink("몬스터드링크", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList3.add(new Drink("마운틴듀", 1500, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList4 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList4.add(new Drink("펩시콜라", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList4.add(new Drink("칠성사이다", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList4.add(new Drink("스프라이트", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList4.add(new Drink("환타오렌지", 1500, "src/main/resources/image/5.jpg"));
        drinkArrayList4.add(new Drink("포카리스웨트", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList4.add(new Drink("스파클링", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList4.add(new Drink("게토레이", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList4.add(new Drink("비락식혜", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList4.add(new Drink("솔의눈", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList4.add(new Drink("레쓰비", 1500, "src/main/resources/image/15.jpg"));
        drinkArrayList4.add(new Drink("데자와", 1500, "src/main/resources/image/19.jpg"));
        drinkArrayList4.add(new Drink("코카콜라", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList4.add(new Drink("환타포도", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList4.add(new Drink("핫식스", 1500, "src/main/resources/image/7.jpg"));
        drinkArrayList4.add(new Drink("몬스터드링크", 1500, "src/main/resources/image/9.jpg"));
        drinkArrayList4.add(new Drink("마운틴듀", 1500, "src/main/resources/image/20.jpg"));
        drinkArrayList4.add(new Drink("밀키스", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList4.add(new Drink("레드불", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList4.add(new Drink("빡텐션", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList4.add(new Drink("파워에이드", 1500,  "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList5 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList5.add(new Drink("레쓰비", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList5.add(new Drink("펩시콜라", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList5.add(new Drink("칠성사이다", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList5.add(new Drink("마운틴듀", 1500,  "src/main/resources/image/20.jpg"));
        drinkArrayList5.add(new Drink("밀키스", 1500, "src/main/resources/image/14.jpg"));
        drinkArrayList5.add(new Drink("스프라이트", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList5.add(new Drink("환타오렌지", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList5.add(new Drink("데자와", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList5.add(new Drink("코카콜라", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList5.add(new Drink("포카리스웨트", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList5.add(new Drink("스파클링", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList5.add(new Drink("게토레이", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList5.add(new Drink("비락식혜", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList5.add(new Drink("솔의눈", 1500, "src/main/resources/image/18.jpg"));
        drinkArrayList5.add(new Drink("환타포도", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList5.add(new Drink("핫식스", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList5.add(new Drink("몬스터드링크", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList5.add(new Drink("레드불", 1500, "src/main/resources/image/8.jpg"));
        drinkArrayList5.add(new Drink("빡텐션", 1500, "src/main/resources/image/10.jpg"));
        drinkArrayList5.add(new Drink("파워에이드", 1500,  "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList6 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList6.add(new Drink("스파클링", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList6.add(new Drink("레드불", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList6.add(new Drink("빡텐션", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList6.add(new Drink("코카콜라", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList6.add(new Drink("스프라이트", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList6.add(new Drink("환타오렌지", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList6.add(new Drink("포카리스웨트", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList6.add(new Drink("게토레이", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList6.add(new Drink("비락식혜", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList6.add(new Drink("솔의눈", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList6.add(new Drink("펩시콜라", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList6.add(new Drink("칠성사이다", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList6.add(new Drink("레쓰비", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList6.add(new Drink("데자와", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList6.add(new Drink("환타포도", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList6.add(new Drink("핫식스", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList6.add(new Drink("몬스터드링크", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList6.add(new Drink("마운틴듀", 1500,  "src/main/resources/image/20.jpg"));
        drinkArrayList6.add(new Drink("밀키스", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList6.add(new Drink("파워에이드", 1500,  "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList7 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList7.add(new Drink("핫식스", 1500, "src/main/resources/image/7.jpg"));
        drinkArrayList7.add(new Drink("몬스터드링크", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList7.add(new Drink("파워에이드", 1500,  "src/main/resources/image/13.jpg"));
        drinkArrayList7.add(new Drink("펩시콜라", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList7.add(new Drink("게토레이", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList7.add(new Drink("비락식혜", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList7.add(new Drink("환타오렌지", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList7.add(new Drink("포카리스웨트", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList7.add(new Drink("스파클링", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList7.add(new Drink("데자와", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList7.add(new Drink("솔의눈", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList7.add(new Drink("레쓰비", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList7.add(new Drink("코카콜라", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList7.add(new Drink("환타포도", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList7.add(new Drink("마운틴듀", 1500,  "src/main/resources/image/20.jpg"));
        drinkArrayList7.add(new Drink("빡텐션", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList7.add(new Drink("밀키스", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList7.add(new Drink("레드불", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList7.add(new Drink("칠성사이다", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList7.add(new Drink("스프라이트", 1500,  "src/main/resources/image/4.jpg"));


        ArrayList<Drink> drinkArrayList8 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList8.add(new Drink("레드불", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList8.add(new Drink("빡텐션", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList8.add(new Drink("솔의눈", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList8.add(new Drink("레쓰비", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList8.add(new Drink("데자와", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList8.add(new Drink("코카콜라", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList8.add(new Drink("환타포도", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList8.add(new Drink("포카리스웨트", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList8.add(new Drink("스파클링", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList8.add(new Drink("게토레이", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList8.add(new Drink("비락식혜", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList8.add(new Drink("스프라이트", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList8.add(new Drink("환타오렌지", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList8.add(new Drink("핫식스", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList8.add(new Drink("몬스터드링크", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList8.add(new Drink("마운틴듀", 1500,  "src/main/resources/image/20.jpg"));
        drinkArrayList8.add(new Drink("펩시콜라", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList8.add(new Drink("칠성사이다", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList8.add(new Drink("밀키스", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList8.add(new Drink("파워에이드", 1500,  "src/main/resources/image/13.jpg"));

        /*
        * 이 곳에 정의된 DVM들은 주소 정보가 없음
        * 주소 정보를 얻기 위해선 마찬가지로 네트워킹을 통해서만 알 수 있음
        * */
        DVM dvm1 = new DVM1(drinkArrayList, 1);
        DVM dvm2 = new DVM2(drinkArrayList2, 2);
        DVM dvm3 = new DVM3(drinkArrayList3, 3);
        DVM dvm4 = new DVM4(drinkArrayList4, 4);
        DVM dvm5 = new DVM5(drinkArrayList5, 5);
        DVM dvm6 = new DVM6(drinkArrayList6, 6);
        DVM dvm7 = new DVM7(drinkArrayList7, 7);
        DVM dvm8 = new DVM8(drinkArrayList8, 8);

>>>>>>> Stashed changes
        ArrayList<DVM> tempList = new ArrayList<DVM>();
        tempList.add(dvm1);
        tempList.add(dvm2);
        tempList.add(dvm3);
        tempList.add(dvm4);
        tempList.add(dvm5);
        tempList.add(dvm6);
        tempList.add(dvm7);
        tempList.add(dvm8);
        dvmList = tempList;

        network = new Network(dvmList);
    }
}
