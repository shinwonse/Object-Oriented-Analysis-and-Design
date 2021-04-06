package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import watch.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimersView extends JPanel{
    private JLabel timer_label;
    private BaseView base;

    private Timers timer;
    private InstManager instManager;
    private Timekeeping tk;

    private JButton A;  //mode전환    ->    mode
    private JButton B;  //up          ->    reset
    private JButton C;  //ok(start)   ->    pause(continue)
    private JButton D;  //down        ->    none

    /*
        1. Setting      2. Excute       3. Pause    4.Reset
     */

    private  JLabel LCD1; //기능들을 표시할 LCD 화면 //Timer
    private  JLabel LCD2;//Alarm
    private  JLabel LCD3;//Stopwatch
    private  JLabel LCD4;//Dday
    private  JLabel LCD5;//Fitness

    private JLabel dot;
    private JLabel segment;
    //  private JLabel tmp;

    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    int settingNum = 0;
    private String timer_status = "Setting";


    SimpleDateFormat dot_fm;
    SimpleDateFormat seg_fm;
    Calendar calendar;
    String strDate;
    //   public Controller controller = null;

    boolean buzzer_mode;

    public void setBuzzer_mode(boolean buzzer_mode) {
        this.buzzer_mode = buzzer_mode;
    }

    public TimersView(BaseView base)
    {
        timer = instManager.getInstance().getTimer();
        instManager = InstManager.getInstance();
        tk = instManager.getInstance().getTimekeeping();
        calendar = Calendar.getInstance();

        this.base = base;
        this.setBounds(0,0,800,500);
        //    this.setBackground(Color.CYAN);

        dot_fm = new SimpleDateFormat("yy.MM.dd.E요일");
        seg_fm = new SimpleDateFormat("HH:mm:SS");

        ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        timer_label = new JLabel(icon1);
        timer_label.setBounds(0,0,500,500);
        //tk_label.setVisible(true);

        this.add(timer_label);
        this.setLayout(null);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        timer_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        timer_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        timer_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        timer_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        timer_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));




        A = new JButton("A");
        A.setBounds(100,150,50,50);
        this.add(A);
        //mode버튼으로 기능전환
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(buzzer_mode == true){
                    timer_status = "Reset";
                    Buzzer.getInstance().stopBuzzer();
                    base.controller.req_reset();
                    hour = 0;
                    minute = 0;
                    second = 0;
                }
                else {
                    base.controller.req_changeMode();
                }
            }
        });

        B = new JButton("B");
        B.setBounds(350,150,50,50);
        this.add(B);
        //if(타이머설정): up, else(타이머실행): reset
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    timer_status = "Reset";
                    Buzzer.getInstance().stopBuzzer();
                    base.controller.req_reset();
                    hour = 0;
                    minute = 0;
                    second = 0;
                }
                else {
                    if (timer_status.equals("Setting") == true || timer_status.equals("Reset")==true) {
                        switch (settingNum) {
                            case 0:
                                req_nextHour();
                                break;
                            case 1:
                                req_nextMinute();
                                break;
                            case 2:
                                req_nextSecond();
                                break;
                            default:
                                break;
                        }
                    }
                    //타이머 일시정지 중에 리셋버튼이 눌리면
                    else if (timer_status.equals("Pause") == true) {
                        System.out.println("reset합니다");
                        timer_status = "Reset";
                        base.controller.req_reset();
                        hour = 0;
                        minute = 0;
                        second = 0;
                        settingNum=0;
                        segment.setText(Integer.toString(timer.getHour()) + ":" + Integer.toString(timer.getMinute()) + ":" + Integer.toString(timer.getSecond()));
                    }
                    //타이머 실행 중에 리셋 버튼이 눌리면
                    else if (timer_status.equals("Execute") == true) {
                        System.out.println("reset합니다");
                        timer_status = "Reset";
                        base.controller.req_pause("timer");
                        base.controller.req_reset();
                        hour = 0;
                        minute = 0;
                        second = 0;
                        settingNum = 0;
                        segment.setText(Integer.toString(timer.getHour()) + ":" + Integer.toString(timer.getMinute()) + ":" + Integer.toString(timer.getSecond()));
                    }
                    else{
                        //none
                    }
                }
            }
        });

        //if(타이머설정):ok, else(타이머실행):pause/continue
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        this.add(C);
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    timer_status = "Reset";
                    Buzzer.getInstance().stopBuzzer();
                    base.controller.req_reset();
                    hour = 0;
                    minute = 0;
                    second = 0;
                }
                else {
                    if (timer_status.equals("Pause") == true) {
                        //System.out.println("continue합니다");
                        base.controller.req_continue("timer");
                        timer_status = "Execute";
                    } else if (timer_status.equals("Setting") == true) {
                        settingNum++;
                        if (settingNum == 3) {
                            settingNum=0;
                            req_next();
                            timer_status = "Execute";
                        }
                    } else if (timer_status.equals("Execute") == true) {
                        timer_status = "Pause";
                        System.out.println("일시정지합니다");
                        base.controller.req_pause("timer");
                        segment.setText(Integer.toString(timer.getHour()) + ":" + Integer.toString(timer.getMinute()) + ":" + Integer.toString(timer.getSecond()));
                    }else if(timer_status.equals("Reset")==true){
                        settingNum++;
                        if (settingNum == 3) {
                            settingNum = 0;
                            base.controller.req_setTime("timer", hour, minute, second);
                            System.out.println("타이머 컨티뉴 시작");
                            base.controller.req_continue("timer");
                            timer_status = "Execute";
                        }
                    }
                    else{
                        //none
                    }
                }
            }
        });

        //if(타이머설정): down, else(타이머실행): none
        D = new JButton("D");
        D.setBounds(350,300,50,50);
        this.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    timer_status = "Reset";
                    Buzzer.getInstance().stopBuzzer();
                    base.controller.req_reset();
                    hour = 0;
                    minute = 0;
                    second = 0;
                }
                else {
                    if (timer_status.equals("Pause") == true) {
                        //none
                        System.out.println("none입니다.");
                    } else if (timer_status.equals("Setting") == true|| timer_status.equals("Reset")==true) {
                        switch (settingNum) {
                            case 0:
                                req_prevHour();
                                break;
                            case 1:
                                req_prevMinute();
                                break;
                            case 2:
                                req_prevSecond();
                                break;
                            default:
                                break;
                        }
                    } else if (timer_status.equals("Execute") == true) {
                        //none
                        System.out.println("none입니다.");
                    }
                    else{
                        //none
                    }
                }
            }
        });

        Timer m_timer = new Timer();
        TimerTask m_task = new TimerTask(){
            @Override
            public void run(){
                if(timer_status.equals("Execute")){
                    segment.setText(Integer.toString( timer.getHour())+":"+Integer.toString(timer.getMinute())+":"+Integer.toString(timer.getSecond()));
                    System.out.println(timer.getHour() + ":" + timer.getMinute() + ":" + timer.getSecond());
                }
            }
        };
        m_timer.scheduleAtFixedRate(m_task, 0, 1000);



        dot = new JLabel();
        dot.setBounds(150,200,150,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));

        calendar.set(tk.getYear(),tk.getMonth(),tk.getDate(),tk.getHour(),tk.getMinute(),tk.getSecond());
        strDate = dot_fm.format(calendar.getTime());
        dot.setText(strDate);

        timer_label.add(dot);

        segment = new JLabel();

        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,40));
        timer_label.add(segment);

    }
    //req함수들
    public void req_nextHour() {
        if(hour != 99){
            hour++;
        }else{
            hour=0;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }
    public void req_prevHour(){
        if(hour != 0){
            hour--;
        }else{
            hour=0;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }

    public void req_nextMinute(){
        if(minute != 60){
            minute++;
        }else{
            minute=0;
            hour++;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }
    public void req_prevMinute(){
        if(minute != 0){
            minute--;
        }else{
            minute=0;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }

    public void req_nextSecond(){
        if(second != 60){
            second++;
        }else{
            second=00;
            minute++;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }
    public void req_prevSecond(){
        if(second != 0){
            second--;
        }else{
            second=0;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }

    public void req_next(){
        base.controller.req_setTime("timer", hour, minute, second);
        timer_status = "Execute";
        base.controller.req_countDown();
    }

    public void setLCD(TimersView timersView) {
        timersView.LCD1.setVisible(base.controller.req_isFunctionSelected(1));
        timersView.LCD2.setVisible(base.controller.req_isFunctionSelected(2));
        timersView.LCD3.setVisible(base.controller.req_isFunctionSelected(3));
        timersView.LCD4.setVisible(base.controller.req_isFunctionSelected(4));
        timersView.LCD5.setVisible(base.controller.req_isFunctionSelected(5));
    }
}

