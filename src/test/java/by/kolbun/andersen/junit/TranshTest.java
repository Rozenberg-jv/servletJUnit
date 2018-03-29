package by.kolbun.andersen.junit;

import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.entity.Transh;
import by.kolbun.andersen.blogic.entity.User;
import by.kolbun.andersen.blogic.entity.exceptions.TranshInvalidValuesException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.fail;

@SuppressWarnings("SpellCheckingInspection")
public class TranshTest {
    private User u;
    private Account a1;
    private Account a2;

    @Before
    public void setUp() {
        u = new User("fname", "lname");
        a1 = new Account(new BigInteger("1000"), u);
        a2 = new Account(new BigInteger("1000"), u);
    }

    //    @Test(expected = TranshInvalidValuesException.class) // - не работает с самописными исключениями
    @Test
    public void testCreateTransh() {
        try {
            new Transh(a1, a2, new BigInteger("-100"));
            fail("Expected TranshInvalidAmountException");
        } catch (TranshInvalidValuesException e) {
            assertThat(e.getMessage(), containsString("Transh amount is negative"));
        }
    }

    @After
    public void tearDown() {
        a1 = null;
        a2 = null;
        u = null;
    }
}
