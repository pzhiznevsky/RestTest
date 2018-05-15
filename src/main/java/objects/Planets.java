package objects;

import java.util.List;
import java.util.Objects;

public class Planets extends BaseObject {

    private String count;
    private String previous;
    private String next;
    private List<Planet> results;

    public Planets(String key){
        super();
        Planets planets = (Planets)testData.getTestData(key, type);
        this.count = planets.getCount();
        this.previous = planets.getPrevious();
        this.next = planets.getNext();
        this.results = planets.getResults();
    }

    public Planets(String count, String previous, String next, List<Planet> results) {
        super();
        this.count = count;
        this.previous = previous;
        this.next = next;
        this.results = results;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Planet> getResults() {
        return results;
    }

    public void setResults(List<Planet> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planets planets = (Planets) o;
        return Objects.equals(count, planets.count) &&
                Objects.equals(previous, planets.previous) &&
                Objects.equals(next, planets.next) &&
                Objects.equals(results, planets.results);
    }

    @Override
    public int hashCode() {

        return Objects.hash(count, previous, next, results);
    }

    @Override
    public String toString() {
        return "\nPlanets{" +
                "\n\tcount='" + count + '\'' +
                ", \n\tprevious='" + previous + '\'' +
                ", \n\tnext='" + next + '\'' +
                ", \n\tresults=" + results +
                '}';
    }

}
