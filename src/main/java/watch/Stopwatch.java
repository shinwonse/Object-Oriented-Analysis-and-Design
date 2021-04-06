package watch;

public class Stopwatch extends Thread implements CountUp, StateChange{

    private int hour;
    private int minute;
    private int second;
    private boolean is_stop;

    private DBManager dbManager;
    private StopwatchDTO swDTO;

    public Stopwatch(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
        is_stop = true;

        this.dbManager = DBManager.getInstance();
        this.swDTO = StopwatchDTO.getInstance();
    }


    //getter
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public boolean getIs_stop() {
        return is_stop;
    }

    //setter
    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setIs_stop(boolean is_stop) {
        this.is_stop = is_stop;
    }

    @Override
    public void run() {
        countUp();
    }


    // method
    @Override
    synchronized public void countUp() {
        while(true) {
            if (is_stop == false) {
                this.second++;
                if (this.second == 60) {
                    this.second = 0;
                    this.minute++;
                }
                if (this.minute == 60) {
                    this.minute = 0;
                    this.hour++;
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
                // synchronized (this) {
                try {
                    //System.out.println("wait");
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //  }
            }
        }
    }

    @Override
    public void reset() {
        hour = 0;
        minute = 0;
        second = 0;
        dbManager.resetStopwatch();
    }

    public void showRecord(int column){
        dbManager.selectStopwatch(column);
    }

    public void  record(int count){
        //   System.out.println("여기는 스탑워치의 record함수");
        if(swDTO.getNum() >=10){
            dbManager.deleteStopwatch();
        }
        dbManager.insertStopwatch(this.hour, this.minute, this.second, count);
        dbManager.selectStopwatch(swDTO.getNum());
    }


}