package models;

import security.Hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

public class Users {
    private int id;
    private String username;
    private String password;
    private byte[] salt;
    private String email;
    private String display_name;
    private int trees_planted;
    private boolean inClan;
    private String clan_name;

    public Users(String username, String password) throws Exception {
        this.username = username.trim();
        this.password = password.trim();
        this.display_name = this.username;
        this.trees_planted = 0;
        this.inClan = false;
        this.clan_name = "none";
        this.email = "none";
        this.salt = Hashing.salt();
        securePassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return trees_planted == users.trees_planted &&
                inClan == users.inClan &&
                Objects.equals(username, users.username) &&
                Objects.equals(password, users.password) &&
                Objects.equals(email, users.email) &&
                Objects.equals(display_name, users.display_name) &&
                Objects.equals(clan_name, users.clan_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, display_name, trees_planted, inClan, clan_name);
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
        if(!username.isEmpty()){
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(!password.isEmpty()){
            this.password = password;
        }
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

    public int getTrees_planted() {
        return trees_planted;
    }

    public void setTrees_planted(int trees_planted) {
        this.trees_planted = trees_planted;
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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void securePassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String saltedPass = Hashing.generateHash(this.salt,this.password);
        setPassword(saltedPass);
    }

    public static String verifyPassword(byte[] salt, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return Hashing.verifyHash(salt,password);
    }

    public void plantTree(){
        this.trees_planted++;
    }

}
