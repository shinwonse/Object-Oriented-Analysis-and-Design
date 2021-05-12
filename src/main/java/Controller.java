import java.util.ArrayList;

public class Controller {
    ArrayList<Code> code_list = new ArrayList<Code>();
    int currentDVMIndex;
    ArrayList<DVM> accessible_DVM_list;
    Card card_info;
    OtherDVMs otherDVMs;
    Drink selected_drink;
    CardPayment cardPayment = new CardPayment();
    CodePayment codePayment = new CodePayment();

    Controller(){
        otherDVMs = new OtherDVMs();
    }



    int selectCurrentDrink(int dialNum) {
        final int EMPTY_ALL_STOCK = 0; // 모든 DVM의 재고가 0임
        final int CUR_IN_STOCK = 1;    // 현재 DVM에 재고가 있음
        final int OTHER_IN_STOCK = 2;  // 다른 DVM에 재고가 있음
        DVM currentDVM = otherDVMs.getDVM(currentDVMIndex);
        selected_drink = currentDVM.getDrink_list().get(dialNum - 1);   //selected_drink 라는 전역변수에 저장
        boolean current_stock = otherDVMs.checkCurrentDVMsStock(selected_drink, currentDVM);
        if (current_stock) {
            return CUR_IN_STOCK;
        } else {
            accessible_DVM_list = otherDVMs.checkOtherDVMsStock(selected_drink, currentDVM);
            if (accessible_DVM_list == null || accessible_DVM_list.size() == 0)
                return EMPTY_ALL_STOCK;
            else
                return OTHER_IN_STOCK;
        }
    }

    public int selectOtherDrink(int dialNum) {
        final int EMPTY_ALL_STOCK = 0; // 모든 DVM의 재고가 0임
        final int CUR_IN_STOCK = 1;    // 현재 DVM에 재고가 있음
        final int OTHER_IN_STOCK = 2;  // 다른 DVM에 재고가 있음
        DVM currentDVM = otherDVMs.getDVM(currentDVMIndex);
        selected_drink = currentDVM.getDrink_list().get(dialNum - 1);
        accessible_DVM_list = otherDVMs.checkOtherDVMsStock(selected_drink, currentDVM);
        if (accessible_DVM_list == null || accessible_DVM_list.size() == 0)
            return EMPTY_ALL_STOCK;
        else
            return OTHER_IN_STOCK;
    }

    String insertCard(int card_num, boolean isPrepayment){
        Boolean card_available = cardPayment.getCard_available(card_num);
        if(!card_available){
            return "not available card";
        }
        Card card = cardPayment.getCard(card_num);
        int balance = card.getBalance();
        int price = selected_drink.getPrice();
        if(balance < price){
            return "insufficient balance";
        }
        card.updateBalance(price);
        if(isPrepayment){
            Code code = cardPayment.generateCode(selected_drink); // 코드 생성
            addCode(code);
            DVM currentDVM = otherDVMs.getDVM(currentDVMIndex);
            String locationsListStr = otherDVMs.showAccessibleDVMsLocation(accessible_DVM_list, currentDVMIndex);
            String result = "선결제 진행 DVM: " + (currentDVM.getId() + 1)
                    + "\n선결제한 음료수: " + selected_drink.getName()
                    + "\n음료 가격: " + selected_drink.getPrice()
                    + "\n선결제 후 카드 잔고: " + card.getBalance() + "원"
                    + "\n발급 코드: '" + code.getCode() + "'"
                    + "\n\n<해당 음료 구매 가능 DVM 및 DVM 위치>"
                    +"\n " + locationsListStr;
            return result;
        }
        else{
            String result = otherDVMs.requestDrink(selected_drink, currentDVMIndex);
            String result2 = result + "\n결제 후 카드 잔고: " + card.getBalance() + "원";
            return result2;
        }
    }

    private ArrayList<Code> addCode(Code code) {
        code_list.add(code);
        return code_list;
    }

    String enterCode(int code_num) {
        Boolean codeAvailable = checkCodeAvailable(code_num);
        if(!codeAvailable){
            return "";
        }
        Code code_info = getCodeInfo(code_num);
        Drink drink = codePayment.codePayment(code_info);
        String result = otherDVMs.requestDrink(drink, currentDVMIndex);
        deleteCode(code_info);
        String result2 = result + "\n코드 정보: " + code_info.getCode();
        return result2;
    }

    Code getCodeInfo(int code_num) {
        for(Code code : code_list){
            if(code.getCode() == code_num){
                return code;
            }
        }
        return null;
    }

    ArrayList<Code> deleteCode(Code code_info) {
        code_list.removeIf(code -> code.getCode() == code_info.getCode());
        return code_list;
    }

    public Boolean checkCodeAvailable(int code_num) {
        for (Code code : code_list) {
            if (code_num == code.getCode()) {
                return true;
            }
        }
        return false;
    }

    public DVM selectDVM(int num) {
        currentDVMIndex = num - 1;
        return otherDVMs.getDVM(num - 1);
    }


    public ArrayList<ArrayList<Integer>> startService() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<DVM> dvmList = otherDVMs.getDVMList();

        for(int i=0; i<dvmList.size(); i++){
            ArrayList<Integer> std = new ArrayList<Integer>();
            std.add(dvmList.get(i).getId());
            std.add(dvmList.get(i).getAddress());
            result.add(std);

        }

        return result;
    }


}