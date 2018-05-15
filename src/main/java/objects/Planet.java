package objects;

import java.util.List;
import java.util.Objects;

public class Planet extends BaseObject {

    private String name;
    private String rotation_period;
    private String orbital_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    private List<String> residents;
    private List<String> films;
    private String created;
    private String edited;
    private String url;

    public Planet(String key){
        super();
        Planet planet = (Planet)testData.getTestData(key, type);
        this.name = planet.getName();
        this.rotation_period = planet.getRotation_period();
        this.orbital_period = planet.getOrbital_period();
        this.diameter = planet.getDiameter();
        this.climate = planet.getClimate();
        this.gravity = planet.getGravity();
        this.terrain = planet.getTerrain();
        this.surface_water = planet.getSurface_water();
        this.population = planet.getPopulation();
        this.residents = planet.getResidents();
        this.films = planet.getFilms();
        this.created = planet.getCreated();
        this.edited = planet.getEdited();
        this.url = planet.getUrl();
    }

    public Planet(String name, String rotation_period, String orbital_period, String diameter, String climate, String gravity, String terrain, String surface_water, String population, List<String> residents, List<String> films, String created, String edited, String url) {
        super();
        this.name = name;
        this.rotation_period = rotation_period;
        this.orbital_period = orbital_period;
        this.diameter = diameter;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
        this.surface_water = surface_water;
        this.population = population;
        this.residents = residents;
        this.films = films;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRotation_period() {
        return rotation_period;
    }

    public void setRotation_period(String rotation_period) {
        this.rotation_period = rotation_period;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public void setOrbital_period(String orbital_period) {
        this.orbital_period = orbital_period;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public void setSurface_water(String surface_water) {
        this.surface_water = surface_water;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(name, planet.name) &&
                Objects.equals(rotation_period, planet.rotation_period) &&
                Objects.equals(orbital_period, planet.orbital_period) &&
                Objects.equals(diameter, planet.diameter) &&
                Objects.equals(climate, planet.climate) &&
                Objects.equals(gravity, planet.gravity) &&
                Objects.equals(terrain, planet.terrain) &&
                Objects.equals(surface_water, planet.surface_water) &&
                Objects.equals(population, planet.population) &&
                Objects.equals(residents, planet.residents) &&
                Objects.equals(films, planet.films) &&
                Objects.equals(created, planet.created) &&
                Objects.equals(edited, planet.edited) &&
                Objects.equals(url, planet.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population, residents, films, created, edited, url);
    }

    @Override
    public String toString() {
        return "\n\t\tPlanet{" +
                "\n\t\t\tname='" + name + '\'' +
                ", \n\t\t\trotation_period='" + rotation_period + '\'' +
                ", \n\t\t\torbital_period='" + orbital_period + '\'' +
                ", \n\t\t\tdiameter='" + diameter + '\'' +
                ", \n\t\t\tclimate='" + climate + '\'' +
                ", \n\t\t\tgravity='" + gravity + '\'' +
                ", \n\t\t\tterrain='" + terrain + '\'' +
                ", \n\t\t\tsurface_water='" + surface_water + '\'' +
                ", \n\t\t\tpopulation='" + population + '\'' +
                ", \n\t\t\tresidents=" + residents +
                ", \n\t\t\tfilms=" + films +
                ", \n\t\t\tcreated='" + created + '\'' +
                ", \n\t\t\tedited='" + edited + '\'' +
                ", \n\t\t\turl='" + url + '\'' +
                '}';
    }

}
