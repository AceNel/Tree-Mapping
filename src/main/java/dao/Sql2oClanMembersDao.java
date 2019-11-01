package dao;

import models.Clan;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oClanMembersDao implements ClanMembersDao {
    Sql2oClanDao clanDao = new Sql2oClanDao();
    public Sql2oClanMembersDao() {
    }

    @Override
    public void addClanMembers(Users user, Clan clan) {
        String sql = "INSERT INTO clan_members(user_id,clan_id,challenge_pts,tree_pts,average_pts) values (:userid,:clanid,0,0,0);";
        try(Connection con = DB.sql2o.open()){
                con.createQuery(sql)
                    .addParameter("userid",user.getId())
                    .addParameter("clanid",clan.getClanId())
                    .executeUpdate();

            //Update that clan
            clanDao.updateTotalMembers(clan);

        } catch (Sql2oException ex){
            System.out.println("Failed to Add: "+ex);
        }
    }




} //End
