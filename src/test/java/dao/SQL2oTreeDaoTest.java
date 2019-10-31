package dao;

import models.Tree;
import models.Users;
import org.eclipse.jetty.server.Authentication;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SQL2oTreeDaoTest {
    private Sql2oUserDao userDao = new Sql2oUserDao();
    private SQL2oTreeDao treeDao = new SQL2oTreeDao();

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void treeGetsId(){
        Tree tree = newTree();
        System.out.println(tree.getId());
        assertNotEquals(0,tree.getId());
    }

    @Test
    public void userCanPlantTree_true() throws Exception{
        Users user = newUser();
        System.out.println("Tree counter: " +user.getTrees_planted());
        Tree tree = newTree();
        userDao.plantNewTree(user,tree,tree.getLatitude(),tree.getLongitude());
        assertEquals(1,user.getTrees_planted());
    }

    @Test
    public void getAllTrees()throws Exception{
        Tree tree = newTree();
        Tree tree1 = newTree2();
        assertEquals(2,treeDao.getAllTrees().size());
    }

    @Test
    public void getTreePlantedByUser() throws Exception{
        Users user = newUser();
        Tree tree = newTree();
        Tree tree1 = newTree2();
        userDao.plantNewTree(user,tree,tree.getLatitude(),tree.getLongitude());
        userDao.plantNewTree(user,tree1,tree1.getLatitude(),tree1.getLongitude());
        assertEquals(2,treeDao.getTreesPlantedByUser(user.getId()).size());
    }

    @Test
    public void getCoordinatesOfTree() throws Exception{
        Users user = newUser();
        Tree tree = newTree();
        userDao.plantNewTree(user,tree,tree.getLatitude(),tree.getLongitude());
        assertEquals("002",treeDao.findTreeById(tree.getId()).getLongitude());
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

    private Tree newTree(){
        Tree tree = new Tree("Moringa","Arabuko sokoke","001","002");
        treeDao.addTree(tree);
        return tree;
    }

    private Tree newTree2(){
        Tree tree = new Tree("Mwarubaini","Sijui species","003","004");
        treeDao.addTree(tree);
        return tree;
    }

}