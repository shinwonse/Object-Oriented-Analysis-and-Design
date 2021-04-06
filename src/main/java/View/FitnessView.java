package View;


import watch.Buzzer;
import watch.Fitness;
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


public class FitnessView extends JPanel{
    private JLabel fitness_label;
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
    private  JLabel tmp;


    private int count;
    private boolean is_pause = false; //뷰에서 pause체크해준다.
    private int check=0;

    Calendar calendar;
    SimpleDateFormat dot_fm;
    SimpleDateFormat seg_fm;
    String strDate;
    String strDate2;
    Timer tm;
    Fitness fit;


    private String fit_status = "List"; //TimeKeeping 과 TimeSetting 의 View 가 존재한다.
    /*
        1. List     2. Setting      3. Excute
     */
    boolean buzzer_mode;

    public void setBuzzer_mode(boolean buzzer_mode) {
        this.buzzer_mode = buzzer_mode;
    }


    public FitnessView(BaseView base)
    {
        this.base= base;
        this.setBounds(0,0,800,500);
        // this.setBackground(Color.YELLOW);
        count =0;

        calendar = Calendar.getInstance();
        fit = InstManager.getInstance().getFitness();
        dot_fm = new SimpleDateFormat("M월 dd일");
        seg_fm = new SimpleDateFormat("HH:mm:ss");
        base.controller.req_fitnessList(fit.getCount());
        calendar.set(InstManager.getInstance().getTimekeeping().getYear(),InstManager.getInstance().getTimekeeping().getMonth(),InstManager.getInstance().getTimekeeping().getDate(),fit.getHour(),fit.getMinute(),fit.getSecond());

        //ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        fitness_label = new JLabel();
        fitness_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        fitness_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        fitness_label.add(tmp);
        //tk_label.setVisible(true);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        fitness_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        fitness_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        fitness_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        fitness_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        fitness_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));

        this.add(fitness_label);
        this.setLayout(null);

        //카운트업 화면 보여주는 타이머 태스크



        A = new JButton("A");
        A.setBounds(100,150,50,50);
        fitness_label.add(A);
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
        fitness_label.add(B);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else {
                    //if(tk_status.equals("TimeKeeping"))
                    if (fit_status.equals("List") == true) {
                        fit_status = "Setting";
                        dot.setText("Setting - select fitnessList");

                    } else if (fit_status.equals("Setting") == true) {
                        strDate = base.controller.req_nextExercise();
                        dot.setText(strDate);
                    }
                    else{

                    }
                }
            }
        });
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        fitness_label.add(C);
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else {
                    if (fit_status.equals("Setting") == true) {
                        base.controller.req_setExercise(strDate);
                        fit.setIs_stop(false);
                        tm = new Timer();
                        if (fit.getCPM() != 0) {
                            fit.setMonth(InstManager.getInstance().getTimekeeping().getMonth());
                            fit.setDate(InstManager.getInstance().getTimekeeping().getDate());
                            calendar.set(Calendar.YEAR, fit.getMonth(), fit.getDate(), 0, 0, 0);
                            String str = dot_fm.format(calendar.getTime());
                            dot.setText(str + "calories:");
                            TimerTask tt = new TimerTask() {
                                @Override
                                public void run() {
                                    calendar.set(base.controller.getInstManager().getTimekeeping().getYear(), fit.getMonth(), fit.getDate(), fit.getHour(), fit.getMinute(), fit.getSecond());
                                    dot.setText(fit.getExercise() + "calories: " + fit.getTotalCalories());
                                    strDate2 = seg_fm.format(calendar.getTime());
                                    segment.setText(strDate2);

                                }
                            };

                            fit_status = "Execute";//모드 전환
                            fit.setHour(0);
                            fit.setMinute(0);
                            fit.setSecond(0);
                            fit.setTotalCalories(0);
                            if (check == 0)
                                base.controller.req_countUp("fitness");
                            else
                                base.controller.req_continue("fitness");
                            tm.scheduleAtFixedRate(tt, 0, 1000);
                        }


                    } else if (fit_status.equals("Execute") == true) {
                        //
                        if (is_pause == false) {
                            System.out.println("정지버튼");
                            is_pause = true;
                            base.controller.req_pause("fitness");
                        } else {
                            System.out.println("계속버튼");
                            base.controller.req_continue("fitness");
                            is_pause = false;
                        }


                    }
                    else{

                    }
                }
            }
        });

        D = new JButton("D");
        D.setBounds(350,300,50,50);
        fitness_label.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(buzzer_mode == true){
                    Buzzer.getInstance().stopBuzzer();
                }
                else {
                    if (fit_status.equals("List") == true) {
                        //NEXT버튼
                        count++;
                        if (count == fit.getCount() + 1)
                            count = 1;
                        base.controller.req_fitnessList(count);
                        calendar.set(Calendar.YEAR, fit.getMonth(), fit.getDate(), fit.getHour(), fit.getMinute(), fit.getSecond());
                        strDate = seg_fm.format(calendar.getTime());
                        strDate2 = dot_fm.format(calendar.getTime());
                        segment.setText(strDate);
                        dot.setText(strDate2 + "calories: " + InstManager.getInstance().getFitness().getTotalCalories());

                    } else if (fit_status.equals("Execute") == true) {
                        //finish
                        if (is_pause == true) {
                            check = 1;
                            tm.cancel();
                            tm.purge();
                            fit.setMonth(InstManager.getInstance().getTimekeeping().getMonth());
                            fit.setDate(InstManager.getInstance().getTimekeeping().getDate());
                            System.out.println("지금 날짜는 :"+ fit.getDate());
                            base.controller.req_finish("fitness");
                            // strDate2 = seg_fm.format(calendar.getTime());
                            fit_status = "List";
                            //바로 목록 보여줄 수 있도록
                            count=fit.getCount();
                            base.controller.req_fitnessList(count);
                            calendar.set(Calendar.YEAR, fit.getMonth(), fit.getDate(), fit.getHour(), fit.getMinute(), fit.getSecond());
                            strDate = seg_fm.format(calendar.getTime());
                            strDate2 = dot_fm.format(calendar.getTime());
                            segment.setText(strDate);
                            dot.setText(strDate2 + "calories: " + InstManager.getInstance().getFitness().getTotalCalories());
                        }
                        else{

                        }
                    }
                }
            }
        });
        //calendar.set(Calendar.YEAR,base.controller.getInstManager().getFitness().getMonth(),base.controller.getInstManager().getFitness().getDate(),base.controller.getInstManager().getFitness().getHour(),base.controller.getInstManager().getFitness().getMinute(),base.controller.getInstManager().getFitness().getSecond());
        dot = new JLabel();
        dot.setBounds(150,200,200,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        strDate2 = dot_fm.format(calendar.getTime());
        dot.setText(strDate2+"calories: "+fit.getTotalCalories());
        fitness_label.add(dot);
        base.controller.req_fitnessList(count);
        // dot.setText(fitness.getMonth()+"."+fitness.getDate()+"."+fitness.getTotalCalories());

        segment = new JLabel();

        strDate = seg_fm.format(calendar.getTime());
        segment.setText(strDate);
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,30));
        fitness_label.add(segment);



    }

    public void setLCD(FitnessView fitnessView) {
        fitnessView.LCD1.setVisible(base.controller.req_isFunctionSelected(1));
        fitnessView.LCD2.setVisible(base.controller.req_isFunctionSelected(2));
        fitnessView.LCD3.setVisible(base.controller.req_isFunctionSelected(3));
        fitnessView.LCD4.setVisible(base.controller.req_isFunctionSelected(4));
        fitnessView.LCD5.setVisible(base.controller.req_isFunctionSelected(5));
    }

}

