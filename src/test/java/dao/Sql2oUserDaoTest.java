package dao;

import models.Users;
import org.junit.Rule;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    Sql2oUserDao userDao = new Sql2oUserDao();

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void getId_userIsInsertedIntoDatabase_int() throws Exception {
        Users user = newUser();
        assertNotEquals(0,user.getId());
    }

    @Test
    public void verifyHashedPassword_true() throws Exception {
        Users user = newUser();
        byte[] salt = userDao.getUserSalt("username");
        String verifiedPass = Users.verifyPassword(salt,"asdf");
        assertEquals(user.getPassword(),verifiedPass);
    }

    @Test
    public void uniqueUsername_cannotRegisterWithExistingUsername() throws Exception {
        Users user = newUser();
        Users user2 = newUser();
        assertEquals(1,userDao.allUsers().size());
    }

    @Test
    public void findUser_int() throws Exception{
        Users user = newUser();
        Users user2 = newUser2();
        assertEquals(2,userDao.allUsers().size());
        assertTrue(user2.equals(userDao.allUsers().get(1)));
    }






    //Helper
    private Users newUser() throws Exception{
        Users user = new Users("username","asdf");
        userDao.add(user);
        return user;
    }

    private Users newUser2() throws Exception {
        Users user = new Users("anotherusername","1234");
        userDao.add(user);
        return user;
    }



}