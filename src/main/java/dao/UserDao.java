package dao;

import models.Users;

public interface UserDao {
    //create
    void add(Users user);

    //read

    //update
    void updateUsername(Users user);
    void updatePassword(Users user);

    //delete

}
