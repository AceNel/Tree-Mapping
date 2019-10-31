package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClanTest {

        @Test
        public void newClan_instantiatesCorrectly_true() {
            Clan newClan = Clan.setUpNewClan();
            assertTrue(newClan instanceof Clan);
        }

    @Test
    public void newClan_getClanName_String() {
        Clan newClan = Clan.setUpNewClan();
        assertEquals("Area 51",newClan.getClanName());
    }

    @Test
    public void newClan_getDescription_String() {
        Clan newClan = Clan.setUpNewClan();
        assertEquals("We lead others follow",newClan.getClanDescription());
    }

    @Test
    public void newClan_getTotalMembers_Int() {
        Clan newClan = Clan.setUpNewClan();
        assertEquals(5,newClan.getTotal_members());
    }

    @Test
    public void Clan_getInstances_true() {
        Clan newClan = Clan.setUpNewClan();
        Clan another = Clan.setUpNewClan();
        assertTrue(Clan.getInstances().contains(newClan));
        assertTrue(Clan.getInstances().contains(another));
    }




}

