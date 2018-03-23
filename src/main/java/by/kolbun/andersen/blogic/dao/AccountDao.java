package by.kolbun.andersen.blogic.dao;

import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.entity.Transh;
import by.kolbun.andersen.blogic.entity.TranshStatus;
import by.kolbun.andersen.blogic.entity.User;
import by.kolbun.andersen.blogic.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@SuppressWarnings(value = "SpellCheckingInspection")
public class AccountDao implements IAccountDao {
    private static AccountDao instance = new AccountDao();
    private Session session = HibernateUtil.getSession();
    private Transaction transaction;

    public static AccountDao getInstance() {
        return instance;
    }

    private AccountDao() {
    }

    @Override
    public int add(Account a) {
        transaction = session.beginTransaction();
        int id = (int) session.save(a);
        transaction.commit();
        return id;
    }

    @Override
    public Account get(int id) {
        transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.select(root).where(cb.equal(root.get("id"), id));
        Account result = session.createQuery(query).uniqueResult();
        transaction.commit();
        return result;
    }

    /**
     * Update money amount and user info
     * NOT update transactions
     */
    @Override
    public void update(Account a) {
        transaction = session.beginTransaction();
        /*Account o = session.get(Account.class, a.getId());
        o.setMoney(a.getMoney());
        o.setUser(o.getUser());
        session.update(o);*/
        session.saveOrUpdate(a);
        transaction.commit();
    }

    @Override
    public void delete(int id) {
        transaction = session.beginTransaction();
        Account d = session.load(Account.class, id);
        session.delete(d);
        transaction.commit();
    }

    @Override
    @Transactional
    public List<Account> getAll() {
        transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.select(root);
        List<Account> result = session.createQuery(query).list();
        transaction.commit();
        return result;
    }

    @Override
    public void doTransaction(int idSender, int idReceiver, BigInteger amount) {
        transaction = session.beginTransaction();
        Account sender = session.get(Account.class, idSender);
        Account receiver = session.get(Account.class, idReceiver);
        Transh transh = new Transh(sender, receiver, amount);

        if (sender.getMoney().compareTo(amount) >= 0) {
            transh.setStatus(TranshStatus.PROCESSING);
        } else {
            transh.setStatus(TranshStatus.DENIED_NOTENAUGHMONEY);
        }

        int tId = (int) session.save(transh);
        transaction.commit();

        if (transh.getStatus() == TranshStatus.PROCESSING)
            execTransh(tId);
    }

    private void execTransh(int tId) {
        try {
            transaction = session.beginTransaction();
            Transh transh = session.get(Transh.class, tId);
            Account sender = session.load(Account.class, transh.getSender().getId());
            Account receiver = session.load(Account.class, transh.getReceiver().getId());
            BigInteger amount = transh.getAmount();
            sender.setMoney(sender.getMoney().subtract(amount));
            receiver.setMoney(receiver.getMoney().add(amount));
            session.update(sender);
            session.update(receiver);
            transh.setStatus(TranshStatus.SUCCESS);
            session.save(transh);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            transaction = session.beginTransaction();
            Transh transh = session.get(Transh.class, tId);
            transh.setStatus(TranshStatus.FAIL);
            session.update(transh);
            transaction.commit();
        }
    }

    // debug method
    public void fillRows() {
        Account a;
        User u;
        for (int i = 0; i < 4; i++) {
            u = new User("fname" + i, "lname" + i);
            a = new Account(new BigInteger(i % 3 * 300 + ""), u);
            add(a);
        }
    }

    public void switchStatus(int id) {
        transaction = session.beginTransaction();

        Account a = session.get(Account.class, id);

        a.switchStatus();

        session.update(a);

        transaction.commit();
    }
}
