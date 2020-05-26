import Exeptions.DateExeption;

import java.util.*;

public class Cinema {
    private TreeMap<Days, Schedule> shedules;
    private ArrayList<Movie> moviesLibrary = new ArrayList<>();
    private Time open;
    private Time close;


    public Cinema() {
    }

    public Cinema(Time open, Time close) {
        shedules = new TreeMap<>();
        this.open = open;
        this.close = close;
    }

    public TreeMap<Days, Schedule> getShedules() {
        return shedules;
    }

    public void setShedules(TreeMap<Days, Schedule> shedules) {
        this.shedules = shedules;
    }

    public ArrayList<Movie> getMoviesLibrary() {
        return moviesLibrary;
    }

    public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
        this.moviesLibrary = moviesLibrary;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    public void addMovie(Movie movie) {
        if (!moviesLibrary.stream().anyMatch(s -> s.getTitle().equalsIgnoreCase(movie.getTitle())))
            moviesLibrary.add(movie);
        else
            System.out.println(movie.getTitle() + " already exist");
        moviesLibrary.toString();
    }

    public void removeMovie(Movie movie) throws Exception {
        try {
            if (moviesLibrary.stream().anyMatch(s -> s.getTitle().equalsIgnoreCase(movie.getTitle()))) {
                moviesLibrary.remove(moviesLibrary.stream().filter(s -> s.getTitle().equalsIgnoreCase(movie.getTitle())).findFirst().get());
                shedules.values().forEach(x -> x.getSeances().removeIf(a -> a.getMovie().equals(movie)));
            }

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No element");
        }

    }

    public void addSeance(Seance seance, String day) throws DateExeption {
        if (isDayExist(day)) {
            if (seance.getStartTime().getHour() * 60 + seance.getStartTime().getMin() >= open.getHour() * 60 + open.getMin()
                    && (seance.getEndTime().getHour() * 60 + seance.getEndTime().getMin() <= close.getHour() * 60
                    + close.getMin())) {

                Optional<Map.Entry<Days, Schedule>> findAny = shedules.entrySet().stream()
                        .filter(e -> e.getKey().name().equalsIgnoreCase(day)).findAny();
                if (!findAny.isPresent()) {
                    shedules.put(Days.valueOf(day.toUpperCase()), new Schedule());
                    shedules.entrySet().stream().filter(e -> e.getKey().name().equalsIgnoreCase(day)).findAny()
                            .get().getValue().getSeances().add(seance);
                } else {
                    findAny.get().getValue().addSeance(seance);
                }
            }
        } else
            throw new DateExeption("Not normal day");

    }


    public void removeSeance(Seance seance, String day) {


        shedules.entrySet().stream().forEach(x ->
        {
            if (x.getKey().name().equalsIgnoreCase(day)) {
                x.getValue().removeSeances(seance);
            }
        });

        /*if(isDayExist(day)){
            shedules.entrySet().stream().forEach(e->{
                if(e.getValue().equals(seance)){
                    e.getValue().removeSeances(seance);
                }
            });
        }*/
        /*if(isDayExist(day)){
            shedules.entrySet().stream().forEach(e->{
                if(e.getKey().toString().equalsIgnoreCase(day)) {
                    e.getValue().
                }
            });
        }*/

    }


    public boolean isDayExist(String day) throws DateExeption {
        Days[] days = Days.values();
        for (Days tmp : days) {
            if (tmp.name().equalsIgnoreCase(day))
                return true;

        }
        throw new DateExeption("None");


    }


    public void showSea(String day) {
        for (Map.Entry<Days, Schedule> entry : shedules.entrySet()) {
            if (entry.getKey().name().equalsIgnoreCase(day)) {
                System.out.println(entry.getValue());
            }
        }
    }

    public void showMovies() {
        moviesLibrary.stream().forEach(x -> System.out.println(x.toString()));
    }

    public void showSeances() {
        shedules.entrySet().stream().forEach(x -> System.out.println(x.getValue()));
    }
    public void times(){
        System.out.println("Now cinema open in : " +getOpen().getHour() + " hours, " + getOpen().getMin() +" min.");
    }
    public void timesClos(){
        System.out.println("Now cinema close in : " +getOpen().getHour() + " hours, " + getOpen().getMin() +" min.");
    }



}