package watch;

public class StopwatchDTO {

    private int hour;
    private int minute;
    private int second;
    private int num;

    private static StopwatchDTO ourInstance = new StopwatchDTO();

    public static StopwatchDTO getInstance() {
        return ourInstance;
    }

    private StopwatchDTO() {
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

    public int getNum() {
        return num;
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

    public void setNum(int num) {
        this.num = num;
    }

}
