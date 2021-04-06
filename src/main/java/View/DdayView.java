package View;


import watch.Buzzer;
import watch.Dday;
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
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;


public class DdayView extends JPanel{
    private JLabel Dday_label;
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
    private Dday dDay;

    private String goal;
    private String[] goalList = {"stop drinking","stop smoking","studying","exercising","diet","save money"};
    private int goal_index = -1;
    private int settingNum = 1;

    private Calendar calendar;
    private SimpleDateFormat seg_fm;

    private Timekeeping timekeeping;

    private String dDay_status = "List"; //TimeKeeping 과 TimeSetting 의 View 가 존재한다.
    /*
        1. List     2. Setting     3. Add   4.DeleteFinish   5. AddFinish
     */
    private String strDate;

    boolean buzzer_mode;

    public void setBuzzer_mode(boolean buzzer_mode) {
        this.buzzer_mode = buzzer_mode;
    }


    public DdayView(BaseView base)
    {
        this.base= base;
        this.setBounds(0,0,800,500);
        this.setBackground(Color.PINK);

        seg_fm = new SimpleDateFormat("yyyy.MM.dd");
        timekeeping = InstManager.getInstance().getTimekeeping();
        calendar = Calendar.getInstance();
        calendar.set(timekeeping.getYear(),timekeeping.getMonth(),timekeeping.getDate());


        //ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        Dday_label = new JLabel();
        Dday_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        Dday_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        Dday_label.add(tmp);
        //tk_label.setVisible(true);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        Dday_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        Dday_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        Dday_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        Dday_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        Dday_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));

        this.add(Dday_label);
        this.setLayout(null);



        A = new JButton("A");
        A.setBounds(100,150,50,50);
        Dday_label.add(A);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else{
                    base.controller.req_changeMode();
                }
            }
        });
        B = new JButton("B");
        B.setBounds(350,150,50,50);
        Dday_label.add(B);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(tk_status.equals("TimeKeeping"))
                //base.change_view(6);
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else{
                    if(dDay_status.equals("List") == true){
                        dDay =base.controller.req_selectDate();
                        if( dDay != null){
                            dDay_status = "Add";
                            segment.setText("   ???   ");
                            calendar.set(timekeeping.getYear(),timekeeping.getMonth(),timekeeping.getDate());
                            strDate = seg_fm.format(calendar.getTime());
                            segment.setText(strDate);
                            req_nextGoal();
                        }


                    }
                    else if(dDay_status.equals("Finish") == true){
                        dDay =base.controller.req_selectDate();
                        if( dDay != null){
                            dDay_status = "Add";
                            segment.setText("   ???   ");
                            req_nextGoal();
                        }
                    }
                    else if(dDay_status.equals("Setting") == true){
                        base.controller.req_deleteDday();
                        base.controller.getInstManager().setdDayIndex(-1);
                        dDay_status = "Finish";

                        dot.setText("delete completed~");
                        segment.setText("   X   ");


                    }
                    else if(dDay_status.equals("Add") == true){
                        switch(settingNum)
                        {
                            case 1:
                                req_nextGoal();
                                break;
                            case 2:
                                if(timekeeping.getYear() > calendar.get(Calendar.YEAR)) {
                                    req_nextYear();
                                }
                                break;
                            case 3:
                                if(timekeeping.getYear() > calendar.get(Calendar.YEAR)) {
                                    req_nextMonth();
                                }
                                else if(timekeeping.getYear() == calendar.get(Calendar.YEAR)){
                                    if(timekeeping.getMonth() > calendar.get(Calendar.MONTH)) {
                                        req_nextMonth();
                                    }
                                }
                                break;
                            case 4:
                                if(timekeeping.getYear() > calendar.get(Calendar.YEAR)) {
                                    req_nextDate();
                                }
                                else if(timekeeping.getYear() == calendar.get(Calendar.YEAR)){

                                    if(timekeeping.getMonth() > calendar.get(Calendar.MONTH)) {
                                        req_nextDate();
                                    }
                                    else if(timekeeping.getMonth() == calendar.get(Calendar.MONTH)) {
                                        if(timekeeping.getDate() > calendar.get(Calendar.DATE)) {
                                            req_nextDate();
                                        }
                                    }
                                }
                                break;

                        }

                    }
                }
            }
        });
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        Dday_label.add(C);
        C.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(dDay_status);
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else {
                    if (dDay_status.equals("List") == true) {
                        if (dDay != null) {
                            dDay_status = "Setting";
                            dot.setText("Really Delete??");
                        }
                    } else if (dDay_status.equals("Setting") == true) {
                        dDay_status = "List";
                        dot.setText((InstManager.getInstance().getdDayIndex() + 1) + "번째 목표 : " + dDay.getGoal());
                        if(dDay.getDayCount() >= 0) {
                            segment.setText((InstManager.getInstance().getdDayIndex() + 1) + "번쨰 D+day 값: D+" + dDay.getDayCount());
                        }
                        else{
                            segment.setText((InstManager.getInstance().getdDayIndex() + 1) + "번쨰 D+day 값: D" + dDay.getDayCount());
                        }

                    } else if (dDay_status.equals("Add") == true) {
                        if (settingNum == 1) {
                            goal = goalList[goal_index];
                            base.controller.req_setGoal(goal);
                            settingNum++;
                            strDate = seg_fm.format(calendar.getTime());
                            segment.setText(strDate);
                        } else if (settingNum == 2) {
                            settingNum++;
                        } else if (settingNum == 3) {
                            settingNum++;
                        } else if (settingNum == 4) {
                            base.controller.req_setDate("dDay", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                            dDay.start();

                            settingNum = 1;
                            calendar.set(timekeeping.getYear(), timekeeping.getMonth(), timekeeping.getDate());

                            base.controller.getInstManager().setdDayIndex(-1);
                            dDay_status = "Finish";
                            dot.setText("Add completed!!");
                            segment.setText("   Cheer Up~   ");


                        }

                    }
                }
            }
        });
        D = new JButton("D");
        D.setBounds(350,300,50,50);
        Dday_label.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else {
                    if (dDay_status.equals("List") == true) {
                        dDay = base.controller.req_DdayList();
                        if (dDay == null) {
                            dot.setText("D+day List Is NULL!");
                            segment.setText("   X   ");
                        } else {
                            dot.setText(dDay.getGoal());
                            dot.setText((InstManager.getInstance().getdDayIndex() + 1) + "번째 목표 : " + dDay.getGoal());
                            if(dDay.getDayCount() >= 0) {
                                segment.setText((InstManager.getInstance().getdDayIndex() + 1) + "번쨰 D+day 값: D+" + dDay.getDayCount());
                            }
                            else{
                                segment.setText((InstManager.getInstance().getdDayIndex() + 1) + "번쨰 D+day 값: D" + dDay.getDayCount());
                            }
                        }

                    } else if (dDay_status.equals("Finish") == true) {
                        dDay_status = "List";
                        dDay = base.controller.req_DdayList();
                        if (dDay == null) {
                            dot.setText("D+day List Is NULL!");
                            segment.setText("   X   ");
                        } else {
                            dot.setText(dDay.getGoal());
                            dot.setText((InstManager.getInstance().getdDayIndex() + 1) + "번째 목표 : " + dDay.getGoal());
                            if(dDay.getDayCount() >= 0) {
                                segment.setText((InstManager.getInstance().getdDayIndex() + 1) + "번쨰 D+day 값: D+" + dDay.getDayCount());
                            }
                            else{
                                segment.setText((InstManager.getInstance().getdDayIndex() + 1) + "번쨰 D+day 값: D" + dDay.getDayCount());
                            }
                        }
                    } else if (dDay_status.equals("Add") == true) {
                        switch (settingNum) {
                            case 1:
                                req_prevGoal();
                                break;
                            case 2:
                                req_prevYear();
                                break;
                            case 3:
                                req_prevMonth();
                                break;
                            case 4:
                                req_prevDate();
                                break;

                        }

                    }
                }
            }
        });
        dot = new JLabel();
        dot.setText("D+day List Is NULL!");
        dot.setBounds(150,200,200,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        Dday_label.add(dot);

        segment = new JLabel();

        segment.setText("   X   ");
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,15));
        Dday_label.add(segment);


        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if (dDay_status.equals("List") == true) {
                    if (dDay != null){
                        if(dDay.getDayCount() >= 0) {
                            segment.setText((InstManager.getInstance().getdDayIndex() + 1) + "번쨰 D+day 값: D+" + dDay.getDayCount());
                        }
                        else{
                            segment.setText((InstManager.getInstance().getdDayIndex() + 1) + "번쨰 D+day 값: D" + dDay.getDayCount());
                        }
                    }
                }
            }
        };
        java.util.Timer tm = new Timer();
        tm.scheduleAtFixedRate(tt,0,1000);

    }

    public void setLCD(DdayView ddayView) {
        ddayView.LCD1.setVisible(base.controller.req_isFunctionSelected(1));
        ddayView.LCD2.setVisible(base.controller.req_isFunctionSelected(2));
        ddayView.LCD3.setVisible(base.controller.req_isFunctionSelected(3));
        ddayView.LCD4.setVisible(base.controller.req_isFunctionSelected(4));
        ddayView.LCD5.setVisible(base.controller.req_isFunctionSelected(5));
    }

    public void req_nextGoal(){
        goal_index ++;
        if(goal_index == 6){
            goal_index = 0;
        }
        dot.setText("목표:"+goalList[goal_index]);
    }
    public void req_prevGoal(){
        goal_index --;
        if(goal_index == -1){
            goal_index = 5;
        }
        dot.setText("목표:"+goalList[goal_index]);
    }

    public void req_nextYear()
    {
        calendar.add(Calendar.YEAR,1);
        strDate = seg_fm.format(calendar.getTime());
        segment.setText(strDate);
    }
    public void req_prevYear()
    {
        calendar.add(Calendar.YEAR,-1);
        strDate = seg_fm.format(calendar.getTime());
        segment.setText(strDate);
    }
    public void req_nextMonth()
    {
        calendar.add(Calendar.MONTH, 1);
        strDate = seg_fm.format(calendar.getTime());
        segment.setText(strDate);
    }
    public void req_prevMonth()
    {
        calendar.add(Calendar.MONTH,-1);
        strDate = seg_fm.format(calendar.getTime());
        segment.setText(strDate);
    }

    public void req_nextDate()
    {
        calendar.add(Calendar.DATE, 1);
        strDate = seg_fm.format(calendar.getTime());
        segment.setText(strDate);
    }
    public void req_prevDate()
    {
        calendar.add(Calendar.DATE,-1);
        strDate = seg_fm.format(calendar.getTime());
        segment.setText(strDate);
    }


}

