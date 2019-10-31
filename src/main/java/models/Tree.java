package models;

import java.util.Objects;

public class Tree {
    private String name;
    private String species;
    private int id;

    public Tree(String name, String species) {
        this.name = name;
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(name, tree.name) &&
                Objects.equals(species, tree.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, species);
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
}
