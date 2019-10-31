package dao;

import models.Clan;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oClanDao implements ClanDao {
    private final Sql2o sql2o;
    public Sql2oClanDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
//Add a clan to the database
    @Override
    public void add(Clan clan) {
        String sql = "INSERT INTO clan(clanname, total_members, clanDescription, users_id) values (:clanname, :total_members, :clanDescription, :users_id)";
        try(Connection con = DB.sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(clan)
                    .executeUpdate()
                    .getKey();
            clan.setClanId(id);
        } catch (Sql2oException ex){
            System.out.println("Failed to Add: "+ex);
        }
    }
// Fetch clans from the database
    @Override
    public List<Clan> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM clan")
                    .executeAndFetch(Clan.class);
        }
    }
//Find Clan
    @Override
    public Clan findById(int ClanId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM clan WHERE id = :id")
                    .addParameter("ClanId", ClanId)
                    .executeAndFetchFirst(Clan.class);
        }
    }
//Update Clan
    @Override
    public void update(String clanName, int total_members, String clanDescription, int user_id) {
        String sql = "UPDATE clan SET (clanname, total_members, clanDescription, users_id) = (:clanname, :total_members, :clanDescription, :users_id) WHERE id=:id"; //CHECK!!!
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("clanname", clanName)
                    .addParameter("total_members", total_members)
                    .addParameter("clanDescription", clanDescription)
                    .addParameter("users_id", user_id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
//delete a clan from database
        @Override
        public void deleteById(int ClanId) {
            String sql = "DELETE from clan WHERE id = :id"; //raw sql
            try (Connection con = sql2o.open()) {
                con.createQuery(sql)
                        .addParameter("ClanId", ClanId)
                        .executeUpdate();
            } catch (Sql2oException ex){
                System.out.println(ex);
            }
        }
    @Override
    public void clearAll() {
        String sql = "DELETE from clan";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}