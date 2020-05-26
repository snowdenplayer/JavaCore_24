import Exeptions.TimeExeption;

public class Time  implements Comparable<Time>{
    private int min;
    private int hour;


    public Time(int min, int hour) throws TimeExeption {
        if((min<0 || min>60) && (hour<0 || hour>24)){
            throw new TimeExeption("Error time");
        }else {
            this.min = min;
            this.hour = hour;
        }
    }

    public Time addTime(Time t) throws TimeExeption {
        int hour = t.hour + this.hour;
        int min = t.min + this.min;
        if(min>59){
            min-=60;
            hour++;
        }
        if(hour>23){
            hour-=24;
        }
        return new Time(min,hour);

    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "hour: " + hour + " min: " + min ;
    }

    public int compareTo(Time o) {

        return (hour * 60 + min) - (o.getHour() * 60 + o.getMin());
    }
}
