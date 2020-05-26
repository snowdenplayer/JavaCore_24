import Exeptions.TimeExeption;

public class Application {
    public static void main(String[] args) throws TimeExeption, Exception {


        /*Movie skubidu = new Movie("Sku-Bi-Du",new Time(40,1));
        Movie skubidu2 = new Movie("Sku-Bi-Du-2",new Time(30,1));

        Seance skubi = new Seance(skubidu,new Time(40,11));
        Seance skubi2 = new Seance(skubidu2,new Time(40,15));

        Schedule schedule = new Schedule();
        schedule.addSeance(skubi);
        schedule.addSeance(skubi2);
        Cinema port = new Cinema(new Time(0,11),new Time(0,24));
        port.addMovie(skubidu);
        port.addMovie(skubidu2);
        port.addSeance(skubi,"MONDAY");
        port.showMovies();
        port.showSeances();
*/
        Menu menu = new Menu();
        menu.start();
    }
}
