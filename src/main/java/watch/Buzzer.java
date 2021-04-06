package watch;

import View.BaseView;

import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Buzzer{
    public int time=0;
    public boolean is_stop;
    private BaseView baseView;
    private static Buzzer buzzerInstance = new Buzzer();
    Toolkit toolkit;
    //다른클래스에서는 getInstance로 호출
    //Buzzer buzzer1 = Buzzer.getInstance(); 로 사용하면 된다
    public static Buzzer getInstance() {
        return buzzerInstance;
    }

    public Buzzer() {
        toolkit = Toolkit.getDefaultToolkit();
        this.time = time;
    }

    public void setBaseView(BaseView baseView) {
        this.baseView = baseView;
    }

    public void beep(){
        File bgm;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        bgm = new File("./beep.wav");

        Clip clip;

        try {
            stream = AudioSystem.getAudioInputStream(bgm);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

        } catch (Exception e) {
            System.out.println("err : " + e);
        }
    }

    public int ringBuzzer() {
        int time_value;
        baseView.on_buzzerMode();
        while(time<10){
            if(is_stop == false) {
                //beep();
                toolkit.beep();
                System.out.println("Beep!!!!!!!!!!!!!!");
                this.time++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            else{
                break;
            }
        }
        time_value = time;
        is_stop = true;
        time = 0;
        return time_value;


    }


    public void stopBuzzer(){
        this.is_stop = true;
        baseView.off_buzzerMode();
    }

    public boolean getIs_stop() {
        return is_stop;
    }

    public void setIs_stop(boolean is_stop) {
        this.is_stop = is_stop;
    }
}
