package by.kolbun.andersen.blogic.service;

import by.kolbun.andersen.blogic.entity.Account;

import java.math.BigInteger;
import java.util.List;

public interface IAccountService {
    int add(Account a);

    Account get(int id);

    void update(Account a);

    void delete(int id);

    List<Account> getAll();

    String doTransh(int idSender, int idReceiver, BigInteger amount);
}
