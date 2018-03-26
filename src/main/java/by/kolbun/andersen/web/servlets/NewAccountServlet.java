package by.kolbun.andersen.web.servlets;

import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.entity.User;
import by.kolbun.andersen.blogic.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

public class NewAccountServlet extends HttpServlet {
    private AccountService service = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/resources/pages/new_account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String money = req.getParameter("money");
        User u = new User(fname, lname);
        Account a = new Account(new BigInteger(money), u);
        int id = service.add(a);
        System.out.println("added new account with id = " + id);
        resp.sendRedirect("/a");
    }
}
