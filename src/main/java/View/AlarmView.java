package View;

import watch.Alarm;
import watch.Buzzer;
import watch.InstManager;
import watch.Timekeeping;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmView extends JPanel {
    private JLabel alarm_label;
    private BaseView base;
    private Alarm alarm;

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

    private boolean turn_over;
    private int dayListNum;
    private String text;
    private int i;
    private int num;
    private ArrayList<Integer> checkDayList;
    private int cycle;
    private int[] cycleList = {0, 5, 10, 15};
    private int index = 0;
    private int choice = 0;

    private Calendar calendar;
    private SimpleDateFormat dot_fm;
    private SimpleDateFormat seg_fm;
    private String strTime;
    private String strDay;
    private int settingNum;

    private String alarm_status = "List";
    /*
        1. List     2. Setting      3. Add
     */

    boolean buzzer_mode;

    public void setBuzzer_mode(boolean buzzer_mode) {
        this.buzzer_mode = buzzer_mode;
    }
    public AlarmView(BaseView base)
    {
        turn_over = false;
        settingNum = 1;
        calendar = Calendar.getInstance();
        calendar.set(0,0,0, 0,0);
        calendar.setWeekDate(0,0,2);
        checkDayList = new ArrayList<Integer>(7);
        this.base = base;
        this.setBounds(0,0,800,500);
        //this.setBackground(Color.GREEN);

        // ImageIcon icon1 = new ImageIcon("C:\\Users\\yheji\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        alarm_label = new JLabel();
        alarm_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        alarm_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        alarm_label.add(tmp);
        //tk_label.setVisible(true);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        alarm_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        alarm_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        alarm_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        alarm_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        alarm_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));

        this.add(alarm_label);
        this.setLayout(null);


        A = new JButton("A");
        A.setBounds(100,150,50,50);
        this.add(A);
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
        this.add(B);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(buzzer_mode == true) {
                    Buzzer.getInstance().stopBuzzer();
                }
                else{
                    if (alarm_status.equals("List") == true) {
                        System.out.println("List");
                        alarm = base.controller.req_alarmList();
                        if (alarm == null) {
                            dot.setText("No Record");
                            segment.setText("   00:00");

                        } else {
                            text = " ";
                            dayListNum = alarm.getDayListNum();
                            System.out.println(dayListNum);
                            for (i = 0; i < dayListNum; i++) {
                                num = alarm.getCheckDayList(i);
                                if (num == 2) {
                                    text = text + "월 ";
                                } else if (num == 3) {
                                    text = text + "화 ";
                                } else if (num == 4) {
                                    text = text + "수 ";
                                } else if (num == 5) {
                                    text = text + "목 ";
                                } else if (num == 6) {
                                    text = text + "금 ";
                                } else if (num == 7) {
                                    text = text + "토 ";
                                } else if (num == 1) {
                                    text = text + "일 ";
                                }

                            }

                            segment.setText(Integer.toString(InstManager.getInstance().getAlarmIndex()) + ". " + Integer.toString(alarm.getHour()) + ":" + Integer.toString(alarm.getMinute()));
                            if (alarm.getStatus() == true) {
                                dot.setText(text + " 주기:" + alarm.getCycle() + " On");
                            } else {
                                dot.setText(text + " 주기:" + alarm.getCycle() + " Off");
                            }
                        }


                    } else if (alarm_status.equals("Setting") == true) {

                        req_nextSetting();

                    } else if (alarm_status.equals("Add") == true) {
                        System.out.println("Add");
                        switch (settingNum) {
                            case 1:

                                req_nextDay();
                                if (checkDayList.contains(new Integer(calendar.get(Calendar.DAY_OF_WEEK)))) {
                                    System.out.println(calendar.get(Calendar.DAY_OF_WEEK) + 1);
                                    dot.setForeground(Color.BLUE);
                                    //req_nextDay();
                                    if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        turn_over = true;
                                    }
                                    System.out.println("Blue");
                                } else {
                                    System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
                                    dot.setForeground(Color.DARK_GRAY);
                                    //req_nextDay();
                                    if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                                        turn_over = true;
                                    }
                                    System.out.println("Gray");
                                }


                                if (calendar.get(Calendar.DAY_OF_WEEK) == 2 && turn_over == true && checkDayList.size() != 0) {
                                    //dot.setForeground(Color.DARK_GRAY);
                                    settingNum++;
                                    Collections.sort(checkDayList);
                                    dot.setForeground(Color.DARK_GRAY);
                                    System.out.println("Day select finish");

                                    text = " ";
                                    dayListNum = checkDayList.size();
                                    for (i = 0; i < dayListNum; i++) {
                                        num = checkDayList.get(i);
                                        if (num == 2) {
                                            text = text + "월 ";
                                        } else if (num == 3) {
                                            text = text + "화 ";
                                        } else if (num == 4) {
                                            text = text + "수 ";
                                        } else if (num == 5) {
                                            text = text + "목 ";
                                        } else if (num == 6) {
                                            text = text + "금 ";
                                        } else if (num == 7) {
                                            text = text + "토 ";
                                        } else if (num == 1) {
                                            text = text + "일 ";
                                        }

                                    }
                                    System.out.println(text);
                                    dot.setText(text + " 주기:" + cycleList[index]);

                                }

                                break;
                            case 2:
                                req_nextCycle();
                                break;
                            case 3:
                                req_nextHour();
                                break;
                            case 4:

                                req_nextMinute();
                                break;

                        }

                    }

                }
            }
        });
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        this.add(C);
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buzzer_mode == true) {
                    Buzzer.getInstance().stopBuzzer();
                } else {
                    if (alarm_status.equals("List") == true) {
                        alarm_status = "Setting";
                        if (alarm != null) {
                            choice = 0;
                            dot.setText("Delete?");

                        } else {
                            dot.setText("No Record");
                            segment.setText("   00:00");

                        }

                    } else if (alarm_status.equals("Setting") == true) {
                        alarm_status = "List";
                        base.controller.getInstManager().setAlarmIndex(-1);
                        alarm = base.controller.req_alarmList();
                        if (alarm == null) {
                            dot.setText("No Record");
                            segment.setText("   00:00");

                        } else {
                            text = " ";
                            dayListNum = alarm.getDayListNum();
                            System.out.println(dayListNum);
                            for (i = 0; i < dayListNum; i++) {
                                num = alarm.getCheckDayList(i);
                                if (num == 2) {
                                    text = text + "월 ";
                                } else if (num == 3) {
                                    text = text + "화 ";
                                } else if (num == 4) {
                                    text = text + "수 ";
                                } else if (num == 5) {
                                    text = text + "목 ";
                                } else if (num == 6) {
                                    text = text + "금 ";
                                } else if (num == 7) {
                                    text = text + "토 ";
                                } else if (num == 1) {
                                    text = text + "일 ";
                                }

                            }

                            segment.setText(Integer.toString(InstManager.getInstance().getAlarmIndex()) + ". " + Integer.toString(alarm.getHour()) + ":" + Integer.toString(alarm.getMinute()));
                            if (alarm.getStatus() == true) {
                                dot.setText(text + " 주기:" + alarm.getCycle() + " On");
                            } else {
                                dot.setText(text + " 주기:" + alarm.getCycle() + " Off");
                            }
                        }

                    } else if (alarm_status.equals("Add") == true) {

                        if (settingNum == 1) {
                            if (checkDayList.contains(new Integer(calendar.get(Calendar.DAY_OF_WEEK)))) {
                                checkDayList.remove(new Integer(calendar.get(Calendar.DAY_OF_WEEK)));
                                dot.setForeground(Color.DARK_GRAY);
                            } else {
                                checkDayList.add(new Integer(calendar.get(Calendar.DAY_OF_WEEK)));
                                dot.setForeground(Color.BLUE);
                            }

                        } else if (settingNum == 2) {
                            cycle = cycleList[index];
                            settingNum++;
                        } else if (settingNum == 3) {
                            settingNum++;
                        } else if (settingNum == 4) {
                            base.controller.req_setDate(checkDayList, dayListNum, cycleList[index], calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                            System.out.println("alarm start!!");
                            System.out.println("시계시간: " + InstManager.getInstance().getTimekeeping().getHour());
                            System.out.println("시계분: " + InstManager.getInstance().getTimekeeping().getMinute());
                            System.out.println("시계요일: " + InstManager.getInstance().getTimekeeping().getDayNum());
                            System.out.println("알람시간: " + alarm.getHour());
                            System.out.println("알람분: " + alarm.getMinute());
                            for (int i = 0; i < alarm.getCheckDayList().size(); i++) {
                                System.out.println("알람요일: " + alarm.getCheckDayList().get(i));
                            }
                            alarm.start();

                            alarm_status = "List";
                            settingNum = 1;
                            index = 0;
                            dot.setForeground(Color.DARK_GRAY);
                            calendar.set(0, 0, 0, 0, 0);
                            calendar.setWeekDate(0, 0, 2);
                            checkDayList.clear();
                            turn_over = false;

                            base.controller.getInstManager().setAlarmIndex(-1);
                            alarm = base.controller.req_alarmList();
                            text = " ";
                            dayListNum = alarm.getDayListNum();
                            System.out.println(dayListNum);
                            for (i = 0; i < dayListNum; i++) {
                                num = alarm.getCheckDayList(i);
                                if (num == 2) {
                                    text = text + "월 ";
                                } else if (num == 3) {
                                    text = text + "화 ";
                                } else if (num == 4) {
                                    text = text + "수 ";
                                } else if (num == 5) {
                                    text = text + "목 ";
                                } else if (num == 6) {
                                    text = text + "금 ";
                                } else if (num == 7) {
                                    text = text + "토 ";
                                } else if (num == 1) {
                                    text = text + "일 ";
                                }

                            }

                            segment.setText(Integer.toString(InstManager.getInstance().getAlarmIndex()) + ". " + Integer.toString(alarm.getHour()) + ":" + Integer.toString(alarm.getMinute()));
                            if (alarm.getStatus() == true) {
                                dot.setText(text + " 주기:" + alarm.getCycle() + " On");
                            } else {
                                dot.setText(text + " 주기:" + alarm.getCycle() + " Off");
                            }

                        }

                    }

                }
            }
        });


        D = new JButton("D");
        D.setBounds(350,300,50,50);
        this.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else{

                    if(alarm_status.equals("List") == true){
                        Alarm alarm_ver2 = alarm;
                        alarm = base.controller.req_setAlarm();
                        if(alarm != null){
                            alarm_status = "Add";
                            strDay = dot_fm.format(calendar.getTime());
                            dot.setText(strDay);
                            settingNum = 1;
                            segment.setText("   00:00");
                        }
                        else{
                            alarm = alarm_ver2;

                        }
                    }
                    else if(alarm_status.equals("Setting") == true){
                        System.out.println("choice : "+choice);
                        if(choice == 0){
                            if(alarm != null) {
                                base.controller.req_deleteAlarm();
                            }
                        }
                        else if(choice ==1) {
                            if (alarm != null) {
                                System.out.println("ChangeOnOff");
                                base.controller.req_onOff();
                            }
                        }
                        alarm_status = "List";
                        //index = 0;

                        base.controller.getInstManager().setAlarmIndex(-1);
                        alarm = base.controller.req_alarmList();
                        if(alarm == null){
                            dot.setText("No Record");
                            segment.setText("   00:00");

                        }
                        else {
                            text = " ";
                            dayListNum = alarm.getDayListNum();
                            System.out.println(dayListNum);
                            for (i = 0; i < dayListNum; i++) {
                                num = alarm.getCheckDayList(i);
                                if (num == 2) {
                                    text = text + "월 ";
                                } else if (num == 3) {
                                    text = text + "화 ";
                                } else if (num == 4) {
                                    text = text + "수 ";
                                } else if (num == 5) {
                                    text = text + "목 ";
                                } else if (num == 6) {
                                    text = text + "금 ";
                                } else if (num == 7) {
                                    text = text + "토 ";
                                } else if (num == 1) {
                                    text = text + "일 ";
                                }

                            }

                            segment.setText(Integer.toString(InstManager.getInstance().getAlarmIndex()) + ". " + Integer.toString(alarm.getHour()) + ":" + Integer.toString(alarm.getMinute()));
                            if (alarm.getStatus() == true) {
                                dot.setText(text + " 주기:" + alarm.getCycle() + " On");
                            } else {
                                dot.setText(text + " 주기:" + alarm.getCycle() + " Off");
                            }

                        }
                    }
                    else if(alarm_status.equals("Add") == true){
                        //DOWN버튼
                        switch(settingNum)
                        {
                            case 1:
                                if(calendar.get(Calendar.DAY_OF_WEEK) != 2) {
                                    req_prevDay();
                                    if (checkDayList.contains(new Integer(calendar.get(Calendar.DAY_OF_WEEK)))) {
                                        dot.setForeground(Color.BLUE);
                                    } else {
                                        dot.setForeground(Color.DARK_GRAY);
                                    }

                                }
                                break;
                            case 2:
                                req_prevCycle();
                                break;
                            case 3:
                                req_prevHour();
                                break;
                            case 4:
                                req_prevMinute();
                                break;

                        }

                    }

                }
            }
        });

        dot = new JLabel();
        dot.setText("05.24.FRI");
        dot.setBounds(150,200,200,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        alarm_label.add(dot);

        segment = new JLabel();

        // segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,40));
        alarm_label.add(segment);

        dot_fm = new SimpleDateFormat("E요일");
        seg_fm = new SimpleDateFormat("   HH:mm");

        dot.setText("No Record");
        segment.setText("   00:00");

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if (alarm_status.equals("List") == true) {
                    if (alarm != null) {
                        if (alarm.getStatus() == true) {
                            dot.setText(text + " 주기:" + alarm.getCycle() + " On");
                        } else {
                            dot.setText(text + " 주기:" + alarm.getCycle() + " Off");
                        }
                    }
                }
            }
        };

        java.util.Timer tm = new Timer();
        tm.scheduleAtFixedRate(tt,0,1000);

    }
    public void req_nextDay()
    {
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        strDay = dot_fm.format(calendar.getTime());
        dot.setText(strDay);
    }
    public void req_prevDay()
    {
        calendar.add(Calendar.DAY_OF_WEEK,-1);
        strDay = dot_fm.format(calendar.getTime());
        dot.setText(strDay);
    }
    public void req_nextCycle(){
        index ++;
        if(index == 4){
            index = 0;
        }
        dot.setText(text + " 주기:"+cycleList[index]);
    }
    public void req_prevCycle(){
        index --;
        if(index == -1){
            index = 3;
        }
        dot.setText(text + " 주기:"+cycleList[index]);
    }

    public void req_nextHour()
    {
        calendar.add(Calendar.HOUR,1);
        strTime = seg_fm.format(calendar.getTime());
        segment.setText(strTime);
    }
    public void req_prevHour()
    {
        calendar.add(Calendar.HOUR,-1);
        strTime = seg_fm.format(calendar.getTime());
        segment.setText(strTime);
    }
    public void req_nextMinute()
    {
        calendar.add(Calendar.MINUTE, 1);
        strTime = seg_fm.format(calendar.getTime());
        segment.setText(strTime);
    }
    public void req_prevMinute()
    {
        calendar.add(Calendar.MINUTE,-1);
        strTime = seg_fm.format(calendar.getTime());
        segment.setText(strTime);
    }
    public void req_nextSetting(){
        choice = (choice +1)%2;
        if(choice == 0) {
            dot.setText("Delete?");
            // choice = (choice+1)%2;
        }
        else if(choice == 1){
            dot.setText("change OnOff mode?");
            // choice = (choice+1)%2;
        }
    }

    public void setLCD(AlarmView alarmview) {
        alarmview.LCD1.setVisible(base.controller.req_isFunctionSelected(1));
        alarmview.LCD2.setVisible(base.controller.req_isFunctionSelected(2));
        alarmview.LCD3.setVisible(base.controller.req_isFunctionSelected(3));
        alarmview.LCD4.setVisible(base.controller.req_isFunctionSelected(4));
        alarmview.LCD5.setVisible(base.controller.req_isFunctionSelected(5));
    }

}
