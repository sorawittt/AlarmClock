package sample;

public class AlarmClock {
    private int hour;
    private int minute;

    public AlarmClock(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public AlarmClock() {
        this.hour = -1;
        this.minute = -1;
    }


    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean check(int curHour, int curMinute) {
        if(hour == curHour && minute == curMinute) {
            System.out.println("Yesssssssssss");
            return true;
        }
        return false;
    }



}
