package by.kolbun.andersen.junit;

import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.entity.User;
import by.kolbun.andersen.blogic.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

@RunWith(Theories.class)
public class TranshTheoriesTest {
    @DataPoint
    public static BigInteger A = new BigInteger("0");
    @DataPoint
    public static BigInteger B = new BigInteger("1000");
    @DataPoint
    public static BigInteger C = new BigInteger("1");

    private int id1;
    private int id2;
    private AccountService service;
    private final BigInteger initAmount1 = new BigInteger("1000");
    private final BigInteger initAmount2 = new BigInteger("0");
    private Account a1, a2;
    private User u1, u2;

    @Before
    public void setUp() {
        service = new AccountService();
        u1 = new User("fname1", "lname1");
        u2 = new User("fname2", "lname2");
        a1 = new Account(initAmount1, u1);
        a2 = new Account(initAmount2, u2);
    }

    @Theory
    public void isValidTranshTheory(BigInteger amount) {
        assumeTrue(amount.signum() >= 0);

        id1 = service.add(a1);
        id2 = service.add(a2);
        String mes = service.doTransh(a1.getId(), a2.getId(), amount);

        assertEquals("Transaction was successful", mes);
        assertTrue(a2.getMoney().equals(amount));
    }

    @After
    public void tearDown() {
        service.delTranshByAccId(id1);
        service.delete(id1);
        service.delete(id2);
        service = null;
        u1 = null;
        u2 = null;
        a1 = null;
        a2 = null;
    }
}
