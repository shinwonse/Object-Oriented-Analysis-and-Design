import java.util.ArrayList;
import java.util.Arrays;

public class OtherDVMs {
    ArrayList<DVM> dvmList =new ArrayList<DVM>();
    Network network;


    OtherDVMs(){
        init();
    }

    DVM getDVM(int index){
        return dvmList.get(index);
    }

    public ArrayList<DVM> getDVMList(){return dvmList;}


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
        Message stockBroadCastMessage = currentDVM.makeStockRequestMessage(0, drink_info.getName());
        ArrayList<DVM> accessible_DVM_list = (ArrayList<DVM>) currentDVM.requestStockMessage(network, stockBroadCastMessage);

        return accessible_DVM_list;
    }

    public String requestDrink(Drink selected_drink, int currentDVMIndex) {
        dvmList.get(currentDVMIndex).updateStock(selected_drink);
        String result = "       <음료 구매 완료>" +
                "\n구매 진행한 DVM: DVM" + String.valueOf(currentDVMIndex + 1)
                + "\n구매한 음료: "+ selected_drink.getName()
                + "\n음료 가격: " + selected_drink.getPrice() + "원";
        return result;
    }

    String showAccessibleDVMsLocation(ArrayList<DVM> accessibleDVMList, int currentDVMIndex){
        ArrayList<Integer> dvmLocationList = new ArrayList<>();
        for(int i = 0; i < accessibleDVMList.size(); i++){
            DVM currentDVM = getDVM(currentDVMIndex);
            Message locationRequestMessage = currentDVM.makeLocationRequestMessage(accessibleDVMList.get(i).getId());
            int address = currentDVM.requestLocationMessage(network, locationRequestMessage);
            dvmLocationList.add(address);
        }
        StringBuilder locationListStr = new StringBuilder();
        for(int i = 0; i < accessibleDVMList.size(); i++){
            locationListStr.append("DVM 명: DVM").append((accessibleDVMList.get(i).getId() + 1)).append(" / 위치: ").append(dvmLocationList.get(i)).append("\n");
        }
        return String.valueOf(locationListStr);
    }

    private void init() {
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList2 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList2.add(new Drink("빡텐션", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList2.add(new Drink("파워에이드", 1500, 120, "src/main/resources/image/13.jpg"));
        drinkArrayList2.add(new Drink("밀키스", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList2.add(new Drink("레쓰비", 1500, 12, "src/main/resources/image/15.jpg"));
        drinkArrayList2.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList2.add(new Drink("데자와", 1500, 10, "src/main/resources/image/19.jpg"));
        drinkArrayList2.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList2.add(new Drink("펩시콜라", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList2.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList2.add(new Drink("스프라이트", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList2.add(new Drink("환타오렌지", 1500, 0, "src/main/resources/image/5.jpg"));
        drinkArrayList2.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList2.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList2.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList2.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList2.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList2.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList2.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList2.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList2.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList3 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList3.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList3.add(new Drink("포카리스웨트", 1500, 10, "src/main/resources/image/11.jpg"));
        drinkArrayList3.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList3.add(new Drink("빡텐션", 1500, 13, "src/main/resources/image/10.jpg"));
        drinkArrayList3.add(new Drink("파워에이드", 1500, 10, "src/main/resources/image/13.jpg"));
        drinkArrayList3.add(new Drink("밀키스", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList3.add(new Drink("게토레이", 1500, 20, "src/main/resources/image/12.jpg"));
        drinkArrayList3.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList3.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList3.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList3.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList3.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList3.add(new Drink("코카콜라", 1500, 0, "src/main/resources/image/1.jpg"));
        drinkArrayList3.add(new Drink("펩시콜라", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList3.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList3.add(new Drink("스프라이트", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList3.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList3.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList3.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList3.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList4 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList4.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList4.add(new Drink("칠성사이다", 1500, 4, "src/main/resources/image/3.jpg"));
        drinkArrayList4.add(new Drink("스프라이트", 1500, 8, "src/main/resources/image/4.jpg"));
        drinkArrayList4.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList4.add(new Drink("포카리스웨트", 1500, 10, "src/main/resources/image/11.jpg"));
        drinkArrayList4.add(new Drink("스파클링", 1500, 4, "src/main/resources/image/16.jpg"));
        drinkArrayList4.add(new Drink("게토레이", 1500, 21, "src/main/resources/image/12.jpg"));
        drinkArrayList4.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList4.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList4.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList4.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList4.add(new Drink("코카콜라", 1500, 0, "src/main/resources/image/1.jpg"));
        drinkArrayList4.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList4.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList4.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList4.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));
        drinkArrayList4.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList4.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList4.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList4.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList5 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList5.add(new Drink("레쓰비", 1500, 12, "src/main/resources/image/15.jpg"));
        drinkArrayList5.add(new Drink("펩시콜라", 1500, 32, "src/main/resources/image/2.jpg"));
        drinkArrayList5.add(new Drink("칠성사이다", 1500, 1, "src/main/resources/image/3.jpg"));
        drinkArrayList5.add(new Drink("마운틴듀", 1500, 23, "src/main/resources/image/20.jpg"));
        drinkArrayList5.add(new Drink("밀키스", 1500, 5, "src/main/resources/image/14.jpg"));
        drinkArrayList5.add(new Drink("스프라이트", 1500, 7, "src/main/resources/image/4.jpg"));
        drinkArrayList5.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList5.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList5.add(new Drink("코카콜라", 1500, 0, "src/main/resources/image/1.jpg"));
        drinkArrayList5.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList5.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList5.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList5.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList5.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList5.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList5.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList5.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList5.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList5.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList5.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList6 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList6.add(new Drink("스파클링", 1500, 9, "src/main/resources/image/16.jpg"));
        drinkArrayList6.add(new Drink("레드불", 1500, 5, "src/main/resources/image/8.jpg"));
        drinkArrayList6.add(new Drink("빡텐션", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList6.add(new Drink("코카콜라", 1500, 130, "src/main/resources/image/1.jpg"));
        drinkArrayList6.add(new Drink("스프라이트", 1500, 22, "src/main/resources/image/4.jpg"));
        drinkArrayList6.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList6.add(new Drink("포카리스웨트", 1500, 10, "src/main/resources/image/11.jpg"));
        drinkArrayList6.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList6.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList6.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList6.add(new Drink("펩시콜라", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList6.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList6.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList6.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList6.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList6.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList6.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList6.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));
        drinkArrayList6.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));

        drinkArrayList6.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList7 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList7.add(new Drink("핫식스", 1500, 12, "src/main/resources/image/7.jpg"));
        drinkArrayList7.add(new Drink("몬스터드링크", 1500, 32, "src/main/resources/image/9.jpg"));
        drinkArrayList7.add(new Drink("파워에이드", 1500, 7, "src/main/resources/image/13.jpg"));
        drinkArrayList7.add(new Drink("펩시콜라", 1500, 5, "src/main/resources/image/2.jpg"));
        drinkArrayList7.add(new Drink("게토레이", 1500, 20, "src/main/resources/image/12.jpg"));
        drinkArrayList7.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList7.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList7.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList7.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList7.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList7.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList7.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList7.add(new Drink("코카콜라", 1500, 0, "src/main/resources/image/1.jpg"));
        drinkArrayList7.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList7.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));
        drinkArrayList7.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList7.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList7.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList7.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList7.add(new Drink("스프라이트", 1500, 0, "src/main/resources/image/4.jpg"));


        ArrayList<Drink> drinkArrayList8 = new ArrayList<>(); // 전체 음료수 리스트

        drinkArrayList8.add(new Drink("레드불", 1500, 20, "src/main/resources/image/8.jpg"));
        drinkArrayList8.add(new Drink("빡텐션", 1500, 1, "src/main/resources/image/10.jpg"));
        drinkArrayList8.add(new Drink("솔의눈", 1500, 2, "src/main/resources/image/18.jpg"));
        drinkArrayList8.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList8.add(new Drink("데자와", 1500, 6, "src/main/resources/image/19.jpg"));
        drinkArrayList8.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList8.add(new Drink("환타포도", 1500, 2, "src/main/resources/image/6.jpg"));
        drinkArrayList8.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList8.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList8.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList8.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList8.add(new Drink("스프라이트", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList8.add(new Drink("환타오렌지", 1500, 0, "src/main/resources/image/5.jpg"));
        drinkArrayList8.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList8.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList8.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));
        drinkArrayList8.add(new Drink("펩시콜라", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList8.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList8.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList8.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));

        DVM dvm1 = new DVMc(drinkArrayList, 0, 101);
        DVM dvm2 = new DVMc(drinkArrayList2, 1, 202);
        DVM dvm3 = new DVMc(drinkArrayList3, 2, 303);
        DVM dvm4 = new DVMc(drinkArrayList4, 3, 404);
        DVM dvm5 = new DVMc(drinkArrayList5, 4, 505);
        DVM dvm6 = new DVMc(drinkArrayList6, 5, 606);
        DVM dvm7 = new DVMc(drinkArrayList7, 6, 707);
        DVM dvm8 = new DVMc(drinkArrayList8, 7, 808);
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