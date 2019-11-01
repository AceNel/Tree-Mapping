package models;

import java.util.Objects;

public class ClanMembers {
    private int user_id;
    private int clan_id;
    private int challenge_pts;
    private int tree_pts;
    private int average_pts;
    private int id;

    public ClanMembers(int user_id, int clan_id, int challenge_pts, int tree_pts, int average_pts) {
        this.user_id = user_id;
        this.clan_id = clan_id;
        this.challenge_pts = challenge_pts;
        this.tree_pts = tree_pts;
        this.average_pts = average_pts;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClan_id() {
        return clan_id;
    }

    public void setClan_id(int clan_id) {
        this.clan_id = clan_id;
    }

    public int getChallenge_pts() {
        return challenge_pts;
    }

    public void setChallenge_pts(int challenge_pts) {
        this.challenge_pts = challenge_pts;
    }

    public int getTree_pts() {
        return tree_pts;
    }

    public void setTree_pts(int tree_pts) {
        this.tree_pts = tree_pts;
    }

    public int getAverage_pts() {
        return average_pts;
    }

    public void setAverage_pts(int average_pts) {
        this.average_pts = average_pts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClanMembers that = (ClanMembers) o;
        return user_id == that.user_id &&
                clan_id == that.clan_id &&
                challenge_pts == that.challenge_pts &&
                tree_pts == that.tree_pts &&
                average_pts == that.average_pts &&
                id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, clan_id, challenge_pts, tree_pts, average_pts, id);
    }
}
