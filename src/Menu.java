import Exeptions.TimeExeption;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    Scanner in = new Scanner(System.in);
    Cinema multi;

    {
        try {
            multi = new Cinema(new Time(0,10),new Time(0,24));

        } catch (TimeExeption timeExeption) {
            timeExeption.printStackTrace();
        }
    }

    public static void menu(){
        System.out.println("Enter 1 to add movie");
        System.out.println("Enter 2 to delete movie");
        System.out.println("Enter 3 to add seance");
        System.out.println("Enter 4 to show seances");
        System.out.println("Enter 5 to delete seance");
        System.out.println("Enter 6 to set open time");
        System.out.println("Enter 7 to set close time");
        System.out.println("Enter 8 to show schedule for one day");


    }
   public void start() throws TimeExeption,Exception {
       boolean m = true;
       multi.addMovie(new Movie("ASR",new Time(45,2)));
       multi.addMovie(new Movie("R",new Time(45,2)));
       multi.addMovie(new Movie("A",new Time(45,2)));

       Seance s1 = new Seance(new Movie("ASR",new Time(45,1)),new Time(50,12));
       Seance s2 = new Seance(new Movie("R",new Time(45,1)),new Time(0,16));

       multi.addSeance(s1,"MONDAY");
       multi.addSeance(s2,"tuesday");
       multi.addSeance(s1,"tuesday");


       while (m){
           menu();
           String str = in.next();
           switch (str){
               case "1":{
                   System.out.println("Please enter name of movie");
                   String name = in.next();
                   System.out.println("Please enter min and hours");
                   Time duration = new Time(in.nextInt(),in.nextInt());
                   Movie movie = new Movie(name,duration);
                   multi.addMovie(movie);
                   multi.showMovies();
                   break;
               }
               case "2":{
                   multi.showMovies();
                   System.out.println("Please enter what movie do uou want delete");
                   String name = in.next();
                   multi.removeMovie(multi.getMoviesLibrary().stream().filter(x->x.getTitle().equalsIgnoreCase(name)).findFirst().get());
                   multi.showMovies();
                   break;
               }
               case "3":{
                   System.out.println("Please enter day ");
                   String day = in.next();
                   if (multi.isDayExist(day)) {
                       multi.showSea(day);
                       System.out.println("Please enter what movie do you want add to seance");
                       multi.showMovies();
                       String name = in.next();
                       Movie tmp = multi.getMoviesLibrary().stream().filter(x->x.getTitle().equalsIgnoreCase(name)).findFirst().get();
                       System.out.println("Please enter min and hours when the movie is started");
                       Time duration = new Time(in.nextInt(),in.nextInt());
                       Seance a = new Seance(tmp,duration);
                       multi.addSeance(a,day);
                       multi.showSea(day);
                   }else {
                       System.out.println("The day doesn't exist");
                   }

                   break;
               }
               case "4":{
                   multi.showSeances();
                   break;
               }
               case "5": {
                   System.out.println("Please enter day ");
                   String day = in.next();
                   multi.showMovies();
                   System.out.println("Please enter movie ");
                   String mov = in.next();
                   if (multi.isDayExist(day)) {
                       multi.showSea(day);
                       Optional<Map.Entry<Days, Schedule>> findAny = multi.getShedules().entrySet().stream()
                               .filter(e -> e.getKey().toString().equalsIgnoreCase(day)).findAny();
                       if (findAny.isPresent()) {
                           Seance seance = findAny.get().getValue().getSeances().stream()
                                   .filter(x -> x.getMovie().getTitle().equalsIgnoreCase(mov)).findAny().get();
                           multi.removeSeance(seance, day);
                       }
                       multi.showSea(day);

                   }
                   break;
               }
               case "6":{
                    multi.times();
                   System.out.println("Enter when must open hours/min");
                   multi.getOpen().setHour(in.nextInt());
                   multi.getOpen().setMin(in.nextInt());
                   multi.times();
                   break;
               }
               case "7":{
                   multi.timesClos();
                   System.out.println("Enter when must open hours/min");
                   multi.getClose().setHour(in.nextInt());
                   multi.getClose().setMin(in.nextInt());
                   multi.timesClos();
                   break;
               }
               case "8":{
                   System.out.println("Choose day when you need to delete seance");
                   System.out.println(Arrays.toString(Days.values()));
                   String dayStr = in.next();
                   multi.getShedules().get(Days.valueOf(dayStr)).getSeances().forEach(System.out::println);

               }
               }

           }

       }






   public Movie choseMovie(){
        String name = in.nextLine();
        Movie tmp = multi.getMoviesLibrary().stream().filter(x->x.getTitle().equalsIgnoreCase(name)).findFirst().get();
        return tmp;

   }



}

