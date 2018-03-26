package by.kolbun.andersen.web.servlets;

import by.kolbun.andersen.blogic.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    AccountService service = new AccountService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        int id = Integer.parseInt(req.getParameter("id"));
        service.updateUser(id, fname, lname);
        resp.sendRedirect("/a/s?id=" + id);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);
        req.getRequestDispatcher("/pages/user_update.jsp").forward(req, resp);
    }
}
