package dao;

import models.Users;

import java.util.List;

public interface UserDao {
    //create
    void add(Users user);

    //read
    byte[] getUserSalt(String username);
    List<Users> allUsers();
    Users findUser(int userId);

    //update
    void updateUsername(Users user);
    void updatePassword(Users user);

    //delete

}
