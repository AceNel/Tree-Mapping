package dao;

import models.Users;

public interface UserDao {
    //create
    void add(Users user);

    //read
    byte[] getUserSalt(String username);

    //update
    void updateUsername(Users user);
    void updatePassword(Users user);

    //delete

}
