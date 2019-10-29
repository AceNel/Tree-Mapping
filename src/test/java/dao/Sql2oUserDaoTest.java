package dao;

import models.Users;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    Sql2oUserDao userDao = new Sql2oUserDao();
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void getId_userIsInsertedIntoDatabase_int(){
        Users user = newUser();
        assertNotEquals(0,user.getId());
    }

    //Helper
    private Users newUser(){
        Users user = new Users("username","asdf");
        userDao.add(user);
        return user;
    }
}