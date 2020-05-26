import Exeptions.TimeExeption;

import java.util.Objects;

public class Seance implements Comparable<Seance> {
    private Movie movie;
    private Time startTime;
    private Time endTime;
    private static   int counter = 0;

    public Seance(Movie movie, Time startTime) throws TimeExeption {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = this.startTime.addTime(movie.getDuration());
        this.counter = ++counter;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int compareTo(Seance o) {
        return startTime.compareTo(o.getStartTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return Objects.equals(movie, seance.movie) &&
                Objects.equals(startTime, seance.startTime) &&
                Objects.equals(endTime, seance.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Seance [" + ++counter  + "] " + movie +
                ", startTime=" + startTime +
                ", endTime=" + endTime +'\n';
    }
}
