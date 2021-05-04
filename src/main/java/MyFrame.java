import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MyFrame extends JFrame {
    int inputNum = 0;
    int inputTemp = 0;
    JTextField inputText = new JTextField("          ", SwingConstants.CENTER); // 사용자 입력을 받는 텍스트 필드;

    String[] num_list = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "←", "확인"};
    JButton[] dialButton_list = new JButton[12];
    ArrayList<JLabel> labelList = new ArrayList<JLabel>();
    List imageList = new List();
    String[] dvmList = {"dvm1", "dvm3", "dvm6", "", "", "", "", ""};

    JPanel pDial = new JPanel();
    JPanel panelDown = new JPanel();
    JPanel pTemp = new JPanel();
    JPanel pScreen = new JPanel();
    JPanel pInput = new JPanel();

    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();


    MyFrame() {
        init();
    }


    private void init() {
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

        showAccessibleDVMList(pScreen, dvmList);
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

    private void setDialButton(JButton[] dialButton_list) {
        for (int i = 0; i < 3; i++) { // 1 ~ 9 버튼
            for (int j = 0; j < 3; j++) {
                dialButton_list[i * 3 + j] = new JButton(num_list[i * 3 + j]);
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
//            System.out.println("input은 "+input);
//            System.out.println("input num 은" + inputNum);

            if(inputTemp == 0){
                if(eventText.equals("확인")){
                    inputNum = inputTemp;
                    inputText.setText("");
                    // 선택완료 메시지
                    JOptionPane aa=new JOptionPane();
                    if(inputNum>=1 && inputNum<=8 )
                        aa.showMessageDialog(null, inputNum+ "번 DVM을 선택하셨습니다.");
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
                    if(inputNum>=1 && inputNum<=8)
                        aa.showMessageDialog(null, inputNum+ "번 DVM을 선택하셨습니다.");
                    else
                        aa.showMessageDialog(null, inputNum + "번은 존재하지않습니다. 올바른 번호를 선택해 주십시오 (1~8)");
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