package dao;

import models.Clan;
import models.Tree;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao {
    SQL2oTreeDao treeDao = new SQL2oTreeDao();

    public Sql2oUserDao(){}

    @Override
    public void add(Users user) {
        if(!isUsernameTaken(user.getUsername())){
            String sql = "INSERT INTO users(username, password, salt, email, display_name, trees_planted,inclan,clan_name) values (:username, :password, :salt, :email, :display_name, :trees_planted, :inClan, :clan_name);";
            try(Connection con = DB.sql2o.open()){
                int id = (int) con.createQuery(sql,true)
                        .bind(user)
                        .executeUpdate()
                        .getKey();
                user.setId(id);
            } catch (Sql2oException ex){
                System.out.println("Failed to Add: "+ex);
            }
        }
        else {
            throw new UnsupportedOperationException("Username Taken");
        }
    }

    @Override
    public byte[] getUserSalt(String username) {
        String sql = "SELECT salt FROM users WHERE username = :username;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("username",username)
                    .executeAndFetchFirst(byte[].class);
        }
    }

    @Override
    public List<Users> allUsers() {
        String sql = "SELECT * FROM users;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Users.class);
        }
    }

    @Override
    public Users findUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = :username;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("username",username)
                    .executeAndFetchFirst(Users.class);
        }
    }

    @Override
    public Users findUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",userId)
                    .executeAndFetchFirst(Users.class);
        }
    }

    @Override
    public String findPasswordById(int userId) {
        String sql = "SELECT password FROM users WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",userId)
                    .executeAndFetchFirst(String.class);
        }
    }

    @Override
    public void updateUsername(Users user) {
        String sql = "UPDATE users SET username = :username WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
             con.createQuery(sql)
                     .addParameter("username",user.getUsername())
                     .addParameter("id",user.getId())
                     .executeUpdate();
        }
    }

    @Override
    public void updatePassword(Users user) {
        String sql = "UPDATE users SET password = :password, salt = :salt WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("password",user.getPassword())
                    .addParameter("salt",user.getSalt())
                    .addParameter("id",user.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void updateEmail(Users user) {
        String sql = "UPDATE users SET email = :email WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("email",user.getEmail())
                    .addParameter("id",user.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void updateDisplayName(Users user) {
        String sql = "UPDATE users SET display_name = :display_name WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("display_name",user.getDisplay_name())
                    .addParameter("id",user.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void updateTreesPlanted(Users user) {
        String sql = "UPDATE users SET trees_planted = :trees_planted WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("trees_planted",user.getTrees_planted())
                    .addParameter("id",user.getId())
                    .executeUpdate();

            // TODO: 10/30/19 branch: IF user is in clan, update tree_pts too 
        }
    }

    @Override
    public void joinClan(Users user, Clan clan) {
        String sql = "UPDATE users SET inclan = 'true', clan_name = :clan_name WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("clan_name",user.getClan_name())
                    .addParameter("id",user.getId())
                    .executeUpdate();
            // TODO: 10/30/19 ADD clan name parameter here!!
        }
    }

    @Override
    public int getTreesPlanted(int userId) {
        String sql = "SELECT trees_planted FROM users WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",userId)
                    .executeAndFetchFirst(Integer.class);
        }
    }

    @Override
    public void plantNewTree(Users user, Tree tree, String latitude, String longitude){
        user.plantTree();
        updateTreesPlanted(user);
        treeDao.userPlantTree(user.getId(),tree.getId(),latitude,longitude);
    }

    /* ------- PRIVATE METHODS -------*/
    private boolean isUsernameTaken(String username){
        String sql = "SELECT username FROM users;";
        try(Connection con = DB.sql2o.open()){
            List<String> allUsernames = con.createQuery(sql)
                    .executeAndFetch(String.class);

            for(String name:allUsernames){
                if(username.equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

}
