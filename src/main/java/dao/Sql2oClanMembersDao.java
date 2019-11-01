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

    public void updateUserTreePoints(Users user){
        String sql_treePts = "SELECT tree_pts FROM clan_members WHERE user_id = :userid";
        String sql_challengePts = "SELECT challenge_pts FROM clan_members WHERE user_id = :userid";
        try(Connection con = DB.sql2o.open()){
            Integer treePts = con.createQuery(sql_treePts)
                    .addParameter("userid",user.getId())
                    .executeAndFetchFirst(Integer.class);
            Integer challengePts = con.createQuery(sql_challengePts)
                    .addParameter("userid",user.getId())
                    .executeAndFetchFirst(Integer.class);
            float avgPts = 0;

            /*--------- Logic ---------*/
            treePts+=1;
            avgPts = (float) (treePts+challengePts)/2;

            /*--------- Update ---------*/
            String updateSql = "UPDATE clan_members SET tree_pts = :tree_pts, average_pts = :average_pts WHERE user_id = :userid;";
            con.createQuery(updateSql)
                    .addParameter("tree_pts",treePts)
                    .addParameter("average_pts",avgPts)
                    .addParameter("userid",user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Failed to update points: "+ex);
        }
    }





} //End
