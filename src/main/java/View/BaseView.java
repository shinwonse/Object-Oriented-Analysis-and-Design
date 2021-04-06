package View;

import watch.Buzzer;
import watch.Controller;

import javax.swing.*;

public class BaseView extends JFrame {
    private JPanel contentPane;
    public TimeKeepingView tkv=null;
    public TimersView tmv = null;
    public AlarmView alarmView = null;
    public StopWatchView stopWatchView =null;
    public DdayView ddayView = null;
    public FitnessView fitnessView = null;
    public SelectView selectView = null;

    public Controller controller = null;



    public void change_view(int num)
    {
        switch(num){
            case 0:
                setContentPane(tkv);
                tkv.setLCD(tkv);
                revalidate();
                repaint();
                break;
            case 1:
                setContentPane(tmv);
                tmv.setLCD(tmv);
                revalidate();
                repaint();
                break;
            case 2:
                setContentPane(alarmView);
                alarmView.setLCD(alarmView);
                revalidate();
                repaint();
                break;
            case 3:
                setContentPane(stopWatchView);
                stopWatchView.setLCD(stopWatchView);
                revalidate();
                repaint();
                break;
            case 4:
                setContentPane(ddayView);
                ddayView.setLCD(ddayView);
                revalidate();
                repaint();
                break;
            case 5:
                setContentPane(fitnessView);
                fitnessView.setLCD(fitnessView);
                revalidate();
                repaint();
                break;
            case 6:
                setContentPane(selectView);
                revalidate();
                repaint();
                break;

        }
    }

    public void on_buzzerMode(){
        tkv.setBuzzer_mode(true);
        tmv.setBuzzer_mode(true);
        alarmView.setBuzzer_mode(true);
        stopWatchView.setBuzzer_mode(true);
        ddayView.setBuzzer_mode(true);
        fitnessView.setBuzzer_mode(true);
        selectView.setBuzzer_mode(true);

    }

    public void off_buzzerMode(){

        tkv.setBuzzer_mode(false);
        tmv.setBuzzer_mode(false);
        alarmView.setBuzzer_mode(false);
        stopWatchView.setBuzzer_mode(false);
        ddayView.setBuzzer_mode(false);
        fitnessView.setBuzzer_mode(false);
        selectView.setBuzzer_mode(false);

    }

    public static void main(String[] args)
    {


        BaseView bv = new BaseView();

        bv.controller = new Controller(bv);
        Buzzer.getInstance().setBaseView(bv);
        bv.tkv = new TimeKeepingView(bv);
        bv.tmv = new TimersView(bv);
        bv.alarmView = new AlarmView(bv);
        bv.selectView = new SelectView(bv);
        bv.stopWatchView = new StopWatchView(bv);
        bv.fitnessView = new FitnessView(bv);
        bv.ddayView = new DdayView(bv);


        bv.setTitle("SMA_T6_CLOCK");
        bv.setContentPane(bv.tkv);
        bv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bv.setSize(500,500);
        bv.setVisible(true);


    }
}
