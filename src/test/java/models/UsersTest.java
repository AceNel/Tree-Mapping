package models;

import dao.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

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


    //Helper
    private Users newUser(){
        return new Users("username","asdf");
    }
}