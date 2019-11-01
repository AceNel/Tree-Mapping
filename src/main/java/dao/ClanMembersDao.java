package dao;

import models.Clan;
import models.ClanMembers;
import models.Tree;
import models.Users;

import java.util.List;

public interface ClanMembersDao {
    //create
    void addClanMembers(Users user, Clan clan);
    //void clanAddClanMember(int user_id, int clan_id, int challenge_pts, int tree_pts, int average_pts);

    //read
    //Tree findClanMemberById(int id);
    //List<ClanMembers> getAllClanMembers();
    //List<ClanMembers> getTreesPlantedByUser(int userId);

    //update

    //delete

}