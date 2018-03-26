package by.kolbun.andersen.web.servlets;

import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/*
?act=add&f=&l=&m=
?act=del&id=
?act=get&id=
?act=all
?act=transh&sen=&rec=&m=
?act=fill
?act=block&id=
*/

public class AccountsListServlet extends HttpServlet {
    private AccountService service = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = "";
        Account a;
        int id;

        String act = req.getParameter("act");
        if (act == null) act = "";
        switch (act) {
            /*case "add":
                String fname = req.getParameter("f");
                String lname = req.getParameter("l");
                String money = req.getParameter("m");
                User u = new User(fname, lname);
                a = new Account(new BigInteger(money), u);
                System.out.println("added new account with id = " + service.add(a));
                break;*/
            case "del":
                id = Integer.parseInt(req.getParameter("id"));
                service.delete(id);
                break;
            case "get":
                id = Integer.parseInt(req.getParameter("id"));
                a = service.get(id);
                System.out.println("get account: " + a);
                break;
            case "all":
                List<Account> all = service.getAll();
                System.out.println("all accounts:");
                all.forEach(System.out::println);
                break;
            /*case "transh":
                int senderId = Integer.parseInt(req.getParameter("sen"));
                int receiverId = Integer.parseInt(req.getParameter("rec"));
                BigInteger amount = new BigInteger(req.getParameter("m"));
                message = service.doTransh(senderId, receiverId, amount);
                break;*/
            case "fill":
                service.fillRows();
                break;
            case "block":
                id = Integer.parseInt(req.getParameter("id"));
                service.switchStatus(id);
                break;
            default:
                System.out.println("no action");
                break;
        }

        req.setAttribute("accounts", service.getAll());
        req.setAttribute("message", message);
        System.out.println("message: " + message);
        req.getRequestDispatcher("/pages/accounts.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int senderId = Integer.parseInt(req.getParameter("sender"));
        int receiverId = Integer.parseInt(req.getParameter("receiver"));
        BigInteger amount = new BigInteger(req.getParameter("money"));
        String message = service.doTransh(senderId, receiverId, amount);
        req.setAttribute("accounts", service.getAll());
        req.setAttribute("message", message);
        System.out.println("message: " + message);
        req.getRequestDispatcher("/pages/accounts.jsp").forward(req, resp);
    }
}
