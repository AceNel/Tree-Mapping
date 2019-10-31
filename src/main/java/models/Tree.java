package models;

import java.util.Objects;

public class Tree {
    private String name;
    private String species;
    private String latitude;
    private String longitude;
    private int id;

    public Tree(String name, String species, String latitude, String longitude) {
        this.name = name;
        this.species = species;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(name, tree.name) &&
                Objects.equals(species, tree.species) &&
                Objects.equals(latitude, tree.latitude) &&
                Objects.equals(longitude, tree.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, species, latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
