package dao;

import models.Clan;
import models.Tree;
import models.Users;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oClanMembersDaoTest {
    private Sql2oUserDao userDao = new Sql2oUserDao();
    private SQL2oTreeDao treeDao = new SQL2oTreeDao();
    private Sql2oClanDao clanDao = new Sql2oClanDao();
    private Sql2oClanMembersDao clanMembersDao = new Sql2oClanMembersDao();


    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void addClanMembers() throws Exception{
        Users user = newUser();
        Clan clan = newClan();
        userDao.joinClan(user,clan);
        assertTrue(user.isInClan());
    }

    @Test
    public void getClanMembers() throws Exception{
        Users user = newUser();
        Users user2 = newUser2();
        Clan clan = newClan();
        userDao.joinClan(user,clan);
        userDao.joinClan(user2,clan);
        assertEquals(2,clanDao.getClanMembers(clan).size());
    }



    /*------------HELPERS------------*/
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

    private Clan newClan() throws Exception{
        Clan clan = new Clan("Area 51",0,"We lead others follow");
        clanDao.add(clan);
        return clan;
    }

    private Tree newTree(){
        Tree tree = new Tree("Moringa","Arabuko sokoke","001","002");
        treeDao.addTree(tree);
        return tree;
    }

}