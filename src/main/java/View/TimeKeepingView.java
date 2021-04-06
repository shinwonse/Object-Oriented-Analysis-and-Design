package View;



import watch.Buzzer;
import watch.InstManager;
import watch.Timekeeping;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;


public class TimeKeepingView extends JPanel{
    private JLabel tk_label;
    private BaseView base;

    private JButton A;
    private JButton B;
    private JButton C;
    private JButton D;

    private  JLabel LCD1; //기능들을 표시할 LCD 화면 //Timer
    private  JLabel LCD2;//Alarm
    private  JLabel LCD3;//Stopwatch
    private  JLabel LCD4;//Dday
    private  JLabel LCD5;//Fitness

    private JLabel dot;
    private JLabel segment;
    private JLabel tmp;

    /* private int year;
     private int month;
     private int date;
     private int hour;
     private int minute;
     private int second;*/
    // Timekeeping tk;
    Calendar calendar;
    SimpleDateFormat dot_fm;
    SimpleDateFormat seg_fm;
    String strDate;
    String strDate2;
    int settingNum;

    private String tk_status = "Timekeeping";
    /*
        1.Timekeeping   2. Setting
     */
    boolean buzzer_mode;

    public void setBuzzer_mode(boolean buzzer_mode) {
        this.buzzer_mode = buzzer_mode;
    }

