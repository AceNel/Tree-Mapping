package models;

import java.util.ArrayList;
import java.util.Objects;

public class Clan {
    private String clanName;
    private int clanId;
    private int total_members;
    private String clanDescription;
    private int user_id;
    private String members;
    private static ArrayList<Clan> instances = new ArrayList<Clan>();
    private int clanSize;

    public Clan(String clanName, int total_members, String clanDescription) {
        this.clanName = clanName;
        this.total_members = total_members;
        this.clanDescription = clanDescription;
        this.clanSize = clanSize;
        instances.add(this);

    }

    public String getClanName() {
        return clanName;
    }

    public int getClanId() {
        return clanId;
    }

    public int getTotal_members() {
        return total_members;
    }

    public String getClanDescription() {
        return clanDescription;
    }

    public int getUser_id() {
        return user_id;
    }

    public static ArrayList<Clan> getInstances() {
        return instances;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public void setClanId(int clanId) {
        this.clanId = clanId;
    }

    public void setTotal_members(int total_members) {
        this.total_members = total_members;
    }

    public void setClanDescription(String clanDescription) {
        this.clanDescription = clanDescription;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setclanId(int clanId) {
        this.clanId = clanId;
    }

    public void setClanMembers(Clan newClan) {
        instances.add(newClan);
    }

    public static void clearAllClans(){ instances.clear(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clan clan = (Clan) o;
        return clanId == clan.clanId &&
                total_members == clan.total_members &&
                user_id == clan.user_id &&
                clanSize == clan.clanSize &&
                Objects.equals(clanName, clan.clanName) &&
                Objects.equals(clanDescription, clan.clanDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clanName, clanId, total_members, clanDescription, user_id, clanSize);
    }
// Our 5 predefined clans
    public static Clan setUpNewClan() {
        return new Clan("Area 51",5,"We lead others follow");
    }
    public static Clan setUpNewClan3(){
        return new Clan("Moringa",10,"We are about tech and trees");
    }

    public static Clan setUpNewClan4(){
        return new Clan("UoN",10,"We are about tech and trees");
    }

    public static Clan setUpNewClan5(){
        return new Clan("Andela",10,"We are about tech and trees");
    }

    public static Clan setUpNewClan6(){
        return new Clan("K.U",10,"We  top the charts");
    }
//TODO: I need to add users to predefined classes using their user ids or names

}