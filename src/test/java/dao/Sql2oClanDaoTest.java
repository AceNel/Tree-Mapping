package dao;

import models.Clan;
import models.Users;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oClanDaoTest {
    private Sql2oUserDao userDao = new Sql2oUserDao();
    private Sql2oClanDao clanDao = new Sql2oClanDao();

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void getClanId_clanIsInsertedIntoDatabase_int() throws Exception {
        Clan clan = newClan();
        System.out.println( clan.getClanId());
        assertNotEquals(0, clan.getClanId());
    }
    @Test
    public void getClanName() throws Exception{
        Clan clan = newClan();
        assertEquals("Area 51", clan.getClanName());
    }

    @Test
    public void getClanDescription() throws Exception{
        Clan clan = newClan();
        assertEquals("We lead others follow",clan.getClanDescription());
    }




    //Helper
    private Clan newClan() throws Exception{
        Clan clan = new Clan("Area 51",1,"We lead others follow");
        clanDao.add(clan);
        return clan;
    }

    //Helper
    private Users newUser() throws Exception{
        Users user = new Users("username","atyf");
        userDao.add(user);
        return user;
    }

    private Users newUser2() throws Exception {
        Users user = new Users("anotherusername","1234");
        userDao.add(user);
        return user;
    }



}