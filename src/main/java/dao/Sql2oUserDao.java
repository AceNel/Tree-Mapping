package dao;

import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao {
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
            try {
                throw new UnsupportedOperationException("Username Taken");
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }
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
        String sql = "UPDATE users SET password = :password WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("password",user.getPassword())
                    .addParameter("id",user.getId())
                    .executeUpdate();
        }
    }

    @Override
    public Users findUser(int userId) {
        String sql = "SELECT * FROM users WHERE id = :id;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",userId)
                    .executeAndFetchFirst(Users.class);
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