    public TimeKeepingView(BaseView base)
    {
        this.base= base;
        this.setBounds(0,0,800,500);
        //this.setBackground(Color.ORANGE);
        calendar = Calendar.getInstance();
        dot_fm = new SimpleDateFormat("yy.M.dd.E요일");
        seg_fm = new SimpleDateFormat("HH:mm:ss");

        calendar.set(InstManager.getInstance().getTimekeeping().getYear(),InstManager.getInstance().getTimekeeping().getMonth(),InstManager.getInstance().getTimekeeping().getDate(),InstManager.getInstance().getTimekeeping().getHour(),InstManager.getInstance().getTimekeeping().getMinute(),InstManager.getInstance().getTimekeeping().getSecond());


        ImageIcon icon1 = new ImageIcon("Image/circle.png");
        tk_label = new JLabel(icon1);
        tk_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tk_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        tk_label.add(tmp);
        //tk_label.setVisible(true);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        tk_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        tk_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        tk_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        tk_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        tk_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));

        this.add(tk_label);
        this.setLayout(null);

        A = new JButton("A");
        A.setBounds(100,150,50,50);
        tk_label.add(A);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else {
                    base.controller.req_changeMode();
                }
            }
        });
        B = new JButton("B");
        B.setBounds(350,150,50,50);
        tk_label.add(B);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else {
                    if (tk_status.equals("Timekeeping") == true) {
                        //Setting 으로 상태 바꿔준다.
                        base.change_view(6);
                    } else if (tk_status.equals("Setting") == true) {
                        //이 때 B버튼은 Up버튼임
                        switch (settingNum) {
                            case 0:
                                req_nextYear();
                                break;
                            case 1:
                                req_nextMonth();
                                break;
                            case 2:
                                req_nextDate();
                                break;
                            case 3:
                                req_nextHour();
                                break;
                            case 4:
                                req_nextMinute();
                                break;
                            case 5:
                                req_nextSecond();
                                break;
                        }


                    }
                }
            }
        });
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        tk_label.add(C);
        C.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(buzzer_mode == true){
                                        Buzzer.getInstance().stopBuzzer();
                                    }
                                    else {
                                        if (tk_status.equals("Timekeeping") == true) {//Setting 으로 상태 바꿔준다.
                                            tk_status = "Setting";
                                            base.controller.req_pause("timekeeping");
                                            System.out.println(tk_status);
                                        } else if (tk_status.equals("Setting") == true) {
                                            //이 때 C버튼은 OK(Next)
                                            settingNum++;
                                            System.out.println(settingNum);
                                            if (settingNum == 3) {
                                                base.controller.req_setDate("timekeeping", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                                                Timekeeping tk = InstManager.getInstance().getTimekeeping();
                                                System.out.println(tk.getDate());
                                            } else if (settingNum == 6) {
                                                base.controller.req_setTime("timekeeping", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
                                                System.out.println("화면시간"+calendar.get(Calendar.SECOND)+"초"+calendar.get(Calendar.DATE)+"일"+"timekeeping:"+InstManager.getInstance().getTimekeeping().getDate());
                                                settingNum = 0;
                                                tk_status = "Timekeeping";
                                                base.controller.req_continue("timekeeping");
                                            }
                                            else{

                                            }
                                        }
                                        else{

                                        }
                                    }
                                }
                            }
        );

        D = new JButton("D");
        D.setBounds(350,300,50,50);
        tk_label.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else {
                    if (tk_status.equals("Setting") == true) {
                        //DOWN버튼
                        switch (settingNum) {
                            case 0:
                                req_prevYear();
                                break;
                            case 1:
                                req_prevMonth();
                                break;
                            case 2:
                                req_prevDate();
                                break;
                            case 3:
                                req_prevHour();
                                break;
                            case 4:
                                req_prevMinute();
                                break;
                            case 5:
                                req_prevSecond();
                                break;
                            default:
                                break;
                        }

                    }
                    else{

                    }
                }
            }
        });

        dot = new JLabel();
        dot.setBounds(150,200,150,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tk_label.add(dot);

        segment = new JLabel();
        TimerTask tt = new TimerTask() {
            Timekeeping tk = InstManager.getInstance().getTimekeeping();
            @Override
            public void run() {
                if(tk_status.equals("Timekeeping")) {
                    calendar.set(tk.getYear(),tk.getMonth(),tk.getDate(),tk.getHour(),tk.getMinute(),tk.getSecond());
                    // tk.setDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                    //tk.setTime(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.MONTH));
                    strDate = dot_fm.format(calendar.getTime());
                    dot.setText(strDate);
                    //  System.out.println("화면시간"+calendar.get(Calendar.SECOND)+"초"+calendar.get(Calendar.DATE)+"일"+"timekeeping:"+tk.getDate());
                    strDate2 = seg_fm.format(calendar.getTime());
                    segment.setText(strDate2);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        Timer tm = new Timer();
        tm.scheduleAtFixedRate(tt,0,1000);

        segment.setBounds(150,230,220,30);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,40));
        tk_label.add(segment);



    }

    public void req_nextYear()
    {

        calendar.add(Calendar.YEAR,1);
        strDate = dot_fm.format(calendar.getTime());
        dot.setText(strDate);
    }
    public void req_prevYear()
    {
        calendar.add(Calendar.YEAR,-1);
        strDate = dot_fm.format(calendar.getTime());
        dot.setText(strDate);

    }
    public void req_nextMonth()
    {
        calendar.add(Calendar.MONTH,1);
        strDate = dot_fm.format(calendar.getTime());
        dot.setText(strDate);

    }
    public void req_prevMonth()
    {
        calendar.add(Calendar.MONTH,-1);
        strDate = dot_fm.format(calendar.getTime());
        dot.setText(strDate);
    }
    public void req_nextDate()
    {
        calendar.add(Calendar.DATE,1);
        strDate = dot_fm.format(calendar.getTime());
        dot.setText(strDate);
    }
    public void req_prevDate()
    {
        calendar.add(Calendar.DATE,-1);
        strDate = dot_fm.format(calendar.getTime());
        dot.setText(strDate);

    }
    public void req_nextHour()
    {
        calendar.add(Calendar.HOUR,1);
        strDate2 = seg_fm.format(calendar.getTime());
        segment.setText(strDate2);
    }
    public void req_prevHour()
    {
        calendar.add(Calendar.HOUR_OF_DAY,-1);
        strDate2 = seg_fm.format(calendar.getTime());
        segment.setText(strDate2);
    }
    public void req_nextMinute()
    {
        calendar.add(Calendar.MINUTE,1);
        strDate2 = seg_fm.format(calendar.getTime());
        segment.setText(strDate2);
    }
    public void req_prevMinute()
    {
        calendar.add(Calendar.MINUTE,-1);
        strDate2 = seg_fm.format(calendar.getTime());
        segment.setText(strDate2);
    }
    public void req_nextSecond()
    {
        calendar.add(Calendar.SECOND,1);
        strDate2 = seg_fm.format(calendar.getTime());
        segment.setText(strDate2);
    }
    public void req_prevSecond()
    {
        calendar.add(Calendar.SECOND,-1);
        strDate2 = seg_fm.format(calendar.getTime());
        segment.setText(strDate2);
    }

    public JLabel getDot() {
        return dot;
    }

    public JLabel getSegment() {
        return segment;
    }


    public void setLCD(TimeKeepingView timeKeepingView) {
        timeKeepingView.LCD1.setVisible(base.controller.req_isFunctionSelected(1));
        timeKeepingView.LCD2.setVisible(base.controller.req_isFunctionSelected(2));
        timeKeepingView.LCD3.setVisible(base.controller.req_isFunctionSelected(3));
        timeKeepingView.LCD4.setVisible(base.controller.req_isFunctionSelected(4));
        timeKeepingView.LCD5.setVisible(base.controller.req_isFunctionSelected(5));
    }








}
