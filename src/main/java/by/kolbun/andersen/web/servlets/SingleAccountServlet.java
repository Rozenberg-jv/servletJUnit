package by.kolbun.andersen.web.servlets;

import by.kolbun.andersen.blogic.entity.Account;
import by.kolbun.andersen.blogic.entity.Transh;
import by.kolbun.andersen.blogic.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SingleAccountServlet extends HttpServlet {
    private AccountService service = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Account account = service.get(id);
        List<Transh> transhes = service.getTranshesListByAccId(id);

        req.setAttribute("transhes", transhes);
        req.setAttribute("account", account);

        req.getRequestDispatcher("/pages/info_account.jsp").forward(req, resp);
    }
}
