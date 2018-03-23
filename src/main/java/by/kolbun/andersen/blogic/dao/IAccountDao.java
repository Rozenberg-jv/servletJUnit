package by.kolbun.andersen.blogic.dao;

import by.kolbun.andersen.blogic.entity.Account;

import java.math.BigInteger;
import java.util.List;

public interface IAccountDao {
    int add(Account a);

    Account get(int id);

    void update(Account a);

    void delete(int id);

    List<Account> getAll();

    void doTransaction(int idSender, int idReceiver, BigInteger amount);

//    void doTransh();
}
