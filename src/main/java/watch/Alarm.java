package watch;

import java.util.ArrayList;


public class Alarm extends Thread {
    private int hour, minute, cycle;
    private boolean status = true;
    private ArrayList<Integer> checkDayList;
    private int dayListNum;
    private int[] dayList = {1, 2, 3, 4, 5, 6, 7};  //순서대로 일월화수목금토
    private InstManager inst;
    private Timekeeping time;
    private boolean is_delete = false;
    private Buzzer buzzer;



    public Alarm(){
        inst = InstManager.getInstance();
        time = inst.getTimekeeping();
        checkDayList = new ArrayList<>(7);
        dayListNum = checkDayList.size();
        buzzer = Buzzer.getInstance();
    }


    public void run() {
        checkAlarm();
    }

    public Buzzer getBuzzer() {
        return buzzer;
    }

    //요일 배열을 가져온다
   // public int[] getDayList() {
   //     return dayList;
   // }

    //가져온 day배열중에 설정된 요일의 넘버만 리해준다
    public int getCheckDayList(int index) {
        return this.checkDayList.get(index);
    }

    public int getDayListNum() {
        dayListNum = checkDayList.size();
        return dayListNum;
    }

    public int getCycle() {
        return this.cycle;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public boolean getStatus() {
        return status;
    }

    public ArrayList<Integer> getCheckDayList() {
        return checkDayList;
    }

    public void setDay(int day) {
        this.checkDayList.add(day);
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //usecase: onoff_alarm  그리고 이부분 보고서랑 다이어그램 고치기.
    public void OnOffAlarm() {
        if(status == true) {
            status = false;
            System.out.println("Off status:"+status);
        }

        else {
            status = true;
            System.out.println("On status :"+status);
        }
    }

    synchronized public void checkAlarm() {
        int i;
        int j;
        int time_value;

        while (is_delete == false) {
            try {
                sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();

            }


            if(status == true) {

                System.out.println("alarm check!");

                if (this.hour == time.getHour() && this.minute == time.getMinute()) {
                    System.out.println("시간이 똑같다!!");
                    for (i = 0; i < checkDayList.size(); i++) {
                        if (this.checkDayList.get(i) == time.getDayNum()) {
                            System.out.println("요일도 똑같다!!");
                            //System.out.println("알람요일 : "+this.checkDayList.get(i)+"   시계 요일 : "+ time.getDayNum());
                            if(cycle != 0 ) {
                                // 총 3번 버저를 울린다.
                                for(j = 0; j < 3; j++) {
                                    if(status == true && is_delete == false) {
                                        buzzer.setIs_stop(false);
                                        time_value = buzzer.ringBuzzer();
                                        try {
                                            // (주기-버저울린시간)만큼 잠든다.
                                            time_value = (time_value*3)-1;
                                            sleep((long)(cycle * 60 * 1000 - time_value * 1000));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                            }
                            else {      // cycle == 0
                                buzzer.setIs_stop(false);
                                time_value = buzzer.ringBuzzer();
                                try {
                                    // (1분-버저울린시간)만큼 잠든다.
                                    time_value = (time_value*3)-1;
                                    sleep((long)((60 * 1000) - (time_value * 1000)));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}