package dao;

import models.Clan;

import java.util.List;

public interface ClanDao {

    //create
    void add(Clan clan);

    //read
    List<Clan> getAll();
    Clan findById(int ClanId);

    //update
    void update(String clanName, int total_members, String clanDescription, int users_id);

    //delete
    void deleteById(int ClanId);
    void clearAll();


}
