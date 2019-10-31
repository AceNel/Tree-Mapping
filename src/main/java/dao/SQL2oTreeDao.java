package dao;

import models.Tree;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class SQL2oTreeDao implements TreeDao {
    public SQL2oTreeDao(){}

    @Override
    public void addTree(Tree tree) {
        String sql = "INSERT INTO trees(name, species, latitude, longitude) values(:name, :species, :latitude, :longitude);";
        try(Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(tree)
                    .executeUpdate()
                    .getKey();
            tree.setId(id);
        } catch (Sql2oException ex){
            System.out.println("Failed to Add: "+ex);
        }
    }

    @Override
    public void userPlantTree(int userId, int treeId, String latitude, String longitude){
        String sql = "INSERT INTO trees_planted(userid, treeid, latitude, longitude) values (:userid, :treeid, :latitude, :longitude);";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("userid",userId)
                    .addParameter("treeid",treeId)
                    .addParameter("latitude",latitude)
                    .addParameter("longitude",longitude)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Failed to plant tree: "+ex);
        }
    }

    @Override
    public List<Tree> getAllTrees() {
        String sql = "SELECT * FROM trees;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Tree.class);
        }
    }

    @Override
    public Tree findTreeById(int treeId) {
        String sql = "SELECT * FROM trees WHERE id = :id";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", treeId)
                    .executeAndFetchFirst(Tree.class);
        }
    }

    @Override
    public List<Tree> getTreesPlantedByUser(int userId) {
        List<Tree> allTrees = new ArrayList<>();

        String sql = "SELECT treeid FROM trees_planted WHERE userid = :userid;";
        try(Connection con = DB.sql2o.open()){
             List<Integer> treeIds = con.createQuery(sql)
                    .addParameter("userid",userId)
                    .executeAndFetch(Integer.class);

             String matchingTrees = "SELECT * FROM trees WHERE id = :treeId;";
             for(int id:treeIds){
                 allTrees.add(
                         con.createQuery(matchingTrees)
                                 .addParameter("treeId",id)
                                 .executeAndFetchFirst(Tree.class));
             }
        } catch (Sql2oException ex){
            System.out.println("Couldn't recover trees: " +ex);
        }
        return allTrees;
    }


}
