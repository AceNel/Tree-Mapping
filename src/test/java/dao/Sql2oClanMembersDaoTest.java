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

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void addClanMembers() throws Exception{
        Users user = newUser();
        Clan clan = newClan();
        userDao.joinClan(user,clan);
    }



    /*------------HELPERS------------*/
    private Users newUser() throws Exception{
        Users user = new Users("username","asdf");
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