package models;

public class Users {
    private int id;
    private String username;
    private String password;
    private String email;
    private String display_name;
    private int total_trees_planted;
    private boolean inClan;
    private String clan_name;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        this.display_name = username;
        this.total_trees_planted = 0;
        this.inClan = false;
        this.clan_name = "none";
        this.email = "none";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int getTotal_trees_planted() {
        return total_trees_planted;
    }

    public void setTotal_trees_planted(int total_trees_planted) {
        this.total_trees_planted = total_trees_planted;
    }

    public boolean isInClan() {
        return inClan;
    }

    public void setInClan(boolean inClan) {
        this.inClan = inClan;
    }

    public String getClan_name() {
        return clan_name;
    }

    public void setClan_name(String clan_name) {
        this.clan_name = clan_name;
    }
}
