package dao;

import org.sql2o.Sql2o;

public class DB {
    // TODO: 10/29/19 Change database credentials after merging
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/tree_mapping","nick","00000000");
}
