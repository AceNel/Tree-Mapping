package dao;

import models.Tree;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

public class SQL2oTreeDao implements TreeDao {
    public SQL2oTreeDao(){}

    @Override
    public void addTree(Tree tree) {
        String sql = "INSERT INTO trees(name, species);";
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



}
