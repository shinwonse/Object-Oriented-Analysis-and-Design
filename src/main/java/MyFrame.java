import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame {

    int stage = 0;
    int inputNum = 0;
    int inputTemp = 0;
    JTextField inputText = new JTextField("          ", SwingConstants.CENTER); // 사용자 입력을 받는 텍스트 필드;
    String[] num_list = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "←", "확인"};
    JButton[] dialButton_list = new JButton[12];
    ArrayList<JLabel> labelList = new ArrayList<JLabel>();

    /////////////// panel 들 ///////////////
    JPanel pDial = new JPanel();
    JPanel panelDown = new JPanel();
    JPanel pTemp = new JPanel();
    JPanel pScreen = new JPanel();
    JPanel pInput = new JPanel();

    /////////////// gridBagLayout 편하게 사용하려고 전역으로 선언 ///////////////
    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    // controller 객체
    Controller controller = new Controller();
    int[] dvmAddresses = {101, 202, 303, 404, 505, 606, 707, 808};

    MyFrame() {
        init();
    }


    private void init() {

        setLayout(new GridLayout(2, 1)); // 전체 화면을 그리드형태로 위(스크린) 아래(버튼) 분할

        pDial.setLayout(grid);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        setDialButton(dialButton_list);

        // JPanel에 버튼들 add
        for (int i = 0; i < num_list.length; i++) {
            pDial.add(dialButton_list[i]);
        }

        showAllDVMList(pScreen); // 초기 전체 DVM 출력
        //showAccessibleDVMList(pScreen, dvmList);
        // 루트 프레임에 screen JPanel add
        panelDown.setLayout(grid);
        // 루트 프레임에 button들 JPanel add

        pInput.setLayout(new BorderLayout());
        pInput.add(inputText,BorderLayout.CENTER);

        gbc(pTemp, 0, 0, 4, 1);
        gbc(pInput, 0, 1, 4, 1);
        gbc(pDial, 0, 2, 4, 4);

        pInput.setBackground(Color.GRAY);

        panelDown.add(pTemp);
        panelDown.add(pInput);
        panelDown.add(pDial);
        add(pScreen);
        add(panelDown);

        // ------------------------------------------------
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임의 x버튼을 활성화하여 닫기버튼이 실행가능해짐

        setSize(600, 800); // 프레임 사이즈 지정

        setVisible(true); // 프레임을 보이게 함

        setLocationRelativeTo(null); // 프레임 실행시 위치 중앙

    }

    private void showAllDVMList(JPanel pScreen) {
        ArrayList<ArrayList<Integer>> dvmInfo = controller.startService();


        pScreen.setLayout(grid);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/image/vm_image.png")
                        .getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
                int num = i * 4 + j;
                int id = dvmInfo.get(num).get(0)+1;
                int address = dvmInfo.get(num).get(1);
                labelList.add(new JLabel("<html>"+ (num + 1) + ". DVM" + id + "<br>주소: " + address + "</html>", imageIcon, JLabel.CENTER));
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                gbc(labelList.get(i * 4 + j), j, i, 1, 1);
                //labelList.get(i * 4 + j).setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
        for (int i = 0; i < dvmInfo.size(); i++) {
            pScreen.add(labelList.get(i));
        }
    }

    //자판기 음료수 출력//

    private void showDVMDrinkList(JPanel pScreen, int num) {
        ArrayList<JLabel> label_drink = new ArrayList<>();
        pScreen.setLayout(grid);
        DVM currentDVM = controller.selectDVM(num);
        ArrayList<Drink> currentDrinkList = currentDVM.getDrink_list();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;
                Drink drink = currentDrinkList.get(index);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(drink.getImgURL())
                        .getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
                String name = drink.getName();
                int price = drink.getPrice();
                int stock = drink.getStock();
                label_drink.add(new JLabel("<html>"+ (index + 1) + "." + name + "<br>" + price + "원 (" + stock + "개)</html>", imageIcon, JLabel.LEFT));
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                gbc(label_drink.get(i * 5 + j), j, i, 1, 1);
            }
        }
        for (int i = 0; i < currentDrinkList.size(); i++) {
            pScreen.add(label_drink.get(i));
        }
    }

    private void setDialButton(JButton[] dialButton_list) {
        for (int i = 0; i < 3; i++) { // 1 ~ 9 버튼
            for (int j = 0; j < 3; j++) {
                dialButton_list[i * 3 + j] = new  JButton(num_list[i * 3 + j]);
                gbc(dialButton_list[i * 3 + j], j, i, 1, 1);

                MyFrame.PadInput handler = new MyFrame.PadInput(); // 키패드 이벤트를 발생 시키기 위해 handler 생성
                dialButton_list[i * 3 + j].addActionListener(handler); // 이벤트 등록
            }
        }
        dialButton_list[9] = new JButton(num_list[9]);     // 0 버튼
        gbc(dialButton_list[9], 0, 3, 2, 1);
        PadInput handler9 = new PadInput(); // 키패드 이벤트를 발생 시키기 위해 handler 생성
        dialButton_list[9].addActionListener(handler9); // 이벤트 등록

        dialButton_list[10] = new JButton(num_list[10]);   // ← 버튼
        gbc(dialButton_list[10], 2, 3, 1, 1);
        PadInput handler10 = new PadInput(); // 키패드 이벤트를 발생 시키기 위해 handler 생성
        dialButton_list[10].addActionListener(handler10); // 이벤트 등록

        dialButton_list[11] = new JButton(num_list[11]);   // 확인 버튼
        gbc(dialButton_list[11], 3, 0, 1, 4);
        PadInput handler11 = new PadInput(); // 키패드 이벤트를 발생 시키기 위해 handler 생성
        dialButton_list[11].addActionListener(handler11); // 이벤트 등록
    }

    private class PadInput implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String eventText = event.getActionCommand();

            if(inputTemp == 0){
                if(eventText.equals("확인")){
                    inputNum = inputTemp;
                    inputText.setText("");
                    // 선택완료 메시지
                    JOptionPane aa=new JOptionPane();
                    if(inputNum >=1 && inputNum <= 8) {
                        JOptionPane.showMessageDialog(null, inputNum + "번 DVM을 선택하셨습니다.");
                        pScreen.removeAll();
                        showDVMDrinkList(pScreen, inputNum);
                        pScreen.updateUI();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "올바른 번호를 선택해 주십시오 (1~8)");
                }else if(eventText.equals("←")) {
                    inputTemp = 0;
                    inputText.setText("");
                }else {                                           //0~9 사이의 숫자 input
                    inputTemp = Integer.parseInt(eventText);
                    String temp = inputText.getText();
                    inputText.setText(temp + eventText);
                }
            }
            // 십의자리수 이상의 input 컨트롤
            else{
                if(eventText.equals("확인")){
                    inputNum = inputTemp;
                    inputTemp = 0;
                    inputText.setText("");
                    //JOptionPane aa=new JOptionPane();
                    switch(stage) {
                        case 0: // DVM 선택
                            if(inputNum>=1 && inputNum<=8) {
                                JOptionPane.showMessageDialog(null, inputNum + "번 DVM을 선택하셨습니다.");
                                pScreen.removeAll();
                                showDVMDrinkList(pScreen, inputNum);
                                pScreen.updateUI();
                                stage=1;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, inputNum + "번은 존재하지않습니다. 올바른 번호를 선택해 주십시오 (1~8)");
                            }
                            break;
                        case 1: // Drink 선택
                            if(inputNum>=1 && inputNum<=7) {
                                JOptionPane.showMessageDialog(null,inputNum + "번 음료를 선택하셨습니다.");
                                pScreen.removeAll();
                                proceedCurrentDrink(pScreen, inputNum);
                                pScreen.updateUI();

                            }
                            else if(inputNum >= 8 && inputNum <= 20){
                                // 현재 DVM에 없는 음료를 선택한 경우, 선결제 진행
                                // 다른 DVM에 재고 확인 요청 후 재고가 있는 DVM 출력
                                //JOptionPane.showMessageDialog(null,inputNum + "번 음료는 현재 자판기에 존재하지 않습니다. 선결제를 진행합니다.");
                                pScreen.removeAll();
                                proceedOtherDrink(pScreen, inputNum);
                                pScreen.updateUI();
                            }
                            else
                                JOptionPane.showMessageDialog(null, "잘못된  입력했습니다. 1~20번의 음료를 선택해주세요.");
                            break;
                        case 2: // 결제방법 선택
                            if(inputNum == 1) {
                                JOptionPane.showMessageDialog(null, "카드 결제를 선택하셨습니다.");
                                pScreen.removeAll();
                                showCardInput(pScreen);
                                pScreen.updateUI();
                                stage = 3;
                            }
                            else if(inputNum == 2){
                                JOptionPane.showMessageDialog(null, "코드 결제를 선택하셨습니다.");
                                pScreen.removeAll();
                                showInputCode();
                                pScreen.updateUI();
                                stage = 4;
                            }
                            else
                                JOptionPane.showMessageDialog(null, "번호를 잘못 입력했습니다. 1 or 2를 입력해주세요.");
                            break;
                        case 3: // 카드 선택
//                            aa.showMessageDialog(null, "카드를 선택해주세요.");
                            // 카드 목록 출력
                            String result = controller.insertCard(inputNum, false);
                            if(result.equals("not available card")){
                                JOptionPane.showMessageDialog(null, "유효하지 않은 카드입니다. 초기화면으로 돌아갑니다.");
                                // 초기 화면으로 돌아감
                                stage = 0;
                                pScreen.removeAll();
                                showAllDVMList(pScreen);
                                pScreen.updateUI();
                            }
                            else if(result.equals("insufficient balance"))
                            {
                            JOptionPane.showMessageDialog(null, "잔고가 부족합니다. 초기화면으로 돌아갑니다.");
                            // 초기 화면으로 돌아감
                            stage = 0;
                            pScreen.removeAll();
                            showAllDVMList(pScreen);
                            pScreen.updateUI();
                            }
                            else{
                                JOptionPane.showMessageDialog(null, result);
                                // 음료수 지급, 음료수 재고 업데이트
                                // 초기 화면으로 돌아감
                                stage = 0;
                                pScreen.removeAll();
                                showAllDVMList(pScreen);
                                pScreen.updateUI();
                            }
                            break;
                        case 4: // 코드 입력
                            String prepaymentResult = controller.enterCode(inputNum);
                            if(prepaymentResult.equals("")){
                                JOptionPane.showMessageDialog(null, "유효하지 않은 코드입니다. 초기화면으로 돌아갑니다.");
                                // 초기 화면으로 돌아감
                                stage = 0;
                                pScreen.removeAll();
                                showAllDVMList(pScreen);
                                pScreen.updateUI();
                            }
                            else{
                                JOptionPane.showMessageDialog(null, prepaymentResult);
                                stage = 0;
                                pScreen.removeAll();
                                showAllDVMList(pScreen);
                                pScreen.updateUI();
                            }
                            break;
                        case 5: // 선결제 진행
                            String prePaymentResult = controller.insertCard(inputNum, true);
                            JOptionPane.showMessageDialog(null, prePaymentResult);
                            stage = 0;
                            pScreen.removeAll();
                            showAllDVMList(pScreen);
                            pScreen.updateUI();
                            break;
                        case 6: // 재고 있는 DVM 위치 출력
                            //showAccessibleDVMList(pScreen);
                            stage = 0;
                            pScreen.removeAll();
                            showAllDVMList(pScreen);
                            pScreen.updateUI();
                            break;

                    }
                }else if(eventText.equals("←")) {
//                    int tmp= inputTemp % 10;
//                    inputTemp = (inputTemp - tmp)/10;   이렇게하려다가 생각해보니 걍 10으로 나누면될듯
                    inputTemp = inputTemp/10;
                    if(inputTemp == 0)
                        inputText.setText("");
                    else
                        inputText.setText(String.valueOf(inputTemp));
                }else{                                                              //0~9 사이의 숫자 input
                    inputTemp = inputTemp*10 + Integer.parseInt(eventText);
                    inputText.setText(String.valueOf(inputTemp));

                }
            }
        }
    }

    private void showInputCode() {
        pScreen.setLayout(grid);
        pScreen.add(new JLabel("<html>"+ "코드 번호를 입력해주세요."+"<br>" + "(코드 번호는 5자리 숫자입니다.)</html>"));
    }

    private void showCardInput(JPanel pScreen) {
        pScreen.setLayout(grid);
        pScreen.add(new JLabel("<html>"+ "카드 일련번호를 입력해주세요."+"<br>" + "(성공 번호: 1234 1234)</html>"));
    }

    private void showAccessibleDVMList(JPanel p2, String[] dvmList) {
        p2.setLayout(grid);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                int num = i * 4 + j + 1;
                if (!dvmList[i * 4 + j].equals("")) {
                    labelList.add(new JLabel(num + ". " + dvmList[i * 4 + j]));
                } else
                    labelList.add(new JLabel(""));
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                gbc(labelList.get(i * 4 + j), j, i, 1, 1);
                labelList.get(i * 4 + j).setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
        for (int i = 0; i < dvmList.length; i++) {
            p2.add(labelList.get(i));
        }
    }


    private void gbc(JComponent j, int x, int y, int width, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        grid.setConstraints(j, gbc);
    }

    private void proceedCurrentDrink(JPanel pScreen, int inputNum) {
        int drink_status = controller.selectCurrentDrink(inputNum);
        if(drink_status == 0){ // EMPTY_ALL_STOCK : 모든 DVM 의 재고가 0임
            JOptionPane.showMessageDialog(null, "모든 DVM에 해당 음료의 재고가 없습니다. 초기화면으로 돌아갑니다.");
            // 인증 코드 메시지 출력
            stage = 0;
            showAllDVMList(pScreen);
            pScreen.updateUI();
        }
        else if(drink_status == 1){ // CUR_IN_STOCK : 현재 자판기에 재고가 있음
            ArrayList<JLabel> pay_label = new ArrayList<>();
            pScreen.setLayout(grid);
            pay_label.add(new JLabel("1. 카드결제",JLabel.CENTER));
            pay_label.add(new JLabel("2. 코드결제",JLabel.CENTER));

            for (int i = 0; i < 2; i++) {
                for(int j = 0; j < 1; j++) {
                    gbc(pay_label.get(i + j), j, i, 1, 1);
                }
            }

            for (int i = 0; i < 2; i++) {
                pScreen.add(pay_label.get(i));
            }
            stage = 2;
        }
        else{ // OTHER_IN_STOCK : 다른 자판기에 재고가 있음
            JOptionPane.showMessageDialog(null, "현재 DVM에 해당 음료의 재고가 없지만 다른 DVM에 재고가 존재합니다. 선결제로 넘어갑니다.");
            showCardInput(pScreen);
            stage = 5;
        }
    }

    private void proceedOtherDrink(JPanel pScreen, int inputNum) {
        int drink_status = controller.selectOtherDrink(inputNum);
        if(drink_status == 0){ // 모든 DVM에 재고가 없음
            JOptionPane.showMessageDialog(null, "모든 DVM에 해당 음료의 재고가 없습니다. 초기화면으로 돌아갑니다.");
            // 인증 코드 메시지 출력
            stage = 0;
            showAllDVMList(pScreen);
            pScreen.updateUI();
        }
        else{ // OHTER_IN_STOCK : 다른 자판기에 재고가 있음
            JOptionPane.showMessageDialog(null, "현재 DVM에 해당 음료의 재고가 없지만 다른 DVM에 재고가 존재합니다. 선결제로 넘어갑니다.");
            showCardInput(pScreen);
            stage = 5;
        }
    }
}