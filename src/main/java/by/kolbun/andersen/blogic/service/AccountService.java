package by.kolbun.andersen.blogic.service;

import by.kolbun.andersen.blogic.dao.AccountDao;
import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.entity.Transh;

import java.math.BigInteger;
import java.util.List;

@SuppressWarnings(value = "SpellCheckingInspection")
public class AccountService implements IAccountService {
    private AccountDao dao = AccountDao.getInstance();

    @Override
    public int add(Account a) {
        return dao.add(a);
    }

    @Override
    public Account get(int id) {
        return dao.get(id);
    }

    @Override
    public void update(Account a) {
        System.out.println("AccountService#update(Account a) is not implemented");
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public List<Account> getAll() {
        return dao.getAll();
    }

    @Override
    public String doTransh(int idSender, int idReceiver, BigInteger amount) {
        return dao.doTransh(idSender, idReceiver, amount);
    }

    // debug method
    public void fillRows() {
        dao.fillRows();
    }

    public void switchStatus(int id) {
        dao.switchStatus(id);
    }

    public List<Transh> getTranshesListByAccId(int id) {
        return dao.getTranshesListByAccId(id);
    }
}
