package models;

import dao.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;
import security.Hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

@SuppressWarnings("ConstantConditions")
public class UsersTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void instantiatesCorrectly(){
        Users user = newUser();
        assertTrue(user instanceof Users);
    }

    @Test
    public void getUsername_string(){
        Users user = newUser();
        assertNotEquals("",user.getUsername());
        assertTrue(user.getUsername() instanceof String);
    }

    @Test
    public void getPassword_string(){
        Users user = newUser();
        assertNotEquals("",user.getPassword());
        assertTrue(user.getPassword() instanceof String);
    }

    @Test
    public void getDisplayName_string(){
        Users user = newUser();
        //for new user at first, display name will be username
        assertEquals(user.getUsername(),user.getDisplay_name());
        user.setDisplay_name("Different Name");
        assertNotEquals(user.getUsername(),user.getDisplay_name());
    }

    @Test
    public void getEmail_string(){
        Users user = newUser();
        assertEquals("none",user.getEmail());
        user.setEmail("testEmail@gmail.com");
        assertNotEquals("none",user.getEmail());
    }

    @Test
    public void getTreesPlanted_int(){
        Users user = newUser();
        assertEquals(0,user.getTrees_planted());
    }

    @Test
    public void getClan_checkWhetherUserIsInClan_false(){
        Users user = newUser();
        assertFalse(user.isInClan());
    }

    @Test
    public void getClanName_string(){
        Users user = newUser();
        //for new user at first, display name will be username
        assertEquals("none",user.getClan_name());
        user.setClan_name("another clan's name");
        assertNotEquals("none",user.getClan_name());
    }

    @Test
    public void hashPassword_string() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Users user = newUser();
        String unsecuredPass = user.getPassword();
        user.securePassword();
        System.out.println(user.getPassword());
        assertNotEquals(unsecuredPass,user.getPassword());
    }

    //Helper
    private Users newUser(){
        return new Users("username","asdf");
    }
}