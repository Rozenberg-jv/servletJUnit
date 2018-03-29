package by.kolbun.andersen.junit;

import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.entity.AccountStatus;
import by.kolbun.andersen.blogic.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class AccountTest {

    private Account a;
    private User u;

    @Before
    public void setUp() {
        u = new User("fname", "lname");

        a = new Account(new BigInteger("1000"), u);
    }

    @Test(timeout = 1)
    public void testSwitchStatus() {
        a.switchStatus();
        assertEquals("Account status don't switch after creation", AccountStatus.BLOCKED, a.getStatus());
        a.switchStatus();
        assertEquals("Account status don't switch after switch", AccountStatus.ACTIVE, a.getStatus());
    }

    @Test
    public void testSetUser() {
        User oldU = a.getUser();
        User newU = new User("anotherFN", "anotherLN");
        a.setUser(newU);
        assertTrue("There is not the same object of User in Account after setting new user's data", oldU == a.getUser());
    }

    @Test(expected = NullPointerException.class)
    public void testNPE() {
        u = null;
        assumeTrue(u == null);
        a.setUser(u);
    }

    @After
    public void tearDown() {
        a = null;
        u = null;
    }
}
