package watch;

import View.TimersView;

public class Timers extends Thread{
    private int hour, minute, second;
    private boolean is_stop;
    private boolean check;

    //버저 객체를 가져옴
    Buzzer buzzer = Buzzer.getInstance();

    public Timers(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
        this.is_stop = true;
    }

    public void run(){
        //여기는 run
        //checkTimer()은 countDown()안에 존재한다.
        countDown();
    }

    //usecase: stop_buzzer
    public Buzzer getBuzzer(){
        return buzzer;
    }

    //usecase:set_timer
    public void setTime(int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    //usecase: cancel_timer
    public int getHour(){
        return this.hour;
    }
    //usecase: cancel_timer
    public int getMinute(){
        return this.minute;
    }
    //usecase: cancel_timer
    public int getSecond(){
        return this.second;
    }

    //usecase: count_down
    synchronized public void countDown() {      //000   00x     0xx     xxx     x0x     x00     xx0     0x0
        while (true) {
            if(is_stop == false) {
                if(this.second!=0 && this.minute==0 && this.hour==0){
                    this.second--;
                }else if(this.second!=0 && this.minute!=0 && this.hour==0){
                    this.second--;
                }else if(this.second!=0 && this.minute!=0 && this.hour!=0){
                    this.second--;
                }else if(this.second!=0 && this.minute==0 && this.hour!=0){
                    this.second--;
                }else if(this.second==0 && this.minute==0 && this.hour!=0){
                    this.hour--;
                    this.minute = 59;
                    this.second = 60;
                    this.second--;
                }else if(this.second==0 && this.minute!=0 && this.hour!=0){
                    this.minute--;
                    this.second=60;
                    this.second--;
                }else if(this.second==0 && this.minute!=0 && this.hour==0) {
                    this.minute--;
                    this.second = 60;
                    this.second--;
                } else if(this.second == 0 && this.minute == 0 && this.hour == 0){
                    check = checkTimer();
                    if (check == true) {
                        buzzer.setIs_stop(false);
                        buzzer.ringBuzzer();
                        // break outerLoop;
                    }
                }else{
                    //none
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //is_stop == true
            else{
                //   synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //  }
            }
            System.out.println(hour + ":" +  minute +  ":" +  second);
        }

    }

    //usecase: count_down
    public boolean checkTimer(){
        if(this.hour==0 && this.minute==0 && this.second==0){
            return true;
        }
        else
            return false;
    }

    public void reset(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public boolean getIs_stop() {
        return is_stop;
    }

    public void setIs_stop(boolean is_stop) {
        this.is_stop = is_stop;
    }
}
