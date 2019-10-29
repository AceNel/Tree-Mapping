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
        assertTrue(user.getUsername() instanceof String);
    }

    //Helper
    private Users newUser(){
        return new Users("username","asdf");
    }
}