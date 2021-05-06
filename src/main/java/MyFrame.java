import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MyFrame extends JFrame {

    int stage = 0;
    int inputNum = 0;
    int inputTemp = 0;
    JTextField inputText = new JTextField("          ", SwingConstants.CENTER); // 사용자 입력을 받는 텍스트 필드;
    String[] num_list = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "←", "확인"};
    JButton[] dialButton_list = new JButton[12];
    ArrayList<JLabel> labelList = new ArrayList<JLabel>();
    String[] accessibleDVMList = {"dvm1", "dvm3", "dvm6", "", "", "", "", ""};

    /////////////// panel 들 ///////////////
    JPanel pDial = new JPanel();
    JPanel panelDown = new JPanel();
    JPanel pTemp = new JPanel();
    JPanel pScreen = new JPanel();
    JPanel pInput = new JPanel();

    /////////////// gridBagLayout 편하게 사용하려고 전역으로 선언 ///////////////
    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    /////////////// Controller가 가져야되는 dvmList //////////////
    ArrayList<DVM> dvmList = new ArrayList<>();

    MyFrame() {
        init();
    }


    private void init() {
        //1번 DVM 음료 등록//
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("펩시콜라", 1500, 10, "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("칠성사이다", 1500, 10, "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("환타포도", 1500, 10, "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("레드불", 1500, 10, "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("몬스터드링크", 1500, 10, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("빡텐션", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("포카리스웨트", 1500, 10, "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("게토레이", 1500, 10, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("파워에이드", 1500, 10, "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("밀키스", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("레쓰비", 1500, 10, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("스파클링", 1500, 10, "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("비락식혜", 1500, 10, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("솔의눈", 1500, 10, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("데자와", 1500, 10, "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("마운틴듀", 1500, 10, "src/main/resources/image/20.jpg"));

        //DVMList 초기화 작업
        for(int i = 0; i < 8; i++){
            DVM tempDVM = new DVM(true, new ArrayList<Drink>(), i + 1, i + 1);
            tempDVM.setDrink_list((drinkArrayList));
            dvmList.add(i, tempDVM);
        }

        setTitle("초기화면");
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
        pScreen.setLayout(grid);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/image/vm_image.png")
                        .getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
                int num = i * 4 + j;
                int id = dvmList.get(num).getId();
                int address = dvmList.get(num).getAddress();
                labelList.add(new JLabel("<html>"+ (num + 1) + ". DVM" + id + "<br>주소: " + address + "</html>", imageIcon, JLabel.CENTER));
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                gbc(labelList.get(i * 4 + j), j, i, 1, 1);
                //labelList.get(i * 4 + j).setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
        for (int i = 0; i < accessibleDVMList.length; i++) {
            pScreen.add(labelList.get(i));
        }
    }


    //자판기 음료수 출력//

    private void showDVMDrinkList(JPanel pScreen, int index) {
        ArrayList<JLabel> label_drink = new ArrayList<>();
        pScreen.setLayout(grid);
        ArrayList<Drink> currentDrinkList = dvmList.get(index).getDrink_list();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int num = i * 5 + j;
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(currentDrinkList.get(num).getImgURL())
                        .getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
                String name = currentDrinkList.get(num).getName();
                int price = currentDrinkList.get(num).getPrice();
                int stock = currentDrinkList.get(num).getStock();
                label_drink.add(new JLabel("<html>"+ (num + 1) + "." + name + "<br>" + price + "원 (" + stock + "개)</html>", imageIcon, JLabel.LEFT));
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
            JButton btn1 = new JButton("카드결제");
            JButton btn2 = new JButton("코드결제");
//            System.out.println("input은 "+input);
//            System.out.println("input num 은" + inputNum);

            if(inputTemp == 0){
                if(eventText.equals("확인")){
                    inputNum = inputTemp;
                    inputText.setText("");
                    // 선택완료 메시지
                    JOptionPane aa=new JOptionPane();
                    if(inputNum>=1 && inputNum<=8 ) {
                        aa.showMessageDialog(null, inputNum + "번 DVM을 선택하셨습니다.");
                        pScreen.removeAll();
                        showDVMDrinkList(pScreen, inputNum);
                        pScreen.updateUI();
                    }
                    else
                        aa.showMessageDialog(null, "올바른 번호를 선택해 주십시오 (1~8)");
                }else if(eventText.equals("←")) {
                    inputTemp = 0;
                    inputText.setText("");
                }else {                                           //0~9 사이의 숫자 input
                    inputTemp = Integer.parseInt(eventText);
                    inputText.setText(String.valueOf(inputTemp));
                }
            }
            // 십의자리수 이상의 input 컨트롤
            else{
                if(eventText.equals("확인")){
                    inputNum = inputTemp;
                    inputTemp = 0;
                    inputText.setText("");
                    JOptionPane aa=new JOptionPane();
                    switch(stage) {
                        case 0: // DVM 선택
                            if(inputNum>=1 && inputNum<=8) {
                                aa.showMessageDialog(null, inputNum + "번 DVM을 선택하셨습니다.");
                                pScreen.removeAll();
                                showDVMDrinkList(pScreen, inputNum);
                                pScreen.updateUI();
                                stage=1;
                            }
                            else{
                                aa.showMessageDialog(null, inputNum + "번은 존재하지않습니다. 올바른 번호를 선택해 주십시오 (1~8)");
                            }
                            break;
                        case 1: // Drink 선택
                            if(inputNum>=1 && inputNum<=7) {
                                aa.showMessageDialog(null,inputNum + "번 음료를 선택하셨습니다.");
                                pScreen.removeAll();
                                add(btn1);
                                add(btn2);
                                PayMenuListener payMenuListener = new PayMenuListener();
                                payMenuListener.actionPerformed(btn1);
                                pScreen.updateUI();
                            }
                            break;
                        case 2:
                            break;
                        case 3:
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
}
class PayMenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton payMenu = (JButton) e.getSource();
        if (payMenu.getText().equals("카드결제")) {
            System.out.println("카드결제 선택");
        } else {
            System.out.println("코드결제 선택");
        }
    }
}