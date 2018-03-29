package by.kolbun.andersen.junit;

import by.kolbun.andersen.blogic.service.AccountService;
import org.junit.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * - методы с аннотациями @BeforeClass/@AfterClass выполняются до/после всех тестов соответственно и
 * должны быть static
 * - методы с аннотациями @Before/@After выполняются до и после КАЖДОГО теста соответственно
 */

public class AccountServiceTest {
    @BeforeClass
    public static void setUpClass() {

    }

    @Before
    public void setUp() {

    }

    @Test
    public void testAddNull() {
        AccountService serv = new AccountService();
        int actual = serv.add(null);
//        assertEquals("Not -1 returns for ''null'' input", -1, actual);
        assertThat(actual, is(-1));
    }

    @Test
    public void test() {

    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void tearDownClass() {

    }
}
