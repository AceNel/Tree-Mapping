package dao;

import models.Users;
import org.junit.Rule;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private Sql2oUserDao userDao = new Sql2oUserDao();

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
    public void getAllUsers() throws Exception{
        Users user = newUser();
        Users user2 = newUser2();
        assertEquals(2, userDao.allUsers().size());
    }

    @Test
    public void findParticularUserById() throws Exception{
        Users user = newUser();
        Users userFound = userDao.findUserById(user.getId());
        userFound.setPassword(userDao.findPasswordById(user.getId()));
        assertTrue(user.equals(userFound));
    }

    @Test
    public void findUserByUsername() throws Exception{
        Users user = newUser();
        Users userFound = userDao.findUserByUsername("username");
        assertTrue(user.equals(userFound));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void uniqueUsername_cannotRegisterWithExistingUsername() throws Exception {
        Users user = newUser();
        Users user2 = newUser();
        assertEquals(1,userDao.allUsers().size());
    }

    @Test
    public void uniqueUsername_wrapWithTryCatch() throws Exception {
        Users user = newUser();
        try{
            Users user2 = newUser();
        } catch (UnsupportedOperationException ex){
            System.out.println(ex);
        }
        assertEquals(1,userDao.allUsers().size());
    }

    @Test
    public void userPlantTree_getNumberOfTreesPlanted_int() throws Exception{
        Users user = newUser();
        user.plantTree();
        user.plantTree();
        userDao.updateTreesPlanted(user);
        assertEquals(2,userDao.getTreesPlanted(user.getId()));
    }

    @Test
    public void updateUsername() throws Exception{
        Users user = newUser();
        user.setUsername("notUsername");
        userDao.updateUsername(user);
        Users foundUser = userDao.findUserById(user.getId());
        assertNotEquals("username",foundUser.getUsername());
        assertEquals("notUsername",foundUser.getUsername());
    }

    @Test
    public void updatePassword() throws Exception {
        Users user = newUser();
        byte[] oldSalt = user.getSalt();
        String oldPassword = user.getPassword();
        user.updateSaltedPassword("000000");
        userDao.updatePassword(user);
        Users foundUser = userDao.findUserById(user.getId());
        assertNotEquals(oldSalt,foundUser.getSalt());
        assertNotEquals(oldPassword,foundUser.getPassword());
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