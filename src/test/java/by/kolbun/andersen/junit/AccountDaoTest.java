package by.kolbun.andersen.junit;

import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.entity.User;
import by.kolbun.andersen.blogic.service.AccountService;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AccountDaoTest {
    @Parameterized.Parameters(name = "{index}: {1} {2} : {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, "fname01", "lname01"},
                {10000, "", "lname02"},
                {100, "fname03", ""},
                {0, "", ""}
        });
    }

    private AccountService service;
    private Account a;
    private User u;
    private BigInteger amount;

    public AccountDaoTest(int amount, String fname, String lname) {
        this.amount = new BigInteger(String.valueOf(amount));
        service = new AccountService();
        u = new User(fname, lname);
        a = new Account(this.amount, u);
    }

    @Test
    public void testAddGetDelDB() {
        int id = service.add(a);
        Account aGot = service.get(id);
        assertThat(aGot.getId(), is(id));
        service.delete(id);
        assertNull(service.get(id));
    }

    @Ignore
    @Test
    public void testNegativeAmounts() {
        assertFalse(amount.signum() < 0);
    }

    @After
    public void tearDown() {
        service = null;
        a = null;
        u = null;
    }
}