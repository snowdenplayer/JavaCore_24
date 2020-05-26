import java.util.Set;
import java.util.TreeSet;

public class Schedule implements Comparable<Schedule> {
    private Set<Seance> seances = new TreeSet<>();

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
    }
    public void addSeance(Seance seance){
            seances.add(seance);
    }
    public void removeSeances(Seance seance){

        seances.remove(seance);
    }
    @Override
    public String toString() {
        return "\n" + seances + "\n";
    }


    @Override
    public int compareTo(Schedule o) {
        return 0;
    }
}
