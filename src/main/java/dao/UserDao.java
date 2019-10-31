package dao;

import models.Clan;
import models.Tree;
import models.Users;

import java.util.List;

public interface UserDao {
    //create
    void add(Users user);
    void joinClan(Users user, Clan clan);
    void plantNewTree(Users user, Tree tree, String latitude, String longitude);

    //read
    byte[] getUserSalt(String username);
    List<Users> allUsers();
    Users findUserById(int userId);
    int getTreesPlanted(int userId);
    String findPasswordById(int userId);
    Users findUserByUsername(String username);


    //update
    void updateUsername(Users user);
    void updatePassword(Users user);
    void updateEmail(Users user);
    void updateDisplayName(Users user);
    void updateTreesPlanted(Users user);


    //delete

}
